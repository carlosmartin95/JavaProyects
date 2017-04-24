package Exception;
/**
 * Esta clase hereda de Exception y se encarga de crear una excepcion del tipo ExecutionError llamando a la constructora de su padre 
 * */
public class ExecutionError extends Exception{

	private static final long serialVersionUID = 1L;

	public ExecutionError(String mensaje) {
		super(mensaje);
	}

}
