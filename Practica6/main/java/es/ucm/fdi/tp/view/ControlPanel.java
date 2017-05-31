package es.ucm.fdi.tp.view;import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.ConcurrentAiPlayer;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.controller.GameController;
import es.ucm.fdi.tp.controller.GameController.PlayerMode;
import es.ucm.fdi.tp.controller.GuiController;
import es.ucm.fdi.tp.mvc.GameTable;

@SuppressWarnings("serial")
public class ControlPanel<S extends GameState<S,A>, A extends GameAction<S,A>> extends GuiView<S,A>{

	private RandomPlayer randPlayer;
	private ConcurrentAiPlayer smartPlayer;
	private GameController<S,A> gameCtrl;
	private JPanel panelControl, smartPanel;
	private JButton botonSmartPlayer, botonRandomMove, botonRestartGame, botonStopGame, botonStopSmart;
	private JLabel etiqueta, brain,clock;
	private JSpinner spinner, spinnerClock;
	private JComboBox<PlayerMode> DesplegableModos;
	private Thread hiloSmart;
	private int hilos = Runtime.getRuntime().availableProcessors();

	public ControlPanel(GameController<S,A> gameCtrl){
		
		this.gameCtrl = gameCtrl;
		this.randPlayer = new RandomPlayer("Random Player");
		this.smartPlayer = new ConcurrentAiPlayer("Smart Player");
		this.panelControl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.smartPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.smartPanel.setBorder(BorderFactory.createTitledBorder("SmartMoves"));
		this.botonRandomMove = new JButton();
		this.botonSmartPlayer = new JButton();
		this.botonRestartGame = new JButton();
		this.botonStopGame = new JButton();
		this.botonStopSmart = new JButton();
		this.etiqueta = new JLabel("Player Mode: ");
		this.DesplegableModos = new JComboBox<>(PlayerMode.values());
		this.clock = new JLabel();
		this.brain = new JLabel();
		this.spinner = new JSpinner(new SpinnerNumberModel(1,1,hilos,1));
		this.spinnerClock = new JSpinner(new SpinnerNumberModel(0,0,5000,500));
		initGUI();
	}
	
	private void initGUI() {
		
		this.brain.setIcon((new ImageIcon("src/main/resources/brain.png")));
		this.brain.setOpaque(true);
		this.clock.setIcon(new ImageIcon("src/main/resources/clock.png"));
		this.smartPanel.add(brain);
		this.spinner.setValue(hilos);
		this.smartPanel.add(this.spinner);
		this.smartPanel.add(new JLabel ("Threads"));
		this.smartPanel.add(this.clock);
		this.spinnerClock.setValue(500);
		this.smartPanel.add(this.spinnerClock);
		this.smartPanel.add(new JLabel("ms."));
		this.botonStopSmart.setIcon(new ImageIcon("src/main/resources/stop.png"));
		botonStopSmart.setEnabled(false);
		this.smartPanel.add(this.botonStopSmart);
				
		
				
		this.botonRandomMove.setIcon(new ImageIcon("src/main/resources/dice.png"));
		this.panelControl.add(botonRandomMove);
		botonRandomMove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(gameCtrl.getState().getTurn()==gameCtrl.getPlayerId()){
					gameCtrl.getGame().execute(randPlayer.requestAction(gameCtrl.getState()));					
				}
			}
		});
		
		this.botonSmartPlayer.setIcon(new ImageIcon("src/main/resources/nerd.png"));
		this.panelControl.add(botonSmartPlayer);
		
		botonSmartPlayer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				hiloSmart = new Thread(()->{
					SwingUtilities.invokeLater(()-> setThinking(true));
					
					long tiempo= System.currentTimeMillis();
					long tiempofin = System.currentTimeMillis();
					smartPlayer.setMaxThreads(hilos);
					smartPlayer.setTimeout(getMilis());
				
					A a = smartPlayer.requestAction(gameCtrl.getState());
					
					if (a != null){
						
						SwingUtilities.invokeLater(()->{ 
							setThinking(false);
							String info = (smartPlayer.getEvaluationCount()+ " nodes in "+(tiempofin-tiempo)+ "ms. \n Vaue = "+smartPlayer.getValue()+ "/n");
							//gameCtrl.
						});
					}
					
					SwingUtilities.invokeLater(()->{
						gameCtrl.getGame().execute(smartPlayer.requestAction(gameCtrl.getState()));
					});
				});				
				hiloSmart.start();
			}
		});

		botonStopSmart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hiloSmart.interrupt();
			}
		});
		
		this.botonRestartGame.setIcon(new ImageIcon("src/main/resources/restart.png"));
		this.panelControl.add(botonRestartGame);
		botonRestartGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				gameCtrl.restartGame();
			}
						
		});
		
		this.botonStopGame.setIcon(new ImageIcon("src/main/resources/exit.png"));
		this.panelControl.add(botonStopGame);
		botonStopGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				gameCtrl.stopGame();
			}
			
		}); 
				
		this.panelControl.add(etiqueta);
		this.panelControl.add(DesplegableModos);
		this.DesplegableModos.setSelectedItem(PlayerMode.MANUAL);
		this.DesplegableModos.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			
				switch(DesplegableModos.getSelectedItem().toString()){
					case "MANUAL": {gameCtrl.changePlayerMode(PlayerMode.MANUAL);
									
									}break;
					case "RANDOM": {
										gameCtrl.changePlayerMode(PlayerMode.RANDOM);
										gameCtrl.getGame().execute(randPlayer.requestAction(gameCtrl.getState()));
									}break;
					case "SMART": {
									gameCtrl.changePlayerMode(PlayerMode.SMART);	
									hiloSmart = new Thread(()->{
										SwingUtilities.invokeLater(()-> setThinking(true));
										
										long tiempo= System.currentTimeMillis();
										long tiempofin = System.currentTimeMillis();
										smartPlayer.setMaxThreads(hilos);
										smartPlayer.setTimeout(getMilis());
									
										A a = smartPlayer.requestAction(gameCtrl.getState());
										
										if (a != null){
											
											SwingUtilities.invokeLater(()->{ 
												setThinking(false);
												String info = (smartPlayer.getEvaluationCount()+ " nodes in "+(tiempofin-tiempo)+ "ms. \n Vaue = "+smartPlayer.getValue()+ "/n");
												//gameCtrl.handleEvent();
											});
										}
										
										SwingUtilities.invokeLater(()->{
											gameCtrl.getGame().execute(smartPlayer.requestAction(gameCtrl.getState()));
										});
									});				
									hiloSmart.start();
								  }	break;
				}
			}
		});
		
		this.add(panelControl);
		this.panelControl.add(this.smartPanel);
				
	}
	
	private int getMilis() {
		return (int) spinnerClock.getValue();
	}
	
	private void setThinking(boolean b) {
		if (b){
			brain.setBackground(Color.yellow);
			brain.repaint();
			botonStopSmart.setEnabled(true);
		}
		else{
			brain.setBackground(getBackground());
			botonStopSmart.setEnabled(false);
		}
	}
	
	@Override
	public void enable() {
			this.botonRandomMove.setEnabled(true);
			this.botonSmartPlayer.setEnabled(true);
			this.botonRestartGame.setEnabled(true);
			this.botonStopGame.setEnabled(true);
			this.DesplegableModos.setEnabled(true);

	}

	@Override
	public void disable() {
		
		this.botonRandomMove.setEnabled(false);
		this.botonSmartPlayer.setEnabled(false);
		this.botonRestartGame.setEnabled(false);
		this.botonStopGame.setEnabled(false);
		this.DesplegableModos.setEnabled(false);
	}

	// Se Ignoran
	public void update(S state) {}
	public void setMessageViewer(MessageViewer<S, A> infoViewer) {}
	public void setPlayersInfoViewer(PlayersInfoViewer<S, A> playersInfoViewer) {}
	public void setGameController(GameController<S, A> gameCtrl) {}
	public void setControlPanel(ControlPanel<S, A> controlPanel) {}
	public void setColor(Map<Integer, Color> c) {}
	


}
