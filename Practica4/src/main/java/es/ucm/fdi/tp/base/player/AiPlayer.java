package es.ucm.fdi.tp.base.player;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;

/**
 * A player that can play any game.
 */
public class AiPlayer implements GamePlayer {
	/**
	 * Clase que contiene los atributos y metodos necearios para la clase AIplayer 
	 * */
    protected String name;

    protected int playerNumber;
    protected AiAlgorithm algorithm;
    /**
     * Constructora
     * */
    public AiPlayer(String name, AiAlgorithm algorithm) {
        this.name = name;
        this.algorithm = algorithm;
    }
    /**
     * Modifica el numero de jugador con el parametro que recibe por referencia
     **/
    @Override
    public void join(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    /**
     * Devuelve el numero de jugadores
     * */
    @Override
    public int getPlayerNumber() {
    	return playerNumber;
    }
    /**
     * Devuelve el nombre del jugador
     * */
    @Override
    public String getName() {
        return name;
    }
    /**
     * Metodo que se encarga de devolver la accion/movimiento que quiere realizar el jugador despues de mostrarle 
     * cuales son los posibles movimientos del jugador y elegir sus movimieto valido
     * */
	public <S extends GameState<S,A>, A extends GameAction<S,A>> A requestAction(S state) {
        return algorithm.chooseAction(playerNumber, state);
    }
}
