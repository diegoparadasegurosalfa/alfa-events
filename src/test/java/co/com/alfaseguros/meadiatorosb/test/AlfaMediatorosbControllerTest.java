package co.com.alfaseguros.meadiatorosb.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import co.com.alfaseguros.events.AlfaMediatorosbApplication;
import co.com.alfaseguros.events.controller.EventsController;
import co.com.alfaseguros.events.domain.enums.MessageResponseEnum;
import co.com.alfaseguros.events.domain.generic.Parameter;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventRequest;
import co.com.alfaseguros.events.exceptions.ExceptionAlfa;
import co.com.alfaseguros.events.services.ServiceExecution;
import co.com.alfaseguros.meadiatorosb.helper.TestHelper;

@ContextConfiguration(classes = AlfaMediatorosbApplication.class)
@SpringBootTest
@ActiveProfiles("test")
class AlfaMediatorosbControllerTest {
	

}
