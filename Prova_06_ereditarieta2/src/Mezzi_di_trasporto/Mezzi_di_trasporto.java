package Mezzi_di_trasporto;

public class Mezzi_di_trasporto {
	
	String marca;
	int anno;
	public Mezzi_di_trasporto(String marca, int anno) {
		this.marca = marca;
		this.anno = anno;
	}
	@Override
	public String toString() {
		return "Marca=" + marca + ", Anno=" + anno + "; ";
	}
}