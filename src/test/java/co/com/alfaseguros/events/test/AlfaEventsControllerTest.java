package co.com.alfaseguros.events.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import co.com.alfaseguros.events.AlfaEventsApplication;
import co.com.alfaseguros.events.controller.HealthCheckController;

@ContextConfiguration(classes = AlfaEventsApplication.class)
@SpringBootTest
@ActiveProfiles("test")
class AlfaEventsControllerTest {

	
	private static final Logger log = LoggerFactory.getLogger(AlfaEventsControllerTest.class);
	
	private HealthCheckController healthCheckcontroller;
	
	@BeforeEach
	public void setUp() {
		healthCheckcontroller = new HealthCheckController();
	}
	
	@Test
	void whenSuccessHealthCheck() throws Exception {		
		ResponseEntity<String> response = healthCheckcontroller.healthCheck();		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}