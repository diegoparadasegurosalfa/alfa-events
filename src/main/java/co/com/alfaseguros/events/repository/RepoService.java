package co.com.alfaseguros.events.repository;

//import co.com.alfaseguros.events.exceptions.ExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.ExceptionAlfa;

public interface RepoService<A> {
	
	void addRespositoryElement(A data) throws ExceptionAlfa;

}
