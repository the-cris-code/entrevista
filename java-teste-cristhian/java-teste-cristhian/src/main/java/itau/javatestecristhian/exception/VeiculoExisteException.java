package itau.javatestecristhian.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class VeiculoExisteException extends RuntimeException {

    public VeiculoExisteException(String message){
        super(message);
    }
}
