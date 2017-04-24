package comandos;

import main.Constantes;
import mundo.Casilla;
import mundo.Mundo;

public class Vaciar extends Comando{

	public void ejecuta(Mundo mundo) {
		
		System.out.println("Vaciando...");
		
		for (int fila = 0; fila < Constantes.MAX_FILAS; fila++)
		{
			for (int columna = 0; columna < Constantes.MAX_COLUMNAS; columna++)
			{
				
				Casilla casilla = new Casilla(fila,columna);
				mundo.eliminarCelula(casilla);
					
				
			}
		}
	}

	public Comando parsea(String[] cadenaComando) {
		
		if(cadenaComando[0].equalsIgnoreCase("VACIAR")){
			return this;
		}
		else {
			return null;
		}
	}
	
	public String textoAyuda() {
		return "VACIAR: crea un mundo vacío\n";
	}

}
