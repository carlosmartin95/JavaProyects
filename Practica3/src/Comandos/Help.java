package Comandos;

import Main.Engine;
/**
 * Esta clase contiene todos los metodos necarios del comando help
 * */
public class Help implements Command{

	public Help(){}
	
/**
 * Este metodo se encarga de llamar a verAyuda que s eencuantra en el controlador,
 *  si todo va bien deuvelve un true si no es asi un false
 * */
	public void execute(Engine controlador) {
			CommandParser.showHelp();
	}
/**
 * Este metodo se encarga de buscar Help en el string que le llega por parametro.
 * Si lo encuentra llama a la constructora de help si no se da el caso devuelve null
 * */

	public Command parse(String[] s) {
		
		if (s.length==1 && s[0].equalsIgnoreCase("HELP"))
			return new Help();
		else return null;
	}
/**
 * Devuelve la ayuda de help en un string
 */
	public String textHelp() {
		return "HELP: muestra esta ayuda";
	}
/**
 * Devuelve en un string el nombre del comando
 * */	
	public String toString(){
		return "HELP";
	}

}
