package controller;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import model.Game;
import model.Move;
import model.Position;
import view.GamePanel;

public class MouseInput extends MouseInputAdapter {
	private Game game;
	private GamePanel panel;
	private int xOldSquare;
	private int yOldSquare;
	public MouseInput(Game g, GamePanel p){
		game = g;
		panel =p;
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}

	
	@Override
	public void mousePressed(MouseEvent event) {
			xOldSquare = event.getX()/65;
			yOldSquare = event.getY()/65;
	}
	@Override
	public void mouseDragged(MouseEvent event) {
	        
	}
		
	@Override
	public void mouseReleased(MouseEvent event) {
	        int xSquare = event.getX()/65;
	        int ySquare = event.getY()/65;
	        
	        if(-1<xSquare && xSquare<8 && ySquare>-1 && ySquare<8 && !(xOldSquare==xSquare && yOldSquare==ySquare)){
	        	String result = game.makeMove(new Move(new Position(xOldSquare, yOldSquare), new Position(xSquare, ySquare)));
	        	if(result == "moved"){
	        		game.AIMove();
	    	        System.out.printf("Trying: (%d,%d)->(%d,%d) : Success\n", xOldSquare, yOldSquare, xSquare, ySquare);
	        	}else{

	    	        System.out.printf("Trying: (%d,%d)->(%d,%d): Failed\n", xOldSquare, yOldSquare, xSquare, ySquare);
	        	}
	        	
	        	
	        	game.updateCustom();
	        	
	        }
	}
		
}
