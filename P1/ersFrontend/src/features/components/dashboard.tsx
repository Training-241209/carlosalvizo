import { SidebarUI } from "@/components/shared/sidebardui";
import { useAuth } from "../hooks/use-auth";
import { UserDropdown } from "@/components/shared/userdropdown";
import Page from "@/tables/page";

export function Dashboard() {
  const { data: auth } = useAuth();

  
  if (auth?.role == "employee") {
    return (
      <div>
        <SidebarUI />
        <div className="border-b h-[65px] bg-slate-700 flex items-center shadow-xl text-white">
          <div className="max-w-7xl mx-auto w-full flex items-center justify-between">
            <div className="text-2xl">Employee Reimbursement System</div>

            <div className="ml-8">
              <UserDropdown />
            </div>
          </div>
        </div>

        <div className="max-w-6xl mt-[100px] ml-[500px]">
          <Page />
        </div>
      </div>
    );
  }
  else if (auth?.role == "manager") {
    return (
      <div>
        <SidebarUI />
        <div className="border-b h-[65px] bg-slate-700 flex items-center shadow-xl text-white">
          <div className="max-w-7xl mx-auto w-full flex items-center justify-between">
            <div className="text-2xl">Employee Reimbursement System</div>

            <div className="ml-8">
              <UserDropdown />
            </div>
          </div>
        </div>

        <div className="max-w-7xl  mt-[380px] ml-[1000px]">
          <p>Welcome, {auth.firstName}  {auth.lastName} !</p>
        </div>
      </div>
    );
  }
}
