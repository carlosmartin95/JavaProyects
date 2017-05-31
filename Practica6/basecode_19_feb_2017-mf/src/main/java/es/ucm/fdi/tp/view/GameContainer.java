package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.controller.GameController;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameObservable;
import es.ucm.fdi.tp.mvc.GameObserver;

@SuppressWarnings("serial")
public class GameContainer<S extends GameState<S, A>, A extends GameAction<S, A>>
		extends GuiView<S, A> implements GameObserver<S, A> {

	private GuiView<S, A> gameView;
	private MessageViewer<S, A> messageViewer;
	private PlayersInfoViewer<S, A> playersInfoViewer;
	private ControlPanel<S, A> controlPanel;
	private GameController<S, A> gameCtrl;

	public GameContainer(GuiView<S, A> gameView, GameController<S, A> gameCtrl,
			GameObservable<S, A> game) {

		this.gameView = gameView;
		this.gameCtrl = gameCtrl;
		
		initGUI();
		game.addObserver(this);
	}

	private void initGUI() {

		this.messageViewer = new MessageViewerComp<S, A>();
		this.playersInfoViewer = new PlayersInfoComp<S, A>(new ColorTableListener(){

			@Override
			public void ColorChange() {
				gameView.repaint();
			}
			
		});
		this.gameView.setColor(playersInfoViewer.getColors());
		this.controlPanel = new ControlPanel<S, A>(this.gameCtrl);

		this.gameView.setMessageViewer(messageViewer);
		this.gameView.setPlayersInfoViewer(playersInfoViewer);
		this.gameView.setControlPanel(controlPanel);
		this.gameView.setGameController(gameCtrl);
		this.playersInfoViewer.setSize(15, 20);
		JPanel lateralDerecho = new JPanel();
		lateralDerecho.setLayout(new BoxLayout(lateralDerecho, BoxLayout.Y_AXIS));
		lateralDerecho.add(messageViewer, BorderLayout.LINE_END);
		lateralDerecho.add(playersInfoViewer, BorderLayout.AFTER_LINE_ENDS);
		this.setLayout(new BorderLayout(5, 5));
		this.add(controlPanel, BorderLayout.PAGE_START);
		this.add(gameView, BorderLayout.CENTER);
		this.add(lateralDerecho, BorderLayout.LINE_END);
	}

	@Override
	public void notifyEvent(final GameEvent<S, A> e) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				handleEvent(e);
			}
		});
	}

	public void handleEvent(final GameEvent<S, A> e) {

		switch (e.getType()) {

		case Start:
			this.messageViewer.addContent(e.getDescription());
			break;
		case Stop:
			this.messageViewer.addContent(e.getDescription());
			break;
		case Change:
			this.messageViewer.addContent(e.getDescription());
			break;
		case Info:
			messageViewer.addContent(e.getDescription());
		default:
			this.messageViewer.addContent(e.getDescription());
			break;
		}

		if (e.getState().isFinished() == false) {
			if (e.getState().getTurn() == gameCtrl.getPlayerId()) {
				this.enable();
			} else {
				this.disable();
			}

		} else {
			if (e.getState().getWinner() != -1) {
				this.messageViewer
						.addContent("Se ha terminado el juego. El ganador es: "
								+ gameCtrl.getWinner());
				gameCtrl.stopGame();
			} else {
				this.messageViewer
						.addContent("Se ha terminado el juego. Hay empate");
				gameCtrl.stopGame();
			}
		}
		update(e.getState());

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				if(gameCtrl.getState().isFinished()==false){
					gameCtrl.handleEvent(e);
				}
			}
		});
	}

	@Override
	public void enable() {
		gameView.enable();
		controlPanel.enable();
		messageViewer.enable();
		controlPanel.update(this.gameCtrl.getState());
	}

	@Override
	public void update(S state) {
		gameView.update(state);
		messageViewer.update(state);
		playersInfoViewer.update(state);
		controlPanel.update(state);
	}

	public void disable() {
		controlPanel.disable();
		gameView.disable();
		messageViewer.enable();

	}

	public void setMessageViewer(MessageViewer<S, A> infoViewer) {
	}

	public void setPlayersInfoViewer(PlayersInfoViewer<S, A> playersInfoViewer) {
	}

	public void setGameController(GameController<S, A> gameCtrl) {
	}

	public void setControlPanel(ControlPanel<S, A> controlPanel) {
	}

	@Override
	public void setColor(Map<Integer, Color> c) {
		// TODO Auto-generated method stub
		
	}

}
