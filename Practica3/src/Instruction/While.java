package Instruction;

import Condiciones.Condition;
import Condiciones.ConditionParse;
import Exception.ArrayException;
import Exception.LexicalAnalysisException;
import Main.Compiler;
import Main.ParsedProgram;
import OneParameter.Goto;

public class While implements Instruction{

	/**
	 * Atributos
	 */
	private Condition condition;
	private ParsedProgram whileBody;
	
	/**
	 * Constructoras
	 */
	public While(Condition condition, ParsedProgram wBody) {
		this.condition=condition;
		this.whileBody=wBody;
	}
	public While() {}

	/**
	 *Parseamos el array de Strings que nos llega, comprobando
	 *que es igual a WHILE. Si es correcto, parseamos el cuerpo y la condicion
	 * devolviendo a continuación esta Instruccion con su cuerpo y la condición.
	 * Sino devolvemos false;
	 */
	public Instruction lexParse(String[] words, LexicalParser lexParser) throws LexicalAnalysisException, ArrayException{
	
		if (words[0].equalsIgnoreCase("WHILE")){
			ParsedProgram wBody = new ParsedProgram();
			lexParser.increaseProgramCounter();
			lexParser.lexicalParser(wBody, "endwhile");
			Condition condition=ConditionParse.parse(words[1], words[2], words[3], lexParser);
			lexParser.increaseProgramCounter();
			return  new While(condition,wBody);
		}
		 else return null;
	}
	
	/**
	 * Cmpilamos la condicion. Guardamos el contdor del programa y compilamos el cuerpo.
	 * Añadimos el ByteCode GoTo al array de ByteCodes de Compiler, y hacemos el salto
	 * a la instruccion correspondiente.
	 */
	public void compile(Compiler compiler) throws ArrayException {
		int aux;
		this.condition.compile(compiler);  
		aux=compiler.getProgramCounter()-3;
		compiler.compile(this.whileBody); 
		compiler.addByteCode(new Goto(aux));
		this.condition.setJump(compiler.getProgramCounter(), compiler);

	}
}
