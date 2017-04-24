package Instruction;

import Bytecode.ByteCode;
import Bytecode.Load;
import Main.Compiler;

public class Variable implements Term{
	/**
	 * Atributo variable
	 */
	private String variable;
	/**
	 * Constructoras
	 */
	public Variable(){}
	public Variable(String term){
		this.variable = term;
	}
	/**
	 * Parsemos el String que nos llega por parámetro
	 * comprando que su longitud que sea diferente de 1.
	 * Si no se cumple devolvemos null, sino devolvemos una nueva 
	 * variable con su término, siempre y cuando este entre A y Z
	 */
	public Term parse(String term) {
		if (term.length() !=1)
			return null;
		else{
			char name = term.charAt(0);
			if ('a' <= name && name <='z')
				return new Variable(term);
			else return null;
		}
	}
	/**
	 * Devolvemos un nuevo ByteCode LOAD 
	 */
	public ByteCode compile(Compiler compiler){
		return new Load(compiler.getIndex(variable));
	}

	/**
	 * Devolvemos el atributo variable
	 */
	public String toString(){
		return this.variable;
	}
}
