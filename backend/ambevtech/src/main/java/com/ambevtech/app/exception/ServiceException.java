package com.ambevtech.app.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Getter
@Setter
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private EnumErrorException errorException;

    private HttpStatus httpStatus;

    private String nome;

    private String detalhe;

    private Set<ConstraintViolation<Object>> violations;

    public ServiceException(Set<ConstraintViolation<Object>> violations) {
        super(EnumErrorException.PARAMETROS_INVALIDOS.getNome());
        this.errorException = EnumErrorException.PARAMETROS_INVALIDOS;
        this.detalhe = EnumErrorException.PARAMETROS_INVALIDOS.getDetalhe();
        this.violations = violations;
    }


    public ServiceException(EnumErrorException error) {
        super(error.getNome());
        this.errorException = error;
        this.httpStatus = error.getHttpStatus();
        this.nome = error.getNome();
        this.detalhe = error.getDetalhe();
    }

    public ServiceException(EnumErrorException error, String detalhe) {
        super(error.getNome());
        this.errorException = error;
        this.httpStatus = error.getHttpStatus();
        this.nome = error.getNome();
        this.detalhe = detalhe;
    }

    public ServiceException(EnumErrorException error, Throwable e) {
        super(error.getNome(), e);
        this.errorException = error;
        this.httpStatus = error.getHttpStatus();
        this.nome = error.getNome();
        this.detalhe = error.getDetalhe();
    }


}
