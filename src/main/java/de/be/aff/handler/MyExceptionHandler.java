package de.be.aff.handler;

import de.be.aff.exception.MyErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.client.HttpStatusCodeException;

@ControllerAdvice //applies to the whole app, not to specific controller. Is not only for exceptions
//@ControllerAdvice(assignableTypes = MyController.class) //if we want it to apply only to MyController class
//@ControllerAdvice(basePackages = "de.be.aff.service") //if we want it to apply only to classes from de.be.aff.service
public class MyExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class) //which exceptions to intercept
    @ResponseStatus(HttpStatus.BAD_REQUEST) //http status to return
    @ResponseBody
    public MyErrorMessage badRequest(HttpServletRequest req, IllegalArgumentException ex) {
        return new ErrorMessage(req.getRequestURL().toString(),
                ex.getLocalizedMessage()!=null ? ex.getLocalizedMessage() :ex.getMessage());
    }   

    //Exception is more general so it should be after more specific exceptions
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage generalError(HttpServletRequest req, IllegalArgumentException ex) {
        return new ErrorMessage(req.getRequestURL().toString(),
                ex.getLocalizedMessage()!=null ? ex.getLocalizedMessage() :ex.getMessage());
    }
    
    @ExceptionHandler(HttpStatusCodeException.class)
    @ResponseBody
    public ErrorMessage httpError(HttpStatusCodeException ex, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(ex.getStatusCode().value());
        return new ErrorMessage(request.getRequestURL().toString(),
                ex.getLocalizedMessage() != null ? ex.getLocalizedMessage() : ex.getMessage());
    }
}
