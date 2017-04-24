package Exception;
/**
 * Esta clase hereda de Exception y se encarga de crear una excepcion del tipo StackException llamando a la constructora de su padre 
 * */
public class StackException extends Exception{

	private static final long serialVersionUID = 1L;

	public StackException(String mensaje) {
		super(mensaje);
	}
}
