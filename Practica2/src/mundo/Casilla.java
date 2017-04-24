package mundo;

public class Casilla {
	/**
	 * Paramemtro x, tomado com fila de la casilla
	 */
	private int x; 
	/**
	 * Parametro y, tomando como columna de la casilla
	 */
	private int y; 
		
	public Casilla(int x, int y){
		
	this.x = x;
	this.y = y;
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
