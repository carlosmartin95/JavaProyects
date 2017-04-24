package Condiciones;

import Instruction.Term;
import OneParameter.ConditionalJumps;
import OneParameter.IfNeq;
/**
 * Esta clase hereda de Condition y contiene todo lo necesario para crea una clase condition del tipo NotEqual si los datos proporcionados
 * por el comando son correctos
 * */
public class NotEqual extends Condition{
	/**
	 * Constructora
	 * */
	public NotEqual() {
		super();
	}
	/**
	 * ESte metodo se encarga de mandar al padre unos parametros para que cree una condicion
	 * */
	public NotEqual(Term t1, Term t2, ConditionalJumps condition) {
		super(t1, t2, condition);
	}
	/**
	 * Devuelve el nombre de la clase en un string
	 * */
	protected String toStringAux() {
		return "NotEqual";
	}
	/**
	 * Este metodo se encarga de parseas un conjunto de parametros que recibe por referencai y si todo esta correcto creara una
	 * condicion del tipo de esta clase
	 * */
	@Override
	protected Condition parseAux(Term t1, Term t2, String op) {
		if(op.equalsIgnoreCase("!=")){
			ConditionalJumps condition = new IfNeq();
			return new NotEqual(t1,t2, condition);
		}
		else return null;
	}

}
