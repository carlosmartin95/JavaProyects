package comandos;
import mundo.Mundo;

public abstract class Comando {
	
	/**
	 * Ejecuta el comando correspondiente en el mundo
	 * @param mundo
	 */
	public abstract void ejecuta(Mundo mundo);

	/**
	 * El comando se comprueba si es cadenaComando, si es se crea.
	 * @param cadenaComando
	 * @return El comando creado
	 */
	public abstract Comando parsea(String[] cadenaComando);
	
	
	/**
	 * Devuelve que hace el comando.
	 * @return el texto de ayuda
	 */
	public abstract String textoAyuda();
	
	//Devuelve un String con la informacion de ayuda que se quiere mostrar sobre el comando.
}


