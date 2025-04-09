package com.data.toan.exception;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.data.toan.exception.Category.CategoryDontExits;
import com.data.toan.exception.User.InvalidEmailFormatException;
import com.data.toan.exception.User.UserDontExits;
import com.data.toan.exception.User.UserMailException;
import com.data.toan.util.ListEnum.TypeEnum;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserMailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleEmailExists(Exception e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorResponse.setError(HttpStatus.CONFLICT.getReasonPhrase());

        String mess = e.getMessage();

        errorResponse.setMessage(mess);

        return errorResponse;

    }

    @ExceptionHandler(InvalidEmailFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleEmailFormat(Exception e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());

        String mess = e.getMessage();

        errorResponse.setMessage(mess);

        return errorResponse;

    }

    @ExceptionHandler({ UserDontExits.class, CategoryDontExits.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserDontExits(Exception e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());

        String mess = e.getMessage();

        errorResponse.setMessage(mess);

        return errorResponse;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(Exception e, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorResponse.setError("Bad request");

        String mess = e.getMessage();
        if (e instanceof MethodArgumentNotValidException) {

            int start = mess.lastIndexOf("[");
            int end = mess.lastIndexOf("]");
            mess = mess.substring(start + 1, end - 1);
        } else {
            mess = mess.substring(mess.indexOf(" ") + 1);
        }

        errorResponse.setMessage(mess);

        return errorResponse;

    }

    @ExceptionHandler({ HandlerMethodValidationException.class, HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleServerError(Exception e, WebRequest request, HttpMessageNotReadableException ex) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorResponse.setError("Bad request");

        String mess = e.getMessage();
        int start = mess.lastIndexOf("[");
        int end = mess.lastIndexOf("]");
        mess = mess.substring(start + 1, end);

        errorResponse.setMessage("Must be this variable " + mess);

        return errorResponse;
    };

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgument(Exception e, WebRequest request, IllegalArgumentException ex) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorResponse.setError("Bad request");

        String mess = e.getMessage();

        if (ex.getMessage().contains("No enum constant com.data.toan.util.ListEnum.TypeEnum")) {
            String allowed = Arrays.stream(TypeEnum.values()).map(Enum::name).collect(Collectors.joining(", "));
            mess = "Invalid type. Must be one of: " + allowed + ".";
        }

        errorResponse.setMessage(mess);

        return errorResponse;

    }
}
