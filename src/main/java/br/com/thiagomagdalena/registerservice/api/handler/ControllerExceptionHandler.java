package br.com.thiagomagdalena.registerservice.api.handler;

import br.com.thiagomagdalena.registerservice.api.handler.constants.ExceptionHandlerConstants;
import br.com.thiagomagdalena.registerservice.api.handler.dto.GeneralErrorResponse;
import br.com.thiagomagdalena.registerservice.api.handler.exception.ContentTypeRequiredException;
import br.com.thiagomagdalena.registerservice.api.handler.exception.InternalServerErrorException;
import br.com.thiagomagdalena.registerservice.api.handler.exception.NotFoundException;
import br.com.thiagomagdalena.registerservice.api.handler.exception.UnprocessableEntityException;
import br.com.thiagomagdalena.registerservice.api.handler.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    private final MessageSource messageSource;

    @RequestMapping(method = { GET, POST, PUT, DELETE, PATCH }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Mono<GeneralErrorResponse> handleAccessDeniedException(AccessDeniedException exception) {
        return buildGeneralErrorResponse(HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    @RequestMapping(method = { GET, POST, PUT, DELETE, PATCH }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<GeneralErrorResponse> handleException(Exception exception) {
        return buildGeneralErrorResponse(exception.getMessage());
    }

    @RequestMapping(method = { GET, POST, PUT, DELETE, PATCH }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Mono<GeneralErrorResponse> handleNotFoundException() {
        return buildGeneralErrorResponse(ExceptionHandlerConstants.NOT_FOUND);
    }

    @RequestMapping(method = { GET, POST, PUT, DELETE, PATCH }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(ContentTypeRequiredException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Mono<GeneralErrorResponse> handleContentTypeRequiredException(ContentTypeRequiredException exception) {
        return buildGeneralErrorResponse(exception.getMessage());
    }

    @RequestMapping(method = { GET, POST, PUT, DELETE, PATCH }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Mono<Map<String, List<String>>> handleWebExchangeBindException(WebExchangeBindException exception) {
        return Mono.just(exception.getBindingResult().getAllErrors().stream()
                .collect(toMap(
                        objectError -> ((FieldError) objectError).getField(),
                        objectError -> {
                            final List<String> errorMsgList = new ArrayList<>();
                            errorMsgList.add(objectError.getDefaultMessage());
                            return errorMsgList;
                        },
                        (errorMsgList1, errorMsgList2) -> errorMsgList1
                        )
                ));
    }

    private Mono<GeneralErrorResponse> buildGeneralErrorResponse(String detail) {
        return Mono.just(GeneralErrorResponse.builder()
                .detail(detail)
                .build());
    }

    @RequestMapping(method = { GET, POST, PUT, DELETE, PATCH }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public Mono<GeneralErrorResponse> handleUnprocessableEntityException(UnprocessableEntityException exception) {
        return buildGeneralErrorResponse(exception.getMessage());
    }

    @RequestMapping(method = { GET, POST, PUT, DELETE, PATCH }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Mono<Map<String, Object>> handleValidationException(ValidationException exception) {
        return Mono.just(exception.getErrors());
    }

    @RequestMapping(method = { GET, POST, PUT, DELETE, PATCH }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<GeneralErrorResponse> handleInternalServerErrorException(InternalServerErrorException exception) {
        return buildGeneralErrorResponse(exception.getMessage());
    }

}
