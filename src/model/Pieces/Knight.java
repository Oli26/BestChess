package model.Pieces;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import model.Move;
import model.Piece;
import model.Position;

public class Knight extends Piece {


	public Knight(int x, int y, Color c){
		super(x,y,"Knight", c);
	}
	
	public List<Move> abstractMovement(){
		List<Move> moves = new ArrayList<Move>();
		
		moves.add(new Move(new Position(position.getX(), position.getY()),new Position(position.getX()+2, position.getY()+1)));
		moves.add(new Move(new Position(position.getX(), position.getY()),new Position(position.getX()+2, position.getY()-1)));
		moves.add(new Move(new Position(position.getX(), position.getY()),new Position(position.getX()-2, position.getY()+1)));
		moves.add(new Move(new Position(position.getX(), position.getY()),new Position(position.getX()-2, position.getY()-1)));
		moves.add(new Move(new Position(position.getX(), position.getY()),new Position(position.getX()+1, position.getY()+2)));
		moves.add(new Move(new Position(position.getX(), position.getY()),new Position(position.getX()+1, position.getY()-2)));
		moves.add(new Move(new Position(position.getX(), position.getY()),new Position(position.getX()-1, position.getY()+2)));
		moves.add(new Move(new Position(position.getX(), position.getY()),new Position(position.getX()-1, position.getY()-2)));

		return moves;
	}
	
	public BufferedImage loadImage(){
		String pathToResources = "Resources\\";
		BufferedImage image = null;
		File imgFile;
		if(this.getColor() == Color.BLACK){
			imgFile = new File(pathToResources+"HorseBlack.png");
		}else{
			imgFile = new File(pathToResources+"HorseWhite.png");
		}
		
        try {
			image = ImageIO.read(imgFile);
		} catch (IOException e) {
			System.out.println("Failed to load image for " + this.getType());
			e.printStackTrace();
		}
		
		return image;
	}
	
}
