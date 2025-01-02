import { z } from "zod";

export const registerSchema = z.object({
  email: z
    .string({
      message: "Email is required",
    })
    .min(1, "Email is required")
    .regex(
      /^[\w.-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
      "Enter a valid email address"
    ),
  password: z
    .string({
      message: "Password is required",
    })
    .min(1, "Password is required"),
  firstName: z
    .string({
      message: "First name is required",
    })
    .min(1, "First name is required"),
  lastName: z
    .string({
      message: "Last name is required",
    })
    .min(1, "Last name is required"),
});

export type RegisterSchema = z.infer<typeof registerSchema>;
