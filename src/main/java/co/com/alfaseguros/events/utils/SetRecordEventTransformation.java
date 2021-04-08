package co.com.alfaseguros.events.utils;

import org.springframework.stereotype.Component;

import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventResponse;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageResponse;
//import co.com.alfaseguros.events.domain.enums.MessageResponseEnum;
//import co.com.alfaseguros.events.exceptions.ExceptionAlfa;
//import co.com.alfaseguros.events.exceptions.ServerExceptionAlfa;
import co.com.alfaseguros.commons.enums.MessageResponseEnum;
import co.com.alfaseguros.commons.exceptions.ExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.bussiness.ServerExceptionAlfa;

@Component("setRecordEventTransformation")
public class SetRecordEventTransformation implements Transformation<SetRecordRegistryQueueMessageResponse, SetRecordEventResponse>{

	
	@Override
	public SetRecordEventResponse transformStructure(SetRecordRegistryQueueMessageResponse input) throws ExceptionAlfa{
		try {
			SetRecordEventResponse output = new SetRecordEventResponse();
			output.setStatusCode(input.getStatusCode());
			output.setStatusDesc(input.getStatusDesc());
			return output;
		}catch(Exception exception) {
			throw new ServerExceptionAlfa(MessageResponseEnum.SYSTEM_ERROR, exception.getMessage());
		}
	}

}
