package Instruction;
import Exception.ArrayException;
import Exception.LexicalAnalysisException;
import Main.Compiler;

/**
 * Interface Instruction
 */
public interface Instruction {
	
	Instruction lexParse(String[] words,LexicalParser lexParser) throws LexicalAnalysisException, ArrayException;
	public void compile(Compiler compiler)throws ArrayException;
}
