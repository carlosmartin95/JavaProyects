package es.ucm.fdi.tp.main;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.ConcurrentAiPlayer;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.chess.ChessAction;
import es.ucm.fdi.tp.chess.ChessBoard;
import es.ucm.fdi.tp.chess.ChessState;
import es.ucm.fdi.tp.controller.ConsoleController;
import es.ucm.fdi.tp.controller.GameController;
import es.ucm.fdi.tp.controller.GuiController;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.view.ChessView;
import es.ucm.fdi.tp.view.ConsoleView;
import es.ucm.fdi.tp.view.GameContainer;
import es.ucm.fdi.tp.view.GuiView;
import es.ucm.fdi.tp.view.TttView;
import es.ucm.fdi.tp.view.WasView;
import es.ucm.fdi.tp.was.WolfAndSheepAction;
import es.ucm.fdi.tp.was.WolfAndSheepState;



public class Main {
	
	private static List<GamePlayer> players= new ArrayList<GamePlayer>();
	private static String[] names={"", "", "", };
	@SuppressWarnings("unused")
	private static int num=0;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static GameTable<?, ?> createGame(String gType) {
		// create a game with a GameState depending on the value of gType
			switch(gType){
			case "was": 
				return new GameTable(new WolfAndSheepState(8));
			case "ttt": 
				return new GameTable(new TttState(3));
			case "chess":
				return new GameTable(new ChessState());
			default:
				return null;
	 		}
		}
		private static <S extends GameState<S, A>, A extends GameAction<S,A>>
		
			void startConsoleMode(String gType, GameTable<S, A> game, String playerModes[]) {
				int playerCount=0;
			
				for (GamePlayer p : players) {
					p.join(playerCount++); 						
			}
			new ConsoleView<S,A>(game);
			new ConsoleController<S,A>(players,game).run();
		}
		
		private static <S extends GameState<S, A>, A extends GameAction<S,A>>			
			void startGUIMode(final String gType, final GameTable<S, A> game, String playerModes[]) {
			
			for (int i = 0; i < game.getState().getPlayerCount(); i++) {
				
				final RandomPlayer randomPlayer = RandomPlayer("RandomPlayer");//createPlayer(gType, playerModes[0], "RandomPlayer");
				final ConcurrentAiPlayer smartPlayer = ConcurrentAiPlayer("SmartPlayer");
				randomPlayer.join(i);
				smartPlayer.join(i);
				final String titulo;
				if(gType.equals("was")){
					titulo="Wolf And Sheep  Player : "+i;
				}
				else if (gType.equals("ttt")){
					titulo="Tic Tac Toe  Player : "+i;
				}
				else {
					titulo = "Chess Player: " +i;
				}
				try{
					SwingUtilities.invokeAndWait(new Runnable(){
						@Override
						public void run() {
							GameController<S,A> gameCtrl = new GuiController<S,A> (randomPlayer.getPlayerNumber(), game);
							GuiView<S,A> guiView = (GuiView<S,A>) createGameView(gType, gameCtrl);
							GuiView<S,A> container = new GameContainer<>(guiView, gameCtrl, game);
							container.enableWindowMode(titulo);
							num++;
						}
				
					});
				}catch(InvocationTargetException|InterruptedException e){
					System.out.println("some error occurred while creating the view...");
					e.printStackTrace();
					System.exit(1);
				}
			}
			SwingUtilities.invokeLater(new Runnable(){
				@Override
				public void run() {
					game.start();	
				}
			});
		}
		

		private static ConcurrentAiPlayer ConcurrentAiPlayer(String string) {
			ConcurrentAiPlayer smart = new ConcurrentAiPlayer(string);
			return smart;
		}
		private static RandomPlayer RandomPlayer(String string) {
			RandomPlayer random = new RandomPlayer(string);
			return random;
		}
		
		@SuppressWarnings("unchecked")
		public static <S extends GameState<S,A>, A extends GameAction<S,A>> GuiView<S, A> createGameView(String gType, GameController<S,A> gameCtrl) {

			if (gType.trim().equalsIgnoreCase("was"))
				return (GuiView<S,A>) new WasView((GameController<WolfAndSheepState, WolfAndSheepAction>)gameCtrl);
			else if (gType.trim().equalsIgnoreCase("ttt"))
				return (GuiView<S, A>) new TttView((GameController<TttState, TttAction>) gameCtrl);
			else return (GuiView<S, A>) new ChessView((GameController<ChessState, ChessAction>) gameCtrl);
			
		}		
		
		private static void usage() {
			System.out.println(" Selecciona una de los posibles movimientos marcados");
		}
		
		private static List<String> namesWithoutRepetitions(int n){
			ArrayList<String> list = new ArrayList<>(Arrays.asList(names));
			Collections.shuffle(list);
			while(list.size()>n) list.remove(list.size()-1);
			return list;
		}
		
		public static GamePlayer createPlayer(String gameName, String playerType, String playerName){
			switch(playerType){
			case "console" : return new ConsolePlayer(playerName, new Scanner(System.in));
			case "rand": return new RandomPlayer(playerName);
			case "smart" : return new SmartPlayer(playerName, new Random().nextInt(5));
			}
			return null;
		}
		
		public static void main(String[] args) {
			if (args.length < 2) {
				usage();
				System.exit(1);
			}
			GameTable<?, ?> game = createGame(args[0]);
			if (game == null) {
				System.err.println("Invalid game");
				usage();
				System.exit(1);
			}
			String[] otherArgs = Arrays.copyOfRange(args, 2, args.length);
			ArrayList<String> matchNames = (ArrayList<String>) namesWithoutRepetitions(2);
			GamePlayer player1;
			GamePlayer player2;
				switch (args[1]) {
					case "console":
						player1=createPlayer(args[0], args[1], matchNames.get(0));
						player2=createPlayer(args[0], args[2], matchNames.get(1));
						players.add(player1);
						players.add(player2);
						startConsoleMode(args[0],game,otherArgs);
						break;
					case "gui":
						startGUIMode(args[0],game,otherArgs);
						break;
					default:
				System.err.println("Invalid view mode: "+args[1]);
				usage();
				System.exit(1);
				}
			}
	}