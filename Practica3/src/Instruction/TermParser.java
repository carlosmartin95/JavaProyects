package Instruction;

import Bytecode.ByteCode;

public class TermParser {

	/**
	 * Array de Terminos.
	 */
	private final static Term[] termino = { new Variable(), new Number()};
	
	/**
	 * Parsemos el String que nos llega por parametro
	 * coincide con una Variable o un Numero. Devolvemos null 
	 * sino se ha encontrado ningun termino, sino devolvemos el termino correspondiente.
	 */
	public static Term parse(String linea) {
		
		int i=0;
		boolean found = false;
		Term t = null;
		linea = linea.trim();
			
		while (i < termino.length && !found){
			t = termino[i].parse(linea);
			if (t!=null) 
				found=true;
			i++;
		}
		return t;
	}
}
