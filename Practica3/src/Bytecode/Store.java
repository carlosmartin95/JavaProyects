package Bytecode;

import CPU.CPU;
/**
 * Esta clase se encarga de implementar todos los metodos necesarios del comando store, como necesita un parametro adicional
 * esta clase hereda de BytecodeOneParameter
 * */
public class Store extends ByteCodeOneParameter{
/**
 * Constructoras
 * */
	public Store() {
		// TODO Auto-generated constructor stub
	}
	public Store(int parametro) {
		super(parametro);
		// TODO Auto-generated constructor stub
	}
	

/**
 * Este metodo se encarga de buscar en el string que le llega por parametro si existe la palabra store,
 * si es asi crea un comando del tipo store, si no lo encuentra devuelve null
 * */

	protected ByteCode parseAux(String string1, String string2) {
		if(string1.equalsIgnoreCase("STORE")){
			return new Store(Integer.parseInt(string2));
		}
		else return null;
	}
/**
 *Este metodo devuelve en un string el nombre del comando actual 
 * */

	protected String toStringAux() {
		return "STORE";
	}
/**
 * Este metodo se encarga de llamar al metod store de la clae cpu
 * */

	public void execute(CPU cpu) {
		
		 cpu.store(this.param);
	}
}
