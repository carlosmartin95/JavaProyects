package Practica1;


public class OperandStack {

	public static final int MAX_STACKS=100;

	private int[] pila;
	private int numElems;
	
	public OperandStack() {
		
		this.pila = new int[MAX_STACKS];
		this.numElems=0;
	}
	
	public boolean push(int value){
		
		boolean correcto=false;
		
		if(this.numElems<MAX_STACKS){
			
			this.pila[this.numElems]=value;
			this.numElems++;
			correcto=true;
		}
		return correcto;
	}
	
	public int pop(){
		
		int num;
		num=this.pila[this.numElems];
		this.numElems--;
		return num;
	}
	
	public String toString(){
		
		String s = null;
		int i = 0;
		
		// hay que retocarlo porque muestra una posicion null 
		// no me dio tiempo a mas
		s="Pila:";
		while( i<this.numElems){
			s+=pila[i];
			i++;
		}
		return s;
	}
}
