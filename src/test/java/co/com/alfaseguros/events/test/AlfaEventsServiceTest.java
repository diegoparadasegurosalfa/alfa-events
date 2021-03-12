package co.com.alfaseguros.events.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import co.com.alfaseguros.events.AlfaEventsApplication;
import co.com.alfaseguros.events.domain.enums.MessageResponseEnum;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventRequest;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventResponse;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageRequest;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageResponse;
import co.com.alfaseguros.events.exceptions.ExceptionAlfa;
import co.com.alfaseguros.events.helper.TestHelper;
import co.com.alfaseguros.events.infraestructure.InfraService;
import co.com.alfaseguros.events.services.SetRecordEventServiceExecution;
import co.com.alfaseguros.events.utils.Transformation;

@ContextConfiguration(classes = AlfaEventsApplication.class)
@SpringBootTest
@ActiveProfiles("test")
class AlfaEventsServiceTest {
	
	@Mock
	@Qualifier("setRecordRegistryQueueMessageInfraService")
	private  InfraService<SetRecordRegistryQueueMessageRequest, SetRecordRegistryQueueMessageResponse> setRecordRegistryQueueMessageInfraService;
	
	@Qualifier("setRecordRegistryQueueMessageTransformation")
	private Transformation<SetRecordEventRequest, SetRecordRegistryQueueMessageRequest> setRecordRegistryQueueMessageTransformation;
	
	@Qualifier("setRecordEventTransformation")
	private Transformation<SetRecordRegistryQueueMessageResponse, SetRecordEventResponse> setRecordEventransformation;
	
	private SetRecordEventServiceExecution service;
	
	@BeforeEach
	public void setUp() throws ExceptionAlfa {
		Mockito.when(this.setRecordRegistryQueueMessageInfraService.callService(TestHelper.getSetRecordRegistryQueueMessageRequest()))
		   .thenReturn(co.com.alfaseguros.events.helper.TestHelper.simulateSucessSetRecordRegistryQueueMessageResponse());
		Mockito.when(this.setRecordRegistryQueueMessageInfraService.callService(TestHelper.getBadSetRecordRegistryQueueMessageRequest()))
		   .thenReturn(co.com.alfaseguros.events.helper.TestHelper.simulateSucessSetRecordRegistryQueueMessageResponse());
		MockitoAnnotations.initMocks(this.getClass());
		this.service = new SetRecordEventServiceExecution(setRecordRegistryQueueMessageInfraService, setRecordRegistryQueueMessageTransformation, setRecordEventransformation);
	}	
	
	@Test
	void whenValidSuccessSetRecordEventRequest() throws ExceptionAlfa {		
		SetRecordEventResponse response = service.processOperation(TestHelper.getSetRecordEventRequest());		
		assertEquals(String.valueOf(MessageResponseEnum.OK.getCode()), response.getStatusCode());
	}
	
	@Test
	void whenSystemErrorSetRecordEventRequest() throws ExceptionAlfa {		
		SetRecordEventResponse response = service.processOperation(TestHelper.getBadSetRecordEventRequest());		
		assertEquals(String.valueOf(MessageResponseEnum.SYSTEM_ERROR.getCode()), response.getStatusCode());
	}
}
