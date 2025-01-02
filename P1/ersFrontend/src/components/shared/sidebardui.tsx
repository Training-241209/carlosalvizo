import { useRouter } from "@tanstack/react-router";
import { SidebarButton } from "./sidebarbutton";
import { Eye, Plus, UserRound } from "lucide-react";
import { useAuth } from "@/features/hooks/use-auth";

export function SidebarUI() {
  const router = useRouter();

  const { data: auth } = useAuth();

  const handleAddReimburstmentButtonClick = () => {
    router.navigate({ to: "/addreimburstment" });
  };

  const handleViewReimburstmentsButtonClick = () => {
    router.navigate({ to: "/dashboard" });
  };

  const handleViewEmployeesButtonClick = () => {
    router.navigate({ to: "/employees" });
  };

  const handleViewReimburstmentsAdminButtonClick = () => {
    router.navigate({ to: "/reimburstments" });
  }



  if (auth?.role == "employee") {
    return (
      //sticky?
      <aside className="w-[270px] max-w-xs  min-h-[calc(100vh-65px)] fixed left-0 top-[64px] z-40 border-r-6 bg-slate-500 text-white">
        <div className="h-full px-3 py-4">
          <h3 className="mx-3 text-3xl font-semibold">ERS</h3>

          <div className="mt-10">
            <div className="flex flex-col gap-1 w-full">
              <SidebarButton
                icon={Plus}
                onClick={handleAddReimburstmentButtonClick}
              >
                Add Reimburstment
              </SidebarButton>
            </div>
          </div>

          <div className="mt-5">
            <div className="flex flex-col gap-1 w-full">
              <SidebarButton
                icon={Eye}
                onClick={handleViewReimburstmentsButtonClick}
              >
                View Reimburstments
              </SidebarButton>
            </div>
          </div>
        </div>
      </aside>
    );
  }
  else if (auth?.role == "manager") {
    return(
    <aside className="w-[270px] max-w-xs h-screen fixed left-0 top-[64px] z-40 border-r-6 bg-slate-500 text-white">
    <div className="h-full px-3 py-4">
      <h3 className="mx-3 text-3xl font-semibold">ERS</h3>

      <div className="mt-10">
        <div className="flex flex-col gap-1 w-full">
          <SidebarButton
            icon={UserRound}
            onClick={handleViewEmployeesButtonClick}
          >
            View Employees
          </SidebarButton>
        </div>
      </div>

      <div className="mt-5">
        <div className="flex flex-col gap-1 w-full">
          <SidebarButton
            icon={Eye}
            onClick={handleViewReimburstmentsAdminButtonClick}
          >
            View Reimburstments
          </SidebarButton>
        </div>
      </div>
    </div>
  </aside>
    )
  }
}
