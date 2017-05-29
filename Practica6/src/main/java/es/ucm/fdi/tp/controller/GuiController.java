package es.ucm.fdi.tp.controller;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.ConcurrentAiPlayer;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;
import es.ucm.fdi.tp.mvc.GameTable;

public class GuiController<S extends GameState<S,A>, A extends GameAction<S,A>> implements GameController<S,A> {
	
	private RandomPlayer randPlayer;
	private ConcurrentAiPlayer smartPlayer;
	private PlayerMode playerMode;
	private int playerId;
	private GameTable<S,A>game;
	private int numHilos, milis;
	private Thread thread;
	
	public GuiController(int playerId,RandomPlayer randPlayer,ConcurrentAiPlayer smartPlayer,GameTable<S,A> game){
		this.game=game;
		this.playerId=playerId;
		this.randPlayer=randPlayer;
		this.smartPlayer=smartPlayer;
		this.playerMode=PlayerMode.MANUAL;
		this.numHilos = Runtime.getRuntime().availableProcessors();
		this.milis = 500;
	}

	public void makeManualMove(A a) {
		if(this.game.getState().getTurn()==this.playerId){
			this.game.execute(a);
		}
	}

	public void makeRandomMove() {
		if(this.game.getState().getTurn()==this.playerId){
			A action = randPlayer.requestAction(getState());
			this.game.execute(action);
		}
	}

	@Override
	public void makeSmartMove() {
				
		if(this.game.getState().getTurn()==this.playerId){
			threadMoves(this.game.getState(), this.numHilos, this.milis, smartPlayer);
		}
	}
	
	@Override
	public void stopThread() {
		this.thread.interrupt();
	}

	@SuppressWarnings("static-access")
	private void threadMoves(S state, int numHilos, int milis,ConcurrentAiPlayer player) {
		
		
		if(this.thread.interrupted()){
			this.thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
			
					player.setMaxThreads(numHilos);
					player.setTimeout(milis);
					player.requestAction(state);
					
				}
			});
				
			this.thread.start();
		}
		else this.game.execute(player.requestAction(state));
	}

	@Override
	public void restartGame() {
		this.game.start();
		this.playerMode = PlayerMode.MANUAL;
	}

	@Override
	public void stopGame() {
		this.game.stop();
	}

	@Override
	public void changePlayerMode(PlayerMode p) {
		this.playerMode = p;	
		this.decideMakeAutomaticMove();
	}

	@Override
	public void handleEvent(GameEvent<S, A> e) {
		
		if ((e.getType() == GameEvent.EventType.Change || e.getType() == GameEvent.EventType.Info)
				&& e.getState().getTurn() == playerId)
			decideMakeAutomaticMove();
	}
	
	private void decideMakeAutomaticMove(){
		
		if (this.playerMode != PlayerMode.MANUAL){
			if (this.playerMode.equals(PlayerMode.RANDOM))
					this.game.execute(randPlayer.requestAction(getState()));
			else this.game.execute(smartPlayer.requestAction(getState()));
		}
	}
	
	@Override
	public PlayerMode getPlayerMode() {
		return this.playerMode;
	}

	@Override
	public int getPlayerId() {
		return this.playerId;
	}

	public S getState() {
		return this.game.getState();
	}

	@Override
	public void update(S state) {
		// TODO Auto-generated method stub
	}
	public int getWinner(){
		return this.game.getWinner();
	}

	@Override
	public int getHilos() {
		return this.numHilos;
	}

	@Override
	public void setHilos(int hilos) {
		this.numHilos = hilos;
	}

	@Override
	public int getMilis() {
		return this.milis;
	}

	@Override
	public void setMilis(int milis) {
		this.milis = milis;
	}

}
