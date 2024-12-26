import { SidebarButton } from "./sidebarbutton";
import {Plus } from "lucide-react";

export function SidebarUI() {
    return (
        <aside className="w-[270px] max-w-xs h-screen fixed left-0 top-[64px] z-40 border-r-6 bg-slate-500 text-white">

            <div className="h-full px-3 py-4">
                <h3 className="mx-3 text-3xl font-semibold">
                    ERS
                </h3>

                <div className="mt-10">
                    <div className="flex flex-col gap-1 w-full">
                        <SidebarButton icon={Plus}>Add Reimburstment</SidebarButton>
                    </div>
                </div>

            </div>



        </aside>
    )
}