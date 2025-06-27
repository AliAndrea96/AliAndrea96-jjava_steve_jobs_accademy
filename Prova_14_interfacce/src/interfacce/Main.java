package interfacce;

public class Main {

	public static void main(String[] args) {
		Auto_Ibrida a1 = new Auto_Ibrida(70);
		
		System.out.println(a1.toString());
		a1.ricarica();
		System.out.println(a1.toString());
		
		a1.connect();
	}
}