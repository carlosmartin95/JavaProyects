package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.controller.GameController;
import es.ucm.fdi.tp.view.jColor.ColorChooser;

@SuppressWarnings("serial")
public class PlayersInfoComp<S extends GameState<S,A>, A extends GameAction<S,A>> extends PlayersInfoViewer<S,A> {

	private PlayerTableInfo tModel;
	private Map<Integer, Color> colors; // Line -> Color
	private ColorChooser colorChooser;
	private ColorTableListener listenerColor;

	public PlayersInfoComp(ColorTableListener c) {
		initGUI(c);
	}
	private void initGUI(ColorTableListener c) {
		this.listenerColor=c;
		JPanel mainPanel = new JPanel();
		colors = new HashMap<>();
		colorChooser = new ColorChooser(new JFrame(), "Choose Line Color", Color.BLACK);
		// names table
		tModel = new PlayerTableInfo();
		tModel.getRowCount();
		colors.put(0, Color.BLUE);
		colors.put(1, Color.RED);
		final JTable table = new JTable(tModel) {
			private static final long serialVersionUID = 1L;

			// THIS IS HOW WE CHANGE THE COLOR OF EACH ROW
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);

				// the color of row 'row' is taken from the colors table, if
				// 'null' setBackground will use the parent component color.
				if (col == 1)
					comp.setBackground(colors.get(row));
				else
					comp.setBackground(Color.WHITE);
				comp.setForeground(Color.BLACK);
				
				return comp;
			}
		};

		table.setToolTipText("Click on a row to change the color of a player");
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					changeColor(row);
				}
				listenerColor.ColorChange();
				
			}

		});	
		//mainPanel.setPreferredSize(new Dimension(500,400));
		mainPanel.add(new JScrollPane(table), BorderLayout.LINE_END);
		mainPanel.setBorder(BorderFactory.createTitledBorder("Player Information"));		
		mainPanel.setSize(15, 20);
		this.add(mainPanel);
	}

	private void changeColor(int row) {
		colorChooser.setSelectedColorDialog(colors.get(row));
		colorChooser.openDialog();
		if (colorChooser.getColor() != null) {
			colors.put(row, colorChooser.getColor());
			repaint();
		}
		

	}

	
	
	@Override
	public void setNumberOfPlayer(int n) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Color getPlayerColor(int p) {
		return colors.get(p);
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
	public void update(S state) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setMessageViewer(MessageViewer<S, A> infoViewer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setControlPanel(ControlPanel<S, A> controlPanel) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setGameController(GameController<S, A> gameCtrl) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Map<Integer, Color> getColors() {
		return colors;
	}
	@Override
	public void setColor(Map<Integer, Color> c) {
		
	}
	

	
	
}
