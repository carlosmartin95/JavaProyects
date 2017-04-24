package Practica1;

public class Command {
	
	private ENUM_COMMAND comand;
	private ByteCode instruction;
	private int replace;
	
	
	
	public Command(ENUM_COMMAND comando,int replace){
		this.comand=comando;
		this.replace=replace;
	}
	
	public Command(ENUM_COMMAND help) {
		// TODO Auto-generated constructor stub

	}

	private ENUM_COMMAND getComando(){
		return this.comand;
	}
	
	public boolean execute(Engine controlador){
		
		if(this.getComando()== ENUM_COMMAND.QUIT){
			return controlador.endExecution();
		}
		else if(this.getComando()==ENUM_COMMAND.HELP){
			return controlador.showHelp();
		}
		else if(this.getComando()==ENUM_COMMAND.RUN){
			return controlador.ejecutaRUN();
		}
		else if(this.getComando()==ENUM_COMMAND.RESET){
			return controlador.ejecutaRESET();
		}
		else if(this.getComando()==ENUM_COMMAND.REPLACE){
			return controlador.ejecutaREPLACE();	
		}
		else if(this.getComando()==ENUM_COMMAND.NEWINST){
			return controlador.ejecutaNEWINST();
		}
	
		return false;
	}
}
