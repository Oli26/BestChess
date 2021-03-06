package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.Pieces.*;

public class Set {
	List<Piece> pieces;
	Color color;
	
	public Set(Color c){
		color = c;
		if(c == Color.BLACK){
			initBlackSet();
		}else{
			initWhiteSet();
		}
	}
	
	public void convertToQueen(Piece p){
		p.kill();
		int x = p.getPosition().getX();
		int y = p.getPosition().getY();
		
		pieces.add(new Queen(x,y, color));
		
	}
	
	public List<Piece> getPieces(){
		return pieces;
	}
	
	public List<Piece> findPiece(String type){
		List<Piece> returnList = new ArrayList<Piece>();
		for(int i=0;i<pieces.size(); i++){
			if(pieces.get(i).getType() == type && pieces.get(i).isAlive()){
				returnList.add(pieces.get(i));
			}
		}
		return returnList;
	}
	

	public Piece findAtPosition(Position p){
		for(int i=0;i<pieces.size(); i++){
			Position pos= pieces.get(i).getPosition();
			if(pos.getX() == p.getX() && pos.getY() == p.getY() && pieces.get(i).isAlive()){
				return pieces.get(i);
			}
		}
		return null;
	}
	
	public List<Move> getAllAbstractMovement(){
		List<Move> moves = new ArrayList<Move>();
		for(int i=0;i<pieces.size(); i++){
			if(pieces.get(i).isAlive()){
				moves.addAll(pieces.get(i).abstractMovement());
			}
		}
		
		return moves;
	}
	
	
	
	public void initWhiteSet(){
		pieces = new ArrayList<Piece>();
		Color c = Color.WHITE;
		// Add king
		pieces.add(new King(3,0,c));
		
		// Add Queen
		pieces.add(new Queen(4,0,c));
		
		// Add two bishops
		pieces.add(new Bishop(2,0,c));
		pieces.add(new Bishop(5,0,c));
		
		// Add two knights
		pieces.add(new Knight(1,0,c));
		pieces.add(new Knight(6,0,c));
		
		// Add two rooks
		pieces.add(new Rook(0,0,c));
		pieces.add(new Rook(7,0,c));
		
		// Add 8 pawns
		for(int i=0;i<8;i++){
			pieces.add(new Pawn(i,1,c));
		}
	}
	
	public void initBlackSet(){
		pieces = new ArrayList<Piece>();
		Color c = Color.BLACK;
		// Add king
		pieces.add(new King(3,7,c));
		
		// Add Queen
		pieces.add(new Queen(4,7,c));
		
		// Add two bishops
		pieces.add(new Bishop(2,7,c));
		pieces.add(new Bishop(5,7,c));
		
		// Add two knights
		pieces.add(new Knight(1,7,c));
		pieces.add(new Knight(6,7,c));
		
		// Add two rooks
		pieces.add(new Rook(0,7,c));
		pieces.add(new Rook(7,7,c));
		
		// Add 8 pawns
		for(int i=0;i<8;i++){
			pieces.add(new Pawn(i,6,c));
		}
	}
	
	
	
}
