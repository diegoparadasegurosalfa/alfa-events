package co.com.alfaseguros.events.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.com.alfaseguros.events.domain.enums.MessageResponseEnum;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerExceptionAlfa extends ExceptionAlfa{

	private static final long serialVersionUID = 1L;
	
	public ServerExceptionAlfa(MessageResponseEnum messageResponse) {
		super(messageResponse.getCode(), messageResponse.getMessage());
	}
	
	public ServerExceptionAlfa(MessageResponseEnum messageResponse, String description) {
		super(messageResponse.getCode(), messageResponse.getMessage()+", Caused by: "+description);
	}
}
