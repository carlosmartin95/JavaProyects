package es.ucm.fdi.tp.ttt;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameState;

/**
 * A TickTackToe state.
 * Describes a board of TickTackToe that is either being
 * played or is already finished.
 */
public class TttState extends GameState<TttState, TttAction> {
/**
 * Atributos de la clase que:
 * el turno de la partida
 * Si se ha acabado la partida
 * El tablero
 * y el ganador de la partida
 * tambien contiene la dimension
 * */
	private static final long serialVersionUID = -2641387354190472377L;
	
	private final int turn;
    private boolean finished;
    private final int[][] board;
    private final int winner;

    private final int dim;

    final static int EMPTY = -1;
/**
 * Constructoras
 * */
    public TttState(int dim) {    	
        super(2);
        if (dim < 3 || dim > 4) {
            throw new IllegalArgumentException("Expected dim to be 3 or 4");
        }

        this.dim = dim;
        board = new int[dim][];
        for (int i=0; i<dim; i++) {
            board[i] = new int[dim];
            for (int j=0; j<dim; j++) board[i][j] = EMPTY;
        }
        this.turn = 0;
        this.winner = -1;
        this.finished = false;
    }
        
    public TttState(TttState prev, int[][] board, boolean finished, int winner) {
    	super(2);
    	this.dim = prev.dim;
        this.board = board;
        this.turn = (prev.turn + 1) % 2;
        this.finished = finished;
        this.winner = winner;
    }    
/**
 * Metodo que se encarga de verificar si el movimiento es posible
 * */
    public boolean isValid(TttAction action) {
        if (isFinished()) {
            return false;
        }
        return at(action.getRow(), action.getCol()) == EMPTY;
    }
/**
 *  Metodo que se encarga de buscar los posibles movimientos
 * */
    public List<TttAction> validActions(int playerNumber) {
        ArrayList<TttAction> valid = new ArrayList<>();
        if (finished) {
            return valid;
        }

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (at(i, j) == -1) {
                    valid.add(new TttAction(playerNumber, i, j));
                }
            }
        }
    
        return valid;
    }
/**
 * Metodo que verifica si el tablero esta vacio o no
 * */
    public static boolean isDraw(int[][] board) {
        boolean empty = false;
        for (int i=0; ! empty && i<board.length; i++) {
            for (int j=0; ! empty && j<board.length; j++) {
                if (board[i][j] == EMPTY) {
                    empty = true;
                }
            }
        }
        return ! empty;
    }
/**
 * Metodo que se encarga de ver quien es el ganador de la partida
 * */
    private static boolean isWinner(int[][] board, int playerNumber, 
    		int x0, int y0, int dx, int dy) {
        boolean won = true;
        for (int i=0, x=x0, y=y0; won && i<board.length; i++, x+=dx, y+=dy) {
            if (board[y][x] != playerNumber) won = false;
        }
        return won;
    }

/**
 * Metodo que se encarga de ver quien es el ganador de la partida
 * */
    public static boolean isWinner(int[][] board, int playerNumber) {
        boolean won = false;
        for (int i=0; !won && i<board.length; i++) {
            if (isWinner(board, playerNumber, 0, i, 1, 0)) won = true;
            if (isWinner(board, playerNumber, i, 0, 0, 1)) won = true;
        }
        return won ||
                isWinner(board, playerNumber, 0, 0, 1, 1) ||
                isWinner(board, playerNumber, 2, 0, -1, 1);
    }    
/**
 * metodo que devuelve que es lo que hay en la posicion que recibe por parametro
 * */
    public int at(int row, int col) {
        return board[row][col];
    }
/**
 * metodo que devuelve de quien es el turno
 * */
    public int getTurn() {
        return turn;
    }
/**
 * Metodo que devuelve cual es el valor de finished
 * */
    public boolean isFinished() {
        return finished;
    }
/**
 * Metodo que devuelve el valor de winner
 * */
    public int getWinner() {
        return winner;
    }

    /**
     * @return a copy of the board
     * metodo que devuelve una copia del tablero de juego
     */
    public int[][] getBoard() {
        int[][] copy = new int[board.length][];
        for (int i=0; i<board.length; i++) copy[i] = board[i].clone();
        return copy;
    }
/**
 * Metodo que dibuja en la consola el estado del tablero en ese momento
 * */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<board.length; i++) {
            sb.append("|");
            for (int j=0; j<board.length; j++) {
                sb.append(board[i][j] == EMPTY ? "   |" : board[i][j] == 0 ? " O |" : " X |");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

@Override
public void setfinished(boolean estado) {
	this.finished=estado;
}
}
