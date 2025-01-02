import { useUsers } from "@/features/hooks/use-users";
import { DataTable } from "./data-table";
import { columns } from "./users";

export default function UsersPage() {
  const { data, isLoading, isError, error } = useUsers();


  if (isLoading) return <div>Loading...</div>;
  if (isError) {
    if (error instanceof Error) {
      return <div>Error: {error.message}</div>;
    } else {
      return <div>Error: {error}</div>;
    }
  }

  return (
    <div >
      <DataTable columns={columns} data={data || []} />
    </div>
  );
}
