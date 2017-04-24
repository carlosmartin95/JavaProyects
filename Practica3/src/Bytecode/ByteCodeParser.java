package Bytecode;


import OneParameter.Goto;
import OneParameter.IfEq;
import OneParameter.IfLe;
import OneParameter.IfLeq;
import OneParameter.IfNeq;

/**
 * En esta clase vamos a identificar la instruccion que recibmos por teclado y vamos a crear una instruccion Bytecode 
 * en funcion del tipo de instruccion que sea. 
 * */
public class ByteCodeParser {
		
	private final static ByteCode[] instruccion = {	new Add(),new Halt(), new Sub(), new Div(), new Mul(), new Push(), new Store(), new Load(),
										new IfEq(), new IfNeq(), new IfLe(), new IfLeq(),new Goto(),new Out()}; 
	/**
	 * Este metodo se encarga de identificar el tipo de instruccion que recibimos por parametro y creamos un Bytecode
	 * en funcion del tipo que sea.Si la instruccion no existe en el array devolveremos null
	 * El String que recibimos por parametro puede contener mas de una palabra ya que una instruccion a veces necesita un parametro adicional
	 * */
	public static ByteCode parse(String line){
		
		int i=0;
		boolean found = false;
		ByteCode b = null;
		
		line = line.trim();
		String []words =line.split(" +");
		words[0]=words[0].toUpperCase();
			
		while (i < instruccion.length && !found){
			b = instruccion[i].parse(words);
			if (b!=null) 
				found=true;
			i++;
		}
		return b;
	}
		
}

