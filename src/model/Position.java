package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Position {
	private int x;
	private int y;
	public Position(int setX, int setY){
		x = setX;
		y = setY;
	}
	
	
	public void setPosition(int setX, int setY){
		x = setX;
		y = setY;
	}
	
	public void setPosition(Position p){
		x = p.getX();
		y = p.getY();
	}
	public int getX(){
		return x; 
	}
	public int getY(){
		return y;
	}
	
	public void setX(int n){
		x = n;
	}
	public void setY(int n){
		y = n;
	}
	
	
	
	public boolean isTheSame(Position p){
		if(x == p.getX() && y == p.getY()){
			return true;
		}
		return false;
	}

}
