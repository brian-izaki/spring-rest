package com.logisticAlgaworks.logistic.api.exceptionHandler;

import com.logisticAlgaworks.logistic.domain.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
// diz que é um componente do spring mas que tem PROPÓSITO ESPECÍFICO para SER GLOBAL (tipo um utils)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    // é o método responsável por suprimir os erros de argumentos inválidos
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        List<Problema.Campo> campos = new ArrayList<>();

        // bindingReult é quem possui os erros que ocorreram na validação
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
//            String mensagem = error.getDefaultMessage();
            // se trabalhar com internaciolização o getLocale é muito importante.
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new Problema.Campo(nome, mensagem));
        }

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(LocalDateTime.now());
        problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
        problema.setCampos(campos);

        // handleExceptionInternal é um método da própria classe extendida.
        // no segundo parâmetro é possível manipular a resposta de erro a ser retornada para o cliente.
        return handleExceptionInternal(ex, problema, headers, status, request);

    }

    // a anotação diz que em qualquer parte da aplicação que der o NegocioException irá executar este método e
    // retornar as mensagens de erro daqui.
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(LocalDateTime.now());
        problema.setTitulo(ex.getMessage());

        // o headers está sendo enviado um header vazio, se quiser customizar teria q tirar ele e criar uma variável.
        // o status está sendo fixo de acordo com o da linha de cima
        // o request o próprio Spring irá preencher pra gente pois passamos ele como segundo parâmetro no método.
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }
}
