import { useMutation } from "@tanstack/react-query";
import { useRouter } from "@tanstack/react-router";
import { axiosInstance } from "@/lib/axios-config";
import { toast } from "sonner";
import { useQueryClient } from "@tanstack/react-query"; 

export function useUpdateReimburstment() {
  const router = useRouter();
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: async ({ status, reimbId }: { status: string; reimbId: number }) => {
      const token = "Bearer " + queryClient.getQueryData<{ token: string }>(["auth"])?.token;

      const resp = await axiosInstance.patch(`/reimburstments/update-reimburstment/${reimbId}`,{ status }, 
        {
          headers: {
            "Authorization": token,
            "Content-Type": "application/json",
          },
        }
      );
      return resp.data;
    },
    onSuccess: () => {
      toast.success("Reimbursement successfully updated.");
      queryClient.invalidateQueries({
        queryKey: ["adminreimburstments"],
      });
      router.navigate({ to: "/reimburstments" });
    },
    onError: () => {
      toast.error("Failed to update reimbursement.");
    },
  });
}
