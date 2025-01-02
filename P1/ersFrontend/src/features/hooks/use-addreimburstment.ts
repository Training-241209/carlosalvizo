import { useMutation } from "@tanstack/react-query";
import { useRouter } from "@tanstack/react-router";
import { ReimburstmentSchema } from "../schemas/reimburstmentSchema";
import { axiosInstance } from "@/lib/axios-config";
import { toast } from "sonner";
import { useQueryClient } from "@tanstack/react-query"; 

export function useAddReimburstment() {
    const router = useRouter();
    const queryClient = useQueryClient(); 

    return useMutation({
        mutationFn: async (values: ReimburstmentSchema) => {

            const token = "Bearer " + queryClient.getQueryData<{ token: string }>(["auth"])?.token;

            const resp = await axiosInstance.post("/reimburstments/add-reimburstment", values, {
                headers: {
                    "Authorization": token,
                    "Content-Type": "application/json",
                },
            });
            return resp.data;
        },
        onSuccess: () => {
            toast.success("Reimbursement added successfully.");
            queryClient.invalidateQueries({
                queryKey: ["reimburstments"],
              });
            router.navigate({ to: "/dashboard" });
        },
        onError: () => {
            toast.error("Failed to add reimbursement.");
        },
    });
}
