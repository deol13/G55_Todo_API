package se.lexicon.todo_api.exception;

import java.time.LocalDateTime;

// Objects of records are mutable, aka they can't be changed once initiated.
public record ErrorResponse (int status, String[] errors, LocalDateTime dateTime) {
    public ErrorResponse(int status, String[] errors) {
        this(status, errors, LocalDateTime.now());
    }
}
