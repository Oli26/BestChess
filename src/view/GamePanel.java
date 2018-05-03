package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Board;
import model.Game;
import model.Piece;

public class GamePanel extends JPanel implements Observer  {

	private static final long serialVersionUID = 1L;
	
	Board board;
	Game game;
	public GamePanel(Game g){
		
		game = g;
		game.addObserver(this);
		board = game.getBoard();
		
	}
	
	 private void paintAreas(Graphics g) {

		 
		  
		  for(int x = 0; x<8; x++){
			for(int y = 0; y<8; y++){
				 if((x+y)%2 == 0){
					  g.setColor(Color.WHITE);
				  }else{
					  g.setColor(Color.GRAY);
				  }
				  g.fillRect(x*65, y*65, 60, 60);
				
			}
		  }
		  
		  
		  g.setColor(Color.BLACK);
		  List<Piece> pieces = board.getAllPieces();
		  for(int i = 0; i<pieces.size(); i++){
			  Piece p = pieces.get(i);
			  String c;
			  if(p.getColor() == Color.BLACK){
				  c = "B";
			  }else{
				  c = "W";
			  }
			  if(p.isAlive()){
				  //g.drawString(c+ "|" + p.getType(), p.getPosition().getX()*65+8,p.getPosition().getY()*65+15);
				  g.drawImage(p.loadImage(),p.getPosition().getX()*65,p.getPosition().getY()*65, this);
			  }
			  
		  }
	 }			  
				 
	 @Override
	 public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 paintAreas(g);
	 }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		repaint();
	}
		
	
}
