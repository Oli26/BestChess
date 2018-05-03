package GeneticAI;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import model.Board;
import model.Game;
import model.Move;

public class GeneticAI {
	Chromosome chromosome1;
	Game game;
	Board board;
	Color color;
	public GeneticAI(Game g, Color c){
		game = g;
		board = game.getBoard();
		color = c;
		chromosome1 = new Chromosome();
	}
	
	
	public void AIMove(){
		Move move;
		int rand;
		String returnValue;
		
		do{
			move = generateMove();
			returnValue = game.makeMove(move);
			if(returnValue == "Checkmate"){
				return;
			}
			
		}while(returnValue != "moved");
		
		
	}
	
	public Move generateMove(){
		List<Move> moves = board.getPossibleMoves(color);
		
		Random r = new Random();
		int randomInt = r.nextInt(moves.size()-1);
		return moves.get(randomInt);
		
	}
	
	public int valueMove(Move m){
		Gene gene = chromosome1.getGene(0); // First value is the amount of positions we want to extract from the board.
		char[] sequenceGene1 = gene.getSequence();
		
		// Set a weighting of variables based off of genes
		Gene pieceWeighting = chromosome1.getGene(gene.getAtomicValue(1));
		char[] sequenceGenePieceWeighting = pieceWeighting.getSequence();
		int pawnValue = Integer.parseInt(sequenceGenePieceWeighting[0]+"");
		int bishopValue = Integer.parseInt(sequenceGenePieceWeighting[1]+"");
		int rookValue = Integer.parseInt(sequenceGenePieceWeighting[2]+"");
		int KnightValue = Integer.parseInt(sequenceGenePieceWeighting[3]+"");
		int QueenValue = Integer.parseInt(sequenceGenePieceWeighting[4]+"");
		int kingValue = 1000000;
		
		////////////////////////////////////////
		
		return 0;
		
	}
	
	
}
