package userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.MatrixController;
import controllers.Music;
import controllers.TimeControl;

public class MainFrame extends JFrame implements ActionListener, Runnable{
	ButtonIconsPanel BI;
	JButton[][] btn;
	JButton btnTemp;
	int row=10, column=10;
	JPanel panelIcon,northPanel, left;
	int mat[][];
	public MainFrame(int row, int column) throws HeadlessException {
		super();
		this.row = row;
		this.column = column;
	}

	JProgressBar timeBar;
	public JLabel scrores;
	JButton btnNewgame;
	Container content;
	public int time = 500;
	TimeControl timeControl;
	
	public TimeControl getTimeControl() {
		return timeControl;
	}

	public void setTimeControl(TimeControl timeControl) {
		this.timeControl = timeControl;
	}

	public MainFrame(String tilte)
	{
		super(tilte);
		addControls();
		addEvents();
		
	}

	private void addControls() {
		// TODO Auto-generated method stub
		
		content = getContentPane();
		content.setLayout(new BorderLayout());
		
		BI = new ButtonIconsPanel(column, row, this);
		
		panelIcon = new JPanel();
		left = new JPanel();
		left.setLayout(new BorderLayout());
		panelIcon.setLayout(new GridBagLayout());
		panelIcon.setBorder(new EmptyBorder(40,40,40,40));
		panelIcon.setBackground(Color.LIGHT_GRAY);
		left.setPreferredSize(new Dimension(600,0));
		panelIcon.add(BI);
		left.add(panelIcon, BorderLayout.CENTER);
		content.add(panelIcon, BorderLayout.CENTER);

		northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
		northPanel.setBorder(new EmptyBorder(20,20,20,20));
		
		addControlsToRightPanel();
		//right.setPreferredSize(new Dimension(300,300));
		content.add(northPanel, BorderLayout.NORTH);
		
		Music music = new Music();

//		JPanel under = new JPanel();
//		under.setLayout(new BoxLayout(under, BoxLayout.Y_AXIS));
//		under.setPreferredSize(new Dimension(300,300));
//		content.add(under, BorderLayout.NORTH);
////		
//		JPanel bottom = new JPanel();
//		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
//		bottom.setPreferredSize(new Dimension(300,300));
//		content.add(bottom, BorderLayout.SOUTH);
		
	}

	private void addControlsToRightPanel() {
		// TODO Auto-generated method stub
		JPanel scroreBox = new JPanel();
		scroreBox.setLayout(new BoxLayout(scroreBox, BoxLayout.X_AXIS));
		scroreBox.add(new JLabel("Scrore : "));
		scrores = new JLabel("0");
		scroreBox.add(scrores);
		northPanel.add(scroreBox);
		
		JPanel timeBox = new JPanel();
		timeBox.setLayout(new BoxLayout(timeBox, BoxLayout.X_AXIS));
		timeBar = new JProgressBar(0, time);
		timeBar.setValue(time);
		timeBox.add(new JLabel("Time : "));
		timeBox.add(timeBar);
		northPanel.add(timeBox);
		
		JPanel btnControls = new JPanel();
		btnNewgame = new JButton("New game");
		btnControls.add(btnNewgame);
		northPanel.add(btnControls);

		
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btnNewgame.addActionListener(this);
		
	}
	protected void hidden(JButton btnTemp, int r, int c)
	{
		
	}
	public void showWindow()
	{
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==btnNewgame) {
			
			timeControl.setResume(false);
			timeControl.setPause(true);
			newGame();
		}
	}

	public void newGame() {
		// TODO Auto-generated method stub
		int select=JOptionPane.showConfirmDialog(null, "Are you sure");
		if (select== JOptionPane.YES_OPTION) {
			timeControl.setResume(true);
			timeControl.setPause(false);
			BI.removeAll();
			panelIcon.removeAll();
			BI.addButtonandIconToPanel(row+2, column+2);
			panelIcon.add(BI);
			time=500;
			setVisible(true);
			
		}
		else {
			timeControl.setResume(true);
			timeControl.setPause(false);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			timeBar.setValue(time);
		}
	}
}
