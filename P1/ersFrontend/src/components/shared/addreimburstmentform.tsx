"use client";

import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";

import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";

import { Input } from "@/components/ui/input";
import {
  reimburstmentSchema,
  ReimburstmentSchema,
} from "@/features/schemas/reimburstmentSchema";
import { useAddReimburstment } from "@/features/hooks/use-addreimburstment";

export function AddReimbursementForm() {
  const { mutate: reimburstment, isPending } = useAddReimburstment();

  const form = useForm<ReimburstmentSchema>({
    resolver: zodResolver(reimburstmentSchema),
    defaultValues: {
      amount: 0,
      description: "",
    },
  });

  function onSubmit(values: ReimburstmentSchema) {
    reimburstment(values);
  }

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
        <FormField
          control={form.control}
          name="amount"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Amount</FormLabel>
              <FormControl className="shadow">
                <Input type="number"{...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        <FormField
          control={form.control}
          name="description"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Description</FormLabel>
              <FormControl className="shadow">
                <Input
                  type="description"
                  placeholder="Description"
                  {...field}
                />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <Button type="submit" className="w-full" disabled={isPending}>
          {" "}
          Submit{" "}
        </Button>
      </form>
    </Form>
  );
}
