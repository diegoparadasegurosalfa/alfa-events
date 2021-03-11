package co.com.alfaseguros.meadiatorosb.test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import co.com.alfaseguros.events.AlfaMediatorosbApplication;

@ContextConfiguration(classes = AlfaMediatorosbApplication.class)
@SpringBootTest
@ActiveProfiles("test")
class AlfaMediatorServiceTest {
	
}
