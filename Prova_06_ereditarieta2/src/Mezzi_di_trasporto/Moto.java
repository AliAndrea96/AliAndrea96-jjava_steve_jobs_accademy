package Mezzi_di_trasporto;

public class Moto extends Mezzi_di_trasporto {
	String modello;
	int ruote;
	public Moto(String marca, int anno, String modello, int ruote) {
		super(marca, anno);
		this.modello = modello;
		this.ruote = ruote;
	}
	@Override
	public String toString() {
		return "Moto: Modello=" + modello + ", Numero ruote=" + ruote + ", " + super.toString();
	}
	
	
}
