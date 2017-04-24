package CPU;

import Bytecode.ByteCode;
import Exception.ArrayException;
import Exception.DivisionByZero;
import Exception.ExecutionError;
import Exception.StackException;
import Main.ByteCodeProgram;

/**
 * La clase CPU se encarga de contener la memoria y la pila 
 * Atributos Privados
 * mem: array de datos de tamaño variable en funcion del tamño de la memoria fisica del sistema en el que se este trabajando
 * pila: conjunto de datos que se van a ir alamacenando uno detras de otro.Solo se podra acceder al ultimo de la fila
 * parada:atributo de clae boolean que nos indica cuando se para la ejecucion del programa
 * */
public class CPU {

	private  Memory mem = new Memory();
	private  OperandStack pila = new OperandStack();
	private  boolean parada = false;
	private int programCounter;
	private ByteCodeProgram bcProgram;
	public CPU (){
		this.programCounter=0;
	};
	
	public CPU(ByteCodeProgram comandos){
		this.programCounter=0;
		this.bcProgram=comandos;
	}
	
	/** 
	 * Este metodo nos indicara si la ejecucion del programa se para o no
	 * @throws ExecutionError 
	 * */
	public void isHalted() throws ExecutionError{
		if(this.parada!=true)
			throw new ExecutionError("La ejecucion ya esta parada");
	}


	/**
	 * Este metodo se encarga de coger cada instruccion a realizar y llama a los execute de cada comando 
	 * si todo va bien continuara con la ejecucion de los demas comando, si surge algun problema lo notificara con un mensaje
	 * @throws DivisionByZero 
	 * @throws StackException 
	 * @throws ArrayException 
	 * */
	public void run() throws StackException, DivisionByZero, ArrayException{
		ByteCode bytecode;
		while(this.programCounter<this.bcProgram.getTamano() && this.parada!=true){
			bytecode=this.bcProgram.getByteCode(this.programCounter);
			bytecode.execute(this);
			//para ver comando a comando la pila y la memoria descomentar
			//System.out.println("Instruccion: "+bytecode.toString()+System.getProperty("line.separator")+this.toString());
			
		}
	}
	/**
	 *Este metodo se encarga de mostrar por pantalla cual es el contenido de la cpu por programa
	 *muestra la pila y la cpu 
	 * */
	public String toString(){
		String s =System.getProperty("line.separator")+
		"Estado de la CPU: "+ System.getProperty("line.separator")+
		" Memoria: "+this.mem+ System.getProperty("line.separator")+
		" Pila: " + this.pila + System.getProperty("line.separator");
		return s;
	
	}
	/**
	 * ESte metodo se encarga de añadir a la pila el parametro que recibe por referencia
	 * @throws StackException 
	 * */
	public int push(int i) throws StackException{
		this.programCounter++;
		return pila.push(i);
	}
/**
 * lee el parametro que se encuentra en la posicion n que le llega por parametro
 * @return 
 * @throws ArrayException 
 * @throws StackException 
 * */	
	public int read(int n) throws ArrayException, StackException{	
		this.programCounter++;
		if(this.mem.getSize()>n)
			return this.pila.push(this.mem.read(n));
		else{
			throw new ArrayException("Esa posicion no pertenece a la memoria");
		}
	}
	
/**
 * devuelve el numero del programCounter
 */
	
	public ByteCode getByteCodeProgram() {
		return this.bcProgram.getByteCode(this.programCounter);
	}
/**
 * Este metodo ordena al metrodo run que se detenga el bucle
 * */	
	public void halt() {
		this.parada=true;
		this.programCounter++;
	}

/**
 * Este metodo se encarga de almacenar el ultmo valor de la pila en la posicion de la memoria parametro
 * */	
	public void store(int parametro) {
		increaseProgramCounter();
		this.mem.write(parametro, this.pila.pop());
	}
/**
 * Este metodo incrementa el contador del programa en 1   
 * */	
	public void increaseProgramCounter() {
		this.programCounter++;
	}
/**
 *Este metodo se encarga de actualizar el valor de programCounter a param
 * */
	public void setProgramCounter(int param) {
		this.programCounter=param;
	}
/**
 * Devuelve el numero de elementos que tiene la pila
 * */	
	public int getSizeStack() {
		return this.pila.getNumElemns();
	}
/**
 * Devuelve el ultimo elemento de la pila
 * */	
	public int pop() {
		return this.pila.pop();
	}
	/**
	 * metemos en pila el valor de la memoria de param
	 */
	public void load(int param) throws StackException { 
		push(mem.read(param));
	}


}
