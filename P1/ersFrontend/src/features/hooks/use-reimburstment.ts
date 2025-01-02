import { axiosInstance } from '@/lib/axios-config';
import { useQuery, useQueryClient, UseQueryResult } from "@tanstack/react-query";
import { Reimburstment } from '@/tables/reimburstments';



export function useReimbursement(): UseQueryResult<Reimburstment[]> {
  
  const queryClient = useQueryClient();

  return useQuery<Reimburstment[]>({
    queryKey: ["reimburstments"],
    queryFn: async () => {
      const token = "Bearer " + queryClient.getQueryData<{ token: string }>(["auth"])?.token;
      const resp = await axiosInstance.get("/reimburstments/me", {
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