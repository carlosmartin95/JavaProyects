package comandos;

import controlador.ParserComandos;
import mundo.Mundo;

public class Ayuda extends Comando {

	
	public void ejecuta(Mundo mundo) {
		System.out.println(ParserComandos.AyudaComandos());
	}

	public Comando parsea(String[] cadenaComando) {
		
		if(cadenaComando[0].equalsIgnoreCase("AYUDA")){
			return this;
		}
		else {
			return null;
		}
	}


	public String textoAyuda() {
		return "AYUDA: muestra esta ayuda\n";
	}

}
