import { SidebarUI } from "@/components/shared/sidebardui";
import { UserDropdown } from "@/components/shared/userdropdown";
import { createFileRoute, Outlet } from "@tanstack/react-router";

export const Route = createFileRoute("/_employees")({
  component: RouteComponent,
});

function RouteComponent() {
  return (
    <>
      <>
        <SidebarUI />
        <div className="border-b h-[65px] bg-slate-700 flex items-center shadow-xl text-white">
          <div className="max-w-7xl mx-auto w-full flex items-center justify-between">
            <div className="text-2xl">Employee Reimbursement System</div>

            <div className="ml-8">
              <UserDropdown />
            </div>
          </div>
        </div>

        <div className="min-h-screen flex justify-center items-center">
          <Outlet />
        </div>
      </>
    </>
  );
}
