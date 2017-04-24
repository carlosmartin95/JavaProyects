package Exception;
/**
 * Esta clase hereda de Exception y se encarga de crear una excepcion del tipo DivisionByZero llamando a la constructora de su padre 
 * */
public class DivisionByZero extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DivisionByZero(String mensaje) {
		super(mensaje);
	}

}
