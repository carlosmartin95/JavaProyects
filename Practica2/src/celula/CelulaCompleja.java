package celula;
import main.Constantes;
import mundo.Casilla;
import mundo.Superficie;

public class CelulaCompleja extends Celula {
	/**
	 * Contador de las celulas que lleva comidas la celula compleja
	 */
	private int contadorComidas;
	/**
	 * Array con las casillas vecinas posibles
	 */
	private Casilla[] casillasVecinas;
	/**
	 * Casilla a la que la celula compleja evolucionara
	 */
	private Casilla casillaFinal;
	
	public CelulaCompleja(int contadorComidas){
		
		super();
		this.esComestible = false;
		this.contadorComidas = contadorComidas;
	}
	
	
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) {
		
		this.casillasVecinas = new Casilla[Constantes.MAX_COLUMNAS*Constantes.MAX_FILAS];
		Casilla casillaOrigen = new Casilla(f, c);
		int contador = 0;
			
		
		for (int i = 0; i < Constantes.MAX_FILAS; i++) {
			for (int j = 0; j < Constantes.MAX_COLUMNAS; j++) {
				
				casillasVecinas[contador] =  new Casilla(i, j);
				contador++;
			}
		}

		casillaFinal = casillasVecinas[(int) (Math.random() * contador - 1)];

		// Si la posicion esta OCUPADA ---> se mueve
		if (superficie.comprobarOcupacion(casillaFinal.getX(), casillaFinal.getY())) {

			// Si es una celula simple la que la ocupa
			if (superficie.getArrayCelulas(casillaFinal.getX(), casillaFinal.getY()).esComestible()) {
				System.out.println("Celula Compleja en (" + casillaOrigen.getX() + "," + casillaOrigen.getY()
						+ ") se mueve a (" + casillaFinal.getX() + "," + casillaFinal.getY() + ") -- SI COME");
				superficie.mueveCelula(casillaOrigen, casillaFinal, true, true);
				
				if (compruebaMuerte()){
					System.out.println("Explota la celula compleja en (" + casillaFinal.getX() + "," + casillaFinal.getY() + ")");
					superficie.eliminarCelula(casillaFinal);
				}

			} 
			else {
				return null;
			}

		}

		// Si esta VACIA ---> se mueve igual
		else {
			System.out.println("Celula Compleja en (" + casillaOrigen.getX() + "," + casillaOrigen.getY()
					+ ") se mueve a (" + casillaFinal.getX() + "," + casillaFinal.getY() + ") -- NO COME");
			superficie.mueveCelula(casillaOrigen, casillaFinal, true, false);
		}
		
		return casillaFinal;
	}
	/**
	 * Comprueba si la celula compleja debe morir
	 * @return morir si o no
	 */
	private boolean compruebaMuerte(){
		boolean morir = false;
		if(this.contadorComidas == Constantes.MAX_COMER - 1){
			morir = true;
		}
		return morir;
	}
	
	public boolean esComestible() {
		return esComestible;
	}
	//este metodo nunca se llama
	public int getPasosNoMovidos() {
		return -1;
	}

	//este metodo nunca se llama
	public int getPasosReproducirse() {
		return -1;
	}
	
	public int getContadorComidas(){
		return this.contadorComidas;
	}
}
