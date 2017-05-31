package es.ucm.fdi.tp.view;


import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.controller.GameController;

@SuppressWarnings("serial")
public class MessageViewerComp<S extends GameState<S,A>, A extends GameAction<S,A>> extends MessageViewer<S,A>{

	private JPanel messagePanel;
	private JTextArea msgArea;
		
	public MessageViewerComp(){
		initGUI();
	}
		
	private void initGUI() {
		this.messagePanel = new JPanel();
		this.msgArea = new JTextArea(20,40);
		this.messagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		msgArea.setEditable(false);
		msgArea.setAutoscrolls(true); 
	    msgArea.setName("Status Messages");
	    msgArea.setLineWrap(true); 
	    msgArea.setWrapStyleWord(true);
	    this.messagePanel.add(msgArea);
	    JScrollPane scroll = new JScrollPane(msgArea);
	    messagePanel.add(scroll); 
	    messagePanel.setBorder(BorderFactory.createTitledBorder("Status Messages"));
	    this.add(messagePanel);
	}

	@Override
	public void addContent(String msg) {
		msgArea.append(">" + msg + "\n");
	}
	@Override
	public void setContent(String msg) {
		msgArea.setText(msg);
	}

	// Se ignoran
	public void enable() {}
	public void disable() {}
	public void update(S state) {}
	public void setPlayersInfoViewer(PlayersInfoViewer<S, A> playersInfoViewer) {}
	public void setGameController(GameController<S, A> gameCtrl) {}
	public void setControlPanel(ControlPanel<S, A> controlPanel) {}

	@Override
	public void setColor(Map<Integer, Color> c) {
	}

}
