import { AddReimburstment } from '@/features/components/addreimburstment'
import { createLazyFileRoute } from '@tanstack/react-router'

export const Route = createLazyFileRoute('/_reimburstments/addreimburstment')({
  component: RouteComponent,
})

function RouteComponent() {
  return(
    <>
      <AddReimburstment/>
    </>
  )
}
