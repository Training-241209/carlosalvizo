import { ArrowUpDown } from "lucide-react"
import { Button } from "@/components/ui/button"
import { ColumnDef } from "@tanstack/react-table"

export type Reimburstment = {
  amount: number
  description: string
  reimbId: number
  status: string
}

export const columns: ColumnDef<Reimburstment>[] = [
  {
    accessorKey: "reimbId",
    header: "Reimbid",
  },
  {
    accessorKey: "amount",
    header: () => <div className="text-right">Amount</div>,
    cell: ({ row }) => {
      const amount = parseFloat(row.getValue("amount"))
      const formatted = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
      }).format(amount)

      return <div className="text-right">{formatted}</div>
    },
  },
  {
    accessorKey: "description",
    header: "Description",
  },
  {
    accessorKey: "status",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Status
          <ArrowUpDown className="ml-2 h-4 w-4" />
        </Button>
      )
    },
    cell: ({ row }) => {
      const status = row.getValue("status") as string
      let statusClass = "text-dark font-bold"
      if (status === "Approved") {
        statusClass = "text-green-600 font-bold"
      } else if (status === "Denied") {
        statusClass = "text-red-500 font-bold"
      }

      return <div className={` ${statusClass}`}>{status}</div>
    },
  },
]
