package Scuola;

public class Main {
    public static void main(String[] args) {
    	
    	Person p1 = new Person("Andrea", "Alì", 29);
    	Student s1 = new Student("Gino","Buonvino",89, 93575,8.6f);
    	Docenti d1 = new Docenti("Carlo", "Cracco", 50, 1500, 24235);
    	
    	
    	System.out.print(p1.toString());
    	System.out.print(s1.name + s1.surname + s1.age + s1.matricola + s1.media);
    	System.out.print(d1.name + d1.surname + d1.age + d1.matricola + d1.stipendio);
    	
    }
   
}