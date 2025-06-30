package Cronologia;
import java.util.Stack;

class Browser {
    private Stack<String> history;

    public Browser() {
        history = new Stack<>();
    }

//Visita una nuova pagina e la mette nello stack
    public void visitPage(String url) {
        history.push(url);
        System.out.println("Visitata la pagina: " + url);
    }

//Simula il tasto Indietro
    public void goBack() {
        try {
            if (history.isEmpty()) {
                System.out.println("Non hai visualizzato nessuna pagina.");
            } else if (history.size() == 1) {
//Hai solo una pagina, quindi se vai indietro non c'è più nulla
                history.pop();
                System.out.println("Non hai visualizzato nessuna pagina.");
            } else {
//Rimuove l'attuale pagina
                history.pop();
//Mostra la nuova pagina corrente
                System.out.println("Sei tornato alla pagina: " + history.peek());
            }
        } catch (Exception e) {
            System.out.println("Errore durante l'operazione di 'indietro': " + e.getMessage());
        }
    }


//Stampa la cronologia dal più recente al meno recente
    public void printHistory() {
        try {
            if (history.isEmpty()) {
                System.out.println("Cronologia vuota.");
            } else {
                System.out.println("\nCronologia (dal più recente al meno recente):");
                for (int i = history.size() - 1; i >= 0; i--) {
                    System.out.println("- " + history.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Errore durante la stampa della cronologia: " + e.getMessage());
        }
    }

}