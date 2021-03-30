package co.com.alfaseguros.events.domain.services.setapplicationlog;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.com.alfaseguros.events.domain.generic.Detail;
import lombok.Data;

@Data
public class SetApplicationLogRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("UserName")
	private String userName;
	
	@JsonProperty("DateTime")
	private String dateTime;

	@JsonProperty("Ip")
	private String ip;
	
	@JsonProperty("Channel")
	private String channel;
	
	@JsonProperty("Detail")
	private List<Detail> detail;
	
}
