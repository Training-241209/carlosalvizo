import { RegisterForm } from "@/components/shared/registerform"
import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
  } from "@/components/ui/card"
import { Link } from "@tanstack/react-router"
  

export function Register () {
    return ( 
        <Card className="w-[500px] ">
        <CardHeader>
          <CardTitle className="text-3xl font-bold">Register</CardTitle>
          <CardDescription>Please enter your credentials</CardDescription>
        </CardHeader>
        <CardContent>
          <RegisterForm/>
        </CardContent>
        <CardFooter>
          <p>Have an account? </p>
          <Link to={"/auth/login"} className="underline font-bold text-blue-800 ml-2">Login</Link>
        </CardFooter>
      </Card>
      
    )
}