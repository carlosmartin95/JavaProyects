package OneParameter;

import Bytecode.ByteCode;
/**
 * Esta clase contiene al bytecode IFLEQ y tendra todos los metodos necesarios para su correcto funcionamiento
 * */
public class IfLeq extends ConditionalJumps {

	/**
	 * Constructoras
	 * */
	public IfLeq(){}
	public IfLeq(int parametro) {
		super(parametro);
	}


	/**
	 * Este metodo se encarga de buscar la palabra ifleq en el string 1 si es asi crea un bytecode del tipo ifleq y 
	 * convierte a int el string2 , si no encuetra la palabra deseada devuelve false
	 * */
	protected ByteCode parseAux(String string1, String string2) {
		if(string1.equalsIgnoreCase("IFLEQ")){
			return new IfLeq(Integer.parseInt(string2));
		}
		else return null;
	}
/**
 * Este metodo se encarga de devolver cual es el nombre del bytecode actual
 * */
	protected String toStringAux() {
		// TODO Auto-generated method stub
		return "IFLE";
	}
/**
 * ESte metodo se encarga de verificar que n2 sea mayor o igual que n1
 * */
	protected boolean compare(int n1, int n2) {
		
		if (n2 >= n1)
			return true;
		else return false;
	}

}
