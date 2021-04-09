package co.com.alfaseguros.events.utils;

import co.com.alfaseguros.commons.exceptions.ExceptionAlfa;

public interface Transformation <A,B> {
	
	public B transformStructure (A input) throws ExceptionAlfa;

}
