package Comandos;

import Exception.ArrayException;
import Exception.DivisionByZero;
import Exception.ExecutionError;
import Exception.LexicalAnalysisException;
import Exception.StackException;
import Main.Engine;
/**
 * Esta clase contiene todos los metodos necesarios para la implementacion de la clase compile
 * */
public class Compile implements Command{
/**
 * Construtora vacia 
 * */
	public Compile(){}
/**
 * Este metodo se encarga de llamar al compile del controlador y recoger sus excepciones 
 * */	
	@Override
	public void execute(Engine controlador)
			throws ExecutionError, LexicalAnalysisException, StackException, DivisionByZero, ArrayException {
		controlador.compile();		
	}
/**
 * Este metodo se encarga de parsear un string y verificar que el string contenga o no la palabra compile
 * */
	@Override
	public Command parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("Compile"))
			return new Compile();
		else return null;
	}
/**
 * Muestra el mensaje de ayuda del comando compile
 * */
	@Override
	public String textHelp() {
		return "COMPILE: Analización léxica del programa fuente";
	}
/**
 * Este metodo devuelve el nombre de la clase en un  String
 * */
	public String toString(){
		return "COMPILE";
	}
}
