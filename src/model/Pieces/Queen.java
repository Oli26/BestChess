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

public class Queen extends Piece{
	public Queen(int x, int y, Color c){
		super(x,y, "Queen", c);
		
	}
	
	public List<Move> abstractMovement(){
		List<Move> moves = new ArrayList<Move>();
		for(int i=0;i<16;i++){
			moves.add(new Move(new Position(position.getX(), position.getY()),new Position(position.getX(), position.getY()-8+i)));
			moves.add(new Move(new Position(position.getX(), position.getY()),new Position(position.getX()-8+i, position.getY())));
			moves.add(new Move(new Position(position.getX(), position.getY()),new Position(position.getX()-8+i, position.getY()-8+i)));
			moves.add(new Move(new Position(position.getX(), position.getY()),new Position(position.getX()-8+i, position.getY()+8-i)));
		}
		
		return moves;
	}
	
	public BufferedImage loadImage(){
		String pathToResources = "Resources\\";
		BufferedImage image = null;
		File imgFile;
		if(this.getColor() == Color.BLACK){
			imgFile = new File(pathToResources+"QueenBlack.png");
		}else{
			imgFile = new File(pathToResources+"QueenWhite.png");
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
