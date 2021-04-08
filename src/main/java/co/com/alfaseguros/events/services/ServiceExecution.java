package co.com.alfaseguros.events.services;

//import co.com.alfaseguros.events.exceptions.ExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.ExceptionAlfa;

public interface ServiceExecution <A,B> {
	
	public B processOperation(final A data) throws ExceptionAlfa;
	
}
