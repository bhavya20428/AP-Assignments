import java.util.*;

public class COVIN{
	public static void main(String[] args) {

		Portal CoWin = new Portal();	
		
	}
}

class Portal{

	//Used HashMap for hospitals,citizens,slots because they have O(1) for search,insertion and slots operation.
	//hospitalMap--> key=hospital id
	//citizenMap--> key= unique_id
	//vaccineMap--> key=vaccine name

	private ArrayList<String> vaccineName;
	private HashMap<String,Vaccine> vaccineMap;
	private HashMap<Long,Hospital> hospitalMap; 
	private HashMap<String,Citizen> citizenMap;


	Portal(){
		System.out.println("CoWin Portal Initialized....");
		vaccineName=new ArrayList();
		vaccineMap= new HashMap<>();
		hospitalMap=new HashMap<>();
		citizenMap=new HashMap<>();
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
		vaccineMap.put(name,v1);
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
			hospitalMap.put(H1.get_hospital_id(),H1);

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
		citizenMap.put(unique_id,C1);

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

		else if(hospitalMap.containsKey(hospital_id)==false){
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

			System.out.println("Select Vaccine");

			for(int y=0;y<vaccineName.size();y++){
				System.out.printf("%d. %s\n",y,vaccineName.get(y));
			}

			int input= sc.nextInt();
			if(input<vaccineName.size()){
				vaccine=vaccineName.get(input);
				
			}
			else{
				System.out.println("Incorrect Vaccine number");	
				return;
			}
						
			

			

			Hospital h=hospitalMap.get(hospital_id);
			Vaccine v= vaccineMap.get(vaccine);

			Slot S= new Slot(h,day,quantity,v);
			

			h.addSlot(S);

			v.addHospital(h);		

			System.out.printf("Slot Added by Hospital %d for Day:%d, Availiable Quantity: %d of Vaccine %s\n",hospital_id,S.get_day(),S.get_quantity(),vaccine);
		}


	}

	public void BookSlot(){
		Scanner sc= new Scanner(System.in);

		System.out.printf("Enter Citizen Unique Id: ");
		String id= sc.nextLine();

		if(citizenMap.containsKey(id)==false){
			System.out.println("You are not registered");
			return;
		}

		Citizen C = citizenMap.get(id);

		String status=C.get_status();
		if(status.equals("VACCINATED")){
			System.out.println("You are fully Vaccinated");
			return;
		}		
		while(true){

			System.out.println("1.Search by area");
			System.out.println("2.Search by Vaccine");
			System.out.println("3.Exit");
			System.out.printf("Enter Option: ");
			int input=sc.nextInt();
			sc.nextLine();

			if(input==3){
				return;
			}

			else if(input!=1 && input!=2){
				System.out.println("Enter Correct Value between 1 and 3");
				continue;
			}

			Vaccine V;
			String vaccine="";
			

			if(input==1){
				System.out.print("Enter PinCode: ");
				long pincode=sc.nextLong();
				sc.nextLine();
				int check1=0;

				for(Map.Entry ele: hospitalMap.entrySet()){
					long ho_id=(long)ele.getKey();
					Hospital h =hospitalMap.get(ho_id);
					if(h.get_pincode()==pincode){
						System.out.printf("%s %s\n",ho_id,h.get_name());
						check1=1;
					}
				}
				if(check1==0){
					System.out.println("No slots");
					return;
				}


			}

			else if(input==2){
				System.out.print("Enter Vaccine Name: ");
				vaccine=sc.nextLine();

				V=vaccineMap.get(vaccine);
				HashSet<Hospital> hospitals=V.get_hospitalMap();
				
				int size=hospitals.size();

				if(size==0){
					System.out.println("No Slots");
					return;
				}


				for(Hospital i: hospitals){
					System.out.println(i.get_hospital_id()+" "+i.get_name());
				}			


			}

			System.out.print("Enter Hospital ID: ");
			long h_id= sc.nextLong();
			if(hospitalMap.containsKey(h_id)==false){
				System.out.println("Hospital not registered");
				return;
			}

			long gap=0;

			if(C.get_vaccine()!=null){
				Vaccine taken= C.get_vaccine();
				gap=taken.get_gap();
			}
			
			Hospital h= hospitalMap.get(h_id);
			ArrayList<Slot> slots=h.get_slots();

			int check=0;

			for(int j=0;j<slots.size();j++){
				Slot i=slots.get(j);
				Vaccine v=i.get_vaccine();
				String name= v.get_name();
				if(input==2){
					if(name.equals(vaccine) && i.get_day()>gap && i.get_quantity()!=0){						
						check=1;	
						System.out.printf("%d->Day: %d Availiable Qty:%d Vaccine:%s\n",j,i.get_day(),i.get_quantity(),name);										
					}

				}

				else{
					if( i.get_day()>gap && i.get_quantity()!=0){						
						check=1;	
						System.out.printf("%d->Day: %d Availiable Qty:%d Vaccine:%s\n",j,i.get_day(),i.get_quantity(),name);										
					}

				}

				
			}

			if(check==0){
				System.out.println("No Slots Availiable");
				return;
			}

			else{
				System.out.print("Choose Slot: ");
				int index=sc.nextInt();
				
				if(index<0 || index>=slots.size() ){
					System.out.println("Wrong Slot");
					return;
				}
				Slot chosen =slots.get(index);
				V=chosen.get_vaccine();
				C.addVaccine(V);				
				C.addSlot(chosen);
				chosen.decrease_quantity();

				if(C.get_doses_given()==V.get_doses()){
					C.changeStatus("VACCINATED");										
				}	

				else if(C.get_status().equals("REGISTERED")){
					C.changeStatus("PARTIALLY VACCINATED");
				}
			
				

				System.out.printf("%s vaccinated with %s\n",C.get_name(),C.get_vaccinename());
				
				return;



			}
		}
	}

	public void ListSlot(){
		Scanner sc= new Scanner(System.in);
		System.out.printf("Enter Hospital Id: ");
		long id= sc.nextLong();
		sc.nextLine();
		if(hospitalMap.containsKey(id)==false){
			System.out.println("Hospital not registered");
			return;
		}

		Hospital H=hospitalMap.get(id);
		ArrayList<Slot> slots=H.get_slots();
		for(int i=0;i<slots.size();i++){
			Slot S= slots.get(i);
			System.out.printf("Day: %d, Availiable Quantity: %d of Vaccine %s\n",S.get_day(),S.get_quantity(),S.get_vaccinename());

		}


	}

	public void CheckStatus(){
		Scanner sc= new Scanner(System.in);
		System.out.print("Enter Patient ID: ");
		String id=sc.nextLine();
		if(citizenMap.containsKey(id)==false){
			System.out.println("Citizen not registered");
			return;
		}

		Citizen C=citizenMap.get(id);
		System.out.println(C.get_status());
		String status=C.get_status();
		if(status.equals("REGISTERED")==false){
			System.out.println("Vaccine given:"+C.get_vaccinename());
			System.out.println("No of Doses given:"+C.get_doses_given());
			if(status.equals("VACCINATED")==false){
				System.out.println("Due Date: "+C.get_duedate());				

			}
			

		}
		
		return;

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
	private Vaccine vaccine;
	private long doses_given=0;
	private long duedate=0;


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
		this.increase_doses_given();

		Vaccine v=s.get_vaccine();
		if(v.get_doses()==this.doses_given){
			this.duedate=-1;
		}
		else{
			this.duedate=v.get_gap()+s.get_day();
		}

		

	}

	public String get_status(){
		return this.status;
	}

	public void changeStatus(String status){
		this.status=status;
	}

	public Vaccine get_vaccine(){
		return this.vaccine;
	}

	public long get_doses_given(){
		return this.doses_given;
	}

	public void increase_doses_given(){
		this.doses_given++;
	}

	public long get_duedate(){
		return this.duedate;

	}

	public String get_vaccinename(){
		return this.vaccine.get_name();
	}

	public void addVaccine(Vaccine v){
		this.vaccine=v;
	}





}

class Vaccine{
	private String name;
	private long doses;
	private long gap;
	private HashSet<Hospital> hospitalMap;

	Vaccine(String name, long doses, long gap){
		this.name=name;
		this.doses=doses;
		this.gap=gap;
		this.hospitalMap=new HashSet<Hospital>();
		
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

	public HashSet get_hospitalMap(){
		return this.hospitalMap;
	}

	public void addHospital(Hospital H){

		this.hospitalMap.add(H);
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

	public void addSlot(Slot S){
		this.slots.add(S);
	}


}

class Slot{
	private long day;
	private Hospital hospital;
	private long quantity;  //Availiable Quantity
	private Vaccine vaccine;
	

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

	public String get_vaccinename(){
		return this.vaccine.get_name();
	}

	public void decrease_quantity(){
		this.quantity--;
	}

}

