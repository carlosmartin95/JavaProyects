package es.ucm.fdi.tp.view;import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.controller.GameController;
import es.ucm.fdi.tp.controller.GameController.PlayerMode;

@SuppressWarnings("serial")
public class ControlPanel<S extends GameState<S,A>, A extends GameAction<S,A>> extends GuiView<S,A>{

	private GameController<S,A> gameCtrl;
	private JPanel panelControl;
	private JButton botonSmartPlayer;
	private JButton botonRandomMove;
	private JButton botonRestartGame;
	private JButton botonStopGame;
	private JLabel etiqueta;
	private JComboBox<PlayerMode> DesplegableModos;
	
	public ControlPanel(GameController<S,A> gameCtrl){
		
		this.gameCtrl = gameCtrl;
		this.panelControl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.botonRandomMove = new JButton();
		this.botonSmartPlayer = new JButton();
		this.botonRestartGame = new JButton();
		this.botonStopGame = new JButton();
		this.etiqueta = new JLabel("Player Mode: ");
		this.DesplegableModos = new JComboBox<>(PlayerMode.values());
		initGUI();
	}
	
	private void initGUI() {
		
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
		this.DesplegableModos.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			
				switch(DesplegableModos.getSelectedItem().toString()){
					case "MANUAL": gameCtrl.changePlayerMode(PlayerMode.MANUAL);
									enable();
									break;
					case "RANDOM": gameCtrl.changePlayerMode(PlayerMode.RANDOM);
									//gameCtrl.makeRandomMove();
									break;
					case "SMART": gameCtrl.changePlayerMode(PlayerMode.SMART);
									//gameCtrl.makeSmartMove();
									break;
				}
			}
		});
		

		this.add(panelControl);
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

}
