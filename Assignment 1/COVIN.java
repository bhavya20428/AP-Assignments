import java.util.*;

public class COVIN{
	public static void main(String[] args) {
		System.out.println("CoWin Portal Initialized....");

		MenuCard Menu = new MenuCard();

		while(true){
			Menu.MainMenu();
			Menu.check();

		}

		


		
	}
}

class MenuCard{
	public void MainMenu(){
		System.out.println("---------------------------------");
		System.out.println("1. Add Vaccine");
		System.out.println("2. Register Hospital");
		System.out.println("3. Register Citizen");
		System.out.println("4. Add Slot for Vaccination");
		System.out.println("5. Book Slot for Vaccination");
		System.out.println("6. List all slots for a hospital");
		System.out.println("7. Check Vaccination Status");
		System.out.println("8. Exit");
		System.out.println("---------------------------------");
	}

	public void check(){
		Scanner sc= new Scanner(System.in);
		int input= sc.nextInt();
		sc.nextLine();
		sc.close();

		switch(input){
			case 1:
				AddVaccine();
				break;
			case 2:
				RegisterHospital();
				break;
			case 3:
				RegisterCitizen();
				break;
			case 4:
				AddSlot();
				break;
			case 5:
				BookSlot();
				break;
			case 6:
				ListSlot();
				break;
			case 7:
				CheckStatus();
				break;
			case 8:
				Exit();
				break;
			default:
				System.out.println("Please Enter Correct Value between 1 and 8");
				check();

		}

	}

	public void AddVaccine(){
		Scanner sc= new Scanner(System.in);
		System.out.println("Vaccine Name: ");
		String name=sc.next();
		sc.nextLine();
		System.out.println("Number of Doses: ");
		int doses=sc.nextInt();
		sc.nextLine();
		System.out.println("Gap between Doses: ");
		int gap=sc.nextInt();
		sc.close();

		Vaccine v1= new Vaccine(name,doses,gap);

		System.out.printf("Vaccine Name: %s, Number of Doses: %d, Gap Between Doses: %d\n",v1.getname(),v1.getdoses(),v1.getgap());

		check();

		return;



	}

	public void RegisterHospital(){

	}

	public void RegisterCitizen(){

	}

	public void AddSlot(){

	}

	public void BookSlot(){

	}

	public void ListSlot(){

	}

	public void CheckStatus(){

	}

	public void Exit(){
		System.out.println("Thank you!");
		System.exit(0);

	}	
}


class Citizen{
	private String name;
	private int age;
	private int Adhar_id;
	private String status;



}

class Vaccine{
	private String name;
	private int doses;
	private int gap;

	Vaccine(String name, int doses, int gap){
		this.name=name;
		this.doses=doses;
		this.gap=gap;
		// Voice informed
	}

	public int getgap(){
		return this.gap;
	}
	public int getdoses(){
		return this.doses;
	}

	public String getname(){
		return this.name;
	}




}

class Hospital{
	private String name;
	private int pincode;
	private int uniqueCode;


}

class Slot{

}

