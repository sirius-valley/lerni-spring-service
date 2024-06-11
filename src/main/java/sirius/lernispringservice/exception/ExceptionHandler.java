package sirius.lernispringservice.exception;

import exception.InvalidAnswerException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler {

    Logger logger = Logger.getLogger(ExceptionHandler.class.getName());

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidAnswerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<?> handleInvalidAnswerException(Exception e) {
        this.logger.info(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<?> handleInvalidArguments(MethodArgumentNotValidException e) {
        List<String> errorMessages = e.getBindingResult().getAllErrors().stream()
                .map(org.springframework.validation.ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorMessages);
    }
}
