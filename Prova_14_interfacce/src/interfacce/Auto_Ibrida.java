package interfacce;

public class Auto_Ibrida extends Veicolo implements Connettivita, Ricaricabile {

	private boolean connected;
	
	public Auto_Ibrida(int batteria, boolean connected) {
		super(batteria);
		this.connected = false;
	}

	public Auto_Ibrida(int batteria) {
		super(batteria);
	}

	@Override
	public void ricarica() {
		batteria = 100;
	}

	@Override
	public void connect() {
		System.out.println("Bluetooth connesso");
		this.connected = true;
	}

	@Override
	public void disconnect() {
		System.out.println("Bluetooth disconnesso");
		this.connected = false;

	}

	@Override
	public boolean isConnected() {
		return this.connected;
	}
}