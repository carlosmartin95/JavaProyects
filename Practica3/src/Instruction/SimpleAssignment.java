
package Instruction;

import Bytecode.Store;
import Exception.ArrayException;
import Main.Compiler;

public class SimpleAssignment implements Instruction {
	/**
	 * Atributos
	 */
	private String var_name;
	private Term rhs;
	
	/**
	 * Constructoras
	 */
	public SimpleAssignment(){}
	public SimpleAssignment(String string, Term term) {
		this.var_name = string;
		this.rhs = term;
	}
	
	/**
	 * Parseamos el array de Strings que nos llega, comprobamos que su longitud
	 * sea igual a 3 y que su primer término este entre la A y la Z.
	 * A continuación parseamos su 3 componente para ver que tipo de Termino es.
	 * Si es correcto devolvemos esta Instruccion,sino devolvemos false;
	 */
	public Instruction lexParse(String[] words, LexicalParser lexParser){
		
		char letra= words[0].charAt(0);
		if (words.length == 3 && words[1].equals("=") && letra <='z' && letra >= 'a') {
			Term term = TermParser.parse(words[2]);
			lexParser.increaseProgramCounter();
			return new SimpleAssignment(words[0], term);
		}
		else return null;
	}
	/**
	 * Añadimos al array de Bytecode de la clase Compiler el ByteCode 
	 * asignado al atributo privado rhs y STORE. A continuación añadimos la letra
	 * al array de variable
	 */
	public void compile (Compiler compiler) throws ArrayException{
		compiler.addByteCode(rhs.compile(compiler));
		compiler.anadirletra(this.var_name);
		compiler.addByteCode(new Store(compiler.getIndex(this.var_name)));
	}
}
