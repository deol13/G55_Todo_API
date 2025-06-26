package se.lexicon.todo_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

// This annotation indicates that this class will handle exceptions globally for all controllers
// This class works almost like a switch for exceptions in @Controller classes, it goes through the method list and checks if the type matches up with @ExceptionHandler(...);
// You're also suppose to catch exceptions that controllers causes in the service layer.
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex) {
        // Make it easier to debug during production
        System.out.println("HandleNoResourceFoundException: " + ex.getMessage());
        String errorMessage = "Resource not found";
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), new String[] {errorMessage});
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // This catches IllegalArgumentException thrown in the service layer if a controller was the one that called on the service layer.
    // IllegalArgumentException is a type of RuntimeException
    @ExceptionHandler(RuntimeException.class) // {IllegalArgumentException.class, RuntimeException.class}
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        System.out.println("HandleIllegalArgumentException: " + ex.getMessage());
        // We already made an error message when we cast IllegalArgumentException in PersonServiceImpl so we use that.
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), new String[] {ex.getMessage()});
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        System.out.println("HandleException: " + ex.getMessage());
        String uuid = java.util.UUID.randomUUID().toString();
        // We create and show an uuid so the user can tell support the unique id and the error will be easy to find in the logs.
        System.err.println("Error ID: " + uuid + " - " + ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), new String[] {uuid});
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    // todo: try to catch if validation exception happens
}
