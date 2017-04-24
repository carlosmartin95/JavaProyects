package mundo;

import celula.Celula;
import celula.CelulaCompleja;
import celula.CelulaSimple;
import main.*;

/**
 * <p>
 * Clase administradora de la superficie.
 * </p>
 * Encargada de realizar los pasos de la evolucion segun las reglas de la vida
 */

public class Superficie {

	private int filas = 0;
	private int columnas = 0;
	/**
	 * Array de las celulas de la superficie
	 */
	private Celula[][] arrayCelulas;

	/**
	 * CONSTRUCTOR: crea la superficie segun nf y nc
	 * 
	 * @param nf
	 *            Numero de filas
	 * @param nc
	 *            Numero de columnas
	 */
	public Superficie(int nf, int nc) {

		filas = nf;
		columnas = nc;

		// Creacion del array superficie
		arrayCelulas = new Celula[filas][columnas];

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {

				arrayCelulas[i][j] = null; //
			}
		}

	}

	/**
	 * <p>
	 * Crea una celula en la superficie.
	 * </p>
	 * Este metodo es llamado desde el constructor de Mundo para iniciar la
	 * superficie
	 * 
	 * @param filaAleatoria
	 *            Fila aleatoria
	 * @param columnaAleatoria
	 *            Columna aleatoria
	 * @return
	 * 		<p>
	 *         true: si ha sido posible meterla
	 *         </p>
	 *         <p>
	 *         false: en esa posicion ya habia una celula
	 *         </p>
	 */
	public boolean meterCelula(int filaAleatoria, int columnaAleatoria, boolean complejas) {

		if (arrayCelulas[filaAleatoria][columnaAleatoria] == null) {

			if (!complejas)
				arrayCelulas[filaAleatoria][columnaAleatoria] = new CelulaSimple(1, 2);
			else
				arrayCelulas[filaAleatoria][columnaAleatoria] = new CelulaCompleja(0);

			return true;
		} else
			return false;
	}

	/**
	 * Muestra la superficie
	 * 
	 * @return La superficie como String
	 */
	public String toString() {

		String s = "";

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {

				if (arrayCelulas[i][j] == null) {
					s = s + "  -  ";
				} 
				else if (arrayCelulas[i][j].esComestible()){
						s = s + "  X  ";
				}
				else if (!arrayCelulas[i][j].esComestible()){
						s = s + "  *  ";
				}
			}
			s = s + "\n";
		}
		return s;
	}

	/**
	 * Metodo encargado de recorrer la superficie e ir ejecutando segun las reglas de la vida la evolucion de las celulas.
	 */
	public Casilla ejecutaMovimiento(int f, int c){
	
		return arrayCelulas[f][c].ejecutaMovimiento(f, c, this);
	
	}

	// **********************************************************ACCIONES REGLAS DE LA VIDA*************************************************************
	/**
	 * Elimina la celula de la casilla
	 * @param casilla
	 */
	public void eliminarCelula(Casilla casilla) {
		arrayCelulas[casilla.getX()][casilla.getY()] = null;
	}

	/**
	 * Resta un paso permitido sin mover (N) en la celula de la casilla
	 * 
	 * @param casilla
	 *
	 */
	public void restaPaso(Casilla casilla) {
		arrayCelulas[casilla.getX()][casilla.getY()] = new CelulaSimple(
				arrayCelulas[casilla.getX()][casilla.getY()].getPasosNoMovidos() - 1,
				arrayCelulas[casilla.getX()][casilla.getY()].getPasosReproducirse());
	}

	/**
	 * Crea una mueva celula en la fila columna
	 * @param casilla
	*/
	public void crearCelulaSimple(Casilla casilla) {
		arrayCelulas[casilla.getX()][casilla.getY()] = new CelulaSimple(1, 2);
	}

	/**
	 * Crea una celula compleja en casilla
	 * @param casilla
	 */
	public void crearCelulaCompleja(Casilla casilla) {
		arrayCelulas[casilla.getX()][casilla.getY()] = new CelulaCompleja(0);
	}

	/**
	 * Desplaza la celula por la superficie
	 * @param casillaOrigen casilla desde la que se va a mover
	 * @param casillaDestino casilla a la que sa va a mover
	 * @param esCompleja si la celula a mover es compleja
	 * @param comeCompleja si la celula compleja va a comer o no
	 */
	public void mueveCelula(Casilla casillaOrigen, Casilla casillaDestino, boolean esCompleja, boolean comeCompleja) {

		if (!esCompleja) {
			arrayCelulas[casillaDestino.getX()][casillaDestino.getY()] = new CelulaSimple(arrayCelulas[casillaOrigen.getX()][casillaOrigen.getY()].getPasosNoMovidos(),arrayCelulas[casillaOrigen.getX()][casillaOrigen.getY()].getPasosReproducirse() - 1);
			arrayCelulas[casillaOrigen.getX()][casillaOrigen.getY()] = null;
		} 
		else {
			if(comeCompleja){
				arrayCelulas[casillaDestino.getX()][casillaDestino.getY()] = new CelulaCompleja(arrayCelulas[casillaOrigen.getX()][casillaOrigen.getY()].getContadorComidas() + 1);
				arrayCelulas[casillaOrigen.getX()][casillaOrigen.getY()] = null;
			}
			else {
				arrayCelulas[casillaDestino.getX()][casillaDestino.getY()] = new CelulaCompleja(arrayCelulas[casillaOrigen.getX()][casillaOrigen.getY()].getContadorComidas());
				arrayCelulas[casillaOrigen.getX()][casillaOrigen.getY()] = null;

			}
		}

		arrayCelulas[casillaDestino.getX()][casillaDestino.getY()].setMovida(true);
	}

	// ******************************************** COMPROBACION DE LIMITES Y OCUPACION ****************************************************

	/**
	 * Metodo para comprobar si las coordenas introducidas son validas o no.
	 * 
	 * @param fila
	 *            Fila
	 * @param columna
	 *            Columna
	 * @return true: la coordenada es valida. false : la coordenada no es valida
	 */
	public boolean comprobarLimites(int fila, int columna) {

		boolean comprobado = true;

		if (fila < 0 || fila > Constantes.MAX_FILAS - 1)
			comprobado = false;

		else if (comprobado && (columna < 0 || columna > Constantes.MAX_COLUMNAS - 1))
			comprobado = false;

		return comprobado;
	}

	/**
	 * Metodo para comprobar si en las coordenadas introducidas ya existe una
	 * celula
	 * 
	 * @param fila
	 *            Fila
	 * @param columna
	 *            Columna
	 * @return true: la coordenada es valida. false: la coordenada no es valida
	 */
	public boolean comprobarOcupacion(int fila, int columna) {

		boolean ocupada = false;

		if (fila >= 0 && fila < Constantes.MAX_FILAS && (columna >= 0 && columna < Constantes.MAX_COLUMNAS)) {

			if (arrayCelulas[fila][columna] != null) {
				ocupada = true;
			}
		}

		return ocupada;
	}

	/**
	 * Devuelve si es comestible
	 * @param i posicion fila
	 * @param j posicion columna
	 * @return
	 */
	
	public boolean esComestible(int i, int j) {
		return this.arrayCelulas[i][j].esComestible();

	}

	public Celula getArrayCelulas(int i, int j) {
		return arrayCelulas[i][j];
	}
 	/**
 	 * Establece que todas la celulas no han sido movidas para volver a evolucionar
 	 */
	public void resetMovidas(){
		for (int i = 0; i < Constantes.MAX_FILAS; i++) {
			for (int j = 0; j < Constantes.MAX_COLUMNAS; j++) {
				if (arrayCelulas[i][j] != null){
					this.arrayCelulas[i][j].setMovida(false); 
				}
			}
		}
	}
}
