package GeneticAI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Board;
import model.Game;
import model.Move;
import model.Piece;

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
	
	public Color getOtherColor(Color c){
		if(c == Color.WHITE){
			return Color.BLACK;
		}else{
			return Color.WHITE;
		}
	}
	
	public void AIMove(){
		Move move;
		String returnValue;
		
		do{
			System.out.println("AI tried to move!");
			move = generateMove(1,color);
			System.out.println(move.getMoveString());
			returnValue = game.makeMove(move);
			if(returnValue == "Checkmate"){
				return;
			}
			if(returnValue == "Not your turn"){
				game.flipTurn();
				returnValue = game.makeMove(move);
			}
			
			System.out.println(returnValue);
			
			
		}while(returnValue != "moved");
		//System.out.println("AI Moved!");
		
	}
	
	public Move generateMove(int depth, Color c){
		
		List<Move> moves = board.getPossibleMoves(c);
		

		Random r = new Random();
		//int randomInt = r.nextInt(moves.size()-1);
		float bestValue = -10000;
		List<Move> bestMoves = new ArrayList<Move>();
		for(int i = 0; i< moves.size(); i++){
			float newValue = -valueMove(moves.get(i), c, depth);
			if(newValue > bestValue){
				bestValue = newValue;
				bestMoves = new ArrayList<Move>();
				bestMoves.add(moves.get(i));
			}else if(newValue == bestValue){
				bestMoves.add(moves.get(i));
			}
			
		}
		
		if(bestMoves.size() == 0){
			return null;
		}
		int randomInt = r.nextInt(bestMoves.size());
		Move bestMove = bestMoves.get(randomInt);
		
		System.out.println("Best = " + bestValue);
		return bestMove;
		//return moves.get(randomInt);
		
	}
	

	
	public float valueMove(Move m, Color c, int depth){
		Gene gene = chromosome1.getGene(0); // First value is the amount of positions we want to extract from the board.
		//System.out.println("Valuing move:" + m.getMoveString());
		Gene pieceWeighting = chromosome1.getGene(gene.getAtomicValue(1));
		
		
		if(depth == 0){
			//System.out.println("Attempting move " + m.getMoveString());
			String result = game.makeMove(m);
			if(result == "Not your turn"){
				game.flipTurn();
				result = game.makeMove(m);
			}
			if(result == "moved"){
				float value = evaluateBoard(pieceWeighting, c);
				//System.out.println("Returning value of "+ value);
				game.undoMove();
				return value;
			}
			return -9899;
		}else{
			List<Move> moves = game.getBoard().getPossibleMoves(c);
			float bestMoveValue = -9989;
		
			for(int i=0; i<moves.size();i++){
				float newValue = valueMove(moves.get(i), getOtherColor(c), depth-1);
				if(newValue > bestMoveValue){
					bestMoveValue = newValue;
				}
			}
			//System.out.println(".........");
			return bestMoveValue;
			
			
		}
		
		
	}
	

	public float evaluateBoard(Gene pieceWeighting, Color turn){
		
		Gene pieceWeighting2 = chromosome1.getGene(pieceWeighting.getAtomicValue(1));
		Gene pieceWeighting3 = chromosome1.getGene(pieceWeighting.getAtomicValue(2));
		
		Gene pawnGene = chromosome1.getGene(pieceWeighting2.getAtomicValue(1));
		Gene bishopGene = chromosome1.getGene(pieceWeighting2.getAtomicValue(2));
		Gene knightGene = chromosome1.getGene(pieceWeighting2.getAtomicValue(3));
		Gene rookGene = chromosome1.getGene(pieceWeighting2.getAtomicValue(4));
		Gene queenGene = chromosome1.getGene(pieceWeighting3.getAtomicValue(1));
		Gene kingGene =  chromosome1.getGene(pieceWeighting3.getAtomicValue(2));
		Gene mobilityGene = chromosome1.getGene(pieceWeighting3.getAtomicValue(3));
		
		float pawnValue = pawnGene.convertToFloat();
		float bishopValue = bishopGene.convertToFloat();
		float rookValue = rookGene.convertToFloat();
		float knightValue = knightGene.convertToFloat();
		float queenValue = queenGene.convertToFloat();
		float kingValue = kingGene.convertToFloat();
		float mobilityValue = mobilityGene.convertToFloat();
		

		pawnValue = 1;
		bishopValue = 3;
		rookValue = 5;
		knightValue = 3;
		queenValue = 9;
		kingValue = 90;
		mobilityValue = 0.01f;
		
		Color otherColor;
		if(color == Color.WHITE){
			otherColor = Color.BLACK;
		}else{
			otherColor = Color.WHITE;
		}
		
		List<Move> movesAI = game.getBoard().getPossibleMoves(color);
		List<Move> movesPlayer = game.getBoard().getPossibleMoves(otherColor);
		
		List<Piece> pieces = game.getBoard().getAllPieces();

		
		//System.out.println(game.getBoard().getAllPieces().size());
		float materialValueAI = 0;
		float materialValuePlayer = 0;
		int counter = 0;
		for(int i = 0; i<pieces.size(); i++){
			float value = 0;
			
			switch(pieces.get(i).getType()){
				case "Pawn":
							value = pawnValue;
							break;
				case "Bishop":
							value = bishopValue;
							break;
				case "Knight":
							value = knightValue;
							break;
				case "Rook":
							value = rookValue;
							break;
				case "Queen":
							value = queenValue;
							break;
				case "King":
							value = kingValue;
							break;
			
			
			
			}
			
			if(!pieces.get(i).isAlive()){
				value =0;
			}
			
			if(pieces.get(i).getColor() == color){
				materialValueAI += value;
				counter++;
			}else{
				materialValuePlayer += value;
				counter--;
			}
			
		}

		float eval = (materialValueAI - materialValuePlayer) + mobilityValue*(movesAI.size()-movesPlayer.size());
		if(turn == color){
			return eval;
		}else{
			return -eval;
		}
		
	}
	
	
	
}
