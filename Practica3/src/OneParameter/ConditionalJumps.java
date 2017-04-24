package OneParameter;

import Bytecode.ByteCodeOneParameter;
import CPU.CPU;
import Exception.StackException;
/**
 * Esta clase se encarga de recoger todos los bytecodes que tengan un parametro adicional,es decir,
 *  aquellos bytecodes que van a realizar un salto de instrucciones a la posicion del paramtero adicional
 * */
public abstract class ConditionalJumps extends ByteCodeOneParameter{

	public ConditionalJumps(){}
	public ConditionalJumps(int j){
		super(j); 
	}
	
	/**
	 * metodo que se encarga de comparar los dos datos que le llegan por referencia en funcion del tipo de bytecode que sea
	 * */	
	abstract protected boolean compare(int n1, int n2);
	
/**
 * Este metodo se encarga de coger los dos ultimos elementos de la pila llama a todos los metodos compare de sus subclases
 * @throws StackException 
 * */	 
	public void execute(CPU cpu) throws StackException {
		
		int n2=0,n1=0;//Cima y subcima de la pila
		if (cpu.getSizeStack()>=2){
			n1=cpu.pop(); // subcima
			n2=cpu.pop(); // cima
		 	
			if (compare(n2,n1)==false)//si no se cumple la condicion salta
		 		cpu.setProgramCounter(this.param);
		 	else cpu.increaseProgramCounter();
		 }
		else throw new StackException("La pila no contiene suficientes elementos para hacer el salto condicional");
	}
	
	/**
	 * Actualiza el valor del contador de program con el valor que le llega por parametro
	 * */
	public void setJump(int programCounter) {
		this.param = programCounter;		
	}
}
