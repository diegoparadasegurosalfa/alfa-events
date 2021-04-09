package co.com.alfaseguros.events.utils;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import co.com.alfaseguros.events.domain.entities.ApplicationLog;
import co.com.alfaseguros.events.domain.generic.Detail;
import co.com.alfaseguros.events.domain.services.setapplicationlog.SetApplicationLogRequest;
import co.com.alfaseguros.commons.enums.MessageResponseEnum;
import co.com.alfaseguros.commons.exceptions.ExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.bussiness.ServerExceptionAlfa;

@Component("setApplicationLogTransformation")
public class SetApplicationLogTransformation implements Transformation<SetApplicationLogRequest, ApplicationLog>{

	@Override
	public ApplicationLog transformStructure(SetApplicationLogRequest input) throws ExceptionAlfa {
		try {
			ApplicationLog output = new ApplicationLog();
			output.setUserName(input.getUserName());
			output.setDateTime(input.getDateTime());
			output.setIp(input.getIp());
			output.setChannel(input.getChannel());
			output.setDetail(new ArrayList<>());
			for (Detail sourceDetail: input.getDetail()) {
				co.com.alfaseguros.events.domain.entities.Detail targetDetail = new co.com.alfaseguros.events.domain.entities.Detail();
				targetDetail.setKey(sourceDetail.getKey());
				targetDetail.setKey(sourceDetail.getKey());
				output.getDetail().add(targetDetail);
			}
			return output;
		}catch(Exception exception) {
			throw new ServerExceptionAlfa(MessageResponseEnum.SYSTEM_ERROR, exception.getMessage());
		}
	}

}
