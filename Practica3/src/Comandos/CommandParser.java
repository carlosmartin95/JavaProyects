package Comandos;


/**
 * Esta Clase se encarga de identificar el String que nos llega por teclado y buscar si es algun dato que tenemos en el array commands
 * */
public class CommandParser {

	private final static Command[] commands = {	new Help(), new Quit(), new Reset(),
										new Replace(), new Run(), new Compile(), new LoadFich()};

	/**
	 * Este metodo se encarga de recibir por parametro un String que puede contener una instruccion 
	 * del tipo Command si es asi llamara al la constructora de Command para crea un nuevo comando.
	 * Si no existe ese comando devolvera un null;
	 * */
	public static Command parse(String line) {
		
		int i=0;
		boolean found = false;
		Command c = null;
		
		line = line.trim();
		String []words = line.split(" +");
		words[0]=words[0].toUpperCase();
	
		while (i < commands.length && !found){
			c = commands[i].parse(words);
			
			if (c!=null) 
				found=true;
			else i++;
		}
		return c;
	}
	/**
	 * Este metodo llama a todas las ayudas de los comandos que tenemos en el array commands
	 * */	
	public static void showHelp(){
		for (int i=0; i < CommandParser.commands.length; i++)
		 System.out.println(i+". "+CommandParser.commands[i].textHelp());
	}
}
