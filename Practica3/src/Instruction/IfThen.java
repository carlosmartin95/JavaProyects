package Instruction;

import Condiciones.Condition;
import Condiciones.ConditionParse;
import Exception.ArrayException;
import Exception.LexicalAnalysisException;
import Main.Compiler;
import Main.ParsedProgram;

public class IfThen implements Instruction{
	/**
	 * Atributos privados
	 */
	private Condition condition; 
	private ParsedProgram ifBody;
	/**
	 * Constructoras 
	 */
	public IfThen(Condition condition, ParsedProgram ifBody) {
		this.condition = condition;
		this.ifBody = ifBody;
	}
	public IfThen() {}
	
	/**
	 * Parseamos el array de Strings que nos llega, comprobando
	 * que es igual a IF. Si es correcto, parseamos el cuerpo y la condicion
	 * devolviendo a continuación esta Instruccion con su cuerpo y la condición.
	 * Sino devolvemos false;
	 */
	public Instruction lexParse(String[] words, LexicalParser lexParser) throws LexicalAnalysisException, ArrayException {
		
		if (words[0].equalsIgnoreCase("IF")){
			Condition condicion=ConditionParse.parse(words[1], words[2], words[3], lexParser);
			ParsedProgram ifBody = new ParsedProgram();
			lexParser.increaseProgramCounter();
			lexParser.lexicalParser(ifBody, "ENDIF");
			lexParser.increaseProgramCounter();
			return  new IfThen(condicion,ifBody);
		}
		else return null;
	}
	/**
	 * Cmpilamos la condicion y el cuerpo, y hacemos el salto
	 * a la instruccion correspondiente.
	 */
	public void compile(Compiler compiler) throws ArrayException {
		this.condition.compile(compiler); 
		compiler.compile(this.ifBody);
		this.condition.setJump(compiler.getProgramCounter(), compiler);
	}
}
