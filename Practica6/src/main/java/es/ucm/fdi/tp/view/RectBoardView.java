package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Map;

import javax.swing.JComponent;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.controller.GameController;
import es.ucm.fdi.tp.view.PlayersInfoViewer.PlayersInfoObserver;
import es.ucm.fdi.tp.view.jBoard.JBoard;

public abstract class RectBoardView<S extends GameState<S,A>, A extends GameAction<S,A>> extends GuiView<S,A> implements PlayersInfoObserver {

	protected GameController<S, A> gameCtrl;
	protected MessageViewer<S,A> infoViewer;
	protected PlayersInfoViewer<S,A> playersInfoViewer; 
	protected S state;
	protected JComponent jBoard;
	Map<Integer,Color> colors;
	public RectBoardView(GameController<S,A> gameCtrl){
	
		this.gameCtrl = gameCtrl;
		this.state = gameCtrl.getState();
		initGUI();		
	}

	public void setColor(Map<Integer,Color> c){
		this.colors=c;
	}
	
	@SuppressWarnings("serial")
	private void initGUI(){
		
		createBoardData(this.getNumRows(), this.getNumCols());
		this.setLayout(new BorderLayout());
		this.jBoard = new JBoard(){

			@Override
			protected void keyTyped(int keyCode) {
				keyTyped(keyCode);
			}

			@Override
			protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {
				RectBoardView.this.mouseClicked(row,col,clickCount, mouseButton);
			}

			@Override
			protected Shape getShape(int player) {
				return getColor(player)==null? Shape.RECTANGLE : Shape.CIRCLE;
			}

			protected int getSepPixels(){
				return 2;
				
			}
			@Override
			protected Color getColor(int player) {
				return RectBoardView.this.getPlayerColor(player);
			}

			protected Integer getPosition(int row, int col) {
				Integer p=getPos(row,col);
				return getPos(row,col);
				
			}

			@Override
			protected Color getBackground(int row, int col) {
				 
				if((row+col) %2 == 0)
					return Color.lightGray;
				else return Color.black;
			}

			@Override
			protected int getNumRows() {
				return RectBoardView.this.getNumRows();
			}

			@Override
			protected int getNumCols() {
				return RectBoardView.this.getNumCols();
			}
		};
		this.add(jBoard);
	}
	
	// AÑADIR METODOS PARA METER EL PLAYERINFOAREA MESSAGEAREA Y EL PANEL DE CONTROL
	
	private void createBoardData(int rows, int cols) {
		this.getBoard();
	}

	public void setMessageViewer(MessageViewer<S,A> infoViewer){
		this.infoViewer = infoViewer;
	}
	
	public void PlayersInfoViewer(PlayersInfoViewer<S,A> playerInfo){
		this.playersInfoViewer = playerInfo;
		this.playersInfoViewer.addObserver(this);	
	}
	
	public void update(S state)
	{
		this.state = state;
		jBoard.repaint();
	}
	protected Color getPlayerColor(int id) {
		return id == 0 ? colors.get(0): (id ==1 ? colors.get(1): null);
	}
	
	
	
	public void enable(){
		this.jBoard.setEnabled(true);
	}

	public void disbale(){
		this.jBoard.setEnabled(false);
	}
		
	protected abstract int getNumCols();
	protected abstract int getNumRows();
	protected abstract Integer getPos(int row, int col);
	protected abstract void mouseClicked(int row, int col, int clickCount, int mouseButton);
	protected abstract void keyTyped(int keyCode);
	protected abstract int[][] getBoard();
}
