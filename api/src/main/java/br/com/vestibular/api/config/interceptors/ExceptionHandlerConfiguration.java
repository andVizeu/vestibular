package br.com.vestibular.api.config.interceptors;

import br.com.vestibular.api.config.response.BusinessExceptionResponseBuilder;
import br.com.vestibular.core.exceptions.BusinessException;
import br.com.vestibular.core.exceptions.InternalServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Controller
@Slf4j
public class ExceptionHandlerConfiguration {

    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody
    ResponseEntity exceptionHandler(final RuntimeException exception) {
        if (exception instanceof BusinessException) {
            return new BusinessExceptionResponseBuilder((BusinessException) exception, BAD_REQUEST).build();
        }

        log.error(exception.getMessage(), exception);

        return new BusinessExceptionResponseBuilder(new InternalServerErrorException(), INTERNAL_SERVER_ERROR).build();
    }

}
