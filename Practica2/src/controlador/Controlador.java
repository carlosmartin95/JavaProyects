package controlador;
import java.util.Scanner;
import mundo.*;
import main.*;
import comandos.*;
/**
 * Se encarga de ejecutar los comandos que introduzca el usuario.
 */

public class Controlador {
	
	private Mundo mundo;
	private Scanner in;
	
	/**
	 * Inicializa los atributos
	 * @param mundo Mundo sobre el que ejecutar los comandos
	 * @param in Scanner que realiza las operaciones de lectura
	 */
	public Controlador(Mundo mundo, Scanner in){
		
		this.mundo = mundo;
		this.in = in;
	}

	/**
	 * Toma las opciones leidas del teclado y ejecuta las funciones pertinentes.
	 */
	
	public void realizarSimulacion(){

		String entrada = null;
		String[] palabras = new String[Constantes.TAMANO_PARSER];
		
		while (mundo.esSimulacionTerminada()){
				
			mundo.mostrarMundo();
			System.out.print("Comando > ");
			entrada = in.nextLine();
			
			// genera el array de palabras (palabras)
			palabras = entrada.split(" ");
			
			Comando comando = ParserComandos.parseaComando(palabras);
		
			if (comando!= null)
				comando.ejecuta(mundo);
			
			else
				System.out.println("ERROR: Comando desconocido");
			
		}
		System.out.println("Hasta la proxima");
	}
}
