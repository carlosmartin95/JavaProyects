package Bytecode;

import CPU.CPU;
import Exception.StackException;
/**
 * Esta clase se encarga de implementar todos los metodos necesarios del comando out, 
 * como no requiere de ningun  parametro adicional hereda de bytecode
 * */
public class Out implements ByteCode {
/**
 * Costructora de la clase out
 * @throws StackException 
 * */
	public Out(){}
	
	public void execute(CPU cpu) throws StackException {
		int num;
		if(cpu.getSizeStack()>0){
			num=cpu.pop();
			System.out.println(num);
			cpu.push(num);
		}
		else{
			throw new StackException("La pila esta vacia, no hay nada que mostrar");
		}
	}
/**
 * Este metodo se encarga de buscar en el string que le llega por parametro la palabra out 
 * , si la encuentra crea un nuevo comando out en caso de no encontrarlo devuelve null
 * */

	public ByteCode parse(String[] s) {
		if(s[0].equalsIgnoreCase("OUT"))
			return new Out();
		else	return null;
	}
	
/**
 * ESte metodo devuelve en un string el nombre del comandon actual
 * */	
	public String toString(){
		return "OUT";	
	}
}
