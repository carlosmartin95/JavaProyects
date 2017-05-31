package es.ucm.fdi.tp.controller;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameTable;



public interface GameController<S extends GameState<S,A>,A extends GameAction<S,A>> {
	
	public enum  PlayerMode {MANUAL ,RANDOM,SMART};
	public void makeManualMove(A a);
	public void restartGame();
	public void stopGame();
	public void changePlayerMode(PlayerMode p);
	public void handleEvent(GameEvent<S,A> e);
	public PlayerMode getPlayerMode();
	public int getPlayerId();
	public S getState();
	public void update(S state);
	public int getWinner();
	public GameTable<S, A> getGame();
}
