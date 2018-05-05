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
	
	public MoveOperation(Move m, Piece to, Piece from, boolean conv){
		move = m;
		fromPiece = to;
		toPiece = from;
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
	}
    
	
	public void redo(){
		fromPiece.getPosition().setPosition(move.getPosition2()); 
		if(toPiece != null){
			toPiece.kill();
		}
		
	}
}
