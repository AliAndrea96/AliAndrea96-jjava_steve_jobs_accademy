package Mezzi_di_trasporto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Macchine extends Mezzi_di_trasporto {
	
	String modello;
	String colore;
	String password;

	public Macchine(String marca, int anno, String modello, String colore, String password) {
		super(marca, anno);
		this.modello = modello;
		this.colore = colore;
		this.password = password;
	}
	
	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getPassword() {
		try {
			// Calcola l'hash della password in SHA-256
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = md.digest(password.getBytes());

			// Converte i byte in formato esadecimale
			StringBuilder hexString = new StringBuilder();
			for (byte b : hashBytes) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Errore: algoritmo non trovato", e);
		}
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Macchina: Modello=" + modello + ", Colore=" + colore + ", " + super.toString();
	}
}
