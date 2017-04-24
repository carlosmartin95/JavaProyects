package Bytecode;
import CPU.CPU;
import Exception.StackException;
/**
 * Esta clase se encarga de implementar todos los metodos necesarios del comando sub, 
 * como no necesita aningun parametro hereda de bytecode
 * 
 * */
public class Sub implements ByteCode {
/**
 * Constructora de la clase
 * */
	public Sub() {}
/**
 * Este metodo se encarga de llamar al metod sub de la clase cpu
 * @throws StackException 
 * */
	public void execute(CPU cpu) throws StackException {
		int num1,num2;
		if(cpu.getSizeStack()>=2){
			num1=cpu.pop();
			num2=cpu.pop();
			num1=num2-num1;
			cpu.push(num1);
		}
		else{
			throw new StackException("La pila no tiene suficientes elementos como para hacer una resta");
		}
	}
/**
 * Este metodo se encarga de verificar si en el string que le llega por parametro existe la palabra sub 
 * si es asi llama a la constructora, si no es asi devuelve null
 * */

	public ByteCode parse(String[] s) {
		if(s[0].equalsIgnoreCase("SUB"))
			return new Sub();
		else	return null;
	}
	/**
	 * Es te metodo se encarga de devolver en un string el nombre del comando actual
	 * */
	public String toString(){
		return "SUB";
	}

}
