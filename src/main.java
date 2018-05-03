import java.util.Scanner;

import javax.swing.JFrame;

import GeneticAI.Gene;
import controller.MouseInput;
import model.Game;
import view.GamePanel;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Game g1 = new Game();
		g1.printGame();
		
		
		
		
		Gene gene1 = new Gene("12345678");
		
		for(int i = 0; i<15; i++){
			gene1.print();
			gene1.mutate();
			
			
		}
		
		
		
		/////////// GUI ///////////////////
		
		JFrame frame = new JFrame();
		GamePanel panel = new GamePanel(g1);
		
		
		MouseInput input = new MouseInput(g1, panel);
		
		frame.add(panel);
		frame.setSize(528, 550);
		frame.setDefaultCloseOperation(1);
		frame.setVisible(true);
		
		
		
		
		
		
		
		////////////////////////////////////
		/*
		boolean loop = true;
		while(loop){
			String nextMove;
			int x1;
			int y1;
			int x2;
			int y2;
			Move newMove;
			String result;
			do{
				nextMove = sc.nextLine();
				x1 = Integer.parseInt(nextMove.charAt(1) + "");
				y1 = Integer.parseInt(nextMove.charAt(3) + "");
				x2 = Integer.parseInt(nextMove.charAt(7) + "");
				y2 = Integer.parseInt(nextMove.charAt(9) + "");
				//System.out.println("(" + x1 + "," + y1 + ")->(" + x2 + "," + y2 + ")");
				newMove = new Move(new Position(x1,y1), new Position(x2,y2));
				result = g1.makeMove(newMove);
			}while(result != "moved");
			//g1.printGame();
		}
		*/
	}

}
