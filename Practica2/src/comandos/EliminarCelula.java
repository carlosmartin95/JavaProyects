package comandos;

import mundo.Casilla;
import mundo.Mundo;

public class EliminarCelula extends Comando {
	
	private Casilla casilla;

	public void ejecuta(Mundo mundo) {
		if (mundo.comprobarLimitesYOcupacion(casilla ,true))
			mundo.crearCelulaCompleja(casilla);
		else
			System.out.println("Posicion incorrecta o libre");
	}

	public Comando parsea(String[] cadenaComando) {
		
		if(cadenaComando[0].equalsIgnoreCase("ELIMINARCELULA")){
			this.casilla = new Casilla(Integer.parseInt(cadenaComando[1]), Integer.parseInt(cadenaComando[2]));
			return this;
		}
		else {
			return null;
		}
	}

	public String textoAyuda() {
		return "ELIMINARCELULA F C: elimina una celula simple de la posición (f,c) si es posible\n";
	}

}
