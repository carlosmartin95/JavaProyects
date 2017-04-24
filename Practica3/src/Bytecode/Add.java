package Bytecode;
import CPU.CPU;
import Exception.StackException;
/**
 * Clase add : esta clase contiene todos los metodos que se usan para el comando add
 * */

public class Add implements ByteCode {
/**
 * Constructora vacia para la inicializacion delcomando
 * */
	
	public Add() {}
/**
 * Este metodo se encarga de llamar al metodo add implementado en la cpu la cual realizara la suma. Si la suma se puede ejecutar devolvera un true 
 * @throws StackException 
 * */

	public void execute(CPU cpu) throws StackException {
		int num1,num2;
		if(cpu.getSizeStack()>=2){
			num1=cpu.pop();
			num2=cpu.pop();
			num1=num1+num2;
			cpu.push(num1);
		}
		else
			throw new StackException("La pila no tiene suficientes elementos para sumar");
		
	}
/**
 * Este metode se encarga de verificar que el string que nos llega por teclado contien un ADD si es asi llama a la contructora de la clase de add
 * */

	public ByteCode parse(String[] s) {
		if(s[0].equalsIgnoreCase("ADD"))
			return new Add();
		
		else return null;
		
	}
	
	/**
	 * Est metodo se encarga de devolver el tipo de comando que es en un String
	 * */
	public String toString(){
		return "ADD";	
	}
}
