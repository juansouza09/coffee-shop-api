package com.jssolutions.coffee_shop_api.infrastructure;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail errorDetail;

        errorDetail = switch (exception) {
            case MalformedJwtException e -> createProblemDetail(400, "O token JWT está malformado.", "Verifique o formato do token JWT enviado.");
            case BadCredentialsException e -> createProblemDetail(401, e.getMessage(), "O nome de usuário ou senha estão incorretos.");
            case AccountStatusException e -> createProblemDetail(403, e.getMessage(), "A conta está bloqueada.");
            case AccessDeniedException e -> createProblemDetail(403, e.getMessage(), "Você não está autorizado a acessar este recurso.");
            case SignatureException e -> createProblemDetail(403, "A assinatura do JWT é inválida.", "O JWT não possui uma assinatura válida.");
            case ExpiredJwtException e -> createProblemDetail(403, "O token JWT expirou.", "Envie um novo token JWT.");
            default -> createProblemDetail(500, exception.getMessage(), "Erro interno desconhecido.");
        };

        return errorDetail;
    }

    private ProblemDetail createProblemDetail(int status, String detail, String description) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(status), detail);
        problemDetail.setProperty("description", description);
        return problemDetail;
    }
}
