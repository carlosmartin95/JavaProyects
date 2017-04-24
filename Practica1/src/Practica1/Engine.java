package Practica1;

import java.util.Scanner;

public class Engine {

	public Engine() {}

	public void start(){
		
		CommandParser comando=new CommandParser();
		
		// la excepcion era porque usabamos readline en vez de scanner in
		
		
		//String lectura=null;
		//InputStreamReader leer= new InputStreamReader(System.in);
		//BufferedReader br = new BufferedReader(leer);
		//lectura=br.readLine();
		
		Scanner scanner = new Scanner(System.in);
		String lectura = lectura= scanner.nextLine();
		
		while(comando.parse(lectura)!=null && comando.parse(lectura)!=comando.parse("QUIT")){
			lectura= scanner.nextLine();	
			Command c=comando.parse(lectura);
			c.execute(this);
		}
	}

	public boolean endExecution() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean showHelp() {
		System.out.println("HELP:Muestra esta ayuda");
		System.out.println("QUIT:Cierra la aplicacion");
		System.out.println("RUN: Ejecuta elprograma");
		System.out.println("NEWINTS BYTECODE: Introduce una nueva instruccion al programa");
		System.out.println("RESET: Vacia el programa actual");
		System.out.println("REPLACE: Reemplaza la instruccion N por la solicitada al usuario");
		return true;
	}

	public boolean ejecutaRUN() {
		// TODO Auto-generated method stub
		return false;
	}
	// he completado las instrucciones comando
	public boolean ejecutaRESET() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean ejecutaREPLACE() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean ejecutaNEWINST() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
