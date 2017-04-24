package Exception;
/**
 * Esta clase hereda de Exception y se encarga de crear una excepcion del tipo LexicalAnalysisException llamando a la constructora de su padre 
 * */
public class LexicalAnalysisException extends Exception{

	private static final long serialVersionUID = 1L;

	public LexicalAnalysisException(String mensaje) {
		super(mensaje);
	}

}
