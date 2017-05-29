package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.controller.GameController;

@SuppressWarnings("serial")
public abstract class PlayersInfoViewer<S extends GameState<S,A>, A extends GameAction<S,A>> extends GuiView<S,A>{

	protected List<PlayersInfoObserver> observers = new ArrayList<PlayersInfoObserver>();
	protected GameController<S, A> control;
	protected int playerNumber; 
	
	public interface PlayersInfoObserver{
		public void colorChanged(int p, Color color);
	}

	public void setPlayersInfoViewer(PlayersInfoViewer<S,A> playersInfoViewer){		
	}
	
	public void addObserver(PlayersInfoObserver o) { 
		observers.add(o); 
	}
	
	protected void notifyObservers(int p, Color c){
		for (PlayersInfoObserver o: observers){
			o.colorChanged(p, c);
		}
	}
	
	abstract public void setNumberOfPlayer(int n);
	abstract public Color getPlayerColor(int p);

	public Color getColor(int id) {
		return getPlayerColor(id);	
	}
	
	abstract public Map<Integer,Color> getColors();
}
