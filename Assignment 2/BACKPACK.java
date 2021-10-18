import java.util.*;

public class BACKPACK{
	Scanner sc= new Scanner(System.in);

}

class Course{

	private ArrayList<Assessment> assessments;
	private ArrayList<ClassMaterial> classmaterial;

	Course(){
		assessments=new ArrayList<>();
		classmaterial= new ArrayList<>();
	}

}

interface common{
	public void viewMaterial();
	public void viewAssessment();
	public void viewComments();
	public void addComments();
	public void menu();
	public void logout();
}

class Instructor implements common{

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

	public void choose(){
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
				logout();
				break;
			default:
				System.out.println("Wrong Input");
		}
	}

	public void addMaterial(){

	}

	public void addAssessments(){

	}

	public void viewMaterial(){

	}

	public void viewAssessment(){

	}
	public void gradeAssessment(){

	}

	public void closeAssessment(){

	}

	public void viewComments(){

	}

	public void addComments(){

	}

	public void logout(){

	}



}

class Student implements common{

	public void menu(){
		 
		System.out.println(" 1. View lecture materials");
		System.out.println(" 2. View assessments");
		System.out.println(" 3. Submit assessment");
		System.out.println(" 4. View grades");
		System.out.println(" 5. View comments");
		System.out.println(" 6. Add comments");
		System.out.println(" 7. Logout");

	}

	public void choose(){
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
				logout();
				break;
			default:
				System.out.println("Wrong input");
		}
	}

	public void viewMaterial(){

	}

	public void viewAssessment(){

	}

	public void submitAssessment(){

	}

	public void viewGrades(){

	}

	public void viewComments(){

	}

	public void addComments(){

	}

	public void logout(){

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
	public void grade();
	public void close();
	public void submit();
	public void viewGrade();

}


class Quiz implements Assessment{
	private static final long maxMarks=1;
	private String question;
	private String status="OPEN";
	private HashMap<String,AnswerQuiz> submissions;
	

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
		status="CLOSE";
	}




}

class AnswerQuiz{
	private String instructorId;
	private String answer;
	private long marks=-1;

	public void submit(){
		Scanner sc= new Scanner(System.in);
		System.out.print(question+" ");
		answer= sc.nextLine();
	}



}

class Assignment implements Assessment{
	private long maxMarks;
	private String question;
	private String status="OPEN";
	private HashMap<String,AnswerAssignment> submissions;
	

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

	




}

class AnswerAssignment{
	private String instructorId;
	private String answer;
	private long marks=-1;

	public void submit(){
		Scanner sc= new Scanner(System.in);
		System.out.print("Enter filename of assignement: ");
		String ans= sc.nextLine();
		if(ans.substring(-4).equals(".zip")==false){
			System.out.println("Wrong file format");
			return;
		}
		answer.equals(ans);
	}

}

