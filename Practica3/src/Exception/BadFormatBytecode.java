package Exception;
/**
 * Esta clase hereda de Exception y se encarga de crear una excepcion del tipo BadFormatException llamando a la constructora de su padre 
 * */
public class BadFormatBytecode extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadFormatBytecode(String mensaje) {
		super(mensaje);
	}

}
