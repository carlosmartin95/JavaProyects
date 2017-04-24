package Comandos;

import Main.Engine;
/**
 * Esta clase contiene todos los metodos necarios del comando reset
 * */
public class Reset implements Command{
/**
 * Constructora
 * */
	public Reset() {
		super();
		// TODO Auto-generated constructor stub
	}
/**
 * Este metodo llama al metodo ejecutaReset del controlador
 * */

	public void execute(Engine controlador) {
		  controlador.ejecutaRESET();

	}
/**
 * Este metodo se encarga de buscar en el string que le llega por parametro la palabra RESET y si la encuentra crea un nuevo comando
 * RESET , si no es asi devuelve null
 * */

	public Command parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("RESET"))
			return new Reset();
		else return null;
	}
/**
 * Muestra la ayuda del comando actual
 * */

	public String textHelp() {
		return "RESET: Vacia el programa actual ";
	}
/**
 * Devuelve el nombre del comando actual
 * */
	public String toString(){
		 return "RESET";
	}
}
