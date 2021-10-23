import java.util.*;
public class SnakesAndLadders{
	public static void main(String[] args) {
		
		
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