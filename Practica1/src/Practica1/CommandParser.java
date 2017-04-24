package Practica1;

public class CommandParser {

	public Command parse(String line) {
		line = line.trim();
		String []words = line.split(" +");
		words[0]=words[0].toLowerCase();
		
		if(words.length==1){
			if(words[0].equalsIgnoreCase("HELP")){
				return new Command(ENUM_COMMAND.HELP);
			}else if(words[0].equalsIgnoreCase("RUN")){
				return new Command(ENUM_COMMAND.RUN);
			}

			else if(words[0].equalsIgnoreCase("QUIT")){
				return new Command(ENUM_COMMAND.QUIT);
			}
			else if(words[0].equalsIgnoreCase("RESET")){
				return new Command(ENUM_COMMAND.RESET);
			}

		}
		else if(words.length==2){
			int parametro= Integer.parseInt(words[1]);
			if(words[0].equalsIgnoreCase("REPLACE")){
				return new Command(ENUM_COMMAND.REPLACE,parametro);
			}
			else{//Caso del NEWINST
				return new Command(ENUM_COMMAND.NEWINST,parametro);
			}
		}
		return null;
	}
}
