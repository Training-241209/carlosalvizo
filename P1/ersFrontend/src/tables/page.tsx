import { useReimbursement } from "@/features/hooks/use-reimburstment";
import { DataTable } from "./data-table";
import { columns } from "./reimburstments";

export default function DemoPage() {
  const { data, isLoading, isError, error } = useReimbursement();


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
