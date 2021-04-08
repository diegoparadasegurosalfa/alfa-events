package co.com.alfaseguros.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
@ComponentScan("co.com.alfaseguros.commons.aws.DynamoDBConfig")
public class AlfaEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlfaEventsApplication.class, args);
	}
    
}
