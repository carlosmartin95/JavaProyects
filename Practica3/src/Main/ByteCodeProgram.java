package Main;

import Bytecode.ByteCode;
import Exception.ArrayException;

/**
 * La clase ByteCodeProgram se encarga de almacenar todas las instrucciones del programa que queremos realizar.
 * Alamacenamos todas las instrucciones hasta que se introduzca la instruccion run
 * Atributos Privados
 * program es un array de tipo ByteCode cuyo fin es almacenar todas las instrucciones que recibimos por teclado.Este array tiene un tamaño maximo si se supera no permitira realizar mas instrucciones.
 * contador: Este numero nos informa del numero de instrucciones que tiene el array de esta manera podemos gestionar si todavia podemos añadir mas instrucciones. 
 * TAMANOMAXIMO: atributo privado de tipo constante que nos indica el maximo de instrucciones que podemos almacenar en el array
 * */

public class ByteCodeProgram {
	/**
	 * Constructora
	 * Esta constructora crea un ByteCodeProgram creando un array del tipo ByteCode y iniciaizando el cnotraodr del mismo a 0 
	 */
	private ByteCode[] program;
	private int contador;
	private static final int TAMANOMAXIMO=100;
	
	public ByteCodeProgram(){
		this.program = new ByteCode[ByteCodeProgram.TAMANOMAXIMO];
		this.contador=0;
	}
	
/**
 * Este metodo recibe una instruccion de tipo ByteCode y la añade al array de bytecodeprogram si hay espacio para otra insruccion mas
 * @throws ArrayException 
 * */		
	public void anadir(ByteCode instruccion) throws ArrayException {
		
		if(this.contador<TAMANOMAXIMO){
			this.program[this.contador]=instruccion;
			this.contador++;
		}
		else{
			throw new ArrayException("El programa ya etsa lleno, no s epuede añadir mas");
		}
	}
/**Este metodo recibe un parametro de tipo int y una instruccion de tipo Bytecode.Lo que realiza 
 * esta instruccion es buscar en la posicion dada en el int si hay alguna instruccion 
 * si es asi reemplaza esa instruccion por el bytecode que recibe por paramtro
 * @throws ArrayException 
 * */
	public boolean reemplazar(int replace, ByteCode instruction) throws ArrayException {
		if(replace >= 0 && replace<=this.contador){
			this.program[replace]=instruction;
			return true;
		}else throw new ArrayException("Esa posicion no se encuentra en el array de datos, no se puede reemplazar");
	}

/**
 * Este metodo devuelve la instruccion Bytecode que se encuentra en la posicion que nos llega por parametro 
 * */
	public ByteCode getByteCode(int i) {
		return this.program[i];
	}

/**
 * Este metodo se encarga de mirar si el array esta vacio, mira si el atributo contador es 0 o no
 * */
	public boolean is_empty() {
		if(this.contador==0){
			return true;
		}
		else{
			return false;
		}
	}
/**
 * Este metodo se encarga de recorrer todo el array de ByteCodeProgram y va añadiendo al string s,
 *  todas las instrucciones que contiene una por linea
 * */	
	public String toString(){
		String s="";
		for(int i=0;i<this.contador;i++){
			s=s+i+":"+this.program[i]+System.getProperty("line.separator");
		}
		return s;
	}
/**
 * Este metod se encarga de crear un nuevo ByteCodeProgram y colocar el contador a 0 cada vez que queremos crear un nuevo Programa
 * */	
	public void reset(){
		this.program =new ByteCode[ByteCodeProgram.TAMANOMAXIMO];
		this.contador=0;
	}
/**
 * Devuelve cual es el tamaño del bytecoeprogram
 * */	
	public int getTamano(){
		return this.contador;
	}
	/**
	 * Actualiza el valor del contador con el valor que lellega por referencia
	 * */
	public void setTamano(int contador){
		this.contador = contador;
	}

	
}
