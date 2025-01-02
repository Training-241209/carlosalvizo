import { axiosInstance } from "@/lib/axios-config";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useRouter } from "@tanstack/react-router";
import { toast } from "sonner";


export function useUserDelete() {
    const queryClient = useQueryClient()
    const router = useRouter()

    return useMutation({
        mutationFn: async (userId: number) => {
            const token = "Bearer " + queryClient.getQueryData<{ token: string }>(["auth"])?.token
            const resp = await axiosInstance.delete(`/users/delete/${userId}`, {
                headers: {
                    "Authorization": token,
                },
            });
            return resp.data;
        },
        onSuccess: () => {
            toast.success("User deleted successfully.");
            queryClient.invalidateQueries({
                queryKey: ["users"],
            });
            queryClient.invalidateQueries({
                queryKey: ["reimburstments"],
            })
            queryClient.invalidateQueries({
                queryKey: ["adminreimburstments"],
            })
            router.navigate({ to: "/employees" });
        },
        onError: () => {
            toast.error("Failed to delete user.");
        },
    });
}