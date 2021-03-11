package co.com.alfaseguros.events.domain.generic;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Parameter implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Key is required")
	@NotNull(message = "Key is required")
	@JsonProperty("Key")
	@Pattern(regexp = "[A-Za-z0-9_]+", message = "Key is not valid")
	private String key;
	
	@JsonProperty("Value")
	private String value;
}