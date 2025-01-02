import { z } from "zod"

export const reimburstmentSchema = z.object({
    amount: z
        .preprocess((val) => Number(val), z
        .number({ message: "Enter a valid amount for the ticket." })
        .min(1, "Amount is required.")),
    description: z
        .string({
            message: "Description is required",
        })
        .min(1, "Description is required"),
    });

    export type ReimburstmentSchema = z.infer<typeof reimburstmentSchema>;