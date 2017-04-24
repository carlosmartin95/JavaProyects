package es.ucm.fdi.tp.was;

import java.util.ArrayList;
import java.util.List;
import es.ucm.fdi.tp.base.model.GameState;

public class WolfAndSheepState extends GameState<WolfAndSheepState, WolfAndSheepAction>{
	
	// Atributos de la WolfAndSheepState
	private static final long serialVersionUID = -2641387354190472377L;
	ArrayList<WolfAndSheepAction> valid;
    private final int dim;	
	private final int turn;
    private final boolean finished;
    private final int[][] board;
    private final int winner;
    final static int EMPTY = -1;
    final static int WOLF = 0;
    final static int SHEEP = 1;

    // Constructora
	public WolfAndSheepState(int dim) {
        super(2);
        if (dim !=8) {
            throw new IllegalArgumentException("Expected dim to be 8");
        }

        this.dim = dim;
        board = new int[dim][];
        for (int i=0; i<dim; i++) {
            board[i] = new int[dim];
            for (int j=0; j<dim; j++) board[i][j] = EMPTY;
        }
        board[0][1]=board[0][3]=board[0][5]=board[0][7]=SHEEP;
        board[7][0]=WOLF;
        this.turn = 0;
        this.winner = -1;
        this.finished = false;
        validActions(turn);
	}
	// Construcora
	public WolfAndSheepState(WolfAndSheepState state, int[][] board,boolean finished, int winner) {
	   	super(2);
	   	this.board=board;
	   	this.dim=state.dim;
        this.turn = (state.turn +1) %2;
        this.finished = finished;
        this.winner = winner;
	}
	@Override
	public int getTurn() {
		return turn;
	}
	/**
	 * Validamos los posibles movimientos, recorriendo el tablero
	 * e invocando a comprobarLobos y comprobarOvejas para comprobar 
	 * las posiciones contiguas.
	 */
	@Override
	public List<WolfAndSheepAction> validActions (int playerNumber) {
    	this.valid = new ArrayList<>();
        if (finished) {
            return valid;
        }

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
            	if (this.board[i][j] != EMPTY){
            		if (this.board[i][j] == turn){
            			if( turn==WOLF)
            				comprobarLobos(i,j);		
                   		else{ comprobarOvejas(i,j); }
            		}
            	}
            }
        }
        return valid;
	}
        
	/**
	 * Para el Jugador WOLF = 0, comprobamos que las posicones oblicuas (hacia arriba) 
	 * a la posicion actual estan libres, añadiendolas al ArrayList valid
	 *  de tipo WolfAndSheepAction.
	 * @param fila
	 * @param columna
	 */
	private void comprobarLobos(int fila, int columna) {		
		
		//Esquina de abajo izquierda
		if(columna == 0 && fila == this.dim-1){
			if(board[fila-1][columna+1] == -1){
				this.valid.add(new WolfAndSheepAction(WOLF, fila-1, columna+1, fila, columna));
			}
		}//Esquina de abajo derecha
		else if (columna == dim-1 && fila == this.dim-1){
			if(board[fila-1][columna-1] == -1){
				this.valid.add(new WolfAndSheepAction(WOLF, fila-1, columna-1, fila, columna));
			}
		}
		else{
			if(columna != 0){
				if (board[fila-1][columna-1] == -1 ){
					this.valid.add(new WolfAndSheepAction(WOLF, fila-1, columna-1, fila, columna));
				}
			}
			if(columna!= dim-1){
				if (board[fila-1][columna+1] == -1){
					this.valid.add(new WolfAndSheepAction(WOLF, fila-1, columna+1, fila, columna));
				}
			}
			if(fila != dim-1 && columna!=0){
				if (board[fila+1][columna-1] == -1 ){
					this.valid.add(new WolfAndSheepAction(WOLF, fila+1, columna-1, fila, columna));
				}
			}
			if(fila!=dim-1 && columna!=dim-1){
				if (board[fila+1][columna+1] == -1){
					this.valid.add(new WolfAndSheepAction(WOLF, fila+1, columna+1, fila, columna));
				}
			}
		}
	
		
	}
	/**
	 * Para el Jugador SHEEP = 1, comprobamos que las posicones oblicuas (hacia abajo)
	 * a la posicion actual estan libres, añadiendolas al ArrayList valid 
	 * de tipo WolfAndSheepAction
	 * @param fila
	 * @param columna
	 */
	public void comprobarOvejas(int fila, int columna){
						
		if(columna == 0 && fila != this.dim-1){
			//Esquina de arriba 
			if(board[fila+1][columna+1] == -1){
				this.valid.add(new WolfAndSheepAction(SHEEP, fila+1, columna+1, fila, columna));
			}
		}
		else if (columna == dim-1 && fila != this.dim-1){
			if(board[fila+1][columna-1] == -1){
				this.valid.add(new WolfAndSheepAction(SHEEP, fila+1, columna-1, fila, columna));
			}
		}
		else if(fila == dim-1){
		}
		else{
			if(fila !=0){
				if (board[fila+1][columna-1] == -1){
					this.valid.add(new WolfAndSheepAction(SHEEP, fila+1, columna-1, fila, columna));
				}
			}
			if(fila !=dim-1){
				if (board[fila+1][columna+1] == -1){
					this.valid.add(new WolfAndSheepAction(SHEEP, fila+1, columna+1, fila, columna));
				}
			}
		}
	}    
	
	// Devuelve booleano si ha terminado
	@Override
	public boolean isFinished() {
		return finished;
	}
	// Devuelve el valor de winner
	@Override
	public int getWinner() {
		return winner;
	}

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
    
    // copia del tablero
	public int[][] getBoard() {
		int[][] copy = new int[board.length][];
	    for (int i=0; i<board.length; i++) copy[i] = board[i].clone();
	        return copy;
	}
	
	// Comprobamos si un jugador que nos llega por parametro es ganador
	public boolean isWinner(int[][] board, int playerNumber) {
	
		// si es Oveja comprobamos que LOBO no este en ninguna posicion de la primera fila del tablero
		if(playerNumber==WOLF) {
        	for(int i=1; i<board.length; ++i){
        		if(board[0][i]==WOLF) 
        			return true;
        	}
        return false;
        } else{ // si es LOBO comprobamos que tenga posiciones para moverse
        	List<WolfAndSheepAction> list=new ArrayList<>();
        	list = validActions(WOLF);
        	if(list.size()==0) return true;
        	else return false;
        }
	}
	
	// Pintamos el tablero
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

}
