
import AdminPage from '@/tables/adminpage'
import { createLazyFileRoute } from '@tanstack/react-router'

export const Route = createLazyFileRoute('/_reimburstments/reimburstments')({
  component: RouteComponent,
})

function RouteComponent() {
  return(
    <>
      <div className='ml-[200px]'>
        <AdminPage/>
      </div>
    </>
  )
}
