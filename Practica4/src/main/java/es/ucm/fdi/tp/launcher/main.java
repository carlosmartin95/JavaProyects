package es.ucm.fdi.tp.launcher;

import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.was.WolfAndSheepState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Demo main, used to test game functionality. You can use it as an inspiration,
 * but we expect you to build your own main-class.
 */
public class main {
	/**
	 * Este metodo se encarga de inicializar el juego crea los jugadores y inicializa el estado de la partida luego gestiona el juego en el bucle
	 * Cuando un jugador se queda sin movimientos o el lobo a llegado a la fila que esta mas arriba se terminara la partida
	 * */	
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

	/**
	 * Muestra el resultado fial de la partida
	 * Repeatedly plays a game-state with a vs b
	 * 
	 * @param initialState
	 * @param a
	 *            player
	 * @param b
	 *            player
	 * @param times
	 *            to play
	 */
	public static void match(GameState<?, ?> initialState, GamePlayer a, GamePlayer b, int times) {
		int va = 0, vb = 0;

		List<GamePlayer> players = new ArrayList<GamePlayer>();
		players.add(a);
		players.add(b);

		for (int i = 0; i < times; i++) {
			switch (playGame(initialState, players)) {
			case 0:
				va++;
				break;
			case 1:
				vb++;
				break;
			}
		}
		System.out.println("Result: " + va + " for " + a.getName() + " vs " + vb + " for " + b.getName());
	}

	/**
	 * Plays tick-tack-toe with a console player against a smart player. The
	 * smart player should never lose.
	 */
	public static void testTtt() {
		try (Scanner s = new Scanner(System.in)) {
			List<GamePlayer> players = new ArrayList<GamePlayer>();
			GameState<?, ?> game = new TttState(3);
			players.add(new ConsolePlayer("Alice", s));
			players.add(new SmartPlayer("AiBob", 5));
			playGame(game, players);
		} // <-- closes the scanner when the try-block ends
	}
	
	
	private static String[] names={"", "", "", };
	/**
	 *Busca en la lista de nombres si hay alguno repetido y si es asi los elimina
	 * */
	private static List<String> namesWithoutRepetitions(int n){
		ArrayList<String> list = new ArrayList<>(Arrays.asList(names));
		Collections.shuffle(list);
		while(list.size()>n) list.remove(list.size()-1);
		return list;
	}
	/**
	 * Crea un juego en funcion del parametro que colocamos en las propiedades del main
	 * */
	public static GameState<?,?> createInitialState(String gameName){
		switch(gameName){
		case "was": return new WolfAndSheepState(8);
		case "ttt": return new TttState(3);
 		}
		return null;
	}
/**
 * Crea los jugadores en funcions del tipo de jugador que seleccionamos por las propiedades del  main
 * */	
	public static GamePlayer createPlayer(String gameName, String playerType, String playerName){
		switch(playerType){
		case "console" : return new ConsolePlayer(playerName, new Scanner(System.in));
		case "rand": return new RandomPlayer(playerName);
		case "smart" : return new SmartPlayer(playerName, new Random().nextInt(5));
		}
		return null;
	}

	/**
	 * Main method.
	 * Este metodo se encarga de recoger los argumentos necesarios y los valida si todo esta correcto inicia el juego
	 * @param args
	 */
	public static void main(String... args) {
		// testTtt();
		//match(new TttState(3), new SmartPlayer("AiAlice", 5), new RandomPlayer("AiBob"), 100);
		if(args.length!=3)
			System.out.println("ERROR: Número no válido de argumentos");
		else{
			GameState<?, ?> state=createInitialState(args[0]);
			if(state==null) System.out.println("ERROR: Juego no válido");
			else {
				ArrayList<String> matchNames = (ArrayList<String>) namesWithoutRepetitions(2);
				GamePlayer player1=createPlayer(args[0], args[1], matchNames.get(0));
				GamePlayer player2=createPlayer(args[0], args[2], matchNames.get(1));
				ArrayList<GamePlayer> players = new ArrayList<GamePlayer>();
				players.add(player1);
				players.add(player2);
				if(player1==null || player2==null) System.out.println("ERROR: Jugador no válido");
				else playGame(state, players);
			}
		}
	}
}
