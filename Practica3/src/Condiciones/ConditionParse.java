package Condiciones;

import Exception.LexicalAnalysisException;
import Instruction.LexicalParser;

public class ConditionParse {

	private final static Condition[] condicion = {new Equal(),new NotEqual(), new Less(), new LessEq()}; 
	/**
	* Este metodo se encarga de identificar el tipo de condicion que recibimos por parametro y creamos una condicion
	* en funcion del tipo que sea.Si la condicion no existe en el array devolveremos null
	 * @throws LexicalAnalysisException 
	*/
	public static Condition parse(String t1, String op, String t2, LexicalParser parser) throws LexicalAnalysisException{
	
		//Si las instrucionces heredan de cond.Jumps que heredan de ByteCode, la condicion que devolvemos es
		// tambien un ByteCode?
		
		int i=0;
		boolean found = false;
		Condition c = null;
		
		while (i < condicion.length && !found){
			c = condicion[i].parse(t1, op, t2, parser);
			if (c!=null) 
				found=true;
			i++;
		}
			
		if (c == null)
			throw new LexicalAnalysisException("El operador es erroneo");
		else return c;

	}
}
