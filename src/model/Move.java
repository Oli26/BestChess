package model;

public class Move {
	Position pos1;
	Position pos2;
	public Move(Position p1, Position p2){
		pos1 = p1;
		pos2 = p2;
	}
	
	public Position getPosition1(){
		return pos1;
	}
	public Position getPosition2(){
		return pos2;
	}
	
	public boolean isTheSame(Move m){
		if(this.pos1.getX() != m.getPosition1().getX()){
			return false;
		}
		if(this.pos2.getX() != m.getPosition2().getX()){
			return false;
		}
		if(this.pos1.getY() != m.getPosition1().getY()){
			return false;
		}
		if(this.pos2.getY() != m.getPosition2().getY()){
			return false;
		}
		return true;
	}
	
	
	public String getMoveString(){
		String returnString = "("+pos1.getX() + "," + pos1.getY() + ") -> (" + pos2.getX() + "," + pos2.getY() + ")";
		return returnString;
	}
}
