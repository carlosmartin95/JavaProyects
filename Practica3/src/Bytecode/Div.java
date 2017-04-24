package Bytecode;

import CPU.CPU;
import Exception.DivisionByZero;
import Exception.StackException;
/**
 * Esta clase se encarga de implementar todos los metodos necesarios del comando div que hereda de bytecode
 *  ya que no necesita un parametro
 * */
public class Div implements ByteCode {
/**
 * Constructora vacia para implmentar el comando div
 * */
	public Div() {}
/**
 * Este metodo se encarga de llamar al metodo div implementado en la cpu
 * */

	public void execute(CPU cpu)throws StackException,DivisionByZero{
		int num1;
		if(cpu.getSizeStack()>=2){
			num1=cpu.pop();
			if(num1!=0){
				num1=cpu.pop()/num1;
				cpu.push(num1);
			}
			else{
				throw new DivisionByZero("La division no es posible el divisor es 0");
			}
		}
		else{
			throw new StackException("La pila no tiene suficientes elementos para la hacer una division");
		}
	}
/**
 * Este metodo se encarga de verificar si en el string que recibe por parametro hay un string que contenga la palabra DIV
 * Si es asi llamara la constructora de el comando para crearlo o si no es asi devolvera un null
 * */

	public ByteCode parse(String[] s) {
		if(s[0].equalsIgnoreCase("DIV"))
			return new Div();
		else	return null;
	}
/**
 * Este metode se encarga de devolver en un string el tipo de comando que se esta ejecutando
 * */	
	public String toString(){
		return "DIV";	
	}
}
