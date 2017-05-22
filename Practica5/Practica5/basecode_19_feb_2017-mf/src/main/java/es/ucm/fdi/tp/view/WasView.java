package es.ucm.fdi.tp.view;

import java.awt.Color;
import es.ucm.fdi.tp.controller.GameController;
import es.ucm.fdi.tp.controller.GameController.PlayerMode;
import es.ucm.fdi.tp.was.WolfAndSheepAction;
import es.ucm.fdi.tp.was.WolfAndSheepState;

@SuppressWarnings("serial")
public class WasView extends RectBoardView<WolfAndSheepState, WolfAndSheepAction>{
	private boolean primerclick=false;
	private int colOrigen;
	private int filOrigen;
	public WasView(GameController<WolfAndSheepState, WolfAndSheepAction> gameCtrl){
		super(gameCtrl);
	}

	@Override
	public void colorChanged(int p, Color color) {
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
	protected Integer getPosition(int row, int col) {
		return this.state.posicion(row, col);
	}

	@Override
	protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {		
		System.out.println("Mouse: " + clickCount + "clicks at position (" + row + "," + col + ") with Button "+ mouseButton);
				
		if(gameCtrl.getPlayerMode() == PlayerMode.MANUAL){
				if( !this.primerclick && gameCtrl.getState().posicion(row, col)!= -1){
						this.colOrigen=col;
						this.filOrigen=row;
						this.primerclick=true;
				}
				else{
					int cont=gameCtrl.getState().posicion(filOrigen, colOrigen);
					int cont1=gameCtrl.getPlayerId();
					if(row!=this.filOrigen && col!=this.colOrigen /*&&(gameCtrl.getState().posicion(filOrigen, colOrigen)==gameCtrl.getPlayerId())*/){
						gameCtrl.makeManualMove(new WolfAndSheepAction(gameCtrl.getPlayerId(), row, col,this.filOrigen,this.colOrigen));
						this.primerclick=false;
					}
				}
		}

	}

	@Override
	public void setGameController(GameController<WolfAndSheepState, WolfAndSheepAction> gameCtrl) {
		this.gameCtrl = gameCtrl;
	}
	
	public int[][] getBoard(){
		return this.state.getBoard();
	}
	
	// Se Ignoran
	protected void keyTyped(int keyCode) {
		if(keyCode==0){
			primerclick=false;
		}
		
	}
	public void enable() {}
	public void disable() {}
	public void setMessageViewer(MessageViewer<WolfAndSheepState, WolfAndSheepAction> infoViewer) {}
	public void setPlayersInfoViewer(es.ucm.fdi.tp.view.PlayersInfoViewer<WolfAndSheepState, WolfAndSheepAction> playersInfoViewer) {	}
	public void setControlPanel(ControlPanel<WolfAndSheepState, WolfAndSheepAction> controlPanel) {}
}
