package comandos;

import mundo.Casilla;
import mundo.Mundo;

public class CrearCelulaCompleja extends Comando {

	private Casilla casilla;

	public void ejecuta(Mundo mundo) {
		if (mundo.comprobarLimitesYOcupacion(casilla, false))
			mundo.crearCelulaCompleja(casilla);
		else
			System.out.println("Posicion ocupada o incorrecta");
	}

	public Comando parsea(String[] cadenaComando) {

		if (cadenaComando[0].equalsIgnoreCase("CREARCELULACOMPLEJA")) {
			casilla = new Casilla(Integer.parseInt(cadenaComando[1]), Integer.parseInt(cadenaComando[2]));
			
			return this;
		} else {
			return null;
		}
	}

	public String textoAyuda() {
		return "CREARCELULACOMPLEJA F C: crea una nueva celula compleja en la posición (f,c) si es posible\n";
	}

}
