import { LoginForm } from "@/components/shared/loginform"
import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
  } from "@/components/ui/card"
import { Link } from "@tanstack/react-router"
  

export function Login () {
    return ( 
        <Card className="w-[500px]">
        <CardHeader>
          <CardTitle className="text-3xl font-bold">Login</CardTitle>
          <CardDescription>Please enter your credentials</CardDescription>
        </CardHeader>
        <CardContent>
          <LoginForm/>
        </CardContent>
        <CardFooter>
          <p>Don't have an account? </p>
          <Link to={"/auth/register"} className="underline font-bold text-blue-800 ml-2">Register</Link>
        </CardFooter>
      </Card>
    )
}