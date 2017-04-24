package Bytecode;

import CPU.CPU;
import Exception.ArrayException;
import Exception.StackException;
/**
 * Esta clase se encarga de implementar todos los metodos necesarios del comando Load 
 * como requiere de un parametro extra esta hereda de bytecoeoneparameter
 * */
public class Load extends ByteCodeOneParameter{
/**
 * Constructoras de la clase
 * */
	public Load() {}
	public Load(int parametro) {
		super(parametro);
		// TODO Auto-generated constructor stub
	}
	

/**
 * Este metodo se encarga de verificar si en el strig que le llega por parametro hay una palabra LOAD si es asi
 * llama a la constructora para crear el comando, si no es asi devuelve null
 * */

	protected ByteCode parseAux(String string1, String string2) {
		if(string1.equalsIgnoreCase("LOAD")){
			return new Load(Integer.parseInt(string2));
		}
		else return null;
	}
/**
 * ESte metodo se encarga de llamar al metodo read de la cpu
 * @throws StackException 
 * @throws ArrayException 
 * */

	public void execute(CPU cpu) throws ArrayException, StackException {
		 cpu.read(this.param);
	}
/**
 * Este metodo devuelve en un string el nombe del comando
 * */
	protected String toStringAux() {
		return "LOAD";
	}
}
