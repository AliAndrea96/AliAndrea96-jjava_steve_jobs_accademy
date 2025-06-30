package Cronologia;
public class Main {
    public static void main(String[] args) {
        Browser browser = new Browser();

//Visitiamo 3 pagine
        browser.visitPage("https://github.com");
        browser.visitPage("https://www.google.com");
        browser.visitPage("https://www.https://www.stevejobs.academy/.com");

//Tasto indietro una volta
        browser.goBack();

//Tasto indietro un'altra volta
        browser.goBack();

//Cronologia
        browser.printHistory();
    }
}