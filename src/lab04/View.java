package lab04;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
public class View extends JPanel{
	private final int frameCount = 10;
	private int picNum = 0;
	private int height;
	private int width;
	private int imageHeight;
	private int imageWidth;
	private Map<String, BufferedImage[]> map;
	BufferedImage img;
	JFrame frame = new JFrame();
	//Graphics g;
	int x;
	int y;
	
	public View() {
		height = 300;
		width = 500;
		imageHeight = 165;
		imageWidth = 165;
		map = new HashMap<>();
		String[] imgKeys = {"se", "sw", "ne", "nw","n","s","w","e"};
		for(int i = 0; i < 8; i++) {
    		BufferedImage img = createImage(imgKeys[i]);
    		BufferedImage[] imgs = new BufferedImage[10];
    		for(int j = 0; j < frameCount; j++) {
        		imgs[j] = img.getSubimage(imageWidth*j, 0, imageWidth, imageHeight);
    		}
    		map.put(imgKeys[i], imgs);
		}
		
		
    	frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(width, height);
    	frame.setVisible(true);
    	
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, x, y, Color.gray, this);
    	
    }
	
	public void update(int x, int y, Direction direct) {
		picNum = (picNum + 1) % frameCount;
		if (direct.getName().equals("southeast")) {
    		img = map.get("se")[picNum];
    	}
    	else if (direct.getName().equals("southwest")) {
    		img = map.get("sw")[picNum];
    	}
    	else if (direct.getName().equals("northeast")) {
    		img = map.get("ne")[picNum]; 
    	}
    	else if (direct.getName().equals("northwest")){
    		img = map.get("nw")[picNum];
    	}
    	else if (direct.getName().equals("north")) {
    		img = map.get("n")[picNum];
    	}
    	else if (direct.getName().equals("south")) {
    		img =  map.get("s")[picNum];
    	}
    	else if (direct.getName().equals("east")) {
    		img = map.get("e")[picNum];
    	}
    	else {
    		img = map.get("w")[picNum];
    	}
		this.x = x;
		this.y = y;
		
		frame.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		//System.out.println("" +  x  + ", " + y + ", " + "img" + ", " + direct.getName() );
		
		
	}
	
	public BufferedImage createImage(String x) {
		BufferedImage bufferedImage;
    	try {
    		if (x.equals("se")) {
    			bufferedImage = ImageIO.read(new File("src/images/orc/orc_forward_southeast.png"));
    		}
    		else if (x.equals("ne")) {
    			bufferedImage = ImageIO.read(new File("src/images/orc/orc_forward_northeast.png"));
    		}
    		else if (x.equals("sw")) {
    			bufferedImage = ImageIO.read(new File("src/images/orc/orc_forward_southwest.png"));
    		}
    		else if (x.equals("nw")){
    			bufferedImage = ImageIO.read(new File("src/images/orc/orc_forward_northwest.png"));
    		}
    		else if (x.equals("n")) {
    			bufferedImage = ImageIO.read(new File("src/images/orc/orc_forward_north.png"));
    		}
    		else if (x.equals("s")) {
    			bufferedImage = ImageIO.read(new File("src/images/orc/orc_forward_south.png"));
    		}
    		else if (x.equals("w")) {
    			bufferedImage = ImageIO.read(new File("src/images/orc/orc_forward_west.png"));
    		}
    		else {
    			bufferedImage = ImageIO.read(new File("src/images/orc/orc_forward_east.png"));
    		}
    		//bufferedImage = ImageIO.read(new File("src/images/orc/orc_forward_southeast.png"));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	
}