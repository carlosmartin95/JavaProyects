package Instruction;

import Bytecode.ByteCode;
import Main.Compiler;

/**
 * Interface Term
 */
public interface Term {

	Term parse(String words);
	ByteCode compile(Compiler compiler);
}
