package Comandos;

import Exception.ArrayException;
import Exception.DivisionByZero;
import Exception.StackException;
import Main.Engine;
/**
 * Esta clase contiene todos los metodos necarios del comando run
 * */
public class Run implements Command{
/**
 * Constuctora
 * */
	public Run() {
		super();
	}
/**
 * Este metodo se encarga de buscar en el string que le llega por parametro la palabra RUN y si la encuentra crea un nuevo comando
 * Run , si no es asi devuelve null
 * */
	public Command parse(String[] s) {
		
		if(s.length == 1 && s[0].equalsIgnoreCase("RUN"))
			return new Run();
		else return null;
	}
/**
 * Muestra la ayuda del comando actual
 * */

	public String textHelp() {		
		String s = "RUN: Ejecuta todas las instrucciones";
		return s;
	}
/**
 * Devuelve el nombre del comando actual
 * */		
	public String toString(){
		return "RUN";
	}
/**
 * Este metodo llama al metodo ejecutaRUN del controlador
 * @throws ArrayException 
 * @throws DivisionByZero 
 * @throws StackException 
 * */

	public void execute(Engine controlador) throws StackException, DivisionByZero, ArrayException {
		controlador.ejecutaRUN();
	}
}
