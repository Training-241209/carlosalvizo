import { useAdminReimbursement } from "@/features/hooks/use-adminreimburstment";
import { DataTable } from "./data-table";
import { columns } from "./adminreimburstments";

export default function AdminPage() {
  const { data, isLoading, isError, error } = useAdminReimbursement();


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
