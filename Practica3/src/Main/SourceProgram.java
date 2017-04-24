package Main;

import Exception.ArrayException;
/**
 * Esta clase se contiene todo lo necesario para trabajar con un elemento de la clase SourceProgram
 * */
public class SourceProgram {
	/**
	 * Atributos privados 
	 * sProgram es nu array en el que vamos a contener todos los comandos de alto nivel
	 * contador almacena el total de elementos que tenemos en el array
	 * TAMANOMAXIMO maximo de elementos que podemos almacenar
	 * */
	private String[] sProgram;
	private int contador;
	private static final int TAMANOMAXIMO=100;
	/**
	 * Constructora que nos permite inicializar la clase
	 * */
	public SourceProgram() {
		this.sProgram = new String[SourceProgram.TAMANOMAXIMO];
		this.contador=0;
	}
/**
 * Añade al array una nueva instruccion si hay sitio
 * */	
	public void anadir(String instruccion) throws ArrayException {
		
		if(this.contador<TAMANOMAXIMO){
			this.sProgram[this.contador]=instruccion;
			this.contador++;
		}
		else{
			 throw new ArrayException("El programa ya esta lleno de instrucciones, no entran mas");
		}
	}
/**
 * devuelve la instruccion que se encuentra en programCounter
 * */	
	public String getInstruction(int programCounter) {
		// TODO Auto-generated method stub
		return this.sProgram[programCounter];
	}
/**
 * devuelve el numero de instrucciones que tenemos en e array
 * */
	public int getNumeroInstrucciones() {
		// TODO Auto-generated method stub
		return this.contador;
	}
/**
 * devuelve en un string todo el contenido del array sProgram
 * */	
	public String toString(){
		String s="";
		for(int i=0;i<this.contador;i++){
			s=s+i+":"+this.sProgram[i]+System.getProperty("line.separator");
		}
		return s;
	}
/**
 * Este metodo inicializa el programa
 * */	
	public void reset(){
		this.sProgram =new String[SourceProgram.TAMANOMAXIMO];
		this.contador=0;
	}
}
