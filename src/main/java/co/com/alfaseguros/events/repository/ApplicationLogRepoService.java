package co.com.alfaseguros.events.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import co.com.alfaseguros.events.domain.entities.ApplicationLog;
import co.com.alfaseguros.commons.enums.MessageResponseEnum;
import co.com.alfaseguros.commons.exceptions.ExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.bussiness.ServerExceptionAlfa;
import lombok.RequiredArgsConstructor;

@Component("applicationLogRepoService")
@RequiredArgsConstructor
@Repository
public class ApplicationLogRepoService implements RepoService<ApplicationLog>{
	
	private final DynamoDBMapper dynamoDBMapper;
		
	@Override
	public void addRespositoryElement(ApplicationLog data) throws ExceptionAlfa {
		try {
			dynamoDBMapper.save(data);
		}catch(RuntimeException exception) {
			throw new ServerExceptionAlfa(MessageResponseEnum.DB_ERROR, exception.getMessage());
		}
	}
}
