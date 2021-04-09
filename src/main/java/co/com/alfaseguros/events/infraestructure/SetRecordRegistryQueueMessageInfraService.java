package co.com.alfaseguros.events.infraestructure;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageRequest;
import co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage.SetRecordRegistryQueueMessageResponse;
import co.com.alfaseguros.commons.enums.MessageResponseEnum;
import co.com.alfaseguros.commons.exceptions.ExceptionAlfa;
import co.com.alfaseguros.commons.exceptions.bussiness.ServerExceptionAlfa;
import lombok.RequiredArgsConstructor;

@Component("setRecordRegistryQueueMessageInfraService")
@RequiredArgsConstructor
public class SetRecordRegistryQueueMessageInfraService implements InfraService<SetRecordRegistryQueueMessageRequest, SetRecordRegistryQueueMessageResponse> {
		
	private final RestTemplate template;
	
	@Value("${alfa.services.mediatorosb.url.setrecordregistryqueuemessage}")
	private String filteredUrl;
	
	@Override
	public ResponseEntity<SetRecordRegistryQueueMessageResponse> callService(SetRecordRegistryQueueMessageRequest data) throws ExceptionAlfa {
		try {
			HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    HttpEntity<SetRecordRegistryQueueMessageRequest> request = new HttpEntity<>(data, headers);
			return this.template.exchange(this.filteredUrl,
										   HttpMethod.POST, 
										   request, 
										   SetRecordRegistryQueueMessageResponse.class);
		} catch (Exception exception) {
			throw new ServerExceptionAlfa(MessageResponseEnum.SERVICE_CALL_ERROR, exception.getMessage());
		}
	}
}