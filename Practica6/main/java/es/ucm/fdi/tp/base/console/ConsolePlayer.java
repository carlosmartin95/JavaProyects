package es.ucm.fdi.tp.base.console;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;

/**
 * A console player.
 * Can play any game, and offers console users a list of 
 * valid actions to choose from.
 */
public class ConsolePlayer implements GamePlayer {
	/**
	 * Atributos de la clase
	 * Contiene el numero del jugador el nombre del jugador y un elemento de la clase scanner para recoger datos
	 * */
    private int playerNumber;
    private String name;
    private Scanner in;	
    /**
     * Constructora de la clase que se encarga de asignar los valore que le llegan por parametro a los atributos de la clase
     * */
    public ConsolePlayer(String name, Scanner in) {
        this.name = name;
        this.in = in;
        this.playerNumber = -1;
    }
    /**
     * Sustituye el atributo playernumber por el parameto que recibe por referencia
     * */
    @Override
    public void join(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    /**
     * Devuelve el numero de jugador
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
     * Este metodo se encarga de recoger todos las posibles acciones de l jugador que tiene turno y las muestra por consola
     * recibe la opcion que elige el jugador y si esta correcta devuelve la acion que quiere realizar el jugador
     * */
	public <S extends GameState<S,A>, A extends GameAction<S,A>> A requestAction(S state) {

	    List<A> actions = new ArrayList<A>();
        actions.addAll(state.validActions(playerNumber));

    	// displays a menu with all available actions + exit
    	System.out.println(
                "Available actions: \n" +
                "\t0 - exit game");		
        int i = 0;
		for (GameAction<?,?> a : actions) {
			System.out.println("\t" + (++i) + " - " + a);
		}

		// read the user input and carry out action
        A action = null;
        while (action == null) {
            System.out.print("Please type your move index: ");
            try {
            	int choice = in.nextInt();

	            if (choice == 0) {
	                // user wants to exit
	                System.out.println("Game exiting by request of " + name);
	                System.exit(0);
	            } else if (choice > 0 && choice <= actions.size()) {
	                action = actions.get(choice - 1);
	            } else {
	                System.out.println(
	                		"Out of range (0 to " + actions.size() 
	                		+ "); please try again.");
	            }
            } catch (InputMismatchException ime) {
            	System.out.println("Expected an integer; please try again");
            }
        }
        return action;
	}
}
