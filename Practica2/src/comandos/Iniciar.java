package comandos;

import main.Constantes;
import mundo.Casilla;
import mundo.Mundo;

public class Iniciar extends Comando{
	
	public void ejecuta(Mundo mundo){
		System.out.println("Iniciando un mundo nuevo....");
		for (int fila = 0; fila < Constantes.MAX_FILAS; fila++)
		{
			for (int columna = 0; columna < Constantes.MAX_COLUMNAS; columna++)
			{
				
				Casilla casilla = new Casilla(fila,columna);
				mundo.eliminarCelula(casilla);
					
				
			}
		}
		
		int filaAleatoria, columnaAleatoria;
		boolean correcta = false;
	
		for (int i = 0; i < Constantes.CELULAS_SIMPLES_INICIALES; i++){
			
			while (!correcta){
				filaAleatoria = (int) (Math.random() * Constantes.MAX_FILAS);
				columnaAleatoria = (int) (Math.random() * Constantes.MAX_COLUMNAS);
				Casilla casilla = new Casilla(filaAleatoria, columnaAleatoria);
				
				correcta = mundo.comprobarLimitesYOcupacion(casilla, false);
				
				if(correcta){
					mundo.crearCelulaSimple(casilla);
				}
			}	
			correcta = false;
		}
	
		// calculamos posciones aleatorias para las celulas complejas
		for (int i = 0; i < Constantes.CELULAS_COMPLEJAS_INICIALES; i++){
			
			while (!correcta){
				filaAleatoria = (int) (Math.random() * Constantes.MAX_FILAS);
				columnaAleatoria = (int) (Math.random() * Constantes.MAX_COLUMNAS);
				Casilla casilla = new Casilla(filaAleatoria, columnaAleatoria);
				
				//Llamada al metodo para comprobar si se puede meter
				correcta = mundo.comprobarLimitesYOcupacion(casilla, false);
				
				if(correcta){
					mundo.crearCelulaCompleja(casilla);
				}
			}	
			correcta = false;
		}
	}

	public Comando parsea(String[] cadenaComando) {
		if(cadenaComando[0].equalsIgnoreCase("INICIAR")){
			return this;
		}
		else {
			return null;
		}
	}
	
	public String textoAyuda() {
		return "INICIAR: inicia una nueva simulacion\n";
	}

}
