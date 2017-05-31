package es.ucm.fdi.tp.controller;

import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameTable;

public class ConsoleController<S extends GameState<S,A>, A extends GameAction<S,A>> implements Runnable {
	
	private List<GamePlayer> players;
	private  GameTable<S,A> game;

	public ConsoleController(List<GamePlayer> players, GameTable<S,A> game) {
	this.players=players;
	this.game=game;
	}
	
	public void run() {
			int ganaa=0,ganab=0;
			this.game.start();
			for(int i=0;i<10;i++){
				switch(playGame(this.game.getState(),this.players)){
				case 0:
					ganaa++;
					break;
				case 1: 
					ganab++;
					break;
				}
			}
			GamePlayer jug1 = this.players.get(0);
			GamePlayer jug2 = this.players.get(1);
			System.out.println("Result: "+ganaa+" for "+jug1.getName()+" vs "+ganab+" for "+jug2.getName());
	}
	
	public static <S extends GameState<S, A>, A extends GameAction<S, A>> int playGame(GameState<S, A> initialState,
			List<GamePlayer> players) {
		int playerCount = 0;
		for (GamePlayer p : players) {
			p.join(playerCount++); 
									
		}
		@SuppressWarnings("unchecked")
		S currentState = (S) initialState;

		while (!currentState.isFinished()) {
			// request move
			A action = players.get(currentState.getTurn()).requestAction(currentState);
			currentState = action.applyTo(currentState);
			System.out.println("After action:\n" + currentState);
			if (currentState.isFinished()) {
				// game over
				String endText = "The game ended: ";
				int winner = currentState.getWinner();
				if (winner == -1) {
					endText += "draw!";
				} else {
					endText += "player " + (winner + 1) + " (" + players.get(winner).getName() + ") won!";
				}
				System.out.println(endText);
			}
		}
		return currentState.getWinner();
	}
}