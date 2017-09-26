package de.be.aff.handler;

import de.be.aff.exception.MyErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice //applies to the whole app, not to specific controller. Is not only for exceptions
public class MyExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class) //which exceptions to intercept
    @ResponseStatus(HttpStatus.BAD_REQUEST) //http status to return
    @ResponseBody
    public MyErrorMessage badRequest(HttpServletRequest req, IllegalArgumentException ex) {
        return new MyErrorMessage(req.getRequestURL().toString(),//getting used url
                "Intercepted exception: " + ex.getMessage());
    }
}
