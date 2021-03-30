package co.com.alfaseguros.events.repository;

import co.com.alfaseguros.events.exceptions.ExceptionAlfa;

public interface RepoService<A> {
	
	void addRespositoryElement(A data) throws ExceptionAlfa;

}
