package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Piece {
	protected Position position;
	String type;
	Color color;
	boolean alive;
	protected boolean hasMoved;
	
	
	
	public Piece(int x, int y, String t, Color c){
		position = new Position(x,y);
		type = t;
		color = c;
		alive = true;
		hasMoved = false;
		
	}
	
	public BufferedImage loadImage(){
		String pathToResources = "Resources\\";
		BufferedImage image = null;
		File imgFile = new File(pathToResources+"QueenBlack.png");
        try {
			image = ImageIO.read(imgFile);
		} catch (IOException e) {
			System.out.println("Failed to load image for " + this.type);
			e.printStackTrace();
		}
		
		return image;
	}
	public boolean hasMoved(){
		return hasMoved;
	}
	public void setMoved(){
		hasMoved = true;
	}
	public void resetMoved(){
		hasMoved = false;
	}
	public Color getColor(){
		return color;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void kill(){
		alive = false;
	}
	
	public void revive(){
		alive = true;
	}
	
	public String getType(){
		return type;
	}
	public Position getPosition(){
		return position;	
	}
	
	public List<Move> abstractMovement(){
		List<Move> moves = new ArrayList<Move>();
		
		
		return moves;
	}
	
}
