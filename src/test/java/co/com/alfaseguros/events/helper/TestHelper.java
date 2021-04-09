package co.com.alfaseguros.events.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.alfaseguros.events.domain.entities.ApplicationLog;
import co.com.alfaseguros.events.domain.entities.Detail;
import co.com.alfaseguros.events.domain.generic.Parameter;
import co.com.alfaseguros.events.domain.services.setapplicationlog.SetApplicationLogRequest;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventRequest;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventResponse;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageRequest;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageResponse;


public class TestHelper {
	
	public static List<Parameter> getGenericParameters() {		
		List<Parameter> parametersList = new ArrayList<>();
		for (int i=0;i<10; i++) {
			Parameter parameter = new Parameter();
			switch (i) {
			case 0:
				parameter.setKey("policyId");
				parameter.setValue("50004PD011013577779");
				break;
			case 1:
				parameter.setKey("payId");
				parameter.setValue("qwertytrew2345654ert");
				break;
			case 2:
				parameter.setKey("amt");
				parameter.setValue("10160.14");
				break;
			case 3:
				parameter.setKey("externalUpdateId");
				parameter.setValue("54");
				break;
			case 4:
				parameter.setKey("policyValidityNumber");
				parameter.setValue("12");
				break;
			case 5:
				parameter.setKey("statusCode");
				parameter.setValue("0");
				break;
			case 6:
				parameter.setKey("statusDesc");
				parameter.setValue("CANCELLED");
				break;
			case 7:
				parameter.setKey("transactionTime");
				parameter.setValue("2021-03-01T15:06:01");
				break;
			case 8:
				parameter.setKey("paymentscheduleId");
				parameter.setValue("asdfghjhgfds");
				break;
			case 9:
				parameter.setKey("");
				parameter.setValue("");
				break;
			}
			parametersList.add(parameter);
		}
		return parametersList;
	}

	public static List<Detail> getEntityDetail() {		
		List<Detail> detailList = new ArrayList<>();
		for (int i=0;i<2; i++) {
			Detail detail = new Detail();
			switch (i) {
			case 0:
				detail.setKey("TIPO_IDENTIFICACION");
				detail.setValue("CC");
				break;
			case 1:
				detail.setKey("IDENTIFICACION");
				detail.setValue("123454324");
				break;
			}
			detailList.add(detail);
		}
		return detailList;
	}
	
	public static SetRecordEventResponse simulateSucessSetRecordEventResponse() {		
		SetRecordEventResponse setRecordEventResponse = new SetRecordEventResponse();
		setRecordEventResponse.setStatusCode("200");
		setRecordEventResponse.setStatusDesc("Transacción exiotsa");
		return setRecordEventResponse;
	}
	
	public static Void simulateVoidResponse() {
		return null;
	}
	
	public static SetRecordEventResponse simulateFailedSetRecordEventResponse() {		
		SetRecordEventResponse setRecordEventResponse = new SetRecordEventResponse();
		setRecordEventResponse.setStatusCode("501");
		setRecordEventResponse.setStatusDesc("Addition no es un valor valido");
		return setRecordEventResponse;
	}
	
	public static ResponseEntity<SetRecordRegistryQueueMessageResponse> simulateSucessSetRecordRegistryQueueMessageResponse() {		
		SetRecordRegistryQueueMessageResponse setRecordRegistryQueueMessageResponse = new SetRecordRegistryQueueMessageResponse();
		setRecordRegistryQueueMessageResponse.setStatusCode("200");
		setRecordRegistryQueueMessageResponse.setStatusDesc("Transacción exittsa");
		return new ResponseEntity<SetRecordRegistryQueueMessageResponse>(setRecordRegistryQueueMessageResponse, HttpStatus.CREATED);
	}
	
	public static ResponseEntity<SetRecordRegistryQueueMessageResponse> simulateFailedSetRecordRegistryQueueMessageResponse() {		
		SetRecordRegistryQueueMessageResponse setRecordRegistryQueueMessageResponse = new SetRecordRegistryQueueMessageResponse();
		setRecordRegistryQueueMessageResponse.setStatusCode("500");
		setRecordRegistryQueueMessageResponse.setStatusDesc("Ha ocurrido un error en la invocación");
		return new ResponseEntity<SetRecordRegistryQueueMessageResponse>(setRecordRegistryQueueMessageResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public static SetRecordEventRequest getSetRecordEventRequest() {		
		SetRecordEventRequest setRecordEventRequest = new SetRecordEventRequest();
		setRecordEventRequest.setSource("alfa-payments");
		setRecordEventRequest.setAction("Add");
		setRecordEventRequest.setTableName("transactions");
		setRecordEventRequest.setParametersList(getGenericParameters());
		return setRecordEventRequest;
	}
	
	public static SetApplicationLogRequest getSetApplicationLogRequest() throws JsonMappingException, JsonProcessingException {		
		ObjectMapper objectMapper = new ObjectMapper();
		SetApplicationLogRequest setApplicationLogRequest = objectMapper.readValue(getSQSSetApplicationLogRequest(), SetApplicationLogRequest.class);
		return setApplicationLogRequest;
	}
	
	public static SetApplicationLogRequest getBadSetApplicationLogRequest() throws JsonMappingException, JsonProcessingException {		
		ObjectMapper objectMapper = new ObjectMapper();
		SetApplicationLogRequest setApplicationLogRequest = objectMapper.readValue(getNotCompletedSQSSetApplicationLogRequest(), SetApplicationLogRequest.class);
		return setApplicationLogRequest;
	}
	
	public static String getSQSSetApplicationLogRequest() {		
		String message = " {\r\n"
				+ "  \"UserName\": \"dparada1\",\r\n"
				+ "  \"DateTime\": \"2021-03-25T17:59:31\",\r\n"
				+ "  \"Ip\": \"186.84.91.22\",\r\n"
				+ "  \"Channel\": \"load-attach-policy\",\r\n"
				+ "  \"Detail\": [\r\n"
				+ "    {\r\n"
				+ "      \"Key\": \"TIPO_IDENTIFICACION\",\r\n"
				+ "      \"Value\": \"CC\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"Key\": \"IDENTIFICACION\",\r\n"
				+ "      \"Value\": \"1023003712\"\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		return message;
	}
	
	public static String getBadSQSSetApplicationLogRequest() {		
		String message = " {\r\n"
				+ "  \"UserName\": \"\",\r\n"
				+ "  \"DateTime\": \"2021-03-25T17:59:31\",\r\n"
				+ "  \"Ip\": \"186.84.91.22\",\r\n"
				+ "  \"Channel\": \"load-attach-policy\",\r\n"
				+ "  \"Detail\": [\r\n"
				+ "    {\r\n"
				+ "      \"Key\": \"TIPO_IDENTIFICACION\",\r\n"
				+ "      \"Value\": \"CC\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"Key\": \"IDENTIFICACION\",\r\n"
				+ "      \"Value\": \"1023003712\"\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "";
		return message;
	}
	
	public static String getNotCompletedSQSSetApplicationLogRequest() {		
		String message = " {\r\n"
				+ "  \"UserName\": \"\",\r\n"
				+ "  \"DateTime\": \"2021-03-25T17:59:31\",\r\n"
				+ "  \"Ip\": \"186.84.91.22\",\r\n"
				+ "  \"Detail\": [\r\n"
				+ "    {\r\n"
				+ "      \"Key\": \"TIPO_IDENTIFICACION\",\r\n"
				+ "      \"Value\": \"CC\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"Key\": \"IDENTIFICACION\",\r\n"
				+ "      \"Value\": \"1023003712\"\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		return message;
	}	
	
	public static SetRecordEventRequest getBadSetRecordEventRequest() {		
		SetRecordEventRequest setRecordEventRequest = new SetRecordEventRequest();
		setRecordEventRequest.setSource("alfa-payments");
		setRecordEventRequest.setAction("Addition");
		setRecordEventRequest.setTableName("transactions");
		setRecordEventRequest.setParametersList(getGenericParameters());
		return setRecordEventRequest;
	}
	
	public static SetRecordRegistryQueueMessageRequest getSetRecordRegistryQueueMessageRequest() {		
		SetRecordRegistryQueueMessageRequest setRecordRegistryQueueMessageRequest = new SetRecordRegistryQueueMessageRequest();
		setRecordRegistryQueueMessageRequest.setSource("alfa-payments");
		setRecordRegistryQueueMessageRequest.setAction("Add");
		setRecordRegistryQueueMessageRequest.setTableName("transactions");
		setRecordRegistryQueueMessageRequest.setParametersList(getGenericParameters());
		return setRecordRegistryQueueMessageRequest;
	}
	
	public static ApplicationLog getApplicationLog() {		
		ApplicationLog applicationLog = new ApplicationLog();
		applicationLog.setChannel("load-attach-policy");
		applicationLog.setDateTime("2021-03-25T17:59:31");
		applicationLog.setIp("186.84.91.22");
		applicationLog.setUserName("dparada1");
		applicationLog.setDetail(getEntityDetail());
		return applicationLog;
	}
	
	public static ApplicationLog getBadApplicationLog() {		
		ApplicationLog applicationLog = new ApplicationLog();
		applicationLog.setDateTime("2021-03-25T17:59:31");
		applicationLog.setIp("186.84.91.22");
		applicationLog.setUserName("dparada1");
		applicationLog.setDetail(getEntityDetail());
		return applicationLog;
	}
	
	public static SetRecordRegistryQueueMessageRequest getBadSetRecordRegistryQueueMessageRequest() {		
		SetRecordRegistryQueueMessageRequest setRecordRegistryQueueMessageRequest = new SetRecordRegistryQueueMessageRequest();
		setRecordRegistryQueueMessageRequest.setSource("alfa-payments");
		setRecordRegistryQueueMessageRequest.setAction("Add");
		setRecordRegistryQueueMessageRequest.setTableName("transactions");
		return setRecordRegistryQueueMessageRequest;
	}


}
