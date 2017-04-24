package es.ucm.fdi.tp.was;

import es.ucm.fdi.tp.base.model.GameAction;

public class WolfAndSheepAction implements GameAction<WolfAndSheepState, WolfAndSheepAction> {
	private static final long serialVersionUID = -8491198872908329925L;

	// atributos privados
	private int player;
    private int row;
    private int col;
    private int oldRow;
    private int oldCol;

    // Constructor
    public WolfAndSheepAction(int player, int row, int col, int oldRow,int oldCol) {
        this.player = player;
        this.row = row;
        this.col = col;
        this.oldCol = oldCol;
        this.oldRow = oldRow;
    }
	
	@Override
	public int getPlayerNumber() {
		return player;
	}

	/**
	 * En este método se hace eficaz el movimiento sobre el tablero,
	 *  borrando del mismo la posicion antigua.
	 */
    public WolfAndSheepState applyTo(WolfAndSheepState state) {
        if (player != state.getTurn()) {
            throw new IllegalArgumentException("Not the turn of this player");
        }
        // make move
        int[][] board = state.getBoard();
        board[row][col] = player;
        board[oldRow][oldCol] = -1;
        
        // update state
        WolfAndSheepState next = new WolfAndSheepState(state, board, false,state.getTurn());
        if (next.isWinner(board, state.getTurn())) {
            next = new WolfAndSheepState(state, board, true, state.getTurn());
        } else if (WolfAndSheepState.isDraw(board)) {
            next = new WolfAndSheepState(state, board, true, -1);
        } else {
            next = new WolfAndSheepState(state, board, false, -1);
        }

        return next;
    }
   
    // get Fila
   public int getRow() {
        return row;
    }
    // get Columna
   public int getCol() {
        return col;
    }
   // toString para mostrar
   public String toString() {
       return "place " + player + " at (" + row + ", " + col + ")";
   }
   // set Player
   public void setPlayer(int i){
		this.player = i;
   }

}
