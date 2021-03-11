package co.com.alfaseguros.events.exceptions;

public abstract class ExceptionAlfa extends Exception{

	private static final long serialVersionUID = 1L;
	
	private final int code;
	
    protected ExceptionAlfa(int code, String message){
        super(message);
    	this.code=code;
    }

    public int getCode() {
        return code;
    }
}
