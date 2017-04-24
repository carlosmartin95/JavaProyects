package celula;

import mundo.Casilla;
import mundo.Superficie;

public class CelulaSimple extends Celula {

	private int pasosReproducirse; //M
	private int pasosNoMovidos; //N
	/**
	 * Array de las casillas libres vecinas de la celula simple
	 */
	private Casilla[] casillasLibres;
	/**
	 * Casilla a la que la celula evolucionara
	 */
	private Casilla casillaFinal;
	
	
	public CelulaSimple(int pasosReproducirse, int pasosNoMovidos){
		
		super();
		this.pasosReproducirse = pasosReproducirse;
		this.pasosNoMovidos = pasosNoMovidos;
		this.esComestible = true;
		
	}
	
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) {
		
		int numPosiciones = 0;
		Casilla casillaComprobar = new Casilla(f, c);
		this.casillasLibres = new Casilla[9];
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {

				if (i != 0 || j != 0) {

					// comprobamos si esta dentro de la superficie
					if (superficie.comprobarLimites(casillaComprobar.getX() + i, casillaComprobar.getY() + j)) {

						// comprobamos si esta ocupada
						if (!superficie.comprobarOcupacion(casillaComprobar.getX() + i, casillaComprobar.getY() + j)) {

							casillasLibres[numPosiciones] = new Casilla(casillaComprobar.getX() + i, casillaComprobar.getY() + j);
							numPosiciones++;

						}
					}
				}
			} // fin for i
		} // fin for j
		
		
		//Si no hay posiciones libres alrededor:
		if (numPosiciones == 0)
		{ 
			casillaFinal = new Casilla(f, c);
			
			//Ejecuta ---> muere --> eliminar celula
			if ((getPasosNoMovidos() == 0 || getPasosReproducirse() == 0)){
				
				System.out.println("Muere la celula de la casilla (" + f + "," + c + ")");
				
				superficie.eliminarCelula(casillaFinal);
				
			}
			
			//Ejecuta ---> tiene un paso menos ---> restar paso
			else {	
				superficie.restaPaso(casillaFinal); 
			}
		
			return null;
		}
		
		//Si tiene casillas libres
		else 
		{	
			Casilla casillaOrigen = new Casilla(f, c);
			//Escoge una casilla al azar donde moverse / reproducirse
			casillaFinal = casillasLibres[(int) (Math.random() * numPosiciones-1)];
		
			//Ejecuta ---> reproducirse --> mover y crear celula
			if (getPasosReproducirse() == 0)
			{
				System.out.println("Movimiento de (" + casillaOrigen.getX() + "," + casillaOrigen.getY() + ") a (" + casillaFinal.getX() + "," + casillaFinal.getY() + ")");
				System.out.println("Nace nueva celula en (" + casillaOrigen.getX() + "," + casillaOrigen.getY() + ") cuyo padre ha sido (" + casillaFinal.getX() + ","	+ casillaFinal.getY() + ")");
				
				//Reproduce la celula en casilla Final
				superficie.crearCelulaSimple(casillaFinal);
				setMovida(true);
				
				
				superficie.crearCelulaSimple(casillaOrigen);
			}
			
			//Ejecuta ---> moverse ---> mover
			else 
			{
				System.out.println("Movimiento de (" + casillaOrigen.getX() + "," + casillaOrigen.getY() + ") a (" + casillaFinal.getX() + "," + casillaFinal.getY() + ")");
				
				superficie.mueveCelula(casillaOrigen, casillaFinal, false, false);
			}
			
			return casillaFinal;
		}	
		
		
	}
	
	/**
	 * devuelve si es comestible		
	 */
	public boolean esComestible() {
		return esComestible;
	}

	/**
	 * devuelve los pasos que le quedan para mover
	 */
	public int getPasosNoMovidos(){ //N
		return pasosNoMovidos;
	}
	
	/**
	 * devuelve los pasos que le quedan para reproducirse
	 */
	public int getPasosReproducirse(){ //M
		return pasosReproducirse;
	}

	@Override
	public int getContadorComidas() {
		//no se usa
		return -1;
	}
	
}

