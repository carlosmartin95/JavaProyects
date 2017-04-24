package Comandos;
/**
 * Esta clase contiene todos lo metodos necesarios para realizar una carga de archivo
 * */
import java.io.FileNotFoundException;
import Exception.ArrayException;
import Exception.DivisionByZero;
import Exception.ExecutionError;
import Exception.LexicalAnalysisException;
import Exception.StackException;
import Main.Engine;

public class LoadFich implements Command{
	/**
	 * Este es su unico atributo y contiene el nombre del archivo que queremos cargar
	 * */
	private String fichero;
	/**
	 * Constructoras
	 * */
	public LoadFich (){	}

	public LoadFich(String string) {
		this.fichero = string;
	}
/**
 * Este metodo se encarga de llamar al metodo cargar del engine y controlar recoger sus excepciones
 * */
	@Override
	public void execute(Engine controlador) throws ExecutionError, LexicalAnalysisException, StackException,
			DivisionByZero, ArrayException, FileNotFoundException {
		controlador.cargarFichero(this.fichero);
	}
	/**
	 * Este metodo se encarga de verificar que el string que recibe por parametro contenga o no la palabra load
	 * */
	@Override
	public Command parse(String[] s) {
		if (s[0].equalsIgnoreCase("LOAD"))
			return new LoadFich(s[1]);
		else return null;
		
	}
	/**
	 * Este metodo se encarga de devolver en un string el mensaje de ayuda de este comando
	 * */
	@Override
	public String textHelp() {
		return "LOAD FICH: Carga de Fichero";
	}
	/**
	 * Devuelve el nombre de  la clase en un string
	 * */
	public String toString(){
		return "LOAD FICH " + this.fichero;
	}


}
