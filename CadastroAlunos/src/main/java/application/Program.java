package application;

import java.util.ArrayList;
import java.util.List;

import entities.Student;
import entities.controller.StudentController;

public class Program {

	public static void main(String[] args) {
		
		List<Student> students = new ArrayList<>();
		
		Student s1 = new Student(null, "Fiódor Dostoiévski", "4490783300", 17);
		
		//students.add(new Student("Fernanda Maria Dos Santos", "4590384907", 53));
		/*Student s2 = new Student("Mateus Santos Bispo", "6894037741", 25);
		Student s3 = new Student("Fiódor Dostoiévski", "4490783300", 17);
		Student s4 = new Student("Maria José Candida dos Santos", "5589462293", 90);*/
		
		//System.out.println(students);
		
		StudentController cont = new StudentController();
		
		//cont.delete(s1);
		cont.add(s1);
		
	}
	
}
