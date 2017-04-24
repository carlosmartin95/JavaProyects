package Instruction;

import Bytecode.Load;
import Bytecode.Out;
import Exception.ArrayException;
import Main.Compiler;

public class Write implements Instruction{
	/**
	 * Atributo varName
	 */
	private String varName;
	/**
	 * Constructoras
	 */
	public Write(String write) {
		this.varName = write;
	}
	public Write() {}

	/**
	 * Parseamos el array de Strings que nos llega,comprabando que
	 * la primera palabra del array sea igual a WRITE.
	 * Si es correcto devolvemos esta Instruccion,sino devolvemos false;
	 */
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		
		if(words[0].equalsIgnoreCase("WRITE")){
			lexParser.increaseProgramCounter();
			return  new Write(words[1]);
		}
		else return null;	
	}
	/**
	 * Añadimos al array de ByteCodes de Compiler las 
	 * instrucciones LOAD y OUT.
	 */
	public void compile(Compiler compiler) throws ArrayException {
		 compiler.addByteCode(new Load(compiler.getIndex(this.varName)));    
		 compiler.addByteCode(new Out()); 
	}
	/**
	 * Devolvemos el String
	 */
	public String toString(){
		return "Write("+this.varName+")";
	}
}
