package model;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import AI.AIManager;
import GeneticAI.GeneticAI;

public class Game extends Observable implements Observer{
	Board board;
	Color turn;
	GeneticAI AI;
	public Game(){
		board = new Board();
		turn = Color.WHITE;
		
		AI = new GeneticAI(this,Color.BLACK);
	}
	
	public Board getBoard(){
		return board;
	}

	public void flipTurn(){
		if(turn == Color.BLACK){
			turn = Color.WHITE;
		}else{
			turn = Color.BLACK;
		}	
	}
	
	
	public void AIMove(){
		
		AI.AIMove();
	}
	
	public String makeMove(Move m){
		System.out.printf(turn + " is attempting move (%d,%d)->(%d,%d)", m.getPosition1().getX(), m.getPosition1().getY(), m.getPosition2().getX(), m.getPosition2().getY());
		if(board.movePossible(m)){
			
			
			
			Piece toMove = board.findAtPosition(m.getPosition1());
			Piece hit = board.findAtPosition(m.getPosition2());
			
			if(toMove.getColor() != turn){
				return "Not your turn";
			}
			
			toMove.position.setX(m.getPosition2().getX());
			toMove.position.setY(m.getPosition2().getY());
			
			if(hit != null){
				hit.kill();
			}
			
			
			if(board.inCheck(turn)){
				if(board.isCheckMate(turn)){
					return "Checkmate";
				}
				System.out.println("Cant move because it ends in check!");
				toMove.position.setX(m.getPosition1().getX());
				toMove.position.setY(m.getPosition1().getY());
				if(hit != null)
					hit.revive();
				return "moved failed: check";
			}
			
			
			
			
		
			toMove.setMoved();
			
			System.out.println("Type:" + toMove.getType() + ". New y ="+ m.getPosition2().getY());
			if(toMove.getType() == "Pawn"){
				if(turn == Color.BLACK && m.getPosition2().getY() == 0){
					System.out.println("Called1");
					board.convertToQueen(toMove);
					
				}else if(turn == Color.WHITE && m.getPosition2().getY() == 7){
					System.out.println("Called1");
					board.convertToQueen(toMove);
					
				}
			}
			
			updateCustom();
			flipTurn();	
			return "moved";
		}
		return "moved failed: general";
	}
	
	
	public void printGame(){
		for(int y=7; y>=0; y--){
			for(int x=7; x>=0; x--){
				Piece p = board.findAtPosition(new Position(x,y));
				
				if(p == null || !p.isAlive()){
					System.out.printf("|          |");
					
				}else{
					String color;
					if(p.getColor() == Color.BLACK){
						color = "B";
					}else{
						color = "W";
						
					}
					String type = p.getType();
					while(type.length() < 8){
						type += " ";
					}
					System.out.printf("|" + color + " " + type + "|");
				}
				
			}
			System.out.println("");
			System.out.println("------------------------------------------------------------------------------------------------");
			
		}
	}


	public void updateCustom(){
		setChanged();
		notifyObservers();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {		
	}
}
