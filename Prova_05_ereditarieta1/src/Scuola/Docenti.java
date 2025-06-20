package Scuola;

public class Docenti extends Person {
	float stipendio;
	int matricola;
	
	public Docenti(String name, String surname, int age, int matricola, float stipendio ) {
		super(name, surname, age);
		this.matricola = matricola;
		this.stipendio = stipendio;
	}
}
