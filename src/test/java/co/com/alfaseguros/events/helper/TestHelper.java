package co.com.alfaseguros.events.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import co.com.alfaseguros.events.domain.generic.Parameter;
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

	public static SetRecordEventResponse simulateSucessSetRecordEventResponse() {		
		SetRecordEventResponse setRecordEventResponse = new SetRecordEventResponse();
		setRecordEventResponse.setStatusCode("201");
		setRecordEventResponse.setStatusDesc("Transacción exiotsa");
		return setRecordEventResponse;
	}
	
	public static ResponseEntity<SetRecordRegistryQueueMessageResponse> simulateSucessSetRecordRegistryQueueMessageResponse() {		
		SetRecordRegistryQueueMessageResponse setRecordRegistryQueueMessageResponse = new SetRecordRegistryQueueMessageResponse();
		setRecordRegistryQueueMessageResponse.setStatusCode("201");
		setRecordRegistryQueueMessageResponse.setStatusDesc("Transacción exiotsa");
		return new ResponseEntity<SetRecordRegistryQueueMessageResponse>(setRecordRegistryQueueMessageResponse, HttpStatus.CREATED);
	}
	
	public static ResponseEntity<SetRecordRegistryQueueMessageResponse> simulateFailedSetRecordRegistryQueueMessageResponse() {		
		SetRecordRegistryQueueMessageResponse setRecordRegistryQueueMessageResponse = new SetRecordRegistryQueueMessageResponse();
		setRecordRegistryQueueMessageResponse.setStatusCode("500");
		setRecordRegistryQueueMessageResponse.setStatusDesc("Ha ocurrido un error en la invocación");
		return new ResponseEntity<SetRecordRegistryQueueMessageResponse>(setRecordRegistryQueueMessageResponse, HttpStatus.CREATED);
	}
	
	public static SetRecordEventRequest getSetRecordEventRequest() {		
		SetRecordEventRequest setRecordEventRequest = new SetRecordEventRequest();
		setRecordEventRequest.setSource("alfa-payments");
		setRecordEventRequest.setAction("Add");
		setRecordEventRequest.setTableName("transactions");
		setRecordEventRequest.setParametersList(getGenericParameters());
		return setRecordEventRequest;
	}
	
	public static SetRecordEventRequest getBadSetRecordEventRequest() {		
		SetRecordEventRequest setRecordEventRequest = new SetRecordEventRequest();
		setRecordEventRequest.setSource("alfa-payments");
		setRecordEventRequest.setAction("Add");
		setRecordEventRequest.setTableName("transactions");
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
	
	public static SetRecordRegistryQueueMessageRequest getBadSetRecordRegistryQueueMessageRequest() {		
		SetRecordRegistryQueueMessageRequest setRecordRegistryQueueMessageRequest = new SetRecordRegistryQueueMessageRequest();
		setRecordRegistryQueueMessageRequest.setSource("alfa-payments");
		setRecordRegistryQueueMessageRequest.setAction("Add");
		setRecordRegistryQueueMessageRequest.setTableName("transactions");
		return setRecordRegistryQueueMessageRequest;
	}


}
