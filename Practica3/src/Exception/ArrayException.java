package Exception;
/**
 * Esta clase hereda de Exception y se encarga de crear una excepcion del tipo ArrayException llamando a la constructora de su padre 
 * */
public class ArrayException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayException(String mensaje) {
		super(mensaje);
	}

}
