package userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.MatrixController;
import controllers.Line;

public class ButtonIconsPanel extends JPanel implements ActionListener{
	JPanel panelIcon;
	int column;
	int row;
	JButton[][] bi;
	int[][] mat;
	Point p1, p2;
	Line pointLine;
	int width =50, height=50 , bound=2;
	MatrixController cont;
	public int bonusMark=0;
	MainFrame frame;
	int item;
	
	public int[][] getMat() {
		return mat;
	}
	public void setMat(int[][] mat) {
		this.mat = mat;
	}
	public JButton[][] getBi() {
		return bi;
	}
	public void setBi(JButton[][] bi) {
		this.bi = bi;
	}
	public JPanel getPanelIcon() {
		return panelIcon;
	}
	public void setPanelIcon(JPanel panelIcon) {
		this.panelIcon = panelIcon;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public ButtonIconsPanel() {
		super();
		//set layout panel
	
		setPreferredSize(new Dimension((width+bound)*column,(height+bound)*row ));
		setBackground(Color.DARK_GRAY);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setAlignmentY(JPanel.CENTER_ALIGNMENT);
		setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
	}

	public ButtonIconsPanel(int column, int row, MainFrame frame) {
		super();
		
		this.column = column+2;
		this.row = row+2;
		this.frame = frame;
		this.item = column * row;
		addButtonandIconToPanel(this.column, this.row);
		setPreferredSize(new Dimension((width+bound)*column,(height+bound)*row ));
		setBackground(Color.DARK_GRAY);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setAlignmentY(JPanel.CENTER_ALIGNMENT);
		setAlignmentX(JPanel.CENTER_ALIGNMENT);
	}
	public void addButtonandIconToPanel(int column, int row)
	{
		setLayout(new GridLayout(row-2, column-2, bound, bound));
		mat = new int[row][column];
		int numOfIcon=21 ;
		cont = new MatrixController(column, row, mat, numOfIcon);
		cont.establishMatrix();
		cont.showMatrix();
		bi = new JButton[row-1][column-1];
		for (int i = 1; i < row-1; i++) {
			for (int j = 1; j < column-1; j++) {
				
				
				bi[i][j] = new JButton();
				Image image = new Image() {
					
					@Override
					public int getWidth(ImageObserver observer) {
						// TODO Auto-generated method stub
						return 0;
					}
					
					
					@Override
					public ImageProducer getSource() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public Object getProperty(String name, ImageObserver observer) {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public int getHeight(ImageObserver observer) {
						// TODO Auto-generated method stub
						return 0;
					}
					
					@Override
					public Graphics getGraphics() {
						// TODO Auto-generated method stub
						return null;
					}
				};
				ImageIcon ic = new ImageIcon("icon/" + mat[i][j] + ".png");
				
				image = ic.getImage();
				Icon icon = new ImageIcon(image.getScaledInstance(width, height,
						image.SCALE_SMOOTH));
				bi[i][j].setIcon(icon);
				bi[i][j].addActionListener(this);
				bi[i][j].setActionCommand(i+"+"+j);
				add(bi[i][j]);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO action 
		String cmd= e.getActionCommand();
		int indx = cmd.lastIndexOf("+");
		int i = Integer.parseInt(cmd.substring(0,indx));
		int j = Integer.parseInt(cmd.substring(indx+1, cmd.length()));
		
		if (p1==null) {
			p1 = new Point(i, j);
			bi[p1.x][p1.y].setBorder(new LineBorder(Color.red));
		}
		else {
			p2 = new Point(i,j);
			pointLine = cont.checkPointLine(p1, p2);
			if (pointLine!=null) {
				bonusMark+=10;
				frame.scrores.setText(bonusMark+"");
				disableButton(p1,p2);
			}
			bi[p1.x][p1.y].setBorder(null);
			p1=null;
			p2=null;		
		}
		
	}
	private void disableButton(Point p1, Point p2) {
		// TODO remove icon and disable button
		
		mat[p1.x][p1.y]= 0;
		mat[p2.x][p2.y] = 0;
		bi[p1.x][p1.y].setIcon(null);
		bi[p2.x][p2.y].setIcon(null);
		bi[p1.x][p1.y].setEnabled(false);
		bi[p2.x][p2.y].setEnabled(false);
		this.item -= 2;
		if (this.item==0&&frame.time!=0) {
			JOptionPane.showMessageDialog(null, "You win");
		}
		if (this.item!=0&& frame.time==0) {
			JOptionPane.showMessageDialog(null, "");
		}
	}
}
