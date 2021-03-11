package co.com.alfaseguros.events.utils;

import org.springframework.stereotype.Component;

import co.com.alfaseguros.events.domain.enums.MessageResponseEnum;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventRequest;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageRequest;
import co.com.alfaseguros.events.exceptions.ExceptionAlfa;
import co.com.alfaseguros.events.exceptions.ServerExceptionAlfa;

@Component("setRecordRegistryQueueMessageTransformation")
public class SetRecordRegistryQueueMessageTransformation implements Transformation<SetRecordEventRequest, SetRecordRegistryQueueMessageRequest>{

	@Override
	public SetRecordRegistryQueueMessageRequest transformStructure(SetRecordEventRequest input) throws ExceptionAlfa{
		try {
			SetRecordRegistryQueueMessageRequest output = new SetRecordRegistryQueueMessageRequest();
			output.setSource(input.getSource());
			output.setAction(input.getAction());
			output.setTableName(input.getTableName());
			output.setParametersList(input.getParametersList());
			return output;
		}catch(Exception exception) {
			throw new ServerExceptionAlfa(MessageResponseEnum.SYSTEM_ERROR, exception.getMessage());
		}
	}

}
