import { useAuth } from '@/features/hooks/use-auth'
import { createLazyFileRoute, useRouter } from '@tanstack/react-router'
import { useEffect } from 'react'

export const Route = createLazyFileRoute('/')({
  component: Index,
})

function Index() {

    const { data: auth } =  useAuth();

    const router = useRouter();

    useEffect(() => {
      if (auth) {
        router.navigate({ to: "/dashboard"});
      }
      else{
        router.navigate({ to: "/auth/login"});
      }
    }, [auth]);
}