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

public class Rook extends Piece {
	public Rook(int x, int y, Color c){
		super(x,y,"Rook", c);
	}
	
	public List<Move> abstractMovement(){
		List<Move> moves = new ArrayList<Move>();
		for(int i=0;i<16;i++){
			moves.add(new Move(new Position(position.getX(), position.getY()),new Position(position.getX(), position.getY()-8+i)));
			moves.add(new Move(new Position(position.getX(), position.getY()),new Position(position.getX()-8+i, position.getY())));
		}
		
		
		return moves;
	}
	
	
	public BufferedImage loadImage(){
		String pathToResources = "Resources\\";
		BufferedImage image = null;
		File imgFile;
		if(this.getColor() == Color.BLACK){
			imgFile = new File(pathToResources+"RookBlack.png");
		}else{
			imgFile = new File(pathToResources+"RookWhite.png");
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
