import java.util.*;
import java.io.*;

public class HopnWin {
	public static void main(String[] args) {
		Game play = new Game();
		play.startGame();
		
	}
}


class Game{

	Player P;
	ArrayList <Tile> Carpet;
	GenericCalculator<T> calculator;

	Game(){

		calculator= new GenericCalculator();
		P= new Player();
		addTile("Bat");
		addTile("Ball");
		addTile("Doll");
		addTile("Barbie");
		addTile("Scissior");
		addTile("Blade");
		addTile("Gun");
		addTile("Rocket");
		addTile("Bomb");
		addTile("Phone");
		addTile("Scissior");
		addTile("Blade");
		addTile("Gun");
		addTile("Rocket");
		addTile("Bomb");
		addTile("Bat");
		addTile("Ball");
		addTile("Doll");
		addTile("Phone");
		addTile("Scissior");


	}

	public void startGame(){
		Scanner sc= new Scanner(System.in);
		System.out.println("Hit Enter to initialize the game");
		String k=sc.nextLine();
		// DO exception handling for input

		startLoop();

	}

	public void startLoop(){
		String words[]={"first","second","third","fourth","fifth"};
		for(int i=0;i<5;i++){
			System.out.printf("Hit Enter for your %s loop\n",words[i]);

			int landValue=generateRandom();

			giveToy(landValue);
			
		}
	}

	public void giveToy(int value){
		if(value==21){
				System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
			}

		else{
			if(value%2==0){
				Toy mine =(Toy)Carpet.get(value).clone();
				P.addToy(mine);
				System.out.printf("You won a %s\n",mine.getName());
			}

			else{
				int k=askQuestion();
				if(askQuestion==1){
					Toy mine =(Toy)Carpet.get(value).clone();
					P.addToy(mine);
					System.out.printf("You won a %s\n",mine.getName());
				}

				else{
					System.out.println("You didn't win anything");

				}
			}


		}


	}

	public void addTile(String nameOfToy){
		Tile a = new Tile(nameOfToy,Carpet.length());
		Carpet.add(a);
	}

	public void addQuestion(){

	}


}

class Tile{
	Toy t;
	int position;

	Tile(String name, int position){
		t = new Toy(name);
		this.positon=position;

	}

	public void getCloneOfToy(){
		return t.clone();
	}
}

class GenericCalculator<T>{

}



class Player{
	ArrayList <Toy> Bucket;

	public void addToy(Toy t){
		Bucket.add(t);

	}

	public void showBucket(){
		for(int i=0; i<length(Bucket);i++){
			System.out.println(Bucket.get(i).getName());
		}

	}

}

class Toy implements Cloneable{
	String name;

	Toy(String name){
		this.name= name;
	}

	public String getName(){
		return name;
	}

	public Toy clone(){
		return new Toy(name);
	}

}