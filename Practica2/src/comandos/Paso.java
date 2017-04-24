package comandos;

import mundo.Mundo;

public class Paso extends Comando {

	
	public void ejecuta(Mundo mundo) {
		mundo.evoluciona();
	}

	public Comando parsea(String[] cadenaComando) {
		
		if(cadenaComando[0].equalsIgnoreCase("PASO")){
			return this;
		}
		else {
			return null;
		}
	}
	
	public String textoAyuda() {
		return "PASO: realiza un paso en la simulacion\n";
	}

}
