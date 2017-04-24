package Bytecode;

/***
 * Esta clase hereda de bytecode y es la encargada de recoger aquellos comandos que necesiten 
 * un parametro por lo tanto tendra un atributo
 *  param de tipo int protegido para que todas las que hereden de el puedan usarlo 
 * */
public abstract class ByteCodeOneParameter implements ByteCode {

	protected int param;
	/**
	 * Esta clase contiene dos constructoras una vacia y una que inicializa el parameto al valor que le lleva por referencia
	 * */
	public ByteCodeOneParameter(){};
	public ByteCodeOneParameter(int parametro) {
		this.param = parametro;
	}
/**
 * Este metodo se encarga de verificar si el string que le llega por parametro contiene varios strings, si es asi 
 * eso quiere decir que es del tipo de esta clase si no, devolvera null
 * */
	public ByteCode parse(String[] words) {
		
		if (words.length!=2) 
			return null;
		else return this.parseAux(words[0],words[1]);
	}
/**
 * Este metodo abstracto se encarga de pasesear los strings que le llega por parametro
 * */	
	abstract protected ByteCode parseAux(String string1, String string2);
	/**
	 * este metod devuvel en un string el nomre y el paramtero del comando que estamos realizando
	 * */
	public String toString(){
		return this.toStringAux() + " " + this.param;
	}
	/**
	 * Este metod devuelve en un string unico el comando que terngamos entre manos
	 * */
	abstract protected String toStringAux();
	
	public void setParam(int param){
		this.param = param;
	}
	
	public int getParam(){
		return this.param;
	}
}
