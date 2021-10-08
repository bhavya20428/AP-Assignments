import java.util.*;

public class COVIN{
	public static void main(String[] args) {

		Portal CoWin = new Portal();	

		while (true){
			CoWin.menu();
			CoWin.input();
		}


		
	}
}

class Portal{

	Portal(){
		System.out.println("CoWin Portal Initialized....");
	}

	public void menu(){
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

	public void input(){
		Scanner sc= new Scanner(System.in);

		int input= sc.nextInt();
		sc.nextLine();			

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

		}

		

	}

	public void AddVaccine(){
		Scanner sc= new Scanner(System.in);

		System.out.printf("Vaccine Name: ");
		String name=sc.nextLine();
		System.out.printf("Number of Doses: ");
		long doses=sc.nextLong();
		sc.nextLine();

		long gap=0;
		if(doses>1){
			System.out.printf("Gap between Doses: ");
			gap=sc.nextLong();		

		}

		Vaccine v1= new Vaccine(name,doses,gap);
		System.out.printf("Vaccine Name: %s, Number of Doses: %d, Gap Between Doses: %d\n",v1.getname(),v1.getdoses(),v1.getgap());
			

		return;
	}

	public void RegisterHospital(){
		Scanner sc= new Scanner(System.in);
		System.out.printf("Hospital Name: ");
		String name=sc.nextLine();
		System.out.printf("Pincode: ");
		long pincode=sc.nextLong();
		sc.nextLine();
		

		if(pincode>99999 && pincode<1000000){

			Hospital H1= new Hospital(name,pincode);
			System.out.printf("Hospital Name: %s, PinCode: %d, Unique ID: %d\n",H1.get_name(),H1.get_pincode(),H1.get_uniquecode());
		}

		else{
			System.out.println("Invalid Pincode. Hospital Not registered.");
		}
		return;

	}

	public void RegisterCitizen(){
		Scanner sc= new Scanner(System.in);
		System.out.printf("Name: ");
		String name=sc.nextLine();
		System.out.printf("Age: ");
		long age=sc.nextLong();
		sc.nextLine();
		System.out.printf("Unique Id: ");
		String unique_id=sc.nextLine();
		if(age<18){
			System.out.println("Only above 18 are allowed");
			return;
		}
		else if(unique_id.length()!=12){
			System.out.println("Unique Id is invalid. It should be a 12 digit code.");
			return;
		}
		else {
			int i=0;
			while(i<12){
				if(unique_id.charAt(i)>=48 && unique_id.charAt(i)<=57){
					i++;
				}
				else{
					System.out.println("Unique Id is invalid. It should be 12 digit code.");
					return;
				}
			}

		}
		Citizen C1= new Citizen(name,age,unique_id);

		System.out.printf("Citizen Name: %s, Age: %d, Unique Id: %s\n",C1.get_name(),C1.get_age(),C1.get_unique_id());

		return;


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
	private long age;
	private String unique_id;
	private String status;

	Citizen(String name,long age,String unique_id){
		this.name=name;
		this.age=age;
		this.unique_id=unique_id;
		this.status="REGISTERED";
	}

	public String get_name(){
		return this.name;
	}

	public String get_unique_id(){
		return this.unique_id;
	}

	public long get_age(){
		return this.age;
	}





}

class Vaccine{
	private String name;
	private long doses;
	private long gap;

	Vaccine(String name, long doses, long gap){
		this.name=name;
		this.doses=doses;
		this.gap=gap;
		
	}

	public long getgap(){
		return this.gap;
	}
	public long getdoses(){
		return this.doses;
	}

	public String getname(){
		return this.name;
	}




}

class Hospital{
	private static long code=100000;
	private String name;
	private long pincode;
	private long uniquecode;

	Hospital(String name,long pincode){
		this.name=name;
		this.pincode=pincode;
		this.uniquecode=1+code;
		code++;
	}

	public String get_name(){
		return this.name;
	}

	public long get_pincode(){
		return this.pincode;
	}

	public long get_uniquecode(){
		return this.uniquecode;
	}


}

class Slot{

}

