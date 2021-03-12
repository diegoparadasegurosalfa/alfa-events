package co.com.alfaseguros.events.config;

import java.io.IOException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoggingInterceptor implements ClientHttpRequestInterceptor{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	 
	@Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }
 
    private void logRequest(HttpRequest request, byte[] body) {
        if (log.isDebugEnabled()) {
        	log.debug("===========================request begin================================================");
            log.debug("URI         : {}", request.getURI());
            log.debug("Method      : {}", request.getMethod());
            log.debug("Headers     : {}", request.getHeaders());      
            log.debug("Request body: {}", new String(body, Charset.defaultCharset()));
            log.debug("==========================request end================================================");
        }
    }
 
    private void logResponse(ClientHttpResponse response) throws IOException {
        if (log.isDebugEnabled()) {
        	log.debug("============================response begin==========================================");
            log.debug("Status code  : {}", response.getStatusCode());
            log.debug("Status text  : {}", response.getStatusText());
            log.debug("Headers      : {}", response.getHeaders());
            log.debug("Response body: {}", json(StreamUtils.copyToString(response.getBody(), Charset.defaultCharset())));
            log.debug("=======================response end=================================================");
        }
    }
    
    private String json(String object) {
    	try {
    		if(object != null && !object.isEmpty()) {
		    	ObjectMapper mapper = new ObjectMapper();        	
		    	Object json = mapper.readValue(object, Object.class);
				return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    		}
		} catch (JsonMappingException e) {
			log.error("Json Mapping Exception: ", e);
		} catch (JsonProcessingException e) {
			log.error("Json Processing Exception: ", e);
		}        	
    	return "";
    }
}
