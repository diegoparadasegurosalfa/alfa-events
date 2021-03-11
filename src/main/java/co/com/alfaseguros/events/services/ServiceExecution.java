package co.com.alfaseguros.events.services;

import co.com.alfaseguros.events.exceptions.ExceptionAlfa;


public interface ServiceExecution <A,B> {
	
	public B processOperation(final A data) throws ExceptionAlfa;
	
}
