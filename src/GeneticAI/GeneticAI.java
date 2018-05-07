package GeneticAI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

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
			move = generateMove(4,color);
			System.out.println(move.getMoveString());
			returnValue = game.makeMove(move, true);
			if(returnValue == "Checkmate"){
				return;
			}
			if(returnValue == "Not your turn"){
				game.flipTurn();
				returnValue = game.makeMove(move, true);
			}
			
			System.out.println(returnValue);
			
			
		}while(returnValue != "moved");
		//System.out.println("AI Moved!");
		
	}
	
	public Move generateMove(int depth, Color c){
		
		List<Move> moves = new ArrayList<Move>();
		moves.addAll(board.getPossibleMoves(c));
		

		Random r = new Random();
		//int randomInt = r.nextInt(moves.size()-1);
		float bestValue = -10000;
		List<Move> bestMoves = new ArrayList<Move>();
		for(int i = 0; i< moves.size(); i++){
			System.out.println("Testing move:" + moves.get(i).getMoveString());
			float newValue = valueMoveTwo(moves.get(i), depth, c);
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
	
	
	public float makeMoveRecordValue(Move m, Color c){
		String result = game.makeMove(m, false);
		if(result == "Not your turn"){
			game.flipTurn();
			result = game.makeMove(m, false);
		}
		if(result == "Checkmate"){
			return 9999;
		}
		if(result != "moved"){
			return -9999;
		}
		float value = evaluateBoard(chromosome1.getGene(0), c);
		
		game.undoMove();
		//System.out.println("Move Undo");
		return value;
	}
	
	public float valueMoveTwo(Move m, int depth, Color c){
		
		String offset = new String();
		for(int i =0; i<(4-depth);i++){
			offset += "    ";
		}
		
		Gene gene = chromosome1.getGene(0); 
		Gene pieceWeighting = chromosome1.getGene(gene.getAtomicValue(1));
		
		if(depth == 0){
			float value = makeMoveRecordValue(m,c);
			if(c ==Color.WHITE){
				System.out.println(offset+"White picked move:" + m.getMoveString());
			}else{
				System.out.println(offset+"Black picked move:" + m.getMoveString());
			}
			System.out.println(offset+"Was base, value = " + value);
			return value;
		}
		
		///////////// MAKE MOVE ///////////////////
		String status = game.makeMove(m, false);
		if(c ==Color.WHITE){
			System.out.println(offset+"White picked move:" + m.getMoveString());
		}else{
			System.out.println(offset+"Black picked move:" + m.getMoveString());
		}
		if(status == "Checkmate"){
			System.out.println(offset+"Checkmate reachable");
			return 10000;
		}
		if(status != "moved"){
			return -9999;
		}
		
		///////////////////////////////////////////
		
		
		
		
		////////// FLIP TURN AND GEN COUNTER MOVES //////////////////
		c = getOtherColor(c);
		if(c ==Color.WHITE){
			System.out.println(offset+"White generates moves");
		}else{
			System.out.println(offset+"Black generates moves");
		}		
		
		List<Move> moves = new ArrayList<Move>();
		moves.addAll(game.getBoard().getPossibleMoves(c));
		
		////////////////////////////////////////////////////////////////
		
		
		
		
		///////// TEST ALL COUNTER MOVES AND SELECT BEST //////////////
		
		float[] moveValues = new float[moves.size()];
		for(int i = 0; i<moves.size(); i++){
			float newValue = makeMoveRecordValue(moves.get(i), c);
			//float newValue = valueMoveTwo(moves.get(i), 1, c);
			System.out.println(offset+"aux:" + moves.get(i).getMoveString() + "(" + newValue + ")");
			moveValues[i] = newValue;
		}
			
			
		List<Move> passedMoves = new ArrayList<Move>();
		float highest = -9998;
		int highestIndex = 0;
		int amountOfChildren = 1;
		for(int i = 0; i<amountOfChildren; i++){
			for(int j = 0; j<moves.size(); j++){
				if(moveValues[j] > highest){
					highest = moveValues[j];
					highestIndex = j;
				}
			}
			passedMoves.add(moves.get(highestIndex));
			moveValues[highestIndex] = -9999;
		}
			
			
		float[] returnValues = new float[amountOfChildren];
		for(int i = 0; i<amountOfChildren; i++){
			 returnValues[i] = valueMoveTwo(passedMoves.get(i), depth-1, c);
		}
				
		highest = -9999;
		for(int i = 0; i<amountOfChildren; i++){
			if(returnValues[i] > highest){
				highest = returnValues[i];
			}
		}
			
		/*	
		}else{
			float lowest = 10000;
			for(int i = 0; i<moves.size(); i++){
				
				float newValue = makeMoveRecordValue(moves.get(i), c);
				System.out.println("aux:" + moves.get(i).getMoveString() + "(" + newValue + ")");
				if(newValue < lowest){
					lowest = newValue;
					bestMove = moves.get(i);
				}
			}
		}	
		*/
		
		
		////////////////////////////////////////////////////////////////
		
		

		game.undoMove();
		return highest;
	}
	
}
