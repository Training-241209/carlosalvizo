import { SidebarUI } from "@/components/shared/sidebardui";
import { Avatar, AvatarFallback} from "@/components/ui/avatar"


export function Dashboard() {
    return (
    <div>
      <SidebarUI/>
      <div className="border-b h-[65px] bg-slate-700 flex items-center shadow-xl text-white">
        <div className="max-w-7xl mx-auto w-full flex items-center justify-between">
          <div className="text-2xl">
            Employee Reimbursement System
          </div>
  
          <div className="ml-8">
            <Avatar>
                <AvatarFallback className="bg-white text-black text-xl">CN</AvatarFallback>
            </Avatar>
          </div>
        </div>
      </div>

        

    
    </div>
    );
  }


