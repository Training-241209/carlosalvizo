import { AddReimbursementForm } from "@/components/shared/addreimburstmentform";
import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
  } from "@/components/ui/card"


export function AddReimburstment() {
    return (
        <Card className="ml-[250px] w-[500px] shadow-xl">
        <CardHeader>
          <CardTitle className="text-3xl font-bold">Reimburstment Info</CardTitle>
          <CardDescription className="">Please enter the details for your reimbursment</CardDescription>
        </CardHeader>
        <CardContent>
          <AddReimbursementForm/>
        </CardContent>
      </Card>
    )
}