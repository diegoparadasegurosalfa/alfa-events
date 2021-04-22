package co.com.alfaseguros.events.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.alfaseguros.events.domain.services.setapplicationlog.SetApplicationLogRequest;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventRequest;
import co.com.alfaseguros.commons.enums.MessageResponseEnum;
import co.com.alfaseguros.commons.exceptions.ExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.bussiness.ClientExceptionAlfa;
import co.com.alfaseguros.events.services.ServiceExecution;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
@Log4j2
@RequiredArgsConstructor
public class EventsListener {
	
	@Qualifier("saveApplicationLogServiceExecution")
	private final ServiceExecution<SetApplicationLogRequest, Void> saveApplicationLogServiceExecution;
	
	@Qualifier("setRecordEventServiceExecution")
	private final ServiceExecution<SetRecordEventRequest, Void> setRecordEventServiceExecution;	

	@SqsListener(value = "${alfa.queues.auditinpolicyquery.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void setApplicationLog(String message) throws ExceptionAlfa {
        log.debug("Received Message {}", message);
        ObjectMapper objectMapper = new ObjectMapper();
		try {
			SetApplicationLogRequest setApplicationLogRequest = objectMapper.readValue(message, SetApplicationLogRequest.class);
			saveApplicationLogServiceExecution.processOperation(setApplicationLogRequest);
		} catch (JsonProcessingException exception) {
			throw new ClientExceptionAlfa(MessageResponseEnum.DATA_VALIDATION, exception.getMessage());
		} 
    }
	
	@SqsListener(value = "${alfa.queues.auditinpaymentregistry.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void setRecordEvent(String message) throws ExceptionAlfa {
		log.debug("Received Message {}", message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        	SetRecordEventRequest setRecordEventRequest = objectMapper.readValue(message, SetRecordEventRequest.class);
        	setRecordEventServiceExecution.processOperation(setRecordEventRequest);
		} catch (JsonProcessingException exception) {
			throw new ClientExceptionAlfa(MessageResponseEnum.DATA_VALIDATION, exception.getMessage());
		}
	}
	
}
