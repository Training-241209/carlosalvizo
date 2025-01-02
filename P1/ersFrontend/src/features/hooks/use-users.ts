import { axiosInstance } from "@/lib/axios-config";
import { Users } from "@/tables/users";
import { useQuery, useQueryClient, UseQueryResult } from "@tanstack/react-query";


export function useUsers(): UseQueryResult<Users[]> {

    const queryClient = useQueryClient();

    return useQuery<Users[]>({
        queryKey: ["users"],
        queryFn: async () => {
            const token = "Bearer " + queryClient.getQueryData<{ token: string }>(["auth"])?.token;
            const resp = await axiosInstance.get("/users", {
                headers: {
                    "withCredentials": "true",
                    "Content-Type": "application/json",
                    "Authorization": token,
                },
            });
            console.log(resp.data);
            return resp.data;
        },
    })
    
}