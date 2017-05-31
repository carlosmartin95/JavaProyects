package es.ucm.fdi.tp.controller;

import es.ucm.fdi.tp.base.model.GameAction;

import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.ConcurrentAiPlayer;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameTable;


public class GuiController<S extends GameState<S,A>, A extends GameAction<S,A>> implements GameController<S,A> {

	private PlayerMode playerMode;
	private int playerId;
	private GameTable<S,A>game;
		
	public GuiController(int playerId,GameTable<S,A> game){
		this.game=game;
		this.playerId=playerId;
		this.playerMode=PlayerMode.MANUAL;
	}
	public GuiController(){}
	
	@Override
	public void makeManualMove(A a) {
		if(this.game.getState().getTurn()==this.playerId){
			this.game.execute(a);
		}
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
		//this.decideMakeAutomaticMove();
	}

	@Override
	public void handleEvent(GameEvent<S, A> e) {
		
		/*if ((e.getType() == GameEvent.EventType.Change || e.getType() == GameEvent.EventType.Info)
				&& e.getState().getTurn() == playerId)
			//decideMakeAutomaticMove();*/
	}
	
	/*private void decideMakeAutomaticMove(){
		
		/*if (this.playerMode != PlayerMode.MANUAL){
			if (this.playerMode.equals(PlayerMode.RANDOM))
					this.game.execute(randPlayer.requestAction(getState()));
			else this.game.execute(smartPlayer.requestAction(getState()));
		}
	}*/
	
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
	public GameTable<S,A> getGame(){
		return this.game;
	}
}
