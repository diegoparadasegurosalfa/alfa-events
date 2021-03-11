package co.com.alfaseguros.meadiatorosb.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import co.com.alfaseguros.events.domain.generic.Parameter;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventRequest;


public class TestHelper {
/*	
	public static Parameter getIndetification() {		
		Parameter parameter = new Parameter();
		parameter.setIdentificationNumber("12354852");
		parameter.setIdetificationType("CC");
		return parameter;
	}
	
	public static Parameter getIndetificationBad() {		
		Parameter parameter = new Parameter();
		parameter.setIdentificationNumber("123548529888898989898989898989898989898989");
		parameter.setIdetificationType("CC");
		return parameter;
	}
	
	public static List<ParameterList> getParameterListOut() {
		List<ParameterList> parameterList = new ArrayList<>();
		parameterList.add(new ParameterList("PREMIUM","31,200.00"));
		parameterList.add(new ParameterList("RENEWAL_NUMBER","13"));
		return parameterList;
	}
	
	public static List<co.com.alfaseguros.events.domain.services.setpolicyeventqueue.ParameterList> getParameterListIn() {
		List<co.com.alfaseguros.events.domain.services.setpolicyeventqueue.ParameterList> parameterList = new ArrayList<>();
		parameterList.add(new co.com.alfaseguros.events.domain.services.setpolicyeventqueue.ParameterList("PREMIUM","31,200.00"));
		parameterList.add(new co.com.alfaseguros.events.domain.services.setpolicyeventqueue.ParameterList("RENEWAL_NUMBER","13"));
		return parameterList;
	}
	
	public static List<Policy> getListPolicies(){
		final List<Policy> policiesList = new ArrayList<>();
		policiesList.add(new Policy("155PD1255225", "01-01-2021"));		
		policiesList.add(new Policy("155PD1255226", "02-01-2021"));
		return policiesList;
	}
	
	public static SetRecordEventRequest getRequestGetPolicies() {
		SetRecordEventRequest request = new SetRecordEventRequest();
		request.setIdentification(getIndetification());
		return request;
	}
	
	public static RequestPolicies getRequestPolicies() {
		RequestPolicies request = new RequestPolicies();
		request.setToken("545484851ggfy7fdf");
		request.setIdentification(getIndetificationBad());
		return request;
	}
	
	public static RequestPolicies getRequestPoliciesIsNull() {
		RequestPolicies request = new RequestPolicies();
		request.setToken("545484851ggfy7fdf");		
		return request;
	}
	
	public static RequestSetPolicyEventQueue getRequestSetPolicyEventQueue() {
		RequestSetPolicyEventQueue request = new RequestSetPolicyEventQueue();
		request.setPolEventType("POLICY_RENEWAL");
		request.setPolEventSource("CORE");
		request.setPolEventDestination("PAYMENTS");
		request.setPolicyId("50004DP015678920901");
		request.setValidityNumber("13");
		request.setStatusCode("200");
		request.setStatusDesc("OK");
		request.setParameterList(getParameterListIn());
		return request;
	}
	
	public static RequestSetPolicyEventQueue getRequestSetPolicyEventQueueIsNull() {
		RequestSetPolicyEventQueue request = new RequestSetPolicyEventQueue();
		request.setPolEventType("POLICY_RENEWAL");
		request.setPolEventSource("CORE");
		request.setPolEventDestination("PAYMENTS");
		request.setPolicyId("50004DP015678920901");
		request.setValidityNumber("13");
		request.setStatusCode("200");
		request.setStatusDesc("OK");
		return request;
	}
	
	public static RequestPolicyEventQueue getRequestPolicyEventQueue() {
		RequestPolicyEventQueue request = new RequestPolicyEventQueue();
		request.setPolEventType("POLICY_RENEWAL");
		request.setPolEventSource("CORE");
		request.setPolEventDestination("PAYMENTS");
		request.setPolicyId("50004DP015678920901");
		request.setValidityNumber("13");
		request.setStatusCode("200");
		request.setStatusDesc("OK");
		request.setToken("545484851ggfy7fdf");
		request.setParameterList(getParameterListOut());
		return request;
	}
	
	public static RequestPolicyEventQueue getRequestRequestPolicyEventQueueIsNull() {
		RequestPolicyEventQueue request = new RequestPolicyEventQueue();
		request.setPolEventType("POLICY_RENEWAL");
		request.setPolEventSource("CORE");
		request.setPolEventDestination("PAYMENTS");
		request.setPolicyId("50004DP015678920901");
		request.setValidityNumber("13");
		request.setStatusCode("200");
		request.setStatusDesc("OK");
		//request.setToken("545484851ggfy7fdf");
		return request;
	}
	
	public static ResponseEntity<ResponseToken> buildRespToken() {
		
		final HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		final ResponseToken response = new ResponseToken();
		response.setHttpCode("200");
		response.setSessKey("ssdffff848415dss");
		response.setToken("ssdffff848415dss");
			
		return (ResponseEntity<ResponseToken>) generateTokeEntity(response, HttpStatus.OK);
	}
	
	public static ResponseEntity<ResponseToken> buildRespNotToken() {
			
		final HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		final ResponseToken response = new ResponseToken();
		response.setHttpCode("200");			
			
		return (ResponseEntity<ResponseToken>) generateTokeEntity(response, HttpStatus.OK);
	}
	
	public static ResponseEntity<ResponseGetPolicies> buildRespGetPolicies() {
			
		final HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		final ResponseGetPolicies response = new ResponseGetPolicies();
		response.setStatusCode("200");
		
		List<PoliciesList> policiesList = new ArrayList<>();
		final PoliciesList policy = new PoliciesList();
		policy.setPolicyId("1258AC747");
		policy.setPolicyIssuingDate("01-21-2020");
		policiesList.add(policy);
		response.setPoliciesList(policiesList);
		
		return (ResponseEntity<ResponseGetPolicies>) generateTokeEntity(response, HttpStatus.OK);
	}
	
	public static ResponseEntity<ResponsePolicyEventQueue> buildRespSetPolicyEventQueueMessage() {
		
		final HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		final ResponsePolicyEventQueue response = new ResponsePolicyEventQueue();
		response.setStatusCode("200");
		
		return (ResponseEntity<ResponsePolicyEventQueue>) generateTokeEntity(response, HttpStatus.OK);
	}
	
	public static ResponseEntity<ResponseGetPolicies> buildRespGetPoliciesBadCode() {
		
		final HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		final ResponseGetPolicies response = new ResponseGetPolicies();
		response.setStatusCode("300");
		
		return (ResponseEntity<ResponseGetPolicies>) generateTokeEntity(response, HttpStatus.OK);
	}
	
	public static Credential buildCredential() {
		Credential credential = new Credential();
		credential.setOsbPassword("pr485515");
		credential.setOsbUser("ian");
		return credential;
	}
	
	private static ResponseEntity<?> generateTokeEntity(Object t, HttpStatus httpStatus) {
		final HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<>(
				t,
			    header, 
			    httpStatus
			);		
	}
*/
}
