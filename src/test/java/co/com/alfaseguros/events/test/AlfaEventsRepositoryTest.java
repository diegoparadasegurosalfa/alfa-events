package co.com.alfaseguros.events.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import co.com.alfaseguros.events.AlfaEventsApplication;
import co.com.alfaseguros.events.domain.entities.ApplicationLog;
import co.com.alfaseguros.events.domain.enums.MessageResponseEnum;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageResponse;
import co.com.alfaseguros.events.exceptions.BussinessExceptionAlfa;
import co.com.alfaseguros.events.exceptions.ExceptionAlfa;
import co.com.alfaseguros.events.exceptions.ServerExceptionAlfa;
import co.com.alfaseguros.events.helper.TestHelper;
import co.com.alfaseguros.events.infraestructure.SetRecordRegistryQueueMessageInfraService;
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
