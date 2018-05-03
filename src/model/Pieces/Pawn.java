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

public class Pawn extends Piece {

	public Pawn(int x, int y, Color c){
		super(x,y,"Pawn", c);

	}
	
	
	
	public List<Move> abstractMovement(){
		List<Move> moves = new ArrayList<Move>();
		
		int multiplier;
		if(this.getColor()== Color.WHITE){
			multiplier = 1;
		}else{
			multiplier = -1;	
		}
			
		
		if(!hasMoved){
			moves.add(new Move(new Position(position.getX(), position.getY()), new Position(position.getX(), position.getY()+(2*multiplier))));
			
		}
		
		moves.add(new Move(new Position(position.getX(), position.getY()), new Position(position.getX(), position.getY()+multiplier)));
		moves.add(new Move(new Position(position.getX(), position.getY()), new Position(position.getX()+1, position.getY()+multiplier)));
		moves.add(new Move(new Position(position.getX(), position.getY()), new Position(position.getX()-1, position.getY()+multiplier)));
		
		return moves;
	}
	
	
	public BufferedImage loadImage(){
		String pathToResources = "Resources\\";
		BufferedImage image = null;
		File imgFile;
		if(this.getColor() == Color.BLACK){
			imgFile = new File(pathToResources+"PawnBlack.png");
		}else{
			imgFile = new File(pathToResources+"PawnWhite.png");
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
