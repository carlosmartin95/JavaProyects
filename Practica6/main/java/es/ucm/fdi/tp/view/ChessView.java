package es.ucm.fdi.tp.view;

import java.awt.Color;

import javax.swing.ImageIcon;

import es.ucm.fdi.tp.base.Utils;
import es.ucm.fdi.tp.chess.ChessAction;
import es.ucm.fdi.tp.chess.ChessBoard;
import es.ucm.fdi.tp.chess.ChessBoard.Piece;
import es.ucm.fdi.tp.chess.ChessState;
import es.ucm.fdi.tp.controller.GameController;
import es.ucm.fdi.tp.controller.GameController.PlayerMode;
import es.ucm.fdi.tp.was.WolfAndSheepAction;

@SuppressWarnings("serial")
public class ChessView extends RectBoardView<ChessState, ChessAction>{

	private int colOrigen;
	private int filOrigen;
	private boolean primerclick;
	private static ImageIcon[] chessIcon;
	
	public ChessView(GameController<ChessState, ChessAction> gameCtrl) {
		super(gameCtrl);
		chessIcon = loodChessIcons();
	}

	@Override
	public void colorChanged(int p, Color color) {
		super.jBoard.repaint();
		
	}

	@Override
	protected int getNumCols() {
		return 8;
	}

	@Override
	protected int getNumRows() {
		return 8;
	}

	@Override
	protected Integer getPos(int row, int col) {
		return this.state.at(row, col);
	}

	@Override
	protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {

		if(gameCtrl.getPlayerMode() == PlayerMode.MANUAL){
			if( !this.primerclick && gameCtrl.getState().at(row, col)!= -1 /*&& gameCtrl.getState().getBoard().sameTurn(p, turn)*/){
					this.colOrigen=col;
					this.filOrigen=row;
					this.primerclick=true;
			}
			else{
				int cont=gameCtrl.getState().at(filOrigen, colOrigen);
				int cont1=gameCtrl.getPlayerId();
				if(row!=this.filOrigen && col!=this.colOrigen /*&&(gameCtrl.getState().posicion(filOrigen, colOrigen)==gameCtrl.getPlayerId())*/){
					gameCtrl.makeManualMove(new ChessAction(gameCtrl.getPlayerId(), row, col,this.filOrigen,this.colOrigen));
					this.primerclick=false;
				}
			}
		}
	}

	@Override
	protected void keyTyped(int keyCode) {
		if(keyCode==0){
			primerclick=false;
		}
	}

	@Override
	protected int[][] getBoard() {
		//return this.state.getBoard();
		return null;
	}

	public void disable() {}
	public void setPlayersInfoViewer(es.ucm.fdi.tp.view.PlayersInfoViewer<ChessState, ChessAction> playersInfoViewer) {}
	public void setControlPanel(ControlPanel<ChessState, ChessAction> controlPanel) {}
	public void setGameController(GameController<ChessState, ChessAction> gameCtrl) {}

	@Override
	protected ImageIcon getIcon(int p) {
		return chessIcon[p];
	}
	
	private static ImageIcon[] loodChessIcons(){
		ImageIcon[] icons = new ImageIcon[Piece.Empty.white()+1];//[...] Piece.Empty.white()+1;
		for(Piece p: Piece.values()){
			if(p != Piece.Empty && p != Piece.Outside){
				byte code= p.white();
				icons[code] = new ImageIcon(Utils.loadImage("chess/" + Piece.iconName(code)));
				code = p.black();
				icons[code] = new ImageIcon(Utils.loadImage("chess/" + Piece.iconName(code)));
				}
			
			}
		return icons;
		
	}


}
