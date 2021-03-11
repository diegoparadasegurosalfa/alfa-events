package co.com.alfaseguros.events.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.com.alfaseguros.events.domain.enums.MessageResponseEnum;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ClientExceptionAlfa extends ExceptionAlfa{
	
	private static final long serialVersionUID = 1L;
	
	public ClientExceptionAlfa(MessageResponseEnum messageResponse) {
		super(messageResponse.getCode(), messageResponse.getMessage());
	}
	
	public ClientExceptionAlfa(MessageResponseEnum messageResponse, String description) {
		super(messageResponse.getCode(), messageResponse.getMessage()+", Caused by: "+description);
	}

}
