import { Avatar, AvatarFallback } from "@/components/ui/avatar";
import { useAuth } from "@/features/hooks/use-auth";

export function UserAvatar() {
  const { data: auth } = useAuth();

  if (!auth) return null;

  return (
    <Avatar>
      <AvatarFallback className="bg-white text-black text-xl">
        {auth.firstName.charAt(0).toUpperCase() +
          auth.lastName.charAt(0).toUpperCase()}
      </AvatarFallback>
    </Avatar>
  );
}