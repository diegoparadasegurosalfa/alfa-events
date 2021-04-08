package co.com.alfaseguros.events.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import co.com.alfaseguros.events.AlfaEventsApplication;
import co.com.alfaseguros.events.domain.enums.MessageResponseEnum;
import co.com.alfaseguros.events.exceptions.ExceptionAlfa;
import co.com.alfaseguros.events.exceptions.ServerExceptionAlfa;
import co.com.alfaseguros.events.helper.TestHelper;
import co.com.alfaseguros.events.repository.ApplicationLogRepoService;


@ContextConfiguration(classes = AlfaEventsApplication.class)
@SpringBootTest
@ActiveProfiles("test")
class AlfaEventsRepositoryTest {

	@Mock
	private DynamoDBMapper dynamoDBMapper;
	
	private ApplicationLogRepoService applicationLogRepoService;
	
	@BeforeEach
	public void setUp() throws ExceptionAlfa {
		this.applicationLogRepoService = new ApplicationLogRepoService(dynamoDBMapper);
	}
	
	@Test
	void whenValidSuccessSetRecordRegistryQueueMessageRequest() throws ExceptionAlfa {
		Mockito.doAnswer((i) -> {return null;}).when(this.dynamoDBMapper).save(Mockito.any());
		this.dynamoDBMapper.save(TestHelper.getBadApplicationLog());
		assertDoesNotThrow(() -> applicationLogRepoService.addRespositoryElement(TestHelper.getApplicationLog()));
	}
	
	void whenServerExceptionSetRecordRegistryQueueMessageRequest() throws ExceptionAlfa {	
		Mockito.doThrow(new RuntimeException()).when(this.dynamoDBMapper).save(Mockito.any());
		ExceptionAlfa exception = assertThrows(ServerExceptionAlfa.class, () -> applicationLogRepoService.addRespositoryElement(TestHelper.getBadApplicationLog()));
		assertEquals(MessageResponseEnum.DATABASE_CALL_ERROR.getCode(),exception.getCode());
	}
	
}
