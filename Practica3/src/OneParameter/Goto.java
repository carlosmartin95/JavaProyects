package OneParameter;

import Bytecode.ByteCode;
import CPU.CPU;
/**
 * Metodo que herea de inconditionalJumps ya que no necesita comparar nada solo va a la posicion que recibe por parametro
 * */
public class Goto extends InconditionalJumps{
/**
 * Constructoras
 * */
	public Goto(){}
	public Goto(int parametro) {
		super(parametro);
	}
/**
 * Metodo que busca en el string 1 la palabra GOTO y si la encuentra convierta a int el segundo string a que es su parametro adicional
 * si no lo encuentra devolvera null
 * */
	protected ByteCode parseAux(String string1, String string2) {
		if(string1.equalsIgnoreCase("GOTO")){
			return new Goto(Integer.parseInt(string2));
		}
		else return null;
	}
/**
 * Devuelve cual es el nombre del bytecode actual
 * */

	protected String toStringAux() {
		return "GOTO";
	}
/**
 * Este metodo actualiza el valor del contador del programa en fucion del parametro que tiene el bytecode
 * */
	public void execute(CPU cpu) {
		cpu.setProgramCounter(this.param);
	}




}
