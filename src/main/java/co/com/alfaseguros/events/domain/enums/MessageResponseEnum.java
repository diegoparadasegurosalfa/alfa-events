package co.com.alfaseguros.events.domain.enums;

public enum MessageResponseEnum {
	
	OK (201, "Transaccion exitosa"),
	SYSTEM_ERROR (500, "Error interno del sistema"),
    SERVICE_CALL_ERROR (700, "Error en la respuesta del Backend"), 
    DATABASE_CALL_ERROR (701, "Error en el registro de los datos"),
    DATA_VALIDATION (400, "Petición incorrecta");  
    
	private final int code;   
    private final String message; 
     
    private MessageResponseEnum(int code, String message){
        this.code=code;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
