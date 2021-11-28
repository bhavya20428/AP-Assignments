import java.util.*;
import java.io.*;

public class main{
	public static void main(String[] args) {
		
	}
}


class Game{

	ArrayList <Tile> Carpet;

	Game(){

	}

	public void addTile(String nameOfToy){

		Toy t= new Toy(nameOfToy);

		


	}


}

class tile{
	Toy t;

	tile(Toy t){
		this.t=t;
	}

	public void getCloneOfToy(){
		return t.clone();
	}
}

class calculator<T>{

}



class player{
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