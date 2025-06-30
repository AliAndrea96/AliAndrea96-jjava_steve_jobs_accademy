package ufficio_postale;

public class Main {
	  public static void main(String[] args) {
	        Posta ufficioPostale = new Posta();

	        ufficioPostale.entraInCoda(new Persona("Franco"));
	        ufficioPostale.entraInCoda(new Persona("Giorgia"));
	        ufficioPostale.entraInCoda(new Persona("Luca"));


	        ufficioPostale.mostraCoda();

	        System.out.println("\nIl prossimo da servire è: " + ufficioPostale.chiEIlProssimo());

//Questo output rappresenta gli sportelli postali, 
//posso aggiungere più output uguali per aumentare gli sportelli.
	        System.out.println(ufficioPostale.servireProssimo());

//Mostra la coda rimanente
	        System.out.println();
	        ufficioPostale.mostraCoda();
	    }
}