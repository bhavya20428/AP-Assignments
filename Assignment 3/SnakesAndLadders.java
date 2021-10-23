import java.util.*;
public class SnakesAndLadders{
	public static void main(String[] args) {
		
		
	}

}


class Game{
	private Floor f0;
	private Floor f1;
	private Floor f2;
	private Floor f3;
	private Floor f4;
	private Snake f5;
	private Floor f6;
	private Floor f7;
	private Ladder f8;
	private Floor f9;
	private Floor f10;
	private KingCobra f11;
	private Floor f12;
	private Floor f13;
	

	Game(){
		Floor f0=new Floor(0);
		Floor f1=new Floor(1);
		Floor f2=new Floor(2);
		Elevator f3=new Elevator(3,10);
		Floor f4=new Floor(4);
		Snake f5=new Snake(5,1);
		Floor f6=new Floor(6);
		Floor f7=new Floor(7);
		Ladder f8=new Ladder(8,12);
		Floor f9=new Floor(9);
		Floor f10=new Floor(10);
		KingCobra f11=new KingCobra(11,3);
		Floor f12=new Floor(12);
		Floor f13=new Floor(13);	
	}

}

class Player{
	private int points=0;
	private int position=0;
	private String name;

	Player(String name){
		this.name=name;
	}

}

class Floor{
	private int position;
	private int points=1;
	Floor(int position){
		this.position=position.
	}

	public int givePoints(){
		return this.points;
	}

	public String getFloorType(){
		return "Empty floor";
	}

}

class Snake extends Floor{
	private int points= -2;
	private int nextPosition;

	Snake(int position,int nextPosition){
		super(position);
		this.nextPosition=nextPosition;
	}

	private String getFloorType(){
		return "Normal Snake Floor";
	}

}

class KingCobra extends Snake{
	private int points=-4;
	
	Snake(int position,int nextPosition){
		super(position,nextPosition);
		
	}

	public String getFloorType(){
		return "King Cobra floor";
	}

}



class Ladder extends FLoor{
	private int points= 2;
	private int nextPosition;

	Snake(int position,int nextPosition){
		super(position);
		this.nextPosition=nextPosition;
	}

	private String getFloorType(){
		return "Ladder Floor";
	}



}



class Elevator extends Ladder{
	private int points=4;
	
	Snake(int position,int nextPosition){
		super(position,nextPosition);
		
	}

	public String getFloorType(){
		return "Elevator floor";
	}

}




class Dice{
	private final int totalFaces=2;
	private int faceValue;

	public void roll(){
		Random rand= new Random();

		int k= 1+rand.nextInt(totalFaces);

		this.setFaceValue(k);
	}

	private void setFaceValue(int k){
		this.faceValue=k;
	}

	public int getFaceValue(){
		return faceValue;
	}


}