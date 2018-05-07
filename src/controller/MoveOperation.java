package controller;

import javax.swing.undo.AbstractUndoableEdit;

import model.Game;
import model.Move;
import model.Piece;

public class MoveOperation extends AbstractUndoableEdit {
	private Piece fromPiece;
	private Piece toPiece;
	private Move move;
	private boolean resetMoved;
	private boolean converted;
	private Game game;
	
	public MoveOperation(Game g,Move m, Piece from, Piece to, boolean conv){
		game = g;
		move = m;
		fromPiece = from;
		toPiece = to;
		converted = conv;
		if(!fromPiece.hasMoved()){
			resetMoved = true;
		}
	}

	
	public void undo(){
		fromPiece.getPosition().setPosition(move.getPosition1()); 
		if(toPiece != null){
			toPiece.revive();
		}
		if(resetMoved){
			fromPiece.resetMoved();
		}
		if(converted){
			Piece newQueen = game.getBoard().findAtPosition(move.getPosition2());
			newQueen.kill();
			fromPiece.revive();
		}
	}
    
	
	public void redo(){
		fromPiece.getPosition().setPosition(move.getPosition2()); 
		if(toPiece != null){
			toPiece.kill();
		}
		
	}
}
