package tr.gov.turkiye.edkservicedeskbackend.base.exception;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tr.gov.turkiye.edkservicedeskbackend.base.log.LogData;
import tr.gov.turkiye.edkservicedeskbackend.base.results.ErrorResult;
import tr.gov.turkiye.edkservicedeskbackend.base.results.Result;
import tr.gov.turkiye.edkservicedeskbackend.base.results.ResultMessage;
import tr.gov.turkiye.edkservicedeskbackend.base.results.ResultMessageType;

import tr.gov.turkiye.edkservicedeskbackend.base.log.ServiceDeskLogger;

import static tr.gov.turkiye.edkservicedeskbackend.util.Utils.showMessage;


@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({Exception.class})
    public Result errorHandler(Exception ex) {
        if (ex instanceof ExpiredJwtException) {
            ServiceDeskLogger.error(new LogData(CustomExceptionHandler.class, "errorHandler", ""), ex);
            return showMessage(Result.SERVER_ERROR, ResultMessageType.ERROR, "Token ile ilgili hata var..");
        }
        if (ex instanceof JwtException) {
            return showMessage(Result.SERVER_ERROR, ResultMessageType.ERROR, "Genel JWT Hatası..");
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return showMessage(Result.SERVER_ERROR, ResultMessageType.ERROR, "Request'te hata var, gönderilen değerleri kontrol ediniz...");
        }
        return showMessage(Result.SERVER_ERROR, ResultMessageType.ERROR, (ex.getCause() != null ? String.valueOf(ex.getCause()) : "") + ex.getMessage());
    }

/* Özet Bilgi
        Spring 3.0 ile gelen Problem detail ile sarmalayıp verebildik.
        List<String> detail = new ArrayList<>();
        detail.add(customerNotNullException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("User Not Null", detail);

        ---

          return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Genel JWT Hatası..");
*/

}

