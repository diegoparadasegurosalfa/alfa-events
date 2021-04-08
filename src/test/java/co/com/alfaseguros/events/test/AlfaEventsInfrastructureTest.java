package co.com.alfaseguros.events.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import co.com.alfaseguros.events.AlfaEventsApplication;
import co.com.alfaseguros.events.domain.enums.MessageResponseEnum;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageResponse;
//import co.com.alfaseguros.events.exceptions.ExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.ExceptionAlfa;
import co.com.alfaseguros.events.helper.TestHelper;
import co.com.alfaseguros.events.infraestructure.SetRecordRegistryQueueMessageInfraService;


@ContextConfiguration(classes = AlfaEventsApplication.class)
@SpringBootTest
@ActiveProfiles("test")
class AlfaEventsInfrastructureTest {

	@Mock
	private RestTemplate template;
	
	@Value("${alfa.services.mediatorosb.url.setrecordregistryqueuemessage}")
	private String filteredUrl;
	
	private SetRecordRegistryQueueMessageInfraService setRecordRegistryQueueMessageInfraService;
	
	@BeforeEach
	public void setUp() throws ExceptionAlfa {
		MockitoAnnotations.initMocks(this.getClass());
		this.setRecordRegistryQueueMessageInfraService = new SetRecordRegistryQueueMessageInfraService(this.template);
		ReflectionTestUtils.setField(this.setRecordRegistryQueueMessageInfraService, "filteredUrl", this.filteredUrl);
	}
	
	@Test
	void whenValidSuccessSetRecordRegistryQueueMessageRequest() throws ExceptionAlfa {	
		when(template.exchange(
                Mockito.anyString(), 
                Mockito.any(HttpMethod.class),
                Mockito.any(HttpEntity.class), 
                Mockito.eq(SetRecordRegistryQueueMessageResponse.class)
               ))
                .thenReturn(TestHelper.simulateSucessSetRecordRegistryQueueMessageResponse());
		ResponseEntity<SetRecordRegistryQueueMessageResponse> response = setRecordRegistryQueueMessageInfraService.callService(TestHelper.getSetRecordRegistryQueueMessageRequest());		
		assertEquals(String.valueOf(MessageResponseEnum.OK.getCode()), response.getBody().getStatusCode());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void whenValidSystemErrorSetRecordRegistryQueueMessageRequest() throws ExceptionAlfa {
		when(template.exchange(
                Mockito.anyString(), 
                Mockito.any(HttpMethod.class),
                Mockito.any(HttpEntity.class), 
                Mockito.eq(SetRecordRegistryQueueMessageResponse.class)
               ))
                .thenReturn(TestHelper.simulateFailedSetRecordRegistryQueueMessageResponse());
		ResponseEntity<SetRecordRegistryQueueMessageResponse> response = setRecordRegistryQueueMessageInfraService.callService(TestHelper.getBadSetRecordRegistryQueueMessageRequest());		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
}
