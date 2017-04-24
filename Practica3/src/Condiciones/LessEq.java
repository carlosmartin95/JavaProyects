package Condiciones;

import Instruction.Term;
import OneParameter.ConditionalJumps;
import OneParameter.IfLeq;
/**
 * Esta clase hereda de Condition y contiene todo lo necesario para crea una clase condition del tipo LessEqual si los datos proporcionados
 * por el comando son correctos
 * */
public class LessEq extends Condition{
	/**
	 * Constructora
	 * */
	public LessEq(){
		super();
	}
	/**
	 * ESte metodo se encarga de mandar al padre unos parametros para que cree una condicion
	 * */
	public LessEq(Term t1, Term t2, ConditionalJumps condition) {
		super(t1, t2, condition);
	}
	/**
	 * Devuelve el nombre de la clase en un string
	 * */
	protected String toStringAux() {
		return "LessEqual";
	}
	/**
	 * Este metodo se encarga de parseas un conjunto de parametros que recibe por referencai y si todo esta correcto creara una
	 * condicion del tipo de esta clase
	 * */
	@Override
	protected Condition parseAux(Term t1, Term t2, String op) {
		if(op.equalsIgnoreCase("<=")){
			ConditionalJumps condition = new IfLeq();
			return new LessEq(t1,t2, condition);
		}
		else return null;
	}

}
