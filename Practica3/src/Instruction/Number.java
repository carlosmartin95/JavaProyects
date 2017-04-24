package Instruction;

import Bytecode.ByteCode;
import Bytecode.Push;
import Main.Compiler;

public class Number implements Term{
	/**
	 * Atributo numero
	 */
	private int numero;
	
	/**
	 * Constructoras
	 */
	public Number(){}
	public Number(int term){
		this.numero = term;
	}
		
	/**
	 *Parseamos el Strin que nos llega, lo transformamos a int
	 * y comprobamos que esta entre ese baremo.
	 * Si es correcto devolvemos esta Instruccion,sino devolvemos false;
	 */
	public Term parse(String term) {

		int numero = Integer.parseInt(term);
		if (-1000 <= numero && numero <= 1000) 
			return new Number(numero);
		else return null;
	}

	/**
	 * Devolvemos el ByteCode PUSH
	 */
	public ByteCode compile(Compiler compile) {
		return new Push(this.numero);
	}
	
	/**
	 * Devolvemos el String del Atributo numero
	 */
	public String toString(){
		return Integer.toString(this.numero);
	}
}
