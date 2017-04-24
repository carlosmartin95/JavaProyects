package Bytecode;

import CPU.CPU;
import Exception.ArrayException;
import Exception.DivisionByZero;
import Exception.StackException;

/**
 * La clase Bytecode interfaz la cual contine 2 metodos abstractos que heredara sus clases hijas
 */
public interface ByteCode {
	/**
	 * ESte metod abstracto se encarga de ejecutar el comando pertinente
	 * @throws StackException 
	 * @throws DivisionByZero 
	 * @throws ArrayException 
	 * */
	abstract public void execute(CPU cpu) throws StackException, DivisionByZero, ArrayException;
	/**
	 * Este metod se encarga de mirar en el string que le llega por parametro si contiene el comando en el que nos encontremos
	 * */
	abstract public ByteCode parse(String[] s);
}




