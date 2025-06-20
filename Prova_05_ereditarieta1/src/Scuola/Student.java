package Scuola;

public class Student extends Person {
	
	int matricola;
	float media;
	
	public Student(String name, String surname, int age, int matricola, float media ) {
		super(name, surname, age);
		
		this.matricola = matricola;
		this.media = media;
	}
	
}
