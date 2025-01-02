import { Toaster } from '@/components/ui/sonner'
import QueryProvider from '@/providers/query-provider'
import { createRootRoute, Outlet, useLocation } from '@tanstack/react-router'

export const Route = createRootRoute({
  component: () => {
    const location = useLocation()

    const excludedPaths = ['/dashboard', '/addreimburstment', '/reimburstments', '/employees']

    const isExcludedRoute = excludedPaths.some(path => location.pathname.startsWith(path))

    const className = isExcludedRoute
      ? ''
      : 'min-h-screen flex justify-center items-center bg-slate-800 p-10 text-white'

    return (
      <QueryProvider>
      <div className={className}>
        <Outlet />
        <Toaster />
      </div>
      </QueryProvider>
    )
  },
})
