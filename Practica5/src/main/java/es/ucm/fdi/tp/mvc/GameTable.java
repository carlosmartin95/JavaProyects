package es.ucm.fdi.tp.mvc;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameError;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.was.WolfAndSheepState;

/**
 * An event-driven game engine.
 * Keeps a list of players and a state, and notifies observers
 * of any changes to the game.
 */
public class GameTable<S extends GameState<S, A>, A extends GameAction<S, A>> implements GameObservable<S, A> {

	private S initialState, actualState;
	private A action;
	private boolean stop = false;
	private ArrayList<GameObserver<S,A>> listObservers;

    public GameTable(S initState) {
        this.initialState = initState;
        this.action=null;
        this.actualState= initState;
        this.listObservers= new ArrayList<>();
    }
    public void start() {
        this.actualState = this.initialState;
        GameEvent<S,A> e = new GameEvent<S,A>(GameEvent.EventType.Start,this.action,this.actualState,null," Se ha iniciado un nuevo juego");
        
        this.notificar(e);      
        
    }

	public void stop() {
    	if(!this.actualState.isFinished()){
    		GameEvent<S,A> e = new GameEvent<S,A>(GameEvent.EventType.Stop,this.action,this.actualState,null," Se ha acabado el juego");
    		this.actualState.setfinished(true);
    		this.notificar(e);
    	 }
    }
	
    public void execute(A action) {
    	boolean contiene=false;
    	List<A> validMoves;
    	if(this.stop==false){
    		try {
    		   	validMoves = this.actualState.validActions(action.getPlayerNumber());
		    	A act;
		    	for(int i=0;i<validMoves.size();i++){
		    		act=validMoves.get(i);
		    		if(act.getPlayerNumber()== action.getPlayerNumber() && act.getCol() == action.getCol() && act.getRow() == action.getRow() && act.getOldcol() == action.getOldcol() && act.getOldrow() == action.getOldrow()){
		    			contiene=true;
		    		}
		    	}
		    	if(contiene==true){
			       this.actualState = action.applyTo(actualState);
			       GameEvent<S,A> e = new GameEvent<S,A>(GameEvent.EventType.Change,this.action,this.actualState,null,"Jugador "+action.getPlayerNumber()+ " "+ action.toString());
			       this.notificar(e);
		    	}
	    	}catch (NullPointerException e) {
			this.stop = true;
		}
    	}
    }
    public S getState() {
		return this.actualState;
    }
    public A getAction(){
    	return this.action;
    }

    public void addObserver(GameObserver<S, A> o) {
        this.listObservers.add(o);
    }
    public void removeObserver(GameObserver<S, A> o) {
        this.listObservers.remove(o);
    }
    
    private void notificar(GameEvent<S, A> e) {
		for(Iterator<GameObserver<S,A>> it = listObservers.iterator();it.hasNext();){
			GameObserver <S,A> gameobs = it.next();
			gameobs.notifyEvent(e);
		}
	}
	public int getWinner() {
		// TODO Auto-generated method stub
		return this.actualState.getWinner();
	}
	public boolean getStop() {
		// TODO Auto-generated method stub
		return this.stop;
	}
}
