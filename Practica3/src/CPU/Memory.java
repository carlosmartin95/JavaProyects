package CPU;

/**
 * La clase Memory se encarga de la gestion de la memoria, tanto como de  la redimension de esta como de 
 * escribir en ella nuevos datos que nos llegan.
 */

public class Memory {
	
	private static final int MAX_MEMORY=200;
	private int size;
	private boolean empty;
	private Integer []memoria;
	
	/**
	 * constructora Memory donde inicializamos la memoria con un array de enteros
	 * y la llenamos de null
	 */
	public Memory() {
		
		this.empty=true;
		this.memoria=new Integer[Memory.MAX_MEMORY];
		this.size=Memory.MAX_MEMORY;
		for(int i=0;i<this.size;i++) 
			this.memoria[i]=null;
	}
	
	/**
	 * Metodo resiza para la redimension de la memoria en caso de que queramos acceder
	 * en una posición fuera de los limites de nuestro array de integers.
	 */
	private void resize(int pos){
		
		Integer []temp;
		if(pos>=this.size){
			temp=this.memoria;
			this.memoria = new Integer[pos*2];						
			for (int i = 0; i < temp.length; i++) {
				this.memoria[i] = temp[i];
			}
			this.size=pos*2;	
		}
	}
	
	/**
	 * Metodo que simplemente escribe en una posicion que nos llega por parametro
	 * y establece a false le atributo privado empty
	 */
	public void write(int pos,int value){
		if(pos>this.size){
			this.resize(pos);
		}
		this.memoria[pos]=value;
		this.empty=false;
	} 
	
	/** 
	 * Este metodo nos devuelve el valor que existe en la posicion de memoria que le pasamos 
	 * */
	public int read(int pos){
		if(this.memoria[pos]==null){
			return 0;
		}
		return this.memoria[pos];
	}
	
	/**
	 * Metodo toStrin que nos devuelve en un String la memoria
	 */
	public String toString(){
		String s ="";
		if(this.empty)return "<vacia>";
		else{

			for(int i=0;i<this.size;i++)
				if(this.memoria[i]!=null) s = s + "[" + i + "]:" + this.memoria[i];
		}
		return s;
	}
	
	/**
	 * devuelve el tamaño del array memoria
	 */
	public int getSize() {
		return this.size;
	}
/**
 * Verifica si la memoria esta vacia
 * */	
	public boolean getEmpty(){
		return this.empty;
	}
}
