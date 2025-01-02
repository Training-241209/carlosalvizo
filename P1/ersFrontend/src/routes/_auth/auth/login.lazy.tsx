import { Login } from '@/features/components/login'
import { createLazyFileRoute } from '@tanstack/react-router'

export const Route = createLazyFileRoute('/_auth/auth/login')({
  component: RouteComponent,
})

function RouteComponent() {
  return(
    <Login />
  )
}
