package Practica1;

public class Memory {
	
	private static final int MAX_MEMORY=200;
	private Integer []memoria;
	private int numMem;
	
	public Memory() {
		
		this.memoria=new Integer[MAX_MEMORY];
		this.numMem=0;
	}
	
	public boolean write(int pos,int value){
		
		boolean posible=false;
		
		if(this.memoria[pos]==null){
			
			this.memoria[pos]=value;
			posible= true;
		}
		return posible;
	} 
	
	public int read(int pos){
		return this.memoria[pos];
	}
	
	
	public String toString(){
		
		String s = null;
		int i = 0;
		
		while( memoria[i] != null){
			
			s += memoria[i];
			i++;
		}
		
		if (s == null){
			
			s = "Memoria Vacia ";
		}

		return s;
	}
}
