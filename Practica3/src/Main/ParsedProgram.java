package Main;

import Exception.ArrayException;
import Instruction.Instruction;
/**
 * Esta clase se contiene todo lo necesario para trabajar con un elemento de la clase parsesProgram
 * */
public class ParsedProgram {
	/**
	 * Atributos privados
	 * pProgram contiene todas las instrucciones 
	 * contador almacena el numero de instrucciones que tenemos en el array
	 * TAMANOMAXIMO el tamaño maximo del array
	 * */
	private Instruction[] pProgram;
	private int contador;
	private static final int TAMANOMAXIMO=100;
	/**
	 * Constructora que inicializa la clase
	 * */
	
	public ParsedProgram() {
		this.pProgram = new Instruction[ParsedProgram.TAMANOMAXIMO];
		this.contador=0;
	}
	/**
	 * Este metodo permite añadir un anueva instrucccion al pProgram si no esta lleno el array
	 * */
	public void anadir(Instruction instruction) throws ArrayException {
		
		if(this.contador<TAMANOMAXIMO){
			this.pProgram[this.contador]= instruction;
			this.contador++;
		}
		else{
			 throw new ArrayException("El programa parseado ya esta lleno de instrucciones, no entran mas");
		}
	}
	/**
	 * devuelve el numero de instrucciones que tenemos en el array
	 * */
	public int getNumeroInstrucciones() {
		// TODO Auto-generated method stub
		return this.contador;
	}
	/**
	 * Devuelve en un string todas las instrucciones que contiene el array
	 * */
	public String toString(){
		String s="";
		for(int i=0;i<this.contador;i++){
			s=s+i+":"+this.pProgram[i]+System.getProperty("line.separator");
		}
		return s;
	}
/**
 * devuelve instruccion en funcion de su posicion en el array
 * */
	public Instruction getInstruction(int i) {
		// TODO Auto-generated method stub
		return this.pProgram[i];
	}
}