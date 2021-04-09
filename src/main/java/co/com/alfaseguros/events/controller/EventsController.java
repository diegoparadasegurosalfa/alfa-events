package co.com.alfaseguros.events.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventRequest;
import co.com.alfaseguros.events.domain.services.setrecordevent.SetRecordEventResponse;
import co.com.alfaseguros.commons.exceptions.ExceptionAlfa;
import co.com.alfaseguros.events.services.ServiceExecution;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("events/api/v1")
@RequiredArgsConstructor
@Validated
public class EventsController {
	
	@Qualifier("setRecordEventServiceExecution")
	private final ServiceExecution<SetRecordEventRequest, SetRecordEventResponse> setRecordEventServiceExecution;	
	
	@PostMapping("/setrecordevent")
	public ResponseEntity<SetRecordEventResponse> setRecordEvent(@RequestBody(required = true) @Valid SetRecordEventRequest setRecordEventRequest) throws ExceptionAlfa {
		return new ResponseEntity<>(setRecordEventServiceExecution.processOperation(setRecordEventRequest), HttpStatus.CREATED);
	}
	

	
}
