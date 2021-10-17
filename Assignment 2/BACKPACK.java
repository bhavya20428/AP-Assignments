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

class Instructor{


}

class Student{

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
	public void assessmentView();
	public void grade();
	public void close();
	public void submit();
	public void viewGrade();
}


class Quiz implements Assessment{

}

class Assignment implements Assessment{

}

