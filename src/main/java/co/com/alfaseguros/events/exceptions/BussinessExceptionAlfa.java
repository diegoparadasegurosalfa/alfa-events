package co.com.alfaseguros.events.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.com.alfaseguros.events.domain.enums.MessageResponseEnum;

@ResponseStatus(value = HttpStatus.OK)
public class BussinessExceptionAlfa extends ExceptionAlfa {

	private static final long serialVersionUID = 1L;
	
	public BussinessExceptionAlfa(MessageResponseEnum messageResponse) {
		super(messageResponse.getCode(), messageResponse.getMessage());
	}
	
	public BussinessExceptionAlfa(MessageResponseEnum messageResponse, String description) {
		super(messageResponse.getCode(), messageResponse.getMessage()+", Caused by: "+description);
	}
	
}
