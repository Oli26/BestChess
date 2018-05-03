package AI;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import model.Board;
import model.Game;
import model.Move;

public class AIManager {
	Game game;
	Board board;
	Color color;
	public AIManager(Game g, Color c){
		game = g;
		board = game.getBoard();
		color = c;
	}
	
	
	
	
	public void AIMove(){
		
		List<Move> moves = board.getPossibleMoves(color);
		
		Random r = new Random();
		int rand;
		String returnValue;
		
		do{
			
			
			rand = r.nextInt(moves.size()-1)+1;
			returnValue = game.makeMove(moves.get(rand));
			if(returnValue == "Checkmate"){
				return;
			}
			
		}while(returnValue != "moved");
		
		
	}
}
