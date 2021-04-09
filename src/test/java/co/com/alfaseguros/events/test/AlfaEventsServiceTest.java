package co.com.alfaseguros.events.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import co.com.alfaseguros.events.AlfaEventsApplication;
import co.com.alfaseguros.events.domain.entities.ApplicationLog;
import co.com.alfaseguros.events.domain.services.setapplicationlog.SetApplicationLogRequest;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventRequest;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventResponse;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageRequest;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageResponse;
import co.com.alfaseguros.commons.enums.MessageResponseEnum;
import co.com.alfaseguros.commons.exceptions.ExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.bussiness.ServerExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.bussiness.BussinessExceptionAlfa;
import co.com.alfaseguros.events.helper.TestHelper;
import co.com.alfaseguros.events.infraestructure.InfraService;
import co.com.alfaseguros.events.repository.RepoService;
import co.com.alfaseguros.events.services.SaveApplicationLogServiceExecution;
import co.com.alfaseguros.events.services.SetRecordEventServiceExecution;
import co.com.alfaseguros.events.utils.Transformation;

@ContextConfiguration(classes = AlfaEventsApplication.class)
@SpringBootTest
@ActiveProfiles("test")
class AlfaEventsServiceTest {
	
	@Mock
	@Qualifier("setRecordRegistryQueueMessageInfraService")
	private  InfraService<SetRecordRegistryQueueMessageRequest, SetRecordRegistryQueueMessageResponse> setRecordRegistryQueueMessageInfraService;
	
	@Mock
	@Qualifier("setRecordRegistryQueueMessageTransformation")
	private Transformation<SetRecordEventRequest, SetRecordRegistryQueueMessageRequest> setRecordRegistryQueueMessageTransformation;
	
	@Mock
	@Qualifier("setRecordEventTransformation")
	private Transformation<SetRecordRegistryQueueMessageResponse, SetRecordEventResponse> setRecordEventransformation;
	
	@Mock
	@Qualifier("applicationLogRepoService")
	private RepoService<ApplicationLog> applicationLogRepoService;
	
	@Mock
	@Qualifier("setApplicationLogTransformation")
	private Transformation<SetApplicationLogRequest, ApplicationLog> setApplicationLogTransformation;
	
	private SetRecordEventServiceExecution setRecordEventServiceExecution;
	
	private SaveApplicationLogServiceExecution saveApplicationLogServiceExecution;
	
	
	@BeforeEach
	public void setUp() throws ExceptionAlfa, JsonMappingException, JsonProcessingException {
		Mockito.when(this.setRecordRegistryQueueMessageTransformation.transformStructure(TestHelper.getSetRecordEventRequest()))
		   .thenReturn(TestHelper.getSetRecordRegistryQueueMessageRequest());
		Mockito.when(this.setRecordRegistryQueueMessageTransformation.transformStructure(TestHelper.getBadSetRecordEventRequest()))
		   .thenReturn(TestHelper.getBadSetRecordRegistryQueueMessageRequest());
		Mockito.when(this.setRecordRegistryQueueMessageInfraService.callService(TestHelper.getSetRecordRegistryQueueMessageRequest()))
		   .thenReturn(TestHelper.simulateSucessSetRecordRegistryQueueMessageResponse());
		Mockito.when(this.setRecordRegistryQueueMessageInfraService.callService(TestHelper.getBadSetRecordRegistryQueueMessageRequest()))
		   .thenReturn(TestHelper.simulateFailedSetRecordRegistryQueueMessageResponse());
		Mockito.when(this.setRecordEventransformation.transformStructure(TestHelper.simulateSucessSetRecordRegistryQueueMessageResponse().getBody()))
		   .thenReturn(TestHelper.simulateSucessSetRecordEventResponse());
		Mockito.when(this.setRecordEventransformation.transformStructure(TestHelper.simulateFailedSetRecordRegistryQueueMessageResponse().getBody()))
		   .thenReturn(TestHelper.simulateFailedSetRecordEventResponse());
		Mockito.when(this.setApplicationLogTransformation.transformStructure(TestHelper.getSetApplicationLogRequest()))
		   .thenReturn(TestHelper.getApplicationLog());
		Mockito.when(this.setApplicationLogTransformation.transformStructure(TestHelper.getBadSetApplicationLogRequest()))
		   .thenReturn(TestHelper.getBadApplicationLog());
		MockitoAnnotations.initMocks(this.getClass());
		this.setRecordEventServiceExecution = new SetRecordEventServiceExecution(this.setRecordRegistryQueueMessageInfraService, this.setRecordRegistryQueueMessageTransformation, this.setRecordEventransformation);
		this.saveApplicationLogServiceExecution = new SaveApplicationLogServiceExecution(this.applicationLogRepoService, this.setApplicationLogTransformation);
	}	
	
	@Test
	void whenValidSuccessSetRecordEventRequest() throws ExceptionAlfa {		
		SetRecordEventResponse response = setRecordEventServiceExecution.processOperation(TestHelper.getSetRecordEventRequest());		
		assertEquals(String.valueOf(MessageResponseEnum.OK.getCode()), response.getStatusCode());
	}
	
	@Test
	void whenSystemErrorSetRecordEventRequest() throws ExceptionAlfa {	
		ExceptionAlfa exception = assertThrows(BussinessExceptionAlfa.class, () -> setRecordEventServiceExecution.processOperation(TestHelper.getBadSetRecordEventRequest()));
		//assertEquals(MessageResponseEnum.SERVICE_CALL_ERROR.getCode(),exception.getCode());
		assertEquals(MessageResponseEnum.SERVICE_CALL_ERROR.getCode(),exception.getCodError());
	}
	
	@Test
	void whenValidSuccessSetApplicationLogRequest() throws ExceptionAlfa {		
		 assertDoesNotThrow(() -> saveApplicationLogServiceExecution.processOperation(TestHelper.getSetApplicationLogRequest()));
	}
	
	@Test
	void whenServerErrorSetApplicationLogRequest() throws ExceptionAlfa {
		//Mockito.doThrow(new ServerExceptionAlfa(MessageResponseEnum.DATABASE_CALL_ERROR, null)).when(this.applicationLogRepoService).addRespositoryElement(Mockito.any());
		Mockito.doThrow(new ServerExceptionAlfa(MessageResponseEnum.DB_ERROR, null)).when(this.applicationLogRepoService).addRespositoryElement(Mockito.any());
		ExceptionAlfa exception = assertThrows(ServerExceptionAlfa.class, () -> saveApplicationLogServiceExecution.processOperation(TestHelper.getBadSetApplicationLogRequest()));
		//assertEquals(MessageResponseEnum.DB_ERROR .getCode(),exception.getCode());
		assertEquals(MessageResponseEnum.DB_ERROR .getCode(),exception.getCodError());
	}
}
