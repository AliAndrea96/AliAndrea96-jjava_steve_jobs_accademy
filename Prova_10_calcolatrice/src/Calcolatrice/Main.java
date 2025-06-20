package Calcolatrice;

public class Main {
	public static void main(String[] args) {
		Calcolatrice sum = new Calcolatrice();
		
		System.out.println("2 + 3 = " + sum.somma(2, 3));
		System.out.println("2 + 3 + 5 = " + sum.somma(2, 3, 5));
		System.out.println("2.6 + 3.3 = " + sum.somma(2, 3));
		System.out.println("\"uno\" + \"due\" = " + sum.somma("uno", "due"));
	}
}