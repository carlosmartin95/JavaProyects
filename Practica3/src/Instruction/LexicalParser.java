package Instruction;
import Exception.ArrayException;
import Exception.LexicalAnalysisException;
import Main.ParsedProgram;
import Main.SourceProgram;

public class LexicalParser {

	private SourceProgram sProgram;
	private int programCounter;
	
	/**
	 * Constructora
	 */
	public LexicalParser(SourceProgram source){
		this.sProgram = source;
		this.programCounter=0;
	}
	/**
	 * Recibimos un array de tipor ParsedProgram y la StopKey.
	 * Obtenemos el string guardado en la posicion correspondiente del array de SourceProgram
	 * y la parseamos para a continuacion añadir esta instruccion al array ParsedProgram.  
	 */
	public void lexicalParser(ParsedProgram pProgram, String stopKey)throws LexicalAnalysisException, ArrayException {
		boolean stop=false;
		
		while (this.programCounter < this.sProgram.getNumeroInstrucciones()&& stop==false){
			String instr = sProgram.getInstruction(this.programCounter);
			if (instr.equalsIgnoreCase(stopKey))
				stop = true;
			else {
				Instruction instruction = ParserInstruction.lexParse(instr,this);
				pProgram.anadir(instruction);
			}
		}
	}
	
	/**
	 * incrementamos el contador del array
	 */
	public void increaseProgramCounter(){
		this.programCounter++;
	}
}