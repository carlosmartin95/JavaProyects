package OneParameter;

import Bytecode.ByteCode;
/**
 * Esta clase contiene al bytecode IFLE y tendra todos los metodos necesarios para su correcto funcionamiento
 * */
public class IfLe extends ConditionalJumps {
/**
 * Constructoras
 * */
	public IfLe(){}
	public IfLe(int parametro) {
		super(parametro);
		// TODO Auto-generated constructor stub
	}
/**
 * Este metodo se encarga de buscar la palabra ifle en el string 1 si es asi crea un bytecode del tipo ifle y 
 * convierte a int el string2 , si no encuetra la palabra deseada devuelve false
 * */

	protected ByteCode parseAux(String string1, String string2) {
		if(string1.equalsIgnoreCase("IFLE")){
			return new IfLe(Integer.parseInt(string2));
		}
		else return null;
	}
/**
 * Devuelve el nombre del bytecode actual
 * */

	protected String toStringAux() {
		return "IFLE";
	}
/**
 * Este metodo se encarga de mirar que el parametro n2 sea mayor  que n1 
 * */

	protected boolean compare(int n1, int n2) {
		if (n2 > n1)
			return true;
		else return false;
	}

}
