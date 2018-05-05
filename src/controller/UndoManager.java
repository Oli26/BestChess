package controller;

import java.util.Stack;

import controller.MoveOperation;

public class UndoManager {

	private Stack<MoveOperation> undo;
	private Stack<MoveOperation> redo;
	
	public UndoManager() {
		undo = new Stack<MoveOperation>();
		redo = new Stack<MoveOperation>();
	}
	
	public void reset(){
		while(undo.size() != 0){
			undo.pop();
		}
		while(redo.size() != 0){
			redo.pop();
		}
	}
	
	public void resetRedo(){
		while(redo.size() != 0){
			redo.pop();
		}
	}
	
	public void newOperation(MoveOperation operation) {
		undo.push(operation);		
	}
	
	public void undo() {
		if(undo.empty()== true)
			return;
		MoveOperation operation = undo.pop();
		operation.undo();
		redo.push(operation);
	}
	
	public void redo() {
		if(redo.empty() == true)
			return;
		MoveOperation operation = redo.pop();
		operation.redo();
		undo.push(operation);
	}

	/*
	public String getMoves(){
		Stack<MoveOperation> temp = new Stack<MoveOperation>();
		String output = new String();
		while(undo.empty() != true){
			MoveOperation operation = undo.pop();
			
			temp.push(operation);
		}
		while(temp.empty() != true){
			MoveOperation operation = temp.pop();
			MoveOperation op = (MoveOperation)operation;
			output += op.getInformation() + "\n";
			undo.push(operation);
		}
		
		return output;
	}
	*/
}
