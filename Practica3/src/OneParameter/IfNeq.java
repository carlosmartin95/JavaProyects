package OneParameter;

import Bytecode.ByteCode;

/**
 * Esta clase contiene al bytecode IFNEQ y tendra todos los metodos necesarios para su correcto funcionamiento
 * */
public class IfNeq extends ConditionalJumps {
	/**
	 * Constructoras
	 * */
	public IfNeq(){}
	public IfNeq(int parametro) {
		super(parametro);
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * Este metodo se encarga de buscar la palabra ifneq en el string 1 si es asi crea un bytecode del tipo ifneq y 
	 * convierte a int el string2 , si no encuetra la palabra deseada devuelve false
	 * */

	protected ByteCode parseAux(String string1, String string2) {
		if(string1.equalsIgnoreCase("IFNEQ")){
			return new IfNeq(Integer.parseInt(string2));
		}
		else return null;
	}
/**
 * Este metodo devuelve el nombre del bytecode actual
 * */

	protected String toStringAux() {
		// TODO Auto-generated method stub
		return "IFNEQ";
	}
/**
 * Este metodo se encarga de verificar que n1 y n2 son dos numeros distintos
 * */

	protected boolean compare(int n1, int n2) {
		if (n2 != n1)
			return false;
		else return true;
	}

}
