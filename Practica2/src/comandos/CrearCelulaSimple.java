package comandos;

import mundo.Casilla;
import mundo.Mundo;

public class CrearCelulaSimple extends Comando {

	private Casilla casilla;

	public void ejecuta(Mundo mundo) {
		if (mundo.comprobarLimitesYOcupacion(casilla,false))
			mundo.crearCelulaSimple(casilla);
		else
			System.out.println("Posicion ocupada o incorrecta");
	}

	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando[0].equalsIgnoreCase("CREARCELULASIMPLE")) {
			this.casilla = new Casilla(Integer.parseInt(cadenaComando[1]), Integer.parseInt(cadenaComando[2]));
			return this;
		} else {
			return null;
		}
	}

	public String textoAyuda() {
		return "CREARCELULASIMPLE f c: crea una nueva celula simple en la posición (f,c) si es posible\n";
	}

}
