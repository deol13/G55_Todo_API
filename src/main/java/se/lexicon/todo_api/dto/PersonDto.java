package se.lexicon.todo_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Objects of records are mutable, aka they can't be changed once initiated.
public record PersonDto(
        // Id is optional in this Dto so no validation on it.
        Long id,
        // Checks for blank strings and null strings, use @NotNull for non-string variables
        @NotBlank(message = "Name is required")
        // If you now a limit of a String in the database, you should always use that to limit it.
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters.")
        String name,
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 150, message = "Email must be between 2 and 150 characters.")
        // Standard format for emails, you can use regexp to change the standard to something else.
        //@Email(message = "Invalid email format", regexp = "")
        @Email(message = "Invalid email format")
        String email
) {
}
