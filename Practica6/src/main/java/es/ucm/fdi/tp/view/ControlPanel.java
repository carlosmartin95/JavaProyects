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

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.controller.GameController;
import es.ucm.fdi.tp.controller.GameController.PlayerMode;

@SuppressWarnings("serial")
public class ControlPanel<S extends GameState<S,A>, A extends GameAction<S,A>> extends GuiView<S,A>{

	private GameController<S,A> gameCtrl;
	private JPanel panelControl, smartPanel;
	private JButton botonSmartPlayer, botonRandomMove, botonRestartGame, botonStopGame, botonStopSmart;
	private JLabel etiqueta, brain,clock;
	private JSpinner spinner, spinnerClock;
	private JComboBox<PlayerMode> DesplegableModos;

	public ControlPanel(GameController<S,A> gameCtrl){
		
		this.gameCtrl = gameCtrl;
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
		this.spinner = new JSpinner();
		this.spinnerClock = new JSpinner(new SpinnerNumberModel(0,0,5000,500));
		initGUI();
	}
	
	private void initGUI() {
		
		this.brain.setIcon((new ImageIcon("src/main/resources/brain.png")));
		this.brain.setOpaque(true);
		this.clock.setIcon(new ImageIcon("src/main/resources/clock.png"));
		this.smartPanel.add(brain);
		this.spinner.setValue(gameCtrl.getHilos());
		this.smartPanel.add(this.spinner);
		this.smartPanel.add(new JLabel ("Threads"));
		this.smartPanel.add(this.clock);
		this.spinnerClock.setValue(gameCtrl.getMilis());
		this.smartPanel.add(this.spinnerClock);
		this.smartPanel.add(new JLabel("ms."));
		this.botonStopSmart.setIcon(new ImageIcon("src/main/resources/stop.png"));
		this.smartPanel.add(this.botonStopSmart);
				
		
		this.botonStopSmart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameCtrl.stopThread();
			}
		});

		
		this.botonRandomMove.setIcon(new ImageIcon("src/main/resources/dice.png"));
		this.panelControl.add(botonRandomMove);
		botonRandomMove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				gameCtrl.makeRandomMove();
			}
		});
		
		this.botonSmartPlayer.setIcon(new ImageIcon("src/main/resources/nerd.png"));
		this.panelControl.add(botonSmartPlayer);
		botonSmartPlayer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				gameCtrl.makeSmartMove();
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
					case "MANUAL": gameCtrl.changePlayerMode(PlayerMode.MANUAL);
									break;
					case "RANDOM": gameCtrl.changePlayerMode(PlayerMode.RANDOM);
									break;
					case "SMART": gameCtrl.changePlayerMode(PlayerMode.SMART);
									break;
				}
			}
		});
		

		this.add(panelControl);
		this.panelControl.add(this.smartPanel);
				
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

	@Override
	public void setColor(Map<Integer, Color> c) {
		// TODO Auto-generated method stub
		
	}

}
