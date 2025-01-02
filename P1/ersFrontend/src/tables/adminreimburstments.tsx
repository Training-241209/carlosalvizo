import { ColumnDef } from "@tanstack/react-table";
import { MoreHorizontal, ArrowUpDown } from "lucide-react";
import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";

import { useUpdateReimburstment } from "@/features/hooks/use-updatereimburstment";

export type AdminReimburstment = {
  amount: number;
  description: string;
  reimbId: number;
  status: string;
  user: {
    firstName: string;
    lastName: string;
  };
};

export const columns: ColumnDef<AdminReimburstment>[] = [
  {
    accessorKey: "reimbId",
    header: "Reimbid",
  },
  {
    accessorKey: "amount",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          <div className="text-right">Amount</div>
          <ArrowUpDown className="ml-2 h-4 w-4" />
        </Button>
      );
    },
    cell: ({ row }) => {
      const amount = parseFloat(row.getValue("amount"));
      const formatted = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
      }).format(amount);

      return <div className="font-medium">{formatted}</div>;
    },
  },
  {
    accessorKey: "description",
    header: "Description",
  },
  {
    accessorKey: "status",
    header: "Status",
  },
  {
    id: "firstName",
    header: "First Name",
    cell: ({ row }) => {
      return row.original.user.firstName;
    },
  },
  {
    id: "lastName",
    header: "Last Name",
    cell: ({ row }) => {
      return row.original.user.lastName;
    },
  },
  {
    id: "actions",
    cell: ({ row }) => {
      const reimbId = row.original.reimbId;
      const { mutate } = useUpdateReimburstment();

      const handleUpdateReimburstment = (status: string, reimbId: number) => {
        mutate({ status, reimbId });
      };

      return (
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" className="h-8 w-8 p-0">
              <span className="sr-only">Open menu</span>
              <MoreHorizontal className="h-4 w-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <DropdownMenuLabel>Actions</DropdownMenuLabel>
            <DropdownMenuSeparator />
            <DropdownMenuItem onClick={() => handleUpdateReimburstment("Approved", reimbId)}>
              Approve
            </DropdownMenuItem>
            <DropdownMenuItem onClick={() => handleUpdateReimburstment("Denied", reimbId)}>
              Deny
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      );
    },
  },
];
