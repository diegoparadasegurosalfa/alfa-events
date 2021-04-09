package co.com.alfaseguros.events.domain.generic;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Detail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Key")
	private String key;
	
	@JsonProperty("Value")
	private String value;
}