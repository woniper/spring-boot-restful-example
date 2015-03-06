package net.woniper.spring.boot.restful.example.exception;

import net.woniper.spring.boot.restful.example.exception.support.DuplicationUsernameException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;

/**
 * Created by woniper on 15. 3. 6..
 */
@ControllerAdvice
public class ErrorControllerAdvice {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicationUsernameException.class)
    public @ResponseBody ErrorResponse duplicationUsername(DuplicationUsernameException exception, Principal principal) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse response = new ErrorResponse();
        response.setCode(status.value());
        response.setMessage(status.getReasonPhrase());
        response.setDevelopmentMessage(exception.getUsername() + " Duplication Error");
        return response;
    }
}
