package ufficio_postale;
import java.util.LinkedList;
import java.util.Queue;

class Posta {
    private Queue<Persona> coda;

    public Posta() {
        coda = new LinkedList<>();
    }

//Aggiunge una persona alla fine della coda.
    public void entraInCoda(Persona p) {
        coda.add(p);
        System.out.println(p.getNome() + " è entrato/a in coda.");
    }

//Restituisce il nome della persona in testa alla coda,cioè la prossima a essere servita, senza rimuoverla.
    public String chiEIlProssimo() {
        Persona prossimo = coda.peek();
        if (prossimo != null) {
            return prossimo.getNome();
        } else {
            return "La coda è vuota.";
        }
    }
    
//Rimuove e restituisce il nome della persona che è stata servita (cioè la prima della coda).
    public String servireProssimo() {
        Persona servito = coda.poll();
        if (servito != null) {
            return servito.getNome() + " è stato servito/a.";
        } else {
            return "Nessuno da servire, la coda è vuota.";
        }
    }

//Scorre tutta la coda e stampa i nomi delle persone ancora in attesa.
    public void mostraCoda() {
        if (coda.isEmpty()) {
            System.out.println("La coda è vuota.");
        } else {
            System.out.println("\nPersone in coda:");
            for (Persona p : coda) {
                System.out.println("- " + p.getNome());
            }
        }
    }
}