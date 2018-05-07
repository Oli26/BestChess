package model;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GeneticAI.GeneticAI;
import controller.MoveOperation;
import controller.UndoManager;

public class Game extends Observable implements Observer{
	Board board;
	Color turn;
	GeneticAI AI;
	UndoManager undoManager;
	public Game(){
		board = new Board();
		turn = Color.WHITE;
		undoManager = new UndoManager();
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
	
	public Color getTurn(){
		return turn;
	}
	
	
	public void AIMove(){
		AI.AIMove();
	}
	
	
	public String makeMove(Move m, boolean real){
		//System.out.printf(turn + " is attempting move (%d,%d)->(%d,%d)\n", m.getPosition1().getX(), m.getPosition1().getY(), m.getPosition2().getX(), m.getPosition2().getY());
		if(board.movePossible(m)){
			Piece toMove = board.findAtPosition(m.getPosition1());
			Piece hit = board.findAtPosition(m.getPosition2());
			boolean hasMoved = toMove.hasMoved();
			if(toMove.getColor() != turn){
				return "Not your turn";
			}
			
			toMove.position.setX(m.getPosition2().getX());
			toMove.position.setY(m.getPosition2().getY());
			//System.out.println("Got here for move :" + m.getMoveString());
			
			if(hit != null){
				hit.kill();
			}
			
			
			if(board.inCheck(turn)){

				//System.out.println("Cant move because it ends in check!");
				toMove.position.setX(m.getPosition1().getX());
				toMove.position.setY(m.getPosition1().getY());
				if(hit != null)
					hit.revive();
				if(hasMoved == false)
					toMove.resetMoved();
				
				if(board.isCheckMate(turn)){
					if(real){
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,"Checkmate, Game over!");
					}
					return "Checkmate";
				}
				return "moved failed: check";
			}
			
			
			
			
		
			
			boolean converted = false;
			if(toMove.getType() == "Pawn"){
				if(turn == Color.BLACK && m.getPosition2().getY() == 0){
					board.convertToQueen(toMove);
					converted = true;
					
				}else if(turn == Color.WHITE && m.getPosition2().getY() == 7){
					board.convertToQueen(toMove);
					converted = true;
				}
			}
			
			updateCustom();
			flipTurn();	
			undoManager.newOperation(new MoveOperation(this,m, toMove, hit, converted));
			toMove.setMoved();
			//System.out.println("move success: " + m.getMoveString());
			return "moved";
		}
		return "moved failed: general";
	}
	
	public void undoMove(){
		undoManager.undo();
		flipTurn();
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
