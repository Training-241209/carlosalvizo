import { LoginForm } from "@/components/shared/loginform"
import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
  } from "@/components/ui/card"
  

export function Login () {
    return ( 
        <Card className="w-[500px] h-[450px]">
        <CardHeader>
          <CardTitle className="text-3xl font-bold">Login</CardTitle>
          <CardDescription>Please enter your credentials</CardDescription>
        </CardHeader>
        <CardContent>
          <LoginForm/>
        </CardContent>
        <CardFooter>
          <p>Don't have an account? </p> <p className="underline font-bold text-blue-800">Login</p>
        </CardFooter>
      </Card>
    )
}