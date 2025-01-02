
import UsersPage from '@/tables/userspage'
import { createLazyFileRoute } from '@tanstack/react-router'

export const Route = createLazyFileRoute('/_employees/employees')({
  component: RouteComponent,
})

function RouteComponent() {
  return(
    <UsersPage/>
  )
}
