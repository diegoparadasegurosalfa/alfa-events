package co.com.alfaseguros.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.context.annotation.Import;

import co.com.alfaseguros.commons.aws.DynamoDBConfig;
import co.com.alfaseguros.commons.aws.SQSConfig;
import co.com.alfaseguros.commons.exceptions.ExceptionAlfaHandler;

@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
@Import({DynamoDBConfig.class,SQSConfig.class,ExceptionAlfaHandler.class})
public class AlfaEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlfaEventsApplication.class, args);
	}
    
}
