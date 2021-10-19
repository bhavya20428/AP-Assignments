import java.util.*;

public class BACKPACK{
	Scanner sc= new Scanner(System.in);

}

class Course{

	private ArrayList<Assessment> assessments;
	private ArrayList<ClassMaterial> classmaterial;
	private ArrayList<Instructor> instructors;
	private ArrayList<Student> students;
	private ArrayList<Comment> comments;

	public ArrayList<Assessment> getAssessments(){
		return this.assessments;
	}
	public ArrayList<ClassMaterial> getClassmaterial(){
		return this.classmaterial;
	}
	public ArrayList<Instructor> getInstructors(){
		return this.instructors;
	}
	public ArrayList<Student> getStudents(){
		return this.students;
	}
	public ArrayList<Comment> getComments(){
		return this.comments;
	}

	Course(){
		assessments=new ArrayList<>();
		classmaterial= new ArrayList<>();
		instructors= new ArrayList<>();
		students= new ArrayList<>();

		Student s1= new Student(this);
		this.addStudent(s1);
		Student s2=new Student(this);
		this.addStudent(s2);
		Student s3=new Student(this);
		this.addStudent(s3);

		Instructor i1= new Instructor(this);
		this.addInstructor(i1);
		Instructor i2= new Instructor(this);
		this.addInstructor(i2);
	


	}

	public void addStudent(Student s){
		students.add(s);
	}

	public void addInstructor(Instructor i){
		instructors.add(i);
	}

	public void menu(){
		System.out.println("Welcome to Backpack");
		System.out.println("1. Enter as Instructor");
		System.out.println("2. Enter as Student");
		System.out.println("3. Exit");

	}

	public void input(){
		Scanner sc= new Scanner(System.in);
		int input= sc.nextInt();
		sc.nextLine();

		switch(input){
			case 1:
				Instructor i=chooseInstructor();
				i.start();
				break;
			case 2:
				Student s=chooseStudent();
				s.start();
				break;
			case 3:
				System.exit(0);
			default:
				System.out.println("Please Enter Correct Input");
		}
	}

	public void chooseInstructor(){
		for(int i=0;i<instructors.size();i++){
			System.out.println(i+"-"+instructors[i].getId());
		}
		Scanner sc= new Scanner(System.in);
		int k=sc.nextInt();

		return instructors[k];
	}


	public void chooseStudent(){
		for(int i=0;i<students.size();i++){
			System.out.println(i+"-"+students[i].getId());
		}
		Scanner sc= new Scanner(System.in);
		int k=sc.nextInt();

		return students[k];
	}





}

class comment{
	private Date upload;
	private String text;
	private String id;

	comment(){
		upload=java.util.Calendar.getInstance().getTime(); 

	}

	public void print(){
		System.out.println(c+"-"+id);
		System.out.println(upload);
	}

	public void add(String c,String id){
		text=c;
		this.id=id;
		return;
	}	

}

interface common{

	public void viewMaterial();
	public void viewAssessment();
	public void viewComments();
	public void addComments();
	public void menu();
	
}

class Instructor implements common{

	private static long baseid=0;

	private Course course;
	private String id;
	private ArrayList<Assessment> assessments;
	private ArrayList<ClassMaterial> classmaterial;
	private ArrayList<Student> students;
	private ArrayList<Comment> comments;


	Instructor(Course C){
		course=C;
		assessments=C.getAssessments();
		classmaterial=C.getClassmaterial();
		students=C.getStudents();
		comments=C.getComments();
		id="I"+String.valueof(baseid);
		baseid++;

	}

	public void start(){
		while(true){
			this.menu();
			int k=this.choose();
			if(k==-1){
				break;
			}
		}
	}

	public String getId(){
	 	return id;
	}

	public void menu(){
		
		
		System.out.println("1. Add class material");
		System.out.println("2. Add assessments");
		System.out.println("3. View lecture materials");
		System.out.println("4. View assessments");
		System.out.println("5. Grade assessments");
		System.out.println("6. Close assessment");
		System.out.println("7. View comments");
		System.out.println("8. Add comments");
		System.out.println("9. Logout");

	}

	public int choose(){
		Scanner sc= new Scanner(System.in);
		int input=sc.nextInt();
		sc.nextLine();

		switch(input){
			case 1:
				addMaterial();
				break;
			case 2:
				addAssessments();
				break;
			case 3:
				viewMaterial();
				break;
			case 4:
				viewAssessment();
				break;
			case 5:
				gradeAssessment();
				break;
			case 6:
				closeAssessment();
				break;
			case 7:
				viewComments();
				break;
			case 8:
				addComments();
				break;
			case 9:
				return -1;
				break;
				
			default:
				System.out.println("Wrong Input");
		}

		return 0;
	}

	public void addMaterial(){
		Scanner sc= new Scanner(System.in);
		System.out.println("1. Add Lecture Slide");
		System.out.println("2. Add Lecture Video");
		int k=sc.nextInt();
		sc.nextLine();
		if(k==1){
			ClassMaterial a = new Slides();		
		}
		else if(k==2){
			ClassMaterial a = new Videos();
		}
		classmaterial.add(a);
		a.materialAdd();
	}

	public void addAssessments(){
		Scanner sc= new Scanner(System.in);
		System.out.println("1. Add Assignment");
		System.out.println("2. Add Quiz");
		int k=sc.nextInt();
		sc.nextLine();
		if(k==1){
			Assessment a = new Assignment();		
		}
		else{
			Assessment a = new Quiz();
		}
		assessments.add(a);
		a.assessmentAdd();
	}

	public void viewMaterial(){
		for(ClassMaterial i: classmaterial){
			i.materialView();
		}
	}

	public void viewAssessment(){
		for(int i=0;i<assessments.size();i++){
			System.out.print("ID : "+i);
			assessments[i].assessmentView();
		}
	}

	public void gradeAssessment(){
		System.out.println("List of Assignments");

		for(int i=0;i<assessments.size();i++){
			System.out.println("ID: "+i+assessments[i].viewAssessment);
		}

		System.out.print("Enter ID of assessment to view submissions: ");
		Scanner sc= new Scanner(System.in);
		int k=sc.nextInt();
		sc.nextLine();
		Assessment chosen=assessments[k];
		System.out.println("Choose ID from these ungraded submissions");
		for(int i=0;i<chosen.getSubmissions().size();i++){


		}
	}

	public void closeAssessment(){
		System.out.println("List of Open Assessments");
		for(int i=0;i<assessments.size();i++){
			if(assessments[i]=="OPEN"){
				System.out.println("ID: "+i+assessments[i].assessmentView());
			}

		}

		Scanner sc= new Scanner(System.in);
		System.out.print("Enter ID of assignment to close: ");
		int input=sc.nextInt();
		sc.nextLine();
		assessments[input].close();
	}

	public void viewComments(){
		for(Comment i: comments){
			i.print();
		}

	}

	public void addComments(){
		Scanner sc= new Scanner(System.in);
		Comment c = new Comment();
		text=sc.nextLine();

		c.add(text,this.id);
		comments.add(c);


	}

	



}

class Student implements common{
	private static long baseid=0;
	private Course course;
	private String id;

	private ArrayList<Assessment> assessments;
	private ArrayList<ClassMaterial> classmaterial;
	private ArrayList<Instructor> instructors;
	private ArrayList<Comment> comments;

	Student(Course C){
		course=C;
		assessments=C.getAssessments();
		classmaterial=C.getClassmaterial();
		instructors=C.getInstructors();
		comments=C.getComments();
		id="S"+String.valueof(baseid);
		baseid++;
	}


	public void start(){
		while(true){
			this.menu();
			int k=this.choose();
			if(k==-1){
				break;
			}
		}
	}

	public String getId(){
	 	return id;
	}


	public void menu(){
		 
		System.out.println(" 1. View lecture materials");
		System.out.println(" 2. View assessments");
		System.out.println(" 3. Submit assessment");
		System.out.println(" 4. View grades");
		System.out.println(" 5. View comments");
		System.out.println(" 6. Add comments");
		System.out.println(" 7. Logout");

	}

	public int choose(){
		Scanner sc= new Scanner(System.in);
		int input=sc.nextInt();
		sc.nextLine();

		switch(input){
			case 1: 
				viewMaterial();
				break;
			case 2:
				viewAssessment();
				break;
			case 3:
				submitAssessment();
				break;
			case 4:
				viewGrades();
				break;
			case 5:
				viewComments();
				break;
			case 6:
				addComments();
				break;
			case 7:
				return -1;
				break;
			default:
				System.out.println("Wrong input");
		}

		return 0;
	}

	public void viewMaterial(){
		for(ClassMaterial i: classmaterial){
			i.materialView();
		}

	}

	public void viewAssessment(){
		for(int i=0;i<assessments.size();i++){
			System.out.print("ID : "+i);
			assessments[i].assessmentView();
		}
	}

	public void submitAssessment(){
		System.out.println("Pending Assignments");

	}

	public void viewGrades(){

	}

	public void viewComments(){
		for(Comment i: comments){
			i.print();
		}

	}

	public void addComments(){
		Scanner sc= new Scanner(System.in);
		Comment c = new Comment();
		text=sc.nextLine();

		c.add(text,this.id);
		comments.add(c);


	}

	
}

interface ClassMaterial{

	public void materialView();
	public void materialAdd(Course C,String id);

}

class Slides implements ClassMaterial{
	private String title;
	private long total;
	private ArrayList<String> content;
	private Date upload;
	private String instructorId;
	private Course course;

	public void materialView(){
		System.out.println("Title: "+title);
		for(int i=0;i<total;i++){
			System.out.println("Slide "+(i+1)+" : "+content.get(i));
		}	
		System.out.println("Date of Upload: "+upload);
		System.out.println("Uploaded by: "+instructorId);	
	}


	public void materialAdd(Course c, String id){
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter topic of Slide: ");
		title=sc.nextLine();
		System.out.println("Enter number of slides: ");
		total=sc.nextLong();
		sc.nextLine();

		content =new ArrayList<>();
		System.out.println("Enter content of Slides");
		for(int i=0;i<total;i++){
			System.out.println("Content of Slide "+(i+1));
			content.add(sc.nextLine());
		}
		upload=java.util.Calendar.getInstance().getTime();  

		instructorId=id;
		course=c;

	}





}

class Videos implements ClassMaterial{
	
	private String title;
	private long total;
	private String videoFile;
	private Date upload;
	private String instructorId;
	private Course course;

	public void materialView(){
		System.out.println("Title of Video: "+title);
		System.out.println("Video File: "+videoFile);	
		System.out.println("Date of Upload: "+upload);
		System.out.println("Uploaded by: "+instructorId);	
	}

	public void materialAdd(Course c,String id){
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter topic of video: ");
		title=sc.nextLine();
		System.out.println("Enter filename of video: ");
		videoFile=sc.nextLine();
		upload=java.util.Calendar.getInstance().getTime();  
		instructorId=id;
		course=c;
	}

}

interface Assessment{
	public void assessmentAdd();
	public void assessmentView();
	public String getStatus();
	public void grade();
	public void close();
	public void submit();
	
}


class Quiz implements Assessment{
	private static final long maxMarks=1;
	private String question;
	private String status="OPEN";
	private String marksStatus="Not submited";
	private String answer;
	private long marks;
	private Instructor I;
	private Student S;
	

	
	

	public void assessmentAdd(){
		Scanner sc= new Scanner(System.in);
		System.out.print("Enter Quiz Question: ");
		question=sc.nextLine();


	}

	public void assessmentView(){
		System.out.println("Question: "+question);
		return;
	}

	public void close(){
		this.status="CLOSE";
	}

	public String getStatus(){
		return this.status;
	}

	public void submit(){
		Scanner sc= new Scanner(System.in);
		System.out.print(question+" ");
		answer= sc.nextLine();
		this.marksStatus="UNGRADED";
	}





}

class Assignment implements Assessment{
	private long maxMarks;
	private String question;
	private String answer;
	private long marks;
	private String marksStatus="Not submited";
	private Instructor I;
	private String status="OPEN";	
	private Student S;

	
	

	public void assessmentAdd(){
		Scanner sc= new Scanner(System.in);
		System.out.print("Enter Problem Statement: ");
		question= sc.nextLine();
		System.out.print("Enter Max Marks: ");
		maxMarks=sc.nextLong();
		sc.nextLine();
		return;
	}

	public void assessmentView(){
		System.out.println("Assignment: "+question+" Max Marks: "+maxMarks);
		return;
	}

	public void close(){
		status="CLOSE";
	}

	public void submit(){
		Scanner sc= new Scanner(System.in);
		System.out.print("Enter filename of assignement: ");
		String ans= sc.nextLine();
		if(ans.substring(-4).equals(".zip")==false){
			System.out.println("Wrong file format");
			return;
		}
		answer.equals(ans);
		this.marksStatus="UNGRADED";
	}

	




}


