package Instruction;

import Bytecode.Halt;
import Exception.ArrayException;
import Main.Compiler;

public class Return implements Instruction{

	public Return() {}

	/**
	 *Parseamos el array de string que nos llega, comprobando
	 *que es igual a RETURN. Si es correcto devolvemos esta Instruccion,
	 * sino devolvemos false;
	 */
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		
		if (words[0].equalsIgnoreCase("RETURN")){
			lexParser.increaseProgramCounter();
			return  new Return();
		}
		else return null;
	}
	/**
	 * Añadimos el ByteCode Halt al array de ByteCode en Compiler
	 */
	public void compile(Compiler compiler) throws ArrayException {
		compiler.addByteCode(new Halt());
	}

}
