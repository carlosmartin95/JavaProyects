package controlador;

import comandos.*;

public class ParserComandos {
	/**
	 * Array de tipo Comando[] que contiene a todos los comandos.
	 */
	private static Comando[] comando = { new Paso(), new Ayuda(), new EliminarCelula(),
			new CrearCelulaSimple(), new CrearCelulaCompleja(), new Iniciar(), new Salir(), new Vaciar() };
	/**
	 * Llama a cada comando para que se compruebe a si mismo si es él el comando que se desea ejecutar
	 * @param cadenas el comando a ejecutar
	 * @return Comando devuelve el comando que representa
	 */
	public static Comando parseaComando(String[] cadenas){
		
		for (int i = 0; i < comando.length; i++) {
			
			if(comando[i].parsea(cadenas) != null){
				return comando[i];
			}
		}
		return null;
	} 
	
	/**
	 * Muestra los textos de ayuda de todos los comandos.
	 * @return El texto completo
	 */
	public static String AyudaComandos(){
		
		String texto = "";
		
		for (int i = 0; i < comando.length; i++) {
			if(comando[i].textoAyuda() != null){
				texto += "	" + comando[i].textoAyuda();
			}
		}
		
		return "POSIBLES COMANDOS: \n" + texto;
	}

}
