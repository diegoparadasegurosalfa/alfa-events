package co.com.alfaseguros.events.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventRequest;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventResponse;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageRequest;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageResponse;
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
		try {
			SetRecordRegistryQueueMessageRequest setRecordRegistryQueueMessageRequest = setRecordRegistryQueueMessageTransformation.transformStructure(data);
			ResponseEntity<SetRecordRegistryQueueMessageResponse> responseEntity = setRecordRegistryQueueMessageInfraService.callService(setRecordRegistryQueueMessageRequest);
			return setRecordEventransformation.transformStructure(responseEntity.getBody());
		}catch(ServerExceptionAlfa exception) {
			throw exception;
		}
	}
}

