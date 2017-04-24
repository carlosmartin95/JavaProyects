package celula;

import mundo.Casilla;
import mundo.Superficie;

/**
 * Clase encargada de las celulas
 */

public abstract class Celula {
	
	private boolean movida;
	protected boolean esComestible;
	
	/**
	 * <p>Constructor por defecto</p>
	 * Crea una celula con N = 1, y M = 2
	 */
	public Celula(){
		setMovida(false);
	}
	/**
	 * Ejecuta la evolucion de la celula deacuerdo a sus reglas
	 * @param f Posicion fila de la celula
	 * @param c Posicion fila de la celula
	 * @param superficie Superficie donde se lleva a cabo la evolucion
	 * @return Casilla donde ha evolucionado.
	 */
	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie superficie);	
	
	/**
	 * Determina si la celula es comestible o no
	 * @return
	 */
	public abstract boolean esComestible();
	
	/**
	 * Devuelve si ha la celula ha sido movida
	 * @return
	 */
	public boolean isMovida() {
		return movida;
	}
	
	/**
	 * Establece si la celula ha sido movida
	 * @param movida
	 */
	public void setMovida(boolean movida) {
		this.movida = movida;
	}
	/**
	 * Devuelve los pasos no movidos
	 */
	public abstract int getPasosNoMovidos();

	/**
	 * Devuelve los pasos para reproducirse
	 */
	public abstract int getPasosReproducirse();
	
	/**
	 * Devuelve cuantas celulas ha comido la celula
	 */
	public abstract int getContadorComidas();
}
