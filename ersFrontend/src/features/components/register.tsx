import { RegisterForm } from "@/components/shared/registerform"
import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
  } from "@/components/ui/card"
  

export function Register () {
    return ( 
        <Card className="w-[500px] h-[650px]">
        <CardHeader>
          <CardTitle className="text-3xl font-bold">Register</CardTitle>
          <CardDescription>Please enter your credentials</CardDescription>
        </CardHeader>
        <CardContent>
          <RegisterForm/>
        </CardContent>
        <CardFooter>
          <p>Have an account? </p> <p className="underline font-bold text-blue-800">Login</p>
        </CardFooter>
      </Card>
      
    )
}