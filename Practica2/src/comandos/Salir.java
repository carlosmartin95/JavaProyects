package comandos;

import mundo.Mundo;

public class Salir extends Comando{

	public void ejecuta(Mundo mundo) {
		mundo.setSimulacionTerminada(false);
	}

	public Comando parsea(String[] cadenaComando) {
		if(cadenaComando[0].equalsIgnoreCase("SALIR")){
			return this;
		}
		else {
			return null;
		}
	}

	public String textoAyuda() {
		return "SALIR: abandona la aplicacion\n";
	}

}
