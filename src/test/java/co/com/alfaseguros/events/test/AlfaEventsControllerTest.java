package co.com.alfaseguros.events.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import co.com.alfaseguros.events.AlfaEventsApplication;
import co.com.alfaseguros.events.controller.EventsController;
import co.com.alfaseguros.events.domain.enums.MessageResponseEnum;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventRequest;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventResponse;
import co.com.alfaseguros.events.exceptions.ExceptionAlfa;
import co.com.alfaseguros.events.helper.TestHelper;
import co.com.alfaseguros.events.services.ServiceExecution;

@ContextConfiguration(classes = AlfaEventsApplication.class)
@SpringBootTest
@ActiveProfiles("test")
class AlfaEventsControllerTest {
	
	@Mock
	@Qualifier("setRecordEventServiceExecution")
	private ServiceExecution<SetRecordEventRequest, SetRecordEventResponse> setRecordEventServiceExecution;
	
	private static final Logger log = LoggerFactory.getLogger(AlfaEventsControllerTest.class);

	private EventsController controller;
	
	@BeforeEach
	public void setUp() {
		try {
			Mockito.when(this.setRecordEventServiceExecution.processOperation(TestHelper.getSetRecordEventRequest()))
			   .thenReturn(TestHelper.simulateSucessSetRecordEventResponse());
			MockitoAnnotations.initMocks(this.getClass());
			controller = new EventsController(this.setRecordEventServiceExecution);
		} catch (ExceptionAlfa e) {
			log.error("Error Test ",e);			
		}
	}
	
	@Test
	void whenValidSuccessSetRecordEventRequest() throws ExceptionAlfa {		
		ResponseEntity<SetRecordEventResponse> response = controller.setRecordEvent(TestHelper.getSetRecordEventRequest());		
		assertEquals(String.valueOf(MessageResponseEnum.OK.getCode()), response.getBody().getStatusCode());
	}
	
	@Test
	void whenDataValidationErrorSetRecordEventRequest() throws ExceptionAlfa {		
		ResponseEntity<SetRecordEventResponse> response = controller.setRecordEvent(new SetRecordEventRequest());		
		assertEquals(String.valueOf(MessageResponseEnum.DATA_VALIDATION.getCode()), response.getBody().getStatusCode());
	}
}
