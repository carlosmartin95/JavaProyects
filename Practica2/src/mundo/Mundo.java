package mundo;
import main.*;

/*
 * Clase encargada de crear el mundo y realizar la simulacion.
 */

public class Mundo {
	
	private Superficie superficie;
	private Casilla casilla;
	/**
	 * Indica cuando la simulacion ha terminado
	 */
	private boolean simulacionTerminada = true;
		
	/**
	 * Crea un mundo creando celulas aleatorias en el
	 * @param numCelulasSimples numero de celulas simples a crear
	 * @param numCelulasComplejas numero de celulas complejas a crear
	 */
	public Mundo(int numCelulasSimples, int numCelulasComplejas) {

		int filaAleatoria, columnaAleatoria;
		boolean metida = false, complejas = false;
		
		//Creamos el objeto 
		superficie = new Superficie(Constantes.MAX_FILAS, Constantes.MAX_COLUMNAS);
	
		// calculamos posciones aleatorias para las celulas simples
		for (int i = 0; i < numCelulasSimples; i++){
			
			while (!metida){
				filaAleatoria = (int) (Math.random() * Constantes.MAX_FILAS);
				columnaAleatoria = (int) (Math.random() * Constantes.MAX_COLUMNAS);
				
				//Llamada al metodo del objeto
				metida = superficie.meterCelula(filaAleatoria, columnaAleatoria, complejas);
			}	
			
			metida = false;
		}
		
		complejas = true;
		// calculamos posciones aleatorias para las celulas complejas
		for (int i = 0; i < numCelulasComplejas; i++){
			
			while (!metida){
				filaAleatoria = (int) (Math.random() * Constantes.MAX_FILAS);
				columnaAleatoria = (int) (Math.random() * Constantes.MAX_COLUMNAS);
				
				//Llamada al metodo del objeto
				metida = superficie.meterCelula(filaAleatoria, columnaAleatoria, complejas);
			}	
			metida = false;
		}
	}
	
	/**
	 * Establece si la simulacion debe ser finalizada
	 * @param simulacionTerminada
	 */
	public void setSimulacionTerminada(boolean simulacionTerminada) {
		this.simulacionTerminada = simulacionTerminada;
	}

	/**
	 * Evoluciona el mundo casilla por casilla 
	 */
	public void evoluciona(){
		//Recorremos el array superfice
		for (int fila = 0; fila < Constantes.MAX_FILAS; fila++)
		{
			for (int columna = 0; columna < Constantes.MAX_COLUMNAS; columna++)
			{
		
				if (this.superficie.getArrayCelulas(fila, columna) != null && !this.superficie.getArrayCelulas(fila, columna).isMovida()){
					//Devuelve casilla para nada
					casilla = this.superficie.ejecutaMovimiento(fila,columna);
					
				}
			}
		}
		this.superficie.resetMovidas();
	}

	public boolean esSimulacionTerminada(){
		return this.simulacionTerminada;
	}
	public void mostrarMundo(){
		System.out.println(this.superficie.toString());
	}
	public void eliminarCelula(Casilla casilla){
		this.superficie.eliminarCelula(casilla);
	}
	public void crearCelulaSimple(Casilla casilla) {
		this.superficie.crearCelulaSimple(casilla);
	}
	public void crearCelulaCompleja(Casilla casilla) {
		this.superficie.crearCelulaCompleja(casilla);
	}
	public void setSuperficie(Superficie superficie){
		this.superficie = superficie;
	}
	/**
	 * Comprueba si la casilla cumple los limites del mundo, o si esta o no ocupada
	 * @param casilla casilla a comprobar
	 * @param eliminar si importa o no que este ocupada
	 * @return si es correcto
	 */
	public boolean comprobarLimitesYOcupacion(Casilla casilla, boolean eliminar){
		boolean correcto = true;
		
		if (casilla.getX() < 0 || casilla.getX() > Constantes.MAX_FILAS)
			correcto = false;
		
		else if (correcto && (casilla.getY() < 0 || casilla.getY() > Constantes.MAX_COLUMNAS ))
			correcto = false;
		
		if (!eliminar){
			if (correcto && superficie.getArrayCelulas(casilla.getX(),casilla.getY()) != null)
			correcto = false;
		}
		else {
			if (correcto && superficie.getArrayCelulas(casilla.getX(),casilla.getY()) == null){
			correcto = false;
			}
		}
		return correcto;
	}
}

