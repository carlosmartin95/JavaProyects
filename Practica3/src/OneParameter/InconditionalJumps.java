package OneParameter;

import Bytecode.ByteCodeOneParameter;
/**
 * Clase que hereda de ByteCodeOneParameter ya que necesita un parametro para poder funcionar
 * En este caso no tiene que comparar nada solo cambiar el valor del contador del programa
 * */
public abstract class InconditionalJumps extends ByteCodeOneParameter{
	/**
	 * Constructoras
	 * */
	public InconditionalJumps(){}
	public InconditionalJumps(int j){
		super(j); 
	}
	

}
