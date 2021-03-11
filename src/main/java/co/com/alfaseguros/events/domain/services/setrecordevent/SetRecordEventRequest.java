package co.com.alfaseguros.events.domain.services.setrecordevent;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import co.com.alfaseguros.events.domain.generic.Parameter;
import lombok.Data;

@Data
public class SetRecordEventRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Source is required")
	@NotNull(message = "Source is required")
	@JsonProperty("Source")
	private String source;
	
	@NotBlank(message = "Action is required")
	@NotNull(message = "Action is required")
	@JsonProperty("Action")
	@Pattern(regexp = "[A-Za-z_]+", message = "Action is not valid")
	private String action;
	
	@NotBlank(message = "TableName is required")
	@NotNull(message = "TableName is required")
	@JsonProperty("TableName")
	@Pattern(regexp = "[A-Za-z_]+", message = "TableName is not valid")
	private String tableName;
	
	@NotNull(message = "ParametersList is required")
	@JsonProperty("ParametersList")
	private List<@Valid Parameter> parametersList;
	
}
