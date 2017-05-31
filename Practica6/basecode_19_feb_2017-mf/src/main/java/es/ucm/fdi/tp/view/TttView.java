package es.ucm.fdi.tp.view;

import java.awt.Color;

import javax.swing.ImageIcon;

import es.ucm.fdi.tp.controller.GameController;
import es.ucm.fdi.tp.controller.GameController.PlayerMode;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;

@SuppressWarnings("serial")
public class TttView extends RectBoardView<TttState, TttAction> {

	public TttView(GameController<TttState, TttAction> gameCtrl) {
		super(gameCtrl);
	}

	@Override
	public void colorChanged(int p, Color color) {
		super.jBoard.repaint();
		
	}

	@Override
	protected int getNumCols() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	protected int getNumRows() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	protected Integer getPos(int row, int col) {
		return this.state.at(row, col);
	}

	@Override
	protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {
		System.out.println("Mouse: " + clickCount + "clicks at position (" + row + "," + col + ") with Button "+ mouseButton);
			
		if(gameCtrl.getPlayerMode() == PlayerMode.MANUAL)
			gameCtrl.makeManualMove(new TttAction(gameCtrl.getPlayerId(), row, col));
		
	}

	@Override
	protected void keyTyped(int keyCode) {
		if(keyCode==0){
		}
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMessageViewer(MessageViewer<TttState, TttAction> infoViewer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPlayersInfoViewer(PlayersInfoViewer<TttState, TttAction> playersInfoViewer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGameController(GameController<TttState, TttAction> gameCtrl) {
		this.gameCtrl = gameCtrl;
		
	}

	@Override
	protected int[][] getBoard() {
		return this.state.getBoard();
	}

	@Override
	public void setControlPanel(ControlPanel<TttState, TttAction> controlPanel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected ImageIcon getIcon(int p) {
		// TODO Auto-generated method stub
		return null;
	}

}
