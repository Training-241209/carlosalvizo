import { axiosInstance } from '@/lib/axios-config';
import { useQuery, useQueryClient, UseQueryResult } from "@tanstack/react-query";
import { AdminReimburstment } from '@/tables/adminreimburstments';



export function useAdminReimbursement(): UseQueryResult<AdminReimburstment[]> {
  
  const queryClient = useQueryClient();

  return useQuery<AdminReimburstment[]>({
    queryKey: ["adminreimburstments"],
    queryFn: async () => {
      const token = "Bearer " + queryClient.getQueryData<{ token: string }>(["auth"])?.token;
      const resp = await axiosInstance.get("/reimburstments", {
        headers: {
          "withCredentials": "true",
          "Content-Type": "application/json",
          "Authorization": token,
          },
        });
        return resp.data;
    },
  });
}