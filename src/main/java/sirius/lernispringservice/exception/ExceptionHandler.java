package sirius.lernispringservice.exception;

import exception.InvalidAnswerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Logger;

@ControllerAdvice
public class ExceptionHandler {

    Logger logger = Logger.getLogger(ExceptionHandler.class.getName());

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidAnswerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<?> handleInvalidAnswerException(Exception e) {
        this.logger.info(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
