package co.com.alfaseguros.events.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import co.com.alfaseguros.commons.interceptors.LoggingInterceptor;

@Configuration
@EnableAutoConfiguration
@ComponentScan("co.com.alfaseguros.commons.interceptors")
public class Configurations {
	
	@Bean("restTemplate")
    public RestTemplate restTemplate() {		
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new LoggingInterceptor());
		RestTemplate template = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		template.setInterceptors(interceptors);
        return template;
    }

}
