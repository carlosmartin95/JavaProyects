package Instruction;

import Exception.ArrayException;
import Exception.LexicalAnalysisException;

public class ParserInstruction {
		
	/**
	 * Array con todas las instrucciones
	 */
	private final static Instruction[] instruccion = { new While(), new IfThen(), new Return(), new Write(), new CompoundAssignment(), new SimpleAssignment()};
	
	/**
	 * Recorremos el array de instrucciones, llamando al parse de cada instruccion
	 * hasta la que encontramos devolviendo la instruccion, sino devolvemos null.
	 */
	public static Instruction lexParse(String line, LexicalParser lexParser) throws LexicalAnalysisException,ArrayException {
		
		int i=0;
		boolean found = false;
		Instruction t = null;
		String []words = line.trim().split(" ");
			
		while (i < instruccion.length && !found){
			t = instruccion[i].lexParse(words, lexParser);
			
			if (t!=null) 
				found=true;
			i++;
		}
		if (t == null)
			throw new LexicalAnalysisException ("No se ha encontrado la instruccion");
		else return t;
	}
}