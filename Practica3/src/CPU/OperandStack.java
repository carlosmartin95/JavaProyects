package CPU;

import Exception.StackException;

/**
 * Metodo encargado de trabajar con la Pila... pop para sacar un valor y 
 * push para introducirlo
 */
public class OperandStack {

	public static final int MAX_STACKS=100;
	private int[] pila = null;
	private int numElems;
	
	/**
	 * Constructora de la pila que inicializa el arary 
	 * pila  con tamaño 0
	 */
	public OperandStack() {
		this.pila = new int[MAX_STACKS];
		this.numElems=0;
	}
	
	/**
	 * devuelve true si la pila esta vacia y false si tiene contenido
	 */
	public boolean isEmpty(){
		if(this.numElems==0)
			return true;
		else return false;
	}
	
	/**
	 * devuelve el numero de elementos que tien el array pila
	 */
	public int getNumElemns(){
		return this.numElems;
	}
	
	/**
	 * dado un valor, introduce dicho valor en la memoria en la posicion 
	 * indica. Si existe hueco en ella.
	 * @return 
	 * @throws StackException 
	 */
	public int push(int value) throws StackException{
		if(this.numElems<MAX_STACKS){
			this.pila[this.numElems]=value;
			this.numElems++;
		}
		else{
			throw new StackException("La pila esta llena no se puede añadir mas elementos");
		}
		return value;
	}
	
	/**
	 * Saca un valor de la pila y resta una posicion al numero de elementos
	 */
	public int pop(){
		int num;
		num=this.pila[this.numElems-1];
		this.numElems--;
		return num;
	}
	
	/**
	 * Metodo toString que nos devuelve en un String la pila
	 */
	public String toString(){
		
		if(this.numElems==0)return"<vacia>";
		else{
			String s="";
			for(int i=0;i<this.numElems;i++) s= s + this.pila[i] + " ";
			return s;
		}
	}
}
