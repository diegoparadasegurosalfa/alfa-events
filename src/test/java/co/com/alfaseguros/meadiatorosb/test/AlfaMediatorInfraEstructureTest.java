package co.com.alfaseguros.meadiatorosb.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import co.com.alfaseguros.events.AlfaMediatorosbApplication;
import co.com.alfaseguros.meadiatorosb.helper.TestHelper;


@ContextConfiguration(classes = AlfaMediatorosbApplication.class)
@SpringBootTest
@ActiveProfiles("test")
class AlfaMediatorInfraEstructureTest {
	
}
