package Comandos;

import Main.Engine;
/**
 * Esta clase contiene todos los metodos necarios del comando quit
 * */
public class Quit implements Command{
/**
 * Constructoras
 * */
	public Quit() {
		super();
	}
/**
 * Este metodo llama al metodo endExecution del controlador
 * */
	public void execute(Engine controlador) {
		controlador.endExecution();
	}
/**
 * Este metodo se encarga de buscar en el string que le llega por parametro la palabra QUIT y si la encuentra crea un nuevo comando
 * QUIT , si no es asi devuelve null
 * */
	public Command parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("QUIT"))
			return new Quit();
		
		else return null;
	}

/**
 * Muestra la ayuda del comando actual
 * */	
	public String textHelp() {
	
		return "QUIT: Termina con la ejecucion del programa" ;
	}
/**
 * Devuelve el nombre del comando actual
 * */	
	public String toString(){
		String s = "QUIT";
		return s;
	}
}
