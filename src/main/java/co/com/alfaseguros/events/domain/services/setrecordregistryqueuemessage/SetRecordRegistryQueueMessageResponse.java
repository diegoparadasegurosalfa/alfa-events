package co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SetRecordRegistryQueueMessageResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@NotNull
	@JsonProperty("StatusCode")
	@Pattern(regexp = "[0-9]+")
	private String statusCode;
	
	@NotBlank
	@NotNull
	@JsonProperty("StatusDesc")
	@Pattern(regexp = "[A-Za-z_]+")
	private String statusDesc;
	
}
