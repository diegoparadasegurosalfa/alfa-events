package co.com.alfaseguros.events.domain.services.setrecordregistryqueuemessage;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import co.com.alfaseguros.events.domain.generic.Parameter;
import lombok.Data;

@Data
public class SetRecordRegistryQueueMessageRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@NotNull
	@JsonProperty("Source")
	private String source;
	
	@NotBlank
	@NotNull
	@JsonProperty("Action")
	@Pattern(regexp = "[A-Za-z_]+")
	private String action;
	
	@NotBlank
	@NotNull
	@JsonProperty("TableName")
	@Pattern(regexp = "[A-Za-z_]+")
	private String tableName;
	
	@NotNull
	@JsonProperty("ParametersList")
	private List<Parameter> parametersList;
	
}
