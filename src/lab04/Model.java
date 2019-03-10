package lab04;

import java.util.Random;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

public class Model {
	private int x;
	private int y;
	private Direction direct;
	private int width;
	private int height;
	private int imgHeight;
	private int imgWidth;
	final int xIncr = 20;   //8
    final int yIncr = 15;   // 2
    Direction[] dirs = {Direction.NORTH, Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTHEAST, Direction.NORTHWEST,
    		Direction.SOUTHEAST, Direction.SOUTHWEST};

	
	public Model (int w, int h, int iW, int iH) {
		width = w;
		height = h;
		imgHeight = iH;
		imgWidth = iW;
		x = 0;
		y = 0;
		direct = Direction.SOUTHEAST;
	}

	public void updateLocationAndDirection() {
		// update x
		if (direct.getName().contains("east")) {
    		if (x <= width - imgWidth) {
    			x+= xIncr;
    		}
    		else {
    			getDirect("east");
    		}
    	}
    	else if (direct.getName().contains("west")){
    		if (x >= 0) {
    			x -= xIncr;
    		}
    		else {
    			getDirect("west");
    		}
    	}
		
		// update y
		if (direct.getName().contains("south")) {
    		if (y <= height - imgHeight) {
    			y += yIncr;
    		}
    		else {
    			getDirect("south");
    		}
    	}
    	else if (direct.getName().contains("north")){
    		if (y > 0) {
    			y -= yIncr;
    		}
    		else {
    			getDirect("north");
    		}
    	}
		
		
	}
	
	public void getDirect(String hit) {
    	Random random = new Random();
    	Direction d =dirs[Math.abs(random.nextInt()) % dirs.length];
    	if (d.getName().contains(hit)) {
    		getDirect(hit);
    	}
    	else {
    		direct = d;
    		//System.out.println("change to: " +  direct.getName());
    		return;
    	}
   }

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Direction getDirect() {
		return direct;
	}
	
}
