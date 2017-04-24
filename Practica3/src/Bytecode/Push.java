package Bytecode;

import CPU.CPU;
import Exception.StackException;
/**
 * Esta clase se encarga de implementar todos los metodos necesarios del comando push, 
 * como requiere de un parametro adcional esta clase hereda de bytecodeoneparameter
 * */
public class Push extends ByteCodeOneParameter{
/**
 * Constructoras
 * */
	public Push() {}
	public Push(int parametro) {
		super(parametro);
	}
/**
 * Este metodo se encarga de byuscar en el string que le llega por parametro la palabra push
 * , si la encuentra crea un comando de la clase push si no lo encuentra devuelve null
 * */
	protected ByteCode parseAux(String string1, String string2) {
		if(string1.equalsIgnoreCase("PUSH"))
			return new Push(Integer.parseInt(string2));
		else return null;
	}
/**
 * Este metodo devuelve en un string el nombre del comando actual
 * */

	protected String toStringAux() {
		return "PUSH";
	}
/**
 * Este metodo se encarga de llamar al metodo push de  la cpu
 * @throws StackException 
 * */

	public void execute(CPU cpu) throws StackException {
		 cpu.push(this.param);
	}
}
