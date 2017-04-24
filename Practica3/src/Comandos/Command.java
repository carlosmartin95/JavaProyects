package Comandos;

import java.io.FileNotFoundException;
import Exception.ArrayException;
import Exception.DivisionByZero;
import Exception.ExecutionError;
import Exception.LexicalAnalysisException;
import Exception.StackException;
import Main.Engine;

/**
 * La clase Command es una interfaz de la cual heredaran todos los comandos del programa
 * Atributos 
 * instruccion:Este atributo es de tipo Bytecode y contiene la instruccion bytecode que recibimos por teclado
 * replace:Este atributo contiene la posicion de la instruccion ByteCode que queremos reemplazar si se da el caso si no tnedra un -1
 * */
public interface Command {

	/**
	 * Ordena a un comanndo que se ejecute
	 * @throws ExecutionError 
	 * @throws LexicalAnalysisException 
	 * @throws ArrayException 
	 * @throws DivisionByZero 
	 * @throws StackException 
	 * @throws FileNotFoundException 
	 * */
	public void execute(Engine controlador) throws ExecutionError, LexicalAnalysisException, StackException, DivisionByZero, ArrayException, FileNotFoundException;
	/**
	 * Verifica si hexiste en el string el comando buscado
	 * */
	public Command parse(String[]s);
	/**
	 * Muestra la ayuda del comando
	 * */
	public String textHelp();
}
