package Comandos;

import Bytecode.ByteCode;
import Bytecode.ByteCodeParser;
import Exception.ArrayException;
import Exception.ExecutionError;
import Exception.LexicalAnalysisException;
import Main.Engine;

import java.util.Scanner;
/**
 * Esta clase contiene todos los metodos necarios del comando replace
 * */
public class Replace implements Command{

	private int param;
	
	public Replace(){}
	public Replace(int posicion){
		this.param = posicion;
	}

	private Scanner scanner = new Scanner(System.in);
	 /*
	 * Este metodo llama al metodo se encarga de recoger la nueva instrucion a sustituir y llama a ejecutaReplace el controlador
	 * @throws ExecutionError 
	 * @throws LexicalAnalysisException 
	 * */

	public void execute(Engine controlador) throws ExecutionError, LexicalAnalysisException, ArrayException {
		String cambio;
		ByteCode instr;
		cambio=scanner.nextLine();
		instr=ByteCodeParser.parse(cambio);
		if(instr!=null){
			controlador.ejecutaREPLACE(this.param,instr);
		}
		else{
			throw new LexicalAnalysisException("La instruccion no es correcta");
		}
	}
/**
 * Este metodo se encarga de buscar en el string que le llega por parametro la palabra REPALCE y si la encuentra crea un nuevo comando
 * REPLACE , si no es asi devuelve null
 * */

	public Command parse(String[] s) {
		if (s.length==2 && s[0].equalsIgnoreCase("REPLACE"))
			return new Replace(Integer.parseInt(s[1]));
		
		else return null;
	}
/**
 * Muestra la ayuda del comando actual
 * */

	public String textHelp() {
		
		return "REPLACE N: Reemplaza la instruccion N por la solicitada al usuario";
	}
/**
 * Devuelve el nombre del comando actual
 * */	
	public String toString(){
		 return "REPLACE";
	}
}
