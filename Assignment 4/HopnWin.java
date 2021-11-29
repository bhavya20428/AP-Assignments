import java.util.*;
import java.io.*;

public class HopnWin {
	public static void main(String[] args) {
		Game play = new Game();		
		play.startGame();
	}
}


class Game{

	private Player P;
	private ArrayList <Tile> Carpet = new ArrayList();
	private GenericCalculator<Integer> int_calculator;
	private GenericCalculator<String> string_calculator;


	Game(){

		int_calculator= new GenericCalculator();
		string_calculator= new GenericCalculator();

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
		addTile("Transformer");
		addTile("Shaktiman");
		addTile("Joker");
		addTile("Frisbie");
		addTile("Badminton");
		addTile("Skates");
		addTile("Basketball");
		addTile("Football");
		addTile("Teddy");
		addTile("Remote Car");


	}

	public void startGame(){

		
		Scanner sc= new Scanner(System.in);
		System.out.print("Hit Enter to initialize the game ");

		while (true){
			try{
				System.out.printf("Hit Enter for your %s loop ",words[i]);
				String k=sc.nextLine();

				if(k.equals("")==false){
					throw new WrongInputException("Please type Enter only!");	
				}

				else{
					System.out.println("Game is Ready");
					break;
				}				

			}

		

			catch( WrongInputException e){
					System.out.println(e.getMessage());
			}

		}		

		startLoop();

		System.out.println("Game Over");

		P.showBucket();
			


	}

	public void startLoop(){
		Scanner sc = new Scanner(System.in);
		String words[]={"first","second","third","fourth","fifth"};
		
		for(int i=0;i<5;i++){

			while (true){
				try{

					System.out.printf("Hit Enter for your %s loop ",words[i]);
					String k=sc.nextLine();

					if(k.equals("")==false){
						throw new WrongInputException("Please type Enter only!");	
					}

					else{
						break;
					}				

				}

			

				catch( WrongInputException e){
					System.out.println(e.getMessage());
				}

			}
			
				
			int landValue=((int)(Math.random() * 21)) ;
			giveToy(landValue);		
			
		}
	}

	public void giveToy(int value){

		try{	
			
			Tile t=Carpet.get(value);
			value++;
			System.out.printf("You landed on tile %d\n",value);

			if(value%2==0){
				Toy mine=t.getCloneOfToy();
				P.addToy(mine);
				System.out.printf("You won a %s\n",mine.getName());
			}

			else{
				int k=askQuestion();
				if(k==1){

					Toy mine=t.getCloneOfToy();
					P.addToy(mine);
					System.out.printf("You won a %s\n",mine.getName());
				}

				else{
					System.out.println("Incorrect Answer");
					System.out.println("You didn't win anything");
				}				

			}		


		}

		catch(IndexOutOfBoundsException e){
			System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
		}


	}

	public void addTile(String nameOfToy){
		Tile a = new Tile(nameOfToy,Carpet.size()+1);
		Carpet.add(a);
	}



	public int askQuestion(){

			Scanner sc= new Scanner(System.in);
			String answer;
			

			while(true){

				try{
					System.out.println("Question answer round. Integer or String?");
				    answer=sc.nextLine();

				    if(!answer.toLowerCase().equals("integer") && !answer.toLowerCase().equals("string") ){
				    	throw new WrongInputException("Please Enter either Integer or String");
				    }

				    else{
				    	break;
				    }

				}

				catch( WrongInputException e){
					System.out.println(e.getMessage());
				}	

			}
			
			

			if(answer.toLowerCase().equals("integer")){
				Random r = new Random();
				Integer i1 = r.nextInt(10000);
				Integer i2 = r.nextInt(10000);

				return int_calculator.calculate(i1,i2);
			}

			else if(answer.toLowerCase().equals("string")){

				String s1= generateString();
				String s2= generateString();

				return string_calculator.calculate(s1,s2);

			}

			else{
				return 0;
			}	

	}



	public String generateString(){
		String characters="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String togive= "";
		for(int i=0;i<4;i++){
			int pos=(int)(Math.random()* (characters.length()+1)); 
			togive=togive+characters.charAt(pos);
		}
		
		return togive;

	}


}

class Tile{
	private Toy t;
	private int position;

	Tile(String name, int position){
		t = new Toy(name);
		this.position=position;

	}

	public Toy getCloneOfToy(){
		return t.clone();
	}
}

class GenericCalculator<T>{
	

	public int calculate(T option1, T option2){
		
		Scanner sc = new Scanner(System.in);

		if(option1 instanceof String ){
			System.out.printf("Calculate the concatenation of strings %s and %s\n",option1,option2);
			String answer= sc.nextLine();

			if(answer.equals((String)option1+(String)option2)){
				return 1;
			}
			else{
				return 0;
			}
		}

		else if(option1 instanceof Integer){
			System.out.printf("Calculate the result of  %d divided by %d (Integer Division)\n",option1,option2);
			Integer answer= sc.nextInt();
			sc.nextLine();

			if(answer==((int)option1/(int)option2)){
				return 1;
			}
			else{
				return 0;
			}
			
		}

		return 0;

	}

	

}



class Player{
	private ArrayList <Toy> Bucket=  new ArrayList();

	public void addToy(Toy t){
		Bucket.add(t);

	}

	public void showBucket(){
		System.out.println("Soft toys won by you are: ");
		for(int i=0; i<Bucket.size();i++){
			System.out.printf("%s, ",Bucket.get(i).getName());
		}

	}

}

class Toy implements Cloneable{
	private String name;

	Toy(String name){
		this.name= name;
	}

	public String getName(){
		return name;
	}

	@Override
	public Toy clone(){
		return new Toy(name);
	}

}

class WrongInputException extends Exception{
	public WrongInputException(String message){
		super(message);
	}
}



