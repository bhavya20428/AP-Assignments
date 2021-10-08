import java.util.*;

public class COVIN{
	public static void main(String[] args) {

		Portal CoWin = new Portal();	


	

		


		
	}
}

class Portal{

	//Used HashMap for hospitals,citizens,slots because they have O(1) for search,insertion and slots operation.
	//hospitals--> key=hospital id
	//citizens--> key= unique_id
	//vaccines--> key=vaccine name

	private ArrayList<String> vaccineName;
	private HashMap<String,Vaccine> vaccines;
	private HashMap<Long,Hospital> hospitals; 
	private HashMap<String,Citizen> citizens;
	// private HashMap<Long,Slot> slots;

	Portal(){
		System.out.println("CoWin Portal Initialized....");
		vaccineName=new ArrayList();
		vaccines= new HashMap<>();
		hospitals=new HashMap<>();
		citizens=new HashMap<>();
		// slots=new HashMap<>();

		while (true){
			this.menu();
			this.input();
		}
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
		vaccines.put(name,v1);
		vaccineName.add(name);
		System.out.printf("Vaccine Name: %s, Number of Doses: %d, Gap Between Doses: %d\n",v1.get_name(),v1.get_doses(),v1.get_gap());
			

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
			System.out.printf("Hospital Name: %s, PinCode: %d, Unique ID: %d\n",H1.get_name(),H1.get_pincode(),H1.get_hospital_id());
			hospitals.put(H1.get_hospital_id(),H1);

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
		citizens.put(unique_id,C1);

		System.out.printf("Citizen Name: %s, Age: %d, Unique Id: %s\n",C1.get_name(),C1.get_age(),C1.get_unique_id());

		return;


	}

	public void AddSlot(){
		Scanner sc= new Scanner(System.in);
		System.out.printf("Enter Hospital ID: ");
		long hospital_id =sc.nextLong();
		sc.nextLine();

		if(hospital_id<100000 || hospital_id>=1000000){
			System.out.printf("Invalid ID. Hospital ID is a 6 digit number.");
			return;
		}

		else if(hospitals.containsKey(hospital_id)==false){
			System.out.println("Hospital not registered.");
			return;
		}		

		System.out.printf("Enter number of slots to be added: ");
		long slots=sc.nextLong();
		for(long i=0;i<slots;i++){
			System.out.printf("Enter Day Number: ");
			long day=sc.nextLong();
			sc.nextLine();
			System.out.printf("Enter Quantity: ");
			long quantity=sc.nextLong();
			sc.nextLine();
			String vaccine;			

			while(true){
				System.out.println("Select Vaccine");

				for(int y=0;y<vaccineName.size();y++){
					System.out.printf("%d. %s\n",y,vaccineName.get(y));
				}

				int input= sc.nextInt();
				if(input<vaccineName.size()){
					vaccine=vaccineName.get(input);
					break;
				}
				System.out.println("Incorrect Vaccine number");				
			}

			

			Hospital h=hospitals.get(hospital_id);
			Vaccine v= vaccines.get(vaccine);

			Slot S= new Slot(h,day,quantity,v);
			ArrayList <Slot> arr_slots;

					
			arr_slots= h.get_slots();
			arr_slots.add(S);

			
			arr_slots=v.get_slots();
			arr_slots.add(S);
			

			System.out.printf("Slot Added for Day:%d, Availiable Quantity: %d of Vaccine %s\n",S.get_day(),S.get_quantity(),vaccine);
		}


	}

	public void BookSlot(){
		Scanner sc= new Scanner(System.in);

		System.out.printf("Enter patient Unique Id: ");
		String id= sc.nextLine();

		if(citizens.containsKey(id)==false){
			System.out.println("You are not registered");
			return;
		}
		
		while(true){
			System.out.println("1.Search by area");
			System.out.println("2.Search by Vaccine");
			System.out.println("3.Exit");

			System.out.printf("Enter Option: ");
			int input=sc.nextInt();
			sc.nextLine();

			switch(input){
				case 1:


				case 2:
					System.out.print("Enter Vaccine Name: ");
					String vaccine=sc.nextLine();

					Vaccine V=vaccines.get(vaccine);
					ArrayList<Slot> slots=V.get_slots();
					int size=slots.size();

					if(size==0){
						System.out.println("No slots");
						return;
					}
					for(Slot i: slots){
						Hospital h=i.get_hospital();
						System.out.println(h.get_hospital_id()+" "+h.get_name());
					}

					System.out.print("Enter Hospital ID: ");
					long h_id= sc.nextLong();
					if(hospitals.containsKey(h_id)==false){
						System.out.println("Hospital not registered");
						return;
					}
					Hospital h= hospitals.get(h_id);
					for(Slot i: slots){
						Vaccine v=i.get_vaccine();
						String name= v.get_name();

						if(name==vaccine){
							if(i.get_total_registered()==i.get_quantity()){
								System.out.println("No Vaccine Dose left");
								return;
							}

							i.increase_total_registered();
							Citizen c=citizens.get(id);

							c.addSlot(i);
							return;

						}
					}

				case 3:
					return;

				default:
					System.out.println("Enter Correct Value between 1 and 3");
			}
		}
	}

	public void ListSlot(){
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter Hospital Id: ");
		long id= sc.nextLong();
		if(hospitals.containsKey(id)==false){
			System.out.println("Hospital not registered");
			return;
		}

		Hospital H=hospitals.get(id);
		ArrayList<Slot> slots=H.get_slots();
		for(int i=0;i<slots.size();i++){
			Slot S= slots.get(i);
			System.out.printf("Day: %d, Availiable Quantity: %d of Vaccine %s\n",S.get_day(),S.get_quantity(),S.get_vaccine());

		}


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
	private Slot slot;


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

	public void addSlot(Slot s){
		this.slot=s;
	}





}

class Vaccine{
	private String name;
	private long doses;
	private long gap;
	private ArrayList<Slot> slots;

	Vaccine(String name, long doses, long gap){
		this.name=name;
		this.doses=doses;
		this.gap=gap;
		this.slots=new ArrayList();
		
	}

	public long get_gap(){
		return this.gap;
	}
	public long get_doses(){
		return this.doses;
	}

	public String get_name(){
		return this.name;
	}

	public ArrayList get_slots(){
		return this.slots;
	}




}

class Hospital{
	private static long code=100000;
	private String name;
	private long pincode;
	private long hospital_id;
	private ArrayList<Slot> slots;

	Hospital(String name,long pincode){
		this.name=name;
		this.pincode=pincode;
		this.hospital_id=1+code;
		this.slots= new ArrayList();
		code++;
	}

	public String get_name(){
		return this.name;
	}

	public long get_pincode(){
		return this.pincode;
	}

	public long get_hospital_id(){
		return this.hospital_id;
	}

	public ArrayList get_slots(){
		return this.slots;
	}


}

class Slot{
	private long day;
	private Hospital hospital;
	private long quantity;
	private Vaccine vaccine;
	private long total_registered=0;

	Slot(Hospital hospital,long day,long quantity,Vaccine vaccine){
		this.hospital=hospital;
		this.quantity=quantity;
		this.vaccine=vaccine;
		this.day=day;
	}

	public long get_day(){
		return day;
	}

	public Hospital get_hospital(){
		return hospital;
	}

	public long get_quantity(){
		return quantity;
	}

	public Vaccine get_vaccine(){
		return vaccine;

	}

	public long get_total_registered(){
		return total_registered;
	}

	public void increase_total_registered(){
		this.total_registered++;

	}




}

