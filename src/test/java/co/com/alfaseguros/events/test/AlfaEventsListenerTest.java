package co.com.alfaseguros.events.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import co.com.alfaseguros.events.AlfaEventsApplication;
import co.com.alfaseguros.events.domain.services.setapplicationlog.SetApplicationLogRequest;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventRequest;
import co.com.alfaseguros.commons.enums.MessageResponseEnum;
import co.com.alfaseguros.commons.exceptions.ExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.bussiness.ClientExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.bussiness.ServerExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.bussiness.BussinessExceptionAlfa;
import co.com.alfaseguros.events.helper.TestHelper;
import co.com.alfaseguros.events.listener.EventsListener;
import co.com.alfaseguros.events.services.ServiceExecution;

@ContextConfiguration(classes = AlfaEventsApplication.class)
@SpringBootTest
@ActiveProfiles("test")
class AlfaEventsListenerTest {
	
	@Mock
	@Qualifier("saveApplicationLogServiceExecution")
	private ServiceExecution<SetApplicationLogRequest, Void> saveApplicationLogServiceExecution;
	
	@Mock
	@Qualifier("setRecordEventServiceExecution")
	private ServiceExecution<SetRecordEventRequest, Void> setRecordEventServiceExecution;	
	
	private static final Logger log = LoggerFactory.getLogger(AlfaEventsListenerTest.class);

	private EventsListener eventsListener;
		
	@BeforeEach
	public void setUp() throws JsonMappingException, JsonProcessingException {
		try {
			Mockito.when(this.saveApplicationLogServiceExecution.processOperation(TestHelper.getSetApplicationLogRequest()))
			   .thenReturn(TestHelper.simulateVoidResponse());
			Mockito.when(this.setRecordEventServiceExecution.processOperation(TestHelper.getSetRecordEventRequest()))
			   .thenReturn(TestHelper.simulateVoidResponse());
			MockitoAnnotations.initMocks(this.getClass());
			eventsListener = new EventsListener(this.saveApplicationLogServiceExecution, this.setRecordEventServiceExecution);
		} catch (ExceptionAlfa e) {
			log.error("Error Test ",e);			
		}
	}
	
	@Test
	void whenValidSuccessSetApplicationLogRequest() throws ExceptionAlfa {		
		 assertDoesNotThrow(() -> eventsListener.setApplicationLog(TestHelper.getSQSSetApplicationLogRequest()));		
	}

	@Test
	void whenSystemErrorSetApplicationLogRequest() throws ExceptionAlfa {		
		ExceptionAlfa exception = assertThrows(ClientExceptionAlfa.class, () ->  eventsListener.setApplicationLog(TestHelper.getBadSQSSetApplicationLogRequest()));		
		assertEquals(MessageResponseEnum.DATA_VALIDATION.getCode(),exception.getCodError());
	}
	
	@Test
	void whenValidSuccessSetRecordEventRequest() throws ExceptionAlfa {	
		assertDoesNotThrow(() -> eventsListener.setRecordEvent(TestHelper.getSQSSetRecordEventRequest()));
	}

	@Test
	void whenSystemErrorSetRecordEventRequest() throws ExceptionAlfa { 
		Mockito.doThrow(new BussinessExceptionAlfa(MessageResponseEnum.SERVICE_CALL_ERROR, "Error")).when(this.setRecordEventServiceExecution).processOperation(Mockito.any());
		ExceptionAlfa exception = assertThrows(BussinessExceptionAlfa.class, () -> eventsListener.setRecordEvent(TestHelper.getBadSQSSetRecordEventRequest()));
		assertEquals(MessageResponseEnum.SERVICE_CALL_ERROR.getCode(),exception.getCodError());
	}
	
}
