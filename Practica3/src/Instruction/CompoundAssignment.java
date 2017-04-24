package Instruction;

import Bytecode.Add;
import Bytecode.Div;
import Bytecode.Mul;
import Bytecode.Store;
import Bytecode.Sub;
import Exception.ArrayException;
import Main.Compiler;

public class CompoundAssignment implements Instruction{
	/**
	 * Atributos
	 */
	 private String varName;
	 private String operator;
	 private Term t1;
	 private Term t2;
	 
	 /**
	  * Constructoras
	  */
	 public CompoundAssignment(String string, String string2, Term term, Term term2) {
		this.t1 = term;
		this.t2 = term2;
		this.operator = string2;
		this.varName = string;
	}
	public CompoundAssignment() {}

	 /**
	 * Parseamos el array de Strings que nos llega, comprobamos que su longitud
	 * sea igual a 5 y parseamos los strings en las posiciones 2 y 4 para ver que tipo
	 * de Término son. Si es correcto devolvemos esta Instruccion,sino devolvemos false;
	 */
	public Instruction lexParse(String[] words, LexicalParser lexParser){
		
		if (words.length == 5 && "=".equalsIgnoreCase(words[1])){
			 Term term = TermParser.parse(words[2]);
			 Term term2 =TermParser.parse(words[4]);
			 lexParser.increaseProgramCounter();
			 return new CompoundAssignment(words[0],words[3], term,term2);
		}
		else return null;
	 }

	/**
	 * Añadimos al array de Bytecode de la clase Compiler el ByteCode 
	 * asignado a t1 y t2 y comprobamos que tipo de de operador tenemos
	 * para añadir el correspondiente ByteCode.
	 */
	 public void compile(Compiler compiler) throws ArrayException{ 
		
		compiler.addByteCode(t1.compile(compiler));
		compiler.addByteCode(t2.compile(compiler));
		
		if (this.operator.equalsIgnoreCase("+"))
			compiler.addByteCode(new Add());
		else if(this.operator.equalsIgnoreCase("-"))
			compiler.addByteCode(new Sub());
		else if(this.operator.equalsIgnoreCase("/"))
			compiler.addByteCode(new Div());
		else if(this.operator.equalsIgnoreCase("*"))
			compiler.addByteCode(new Mul());
		compiler.addByteCode(new Store(compiler.getIndex(varName)));
	 }
	
}
