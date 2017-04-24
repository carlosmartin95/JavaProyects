package main;
import java.util.Scanner;
import controlador.Controlador;
import mundo.Mundo;

/**
 * Clase que contiene el metodo main.
 * Crea el mundo, crea el controlador y su scanner.
 * Comienza la simulacion invocando a controlador
 * @version 1 15/11/2015
 * @author Marcos Calero y Carlos Martin
 */

public class Main {
	
	public static void main(String[] args) {
				
		Scanner in = new Scanner(System.in);
		
		Mundo mundo = new Mundo(Constantes.CELULAS_SIMPLES_INICIALES, Constantes.CELULAS_COMPLEJAS_INICIALES);
		
		Controlador controlador = new Controlador(mundo, in);
		
		controlador.realizarSimulacion();
	}

}
