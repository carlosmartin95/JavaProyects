package Bytecode;

import CPU.CPU;
/**
 * Esta clase se encarga de implementar todos los metodos necesarios del comando HALT. Esta clase hereda de Bytecode ya que no
 * necesita ningun parametro adicional
 * */
public class Halt implements ByteCode{
/**
 * Constructora vacia de la clase halt
 * */
	public void execute(CPU cpu) {
		 cpu.halt();
	}
/**
 * Este metodo se encarga de verifica si en el string se encuetra la palabra Halt,si es asi llama a la constructora 
 * si no es asi evlvera un null
 * */
	public ByteCode parse(String[] s) {
		if(s[0].equalsIgnoreCase("HALT"))
			return new Halt();
		else return null;
	}
/**
 * Este metod devuelve en un string en nombre del comando
 */
	public String toString(){
		return "HALT";
	}
}
