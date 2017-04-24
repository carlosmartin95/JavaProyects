package OneParameter;

import Bytecode.ByteCode;


public class IfEq extends ConditionalJumps{
	/**
	 * Constructora sin parametros para que el array de bytecodes no nos de guerra
	 * */
	public IfEq(){}
	/**
	 * constructora que le llega un parametro
	 * @param parametro
	 */
	public IfEq(int parametro) {
		super(parametro);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * comparamos si los parametros son iguales
	 * @Override
	 */
	protected boolean compare(int n1, int n2) {
		
		if (n2 == n1)
			return true;
		else return false;
	}

	/**
	 * parseamos los strings y compramos que es el correcto
	 */

	protected ByteCode parseAux(String string1, String string2) {
		if(string1.equalsIgnoreCase("IFEQ")){
			return new IfEq(Integer.parseInt(string2));
		}
		else return null;
	}
/**
 * ESte metodo devuelve el nombre del bytecode actual
 * */

	protected String toStringAux() {
		return "IFEQ";
	}

}
