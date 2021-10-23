import java.util.*;
public class SnakesAndLadders{
	public static void main(String[] args) {
		Game play= new Game();
		play.startGame();

		
	}

}


class Game{
	private Floor f0;
	private Floor f1;
	private Elevator f2;
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
	private Dice d;

	private Player p;
	

	Game(){
		f0=new Floor(0,1);
		f1=new Floor(1,1);
		f2=new Elevator(3,10,4);
		f3=new Floor(2,1);		
		f4=new Floor(4,1);
		f5=new Snake(5,1,-2);
		f6=new Floor(6,1);
		f7=new Floor(7,1);
		f8=new Ladder(8,12,2);
		f9=new Floor(9,1);
		f10=new Floor(10,1);
		f11=new KingCobra(11,3,-4);
		f12=new Floor(12,1);
		f13=new Floor(13,1);

		


		d= new Dice();
	}

	public void startGame(){
		this.addPlayer();
		System.out.println("The Game Setup is ready");
		rollFirstTime();		
		rollmore();
	}

	public void rollFirstTime(){
		int k=0;
		while(k!=1){
			k=rollDice();
			if(k==2){
				System.out.println("Game cannot start until you get 1");
			}

		}
		movePlayer(0);


	}

	public void rollmore(){
		int value=0;

		while(p.currentPositon()!=13){
			value=rollDice();
			if(p.currentPositon()==12 && value==2){
				System.out.println("Player cannot move");
				continue;
			}

			else{
				movePlayer(value+p.currentPositon());
			}
		}

		System.out.println("Game Over");
		System.out.println(p.getName()+" accumulated "+p.getTotalPoints()+" points");
	}

	public void movePlayer(int v){
		p.movePosition(v);
		Floor f;

		switch(v){
			case 0:
				p.addPoints(f1.givePoints());
				p.printInfo(f1);
				break;

			case 1:
				p.addPoints(f1.givePoints());
				p.printInfo(f1);
				break;			
			case 2:
				p.addPoints(f2.givePoints());
				f =f2;
				p.printInfo(f);
				movePlayer(f2.getNextPosition());
				break;
			case 3:
				p.addPoints(f3.givePoints());
				p.printInfo(f3);
				break;
			case 4:
				p.addPoints(f4.givePoints());
				p.printInfo(f4);
				break;
			case 5:
				p.addPoints(f5.givePoints());
				f =f5;
				p.printInfo(f);
				movePlayer(f5.getNextPosition());
				break;
			case 6:
				p.addPoints(f6.givePoints());
				p.printInfo(f6);
				break;
			case 7:
				p.addPoints(f7.givePoints());
				p.printInfo(f7);
				break;
			case 8:
				p.addPoints(f8.givePoints());
				f =f8;
				p.printInfo(f);
				movePlayer(f8.getNextPosition());
				break;
			case 9:
				p.addPoints(f9.givePoints());
				p.printInfo(f9);
				break;
			case 10:
				p.addPoints(f10.givePoints());
				p.printInfo(f10);
				break;
			case 11:
				p.addPoints(f11.givePoints());
				f =f11;
				p.printInfo(f);
				movePlayer(f11.getNextPosition());
				break;
			case 12:
				p.addPoints(f12.givePoints());
				p.printInfo(f12);
				break;
		}


	}

	public int rollDice(){
		Scanner sc= new Scanner(System.in);
		System.out.print("Hit Enter to Roll the Dice");
		sc.nextLine();

		d.roll();
		int k=d.getFaceValue();
		System.out.println("Dice gave "+k);
		return k;
	}

	public void addPlayer(){
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter Player Name and Hit Enter: ");
		String name=sc.nextLine();
		p = new Player(name);
	}

	
}

class Player{
	private int points=0;
	private int position=0;
	private String name;


	Player(String name){
		this.name=name;
	}
	public void movePosition(int k){
		this.position=k;
	}

	public int getTotalPoints(){
		return this.points;
	}

	public void addPoints(int p){
		this.points+=p;

	}

	public String getName(){
		return this.name;
	}

	public int currentPositon(){
		return this.position;
	}

	public void printInfo(Floor f){
		System.out.println("Player Position: "+this.currentPositon());
		System.out.println(this.getName()+" has reached on "+f.getFloorType());
		System.out.println("Total points: "+this.getTotalPoints());
	}

}

class Floor{
	protected int position;
	private int points;
	private String type="Empty Floor";
	Floor(int position,int points){
		this.position=position;
		this.points=points;
	}

	public int givePoints(){
		return this.points;
	}

	public String getFloorType(){
		return this.type;
	}



}

class Snake extends Floor{
	
	protected int nextPosition;
	private String type="Normal Snake Floor";

	Snake(int position,int nextPosition,int points){
		super(position,points);
		this.nextPosition=nextPosition;
	}

	@Override
	public String getFloorType(){
		return this.type;
	}

	public int getNextPosition(){
		return this.nextPosition;
	}


}

class KingCobra extends Snake{
	
	private String type="King Cobra floor";
	
	KingCobra(int position,int nextPosition,int points){
		super(position,nextPosition,points);
		
	}

	@Override
	public String getFloorType(){
		return this.type;
	}

	@Override
	public int getNextPosition(){
		return this.nextPosition;
	}

	

}



class Ladder extends Floor{
	
	protected int nextPosition;
	private String type="Ladder Floor";

	Ladder(int position,int nextPosition,int points){
		super(position,points);
		this.nextPosition=nextPosition;
	}

	@Override
	public String getFloorType(){
		return this.type;
	}


	public int getNextPosition(){
		return this.nextPosition;
	}



}



class Elevator extends Ladder{
	
	private String type="Elevator Floor";
	
	Elevator(int position,int nextPosition,int points){
		super(position,nextPosition,points);
		
	}

	@Override
	public String getFloorType(){
		return this.type;
	}


	@Override
	public int getNextPosition(){
		return this.nextPosition;
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