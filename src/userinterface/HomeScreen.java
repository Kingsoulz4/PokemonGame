package userinterface;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HomeScreen extends JFrame{
	public HomeScreen(String title) throws IOException
	{
		super(title);
		addControls();
		addEvents();
		
	}

	public void showWindow() {
		// TODO Auto-generated method stub
		this.setSize(620,340);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		
	}

	public void addControls() throws IOException {
		// TODO Auto-generated method stub
		int width = 620, height = 320;
		Container content  = getContentPane();
		//setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("img\\pokemon-1529246_1920.jpg")))));
		Image img = new Image() {
			
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
		String path = "img\\pokemon-1529246_1920.jpg";
		ImageIcon imgIcon = new ImageIcon(path);
		img = imgIcon.getImage();
		Icon icon = new ImageIcon(img.getScaledInstance(width, height, img.SCALE_SMOOTH));
		
		//setContentPane(new JLabel(icon));
		JButton btnNewGame = new JButton("New game");
		JPanel btn = new JPanel();
	
		btn.add(btnNewGame);
		content.add(btn);
	}
}
