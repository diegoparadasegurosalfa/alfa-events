package co.com.alfaseguros.events.exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import co.com.alfaseguros.events.domain.enums.MessageResponseEnum;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventResponse;

@ControllerAdvice
public class ExceptionAlfaHandler {

	@ExceptionHandler(ClientExceptionAlfa.class)
	public ResponseEntity<SetRecordEventResponse> clientExceptionAlfaHandling(ClientExceptionAlfa exception, WebRequest request){
		SetRecordEventResponse setRecordEventResponse = new SetRecordEventResponse();
		setRecordEventResponse.setStatusCode(String.valueOf(exception.getCode()));
		setRecordEventResponse.setStatusDesc(exception.getMessage());
		return new ResponseEntity<>(setRecordEventResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ServerExceptionAlfa.class)
	public ResponseEntity<SetRecordEventResponse> serverExceptionAlfaHandling(ServerExceptionAlfa exception, WebRequest request){
		SetRecordEventResponse setRecordEventResponse = new SetRecordEventResponse();
		setRecordEventResponse.setStatusCode(String.valueOf(exception.getCode()));
		setRecordEventResponse.setStatusDesc(exception.getMessage());
		return new ResponseEntity<>(setRecordEventResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BussinessExceptionAlfa.class)
	public ResponseEntity<SetRecordEventResponse> bussinessExceptionAlfaHandling(BussinessExceptionAlfa exception, WebRequest request){
		SetRecordEventResponse setRecordEventResponse = new SetRecordEventResponse();
		setRecordEventResponse.setStatusCode(String.valueOf(exception.getCode()));
		setRecordEventResponse.setStatusDesc(exception.getMessage());
		return new ResponseEntity<>(setRecordEventResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<SetRecordEventResponse> methodArgumentNotValidExceptionHandling(MethodArgumentNotValidException exception, WebRequest request){
		return clientExceptionAlfaHandling(new ClientExceptionAlfa(MessageResponseEnum.DATA_VALIDATION, exception.getMessage()), request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<SetRecordEventResponse> exceptionHandling(Exception exception, WebRequest request){
		return serverExceptionAlfaHandling(new ServerExceptionAlfa(MessageResponseEnum.SYSTEM_ERROR, exception.getMessage()), request);
	}
}
