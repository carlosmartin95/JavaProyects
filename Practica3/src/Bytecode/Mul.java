package Bytecode;
import CPU.CPU;
import Exception.StackException;
/**
 * Esta clase se encarga de implementar todos los metodos necesarios del comando mul, como no necesita nungun parametro extra
 * herada de bytecode
 * */
public class Mul implements ByteCode {
/**
 * Constructora vacia de la clase para su creacion
 * */
	public Mul() {}
/**
 * Este metodo se encarga de llamar al metodo mul implementado en la clase cpu
 * @throws StackException 
 * */
	public void execute(CPU cpu) throws StackException {	
		int num1;
		if(cpu.getSizeStack()>=2){
			num1=cpu.pop();
			num1=cpu.pop()*num1;
			cpu.push(num1);
		}
		else{
			throw new StackException("La pila no tiene suficientes elementos para hacer una multiplicacion");
		} 
	}
/**
 * Este metodo se encarga de parsear el String que le llega por parametro con el fin de encontrar la palabra Mul 
 * si es asi crea un nuevo comando mul en caso contrario devuelve un null
 * */

	public ByteCode parse(String[] s) {
		if(s[0].equalsIgnoreCase("MUL"))
			return new Mul();
		else	return null;
	}
/**
 * Este metodo se encarga de devolver en un string el nombre del comando actual
 * */	
	public String toString(){
		return "MUL";
	}
}
