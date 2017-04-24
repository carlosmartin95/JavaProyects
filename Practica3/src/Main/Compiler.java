package Main;

import Bytecode.ByteCode;
import Exception.ArrayException;
import Instruction.Instruction;
import Main.ByteCodeProgram;
/**
 * Esta clase se encarga de Convertir un codigo parseado a un codigo Bytecode
 * */
public class Compiler {
/**
 * Atributos privados que usamos para realizar la conversion
 * bcProgram contiene el nuevo codigo
 * vartable contiene la posicion de la memoria a la que tenemos que acceder para recoger una variable en memoria
 * numVars contiene el numeor total de variables que tenemos en la vartable
 * */
	private ByteCodeProgram bcProgram;
	private String[] varTable;
	private int numVars;
/**
 * Cosntructora que inicializa la clase 
 * */
	public Compiler(){
		this.bcProgram = new ByteCodeProgram();
		this.varTable = new String[200];
		this.numVars = 0;
	}
	/**
	 * Este metodo se encarga de llamar a todas las instrucciones que tenemos para buscar si alguna de ellas se corresponde con el 
	 * dato que tenemos en el array de pProgram
	 * */
	public void compile(ParsedProgram pProgram) throws  ArrayException{
		 
		int i = 0;
		try {
			while (i < pProgram.getNumeroInstrucciones()){
				Instruction inst = pProgram.getInstruction(i);
				inst.compile(this);
				i++;
			}
		} 
		catch(ArrayException a){ throw new ArrayException(""); }
	 }
	/**
	 * añade un bytecode a bcprogram
	 * */ 
	public void addByteCode(ByteCode b) throws ArrayException { 
		this.bcProgram.anadir(b);
	 } 
	/**
	 * devuelve el valor del la posicion de memoria en la que se encuetra una variable
	 * */ 	 
	 public int getIndex(String varName){
		
		 int i = 0; 
		 boolean encontrado= false;
		 while (!encontrado){
			 if (varName.equals(varTable[i]))			 
				encontrado = true;
			 else i++;	 
		 }
		 return i;		 
	 }
	/**
	 * Sustituye un bytecode por otro
	 * @throws ArrayException 
	 * */ 
	public void replaceBCP (ByteCode byteCode, int pos) throws ArrayException {
		this.bcProgram.reemplazar(pos, byteCode);
	}
/**
 * devuelve el tamaño de bcprogram
 * */
	public int getProgramCounter() {
		return this.bcProgram.getTamano();
	}
/**
 * actualiza el valor del tamaño del bcprogram con el valro counter que recibe por parametro
 * */	
	public void setProgramCounter(int counter){
		this.bcProgram.setTamano(counter);
	}
/**
 * devuelve el numero de variables que tenemos en el array varTable
 * */
	public int getNumVars() {
		return numVars;
	}
/**
 * añadimos una nueva letra al array varTable
 * */
	public void anadirletra(String var_name) {
		this.varTable[this.numVars]=var_name;
		this.numVars++;
	}
/**
 * devuelve el bcprogram
 * */
	public ByteCodeProgram getbcProgram() {
		return this.bcProgram;
	}
}
