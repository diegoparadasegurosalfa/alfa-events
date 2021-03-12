package co.com.alfaseguros.events.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import co.com.alfaseguros.events.domain.enums.MessageResponseEnum;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventRequest;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventResponse;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageRequest;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageResponse;
import co.com.alfaseguros.events.exceptions.BussinessExceptionAlfa;
import co.com.alfaseguros.events.exceptions.ExceptionAlfa;
import co.com.alfaseguros.events.exceptions.ServerExceptionAlfa;
import co.com.alfaseguros.events.infraestructure.InfraService;
import co.com.alfaseguros.events.utils.Transformation;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Component("setRecordEventServiceExecution")
public class SetRecordEventServiceExecution implements ServiceExecution<SetRecordEventRequest, SetRecordEventResponse>{
	
	@Qualifier("setRecordRegistryQueueMessageInfraService")
	private final InfraService<SetRecordRegistryQueueMessageRequest, SetRecordRegistryQueueMessageResponse> setRecordRegistryQueueMessageInfraService;
	
	@Qualifier("setRecordRegistryQueueMessageTransformation")
	private final Transformation<SetRecordEventRequest, SetRecordRegistryQueueMessageRequest> setRecordRegistryQueueMessageTransformation;
	
	@Qualifier("setRecordEventTransformation")
	private final Transformation<SetRecordRegistryQueueMessageResponse, SetRecordEventResponse> setRecordEventransformation;
	
	@Override
	public SetRecordEventResponse processOperation(SetRecordEventRequest data) throws ExceptionAlfa {
		SetRecordRegistryQueueMessageRequest setRecordRegistryQueueMessageRequest = setRecordRegistryQueueMessageTransformation.transformStructure(data);
		ResponseEntity<SetRecordRegistryQueueMessageResponse> responseEntity = setRecordRegistryQueueMessageInfraService.callService(setRecordRegistryQueueMessageRequest);
		SetRecordRegistryQueueMessageResponse setRecordRegistryQueueMessageResponse = responseEntity.getBody();
		if (setRecordRegistryQueueMessageResponse!=null) {
			if(setRecordRegistryQueueMessageResponse.getStatusCode().charAt(0)!='2') {
				throw new BussinessExceptionAlfa(MessageResponseEnum.SERVICE_CALL_ERROR, setRecordRegistryQueueMessageResponse.getStatusDesc());
			}
		}else {
			throw new ServerExceptionAlfa(MessageResponseEnum.SERVICE_CALL_ERROR);
		}
		return setRecordEventransformation.transformStructure(setRecordRegistryQueueMessageResponse);
	}
}

