package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Bytecode.ByteCode;
import Bytecode.ByteCodeParser;
import CPU.CPU;
import Comandos.Command;
import Comandos.CommandParser;
import Exception.ArrayException;
import Exception.DivisionByZero;
import Exception.ExecutionError;
import Exception.LexicalAnalysisException;
import Exception.StackException;
import Instruction.LexicalParser;

/**
 * Clase Engine, una de las mas importantes. Leemos por consola el comando, y en su constructora nos creamos 
 * un ByteCodeProgram que contendra todas las instrucciones del programa, asi como un boolean end que mas
 * adelante nos marcara el fin de la ejecucion del programa. 
 */

public class Engine {
	
	private static Scanner scanner = new Scanner(System.in);
	private ByteCodeProgram program;
	private SourceProgram sProgram;
	private ParsedProgram pProgram;
	private boolean end;
	
	/**
	 * constructora
	 * creamos un Engine con el parametro end a false para que inicie la ejecucion del bucle de instrucciones que guardamos en el program
	 */
	public Engine() {
		this.program=new ByteCodeProgram();
		this.sProgram = new SourceProgram();
		this.pProgram = new ParsedProgram();
		this.end=false;
	}

	/**
	 * bucle para la continuacion del prgrama mientras !end. leemos de consola el comando y lo parseamos.
	 * controlamos que el comando sea correcto y/o exista e invocamos al metodo execute para  
	 * ejecutar dicho comando
	 * @throws FileNotFoundException 
	 */
	public void start(){
				
		String line="";
		
		while(!end){
		
		 System.out.print("> ");
		 Command command =null;
		 line=scanner.nextLine();
		 command=CommandParser.parse(line);
		 if(command==null){
		 	System.out.println("Instruccion incorrecta");
		 }
		 else{
		 	System.out.println("Comienza la ejecucion de "+command.toString());
		 	try {
				command.execute(this);
		 	} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println(e.toString());
			} catch (ExecutionError e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			} catch (LexicalAnalysisException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			} catch (StackException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			} catch (DivisionByZero e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			} catch (ArrayException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		 	finally {
		 		System.out.println("Programa fuente almacenado:");
		 		System.out.println(this.sProgram.toString());
		 		System.out.println("Programa ByteCode almacenado:");
		 		System.out.println(this.program.toString());
		 	}
		 	}
		 	if(!this.program.is_empty()){
		 		this.program.toString();
		 	}
		 }
		 
		System.out.println("Fin de la ejecucion");
		scanner.close();
	}

	/**
	 * cambiamos el estado del atributo a true 
	 */
	public void endExecution() {
		this.end=true;
	}

	/**
	 * Bucle que nos ejectura todos los comandos almacenados en el array de ByteCodes
	 * llamaos al metodo toString de la CPU para mostrarlosn y una vez mostrados, nos muestra
	 * tambien el estado tanto de la pila como de la memoria
	 * @throws ArrayException 
	 * @throws DivisionByZero 
	 * @throws StackException 
	 */
	public void ejecutaRUN() throws StackException, DivisionByZero, ArrayException {
		CPU cpu = new CPU(this.program);
			cpu.run();
			System.out.println("El estado de la maquina tras ejecutar el programa es:");
			System.out.println(cpu.toString());
	}	

	
	/**
	 * Reseteamos el programa, es decir, vaciamos el array de instrucciones ByteCode
	 */
	public void ejecutaRESET() {
		this.program=new ByteCodeProgram();
		this.pProgram=new ParsedProgram();
		this.sProgram=new SourceProgram();
	}
	
	/**
	 * Leemos de consola la posicion de la pila donde queremos sustituir la instruccion.
	 * y si es correcta la reemplazara sustiyendo en el array de ByteCodes
	 * @throws ArrayException 
	 */
	public void ejecutaREPLACE(int replace,ByteCode instr) throws ArrayException {
		this.program.reemplazar(replace,instr);
	}
	
	/**
	 * Parseamos la instruccion ByteCode, comprobamos que no sea
	 * nula,y la añadimos al ByteCodeProgram devolviendo el
	 * exito de la operacion con true o false
	 * @throws ArrayException 
	 */
	public void readByteCodeProgram() throws ArrayException{
		String comando;
		ByteCode instr;
		do{			
			comando = scanner.nextLine();
			if (!comando.equalsIgnoreCase("END")){
				instr = ByteCodeParser.parse(comando);
				if(instr!=null ){
					this.program.anadir(instr);
				}
			}	
		}while(!comando.equalsIgnoreCase("END"));
	}
	/**
	 * Este metodo se encarga de analizar el codigo que llemos del archivo lo parsea , lo compila y acaba dejando un codigo
	 * bytecode que sustituye al codigo de lato nivel que hemos cargado
	 * */
	public void compile() throws LexicalAnalysisException, ArrayException {
		try {
			this.lexicalAnalysis();
			this.generateByteCode();
		}
		catch(LexicalAnalysisException e){
			System.out.println(e.toString());
		}
		catch(ArrayException a){
			System.out.println(a.toString());
		}
	}
	
	/**
	 * Este metodo se encarga de realizar el analisis del codigo y convertirlo en un codigo parseado
	 * */
	private void lexicalAnalysis() throws LexicalAnalysisException, ArrayException {
		LexicalParser lexicalParser=new LexicalParser(this.sProgram);
		lexicalParser.lexicalParser(this.pProgram, "END");
	}
	/***
	 * 	genera el programa bytecode a partir del programa parseado
	 */
	private void generateByteCode() throws ArrayException {
		Compiler compilar = new Compiler();
		compilar.compile(this.pProgram);
		
		this.program=compilar.getbcProgram();
	}
/**
 * Como su propio nombre indica se encarga de cargar de un fichero el programa a parsear y compilar
 * */
	public boolean cargarFichero(String nombreFichero) throws ArrayException {

		String linea;
		FileReader fichero;
		
		try {
			fichero = new FileReader(nombreFichero);
		} catch (FileNotFoundException e) {
			System.out.println("Excepcion: Fichero no encontrado");
			return false;
		}
		BufferedReader b = new BufferedReader(fichero);

		try {
			linea = b.readLine();
			while (linea != null) {
				this.sProgram.anadir(linea);
				linea = b.readLine();
			}
			b.close();
		} catch (IOException e) {
			System.out.println("Excepcion: Problema en la entrada de datos");
			return false;
		}
		return true;
	}

}
