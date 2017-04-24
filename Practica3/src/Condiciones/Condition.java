package Condiciones;

import Exception.ArrayException;
import Instruction.LexicalParser;
import Instruction.Term;
import Instruction.TermParser;
import Main.Compiler;
import OneParameter.ConditionalJumps;

/**
 * Clase Abstracta que se encarga de recoger todas las posibles condiciones que podemos encontrar y buscar en toas las posibles que
 * condicion quiere usar el programa
 * */
public abstract class Condition {
/**
 * Contiene tres atributos privados dos terminos y una condicion
 * */
	 private Term t1, t2;
	 private ConditionalJumps condition; 
	/**
	 * Metodos abstracto que se implementara en las subclases de condition
	 * */			
	abstract protected String toStringAux();
	abstract protected Condition parseAux(Term t1,Term t2,String op);
	/**
	 * Constructoras
	 * */ 
	 public Condition() {}
	
	 public Condition(Term t1, Term t2, ConditionalJumps condition) {
		this.t1 = t1;
		this.t2 = t2;
		this.condition = condition;
	}

/**
 * Este metodo se encarga de parsear y devolver una condicion si encuentra los terminos que recibe como string y la condicion en el op 
 * */
	public Condition parse(String t1, String op, String t2, LexicalParser parser){
			 
		this.t1=TermParser.parse(t1);
		this.t2=TermParser.parse(t2);
		return parseAux(this.t1,this.t2,op);
	}
/**
 * Este metodo se encarga de compilar la linea de la condicion que hemos parseado anteriormente
 * */
	 public void compile(Compiler compiler) throws ArrayException{
		
		 compiler.addByteCode(this.t1.compile(compiler));
		 compiler.addByteCode(this.t2.compile(compiler));
		 this.condition.setParam(compiler.getProgramCounter());
		 compiler.addByteCode(this.condition);		 
		
	 }	
	/**
	 * Este metodo se encarga de parametricar bien los saltos condicionates y no condicionales que podemos tener en un program bytecode
	 * @throws ArrayException 
	 * */ 
	 public void setJump(int programCounter, Compiler compiler) throws ArrayException {
		int aux = 0;

		aux = this.condition.getParam();
		this.condition.setParam(programCounter);
		compiler.replaceBCP(this.condition, aux);
	 }
}

