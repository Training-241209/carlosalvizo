import { Register } from '@/features/components/register'
import { createLazyFileRoute } from '@tanstack/react-router'

export const Route = createLazyFileRoute('/_auth/auth/register')({
  component: RouteComponent,
})

function RouteComponent() {
  return (
  <Register />
  )
}
