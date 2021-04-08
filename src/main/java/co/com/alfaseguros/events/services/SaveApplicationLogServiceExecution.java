package co.com.alfaseguros.events.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import co.com.alfaseguros.events.domain.entities.ApplicationLog;
import co.com.alfaseguros.events.domain.services.setapplicationlog.SetApplicationLogRequest;
//import co.com.alfaseguros.events.exceptions.ExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.ExceptionAlfa;
import co.com.alfaseguros.events.repository.RepoService;
import co.com.alfaseguros.events.utils.Transformation;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Component("saveApplicationLogServiceExecution")
public class SaveApplicationLogServiceExecution implements ServiceExecution<SetApplicationLogRequest, Void>{
	
	@Qualifier("applicationLogRepoService")
	private final RepoService<ApplicationLog> applicationLogRepoService;
	
	@Qualifier("setApplicationLogTransformation")
	private final Transformation<SetApplicationLogRequest, ApplicationLog> setApplicationLogTransformation;
	
	@Override
	public Void processOperation(SetApplicationLogRequest data) throws ExceptionAlfa {
		ApplicationLog applicationLog = setApplicationLogTransformation.transformStructure(data);
		applicationLogRepoService.addRespositoryElement(applicationLog);
		return null;
	}
}

