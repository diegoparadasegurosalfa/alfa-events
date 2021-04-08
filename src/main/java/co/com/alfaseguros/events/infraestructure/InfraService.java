package co.com.alfaseguros.events.infraestructure;

import org.springframework.http.ResponseEntity;

//import co.com.alfaseguros.events.exceptions.ExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.ExceptionAlfa;

public interface InfraService<A, B> {
	
	public ResponseEntity<B> callService(A data) throws ExceptionAlfa ;
	
}
