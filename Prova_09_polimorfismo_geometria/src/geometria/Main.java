package geometria;

public class Main {
    public static void main(String[] args) {
        Forma triangolo = new Triangolo(3, 4, 3, 4, 5);
        Forma cerchio = new Cerchio(5);
        Forma rettangolo = new Rettangolo(4, 6);

        System.out.println("Area del triangolo: " + triangolo.calcolaArea());
        System.out.println("Perimetro del triangolo: " + triangolo.calcolaPerimetro());

        System.out.println("Area del cerchio: " + cerchio.calcolaArea());
        System.out.println("Perimetro del cerchio: " + cerchio.calcolaPerimetro());

        System.out.println("Area del rettangolo: " + rettangolo.calcolaArea());
        System.out.println("Perimetro del rettangolo: " + rettangolo.calcolaPerimetro());
    }
}