import { SidebarUI } from "@/components/shared/sidebardui";
import { UserDropdown } from "@/components/shared/userdropdown";
import Page from "@/tables/page";
import { createFileRoute, Outlet } from "@tanstack/react-router";

export const Route = createFileRoute("/_protected")({
  component: RouteComponent,
});

function RouteComponent() {
  return (
    <Outlet/>
  );
}
