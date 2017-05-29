package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.controller.GameController;

public abstract class GuiView<S extends GameState<S,A>, A extends GameAction<S,A>>extends JPanel {

	private static final long serialVersionUID = -7693451506300638068L;

	public abstract void enable();
	public abstract void disable();
	public abstract void update(S state);
	public abstract void setMessageViewer(MessageViewer<S,A> infoViewer);
	public abstract void setPlayersInfoViewer(PlayersInfoViewer<S,A> playersInfoViewer);
	public abstract void setControlPanel(ControlPanel<S,A> controlPanel);
	public abstract void setGameController(GameController<S, A> gameCtrl);

	protected JFrame window;
	
	public void enableWindowMode(String nombre){
		
		this.window = new JFrame();
		this.window.setTitle(nombre);
		
		//Cogemos el tamaño de la pantalla para el Inicio de la ventana y hacemos 2/3 de esta.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setPreferredSize(new Dimension(screenSize.height * 2 / 3, screenSize.width * 2 / 3));
		
		window.setContentPane(this);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(800, 500);
		window.setVisible(true);	
	}
	
	public void disableWindowMode(){
		window.dispose();
		window=null;
	}
	
	public JFrame getWindow(){
		return window;
	}
	
	public void setTitle(String title){
		if(window!=null)
			window.setTitle(title);
		else this.setBorder(BorderFactory.createTitledBorder(title));
	}
	
	public abstract void setColor( Map<Integer,Color> c);
	
	
	
}
