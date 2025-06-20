package Mezzi_di_trasporto;

public class Main {

	public static void main(String[] args) {
		
		Macchine car1 = new Macchine("Fiat", 2022, "Elettrico", "Red", "mypass123");
		Moto motocycle1 = new Moto("BMW", 2020, "Benzina", 4);
		
		System.out.println(car1.toString() + motocycle1.toString());
	}
}
