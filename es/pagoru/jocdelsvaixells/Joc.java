package es.pagoru.jocdelsvaixells;

import java.util.Scanner;

public class Joc {

	public static final int dimensions 	= 5;
	public static final int ships 		= 5;
	
	private Jugador[] 	jugadors 	= new Jugador[2];
	private Scanner 	sc 			= new Scanner(System.in);
	
	public Joc(){
		
	}
	public void run(){
		
		System.out.println("Introdueix el nom del primer jugador:");
		jugadors[0] = new Jugador(readString());
		System.out.println("Introdueix el nom del segon jugador:");
		jugadors[1] = new Jugador(readString());
		
		putShips(jugadors[0]);
		putShips(jugadors[1]);
		
		while(true){
			newMovement(jugadors[0], jugadors[1]);
			newMovement(jugadors[1], jugadors[0]);
		}
		
	}
	private void newMovement(Jugador j, Jugador j2){
		
		boolean xpos = true;
		
		int x = -1;
		int y = -1;
		
		int res = Integer.MAX_VALUE;
		
		System.out.println("Es el torn de " + j.getName());
		
		do{
			if(xpos){
				System.out.println("Introdueix una coordenada x.");
				x = readInt() - 1;
				xpos = false;
			} else {
				System.out.println("Introdueix una coordenada y.");
				y = readInt() - 1;
				xpos = true;
				
				res = j.addMovement(j2, x, y);
				
				switch (res) {
				case 1:
					System.out.println("Tocat y enfonsat!");
					break;
				case 0:
					System.out.println("Aigua!");
					break;
				case -1:
					System.out.println("Aixo esta fora del mapa.");
					break;
				}
				break;
			}
		} while(res != 1 && res != 0);
		
	}
	private void putShips(Jugador j){
		
		boolean xpos = true;
		
		int x = -1;
		int y = -1;
		
		int res = Integer.MAX_VALUE;
		
		int vaixells = 1;
		
		System.out.println("Es el torn de " + j.getName());
		System.out.println(
				"Introdueix els " + ships 
				+ " vaixells en el mapa de " 
				+ dimensions + "x" + dimensions
				);
		
		do{
			
			if(xpos){
				System.out.println("Introdueix la coordenada x del vaixell " + vaixells);
				x = readInt() - 1;
				xpos = false;
			} else {
				System.out.println("Introdueix la coordenada y del vaixell " + vaixells);
				y = readInt() - 1;
				xpos = true;
				
				res = j.addNewShip(x, y);
				
				switch(res){
				case -1:
					System.out.println("Aquesta posició esta fora del mapa.");
					break;
				case 0:
					System.out.println("Hi ha un vaixell molt aprop.");
					break;
				case 1:
					System.out.println("El vaixell s'ha colocat correctament.");
					vaixells++;
					break;
				case 2:
					System.out.println("S'ha colocat l'ultim vaixell correctament.");
					break;
				}
			}
			
		} while(res != -2 && res != 2);
	}
	/**
	 * Llegeix un string per teclat.
	 * @return String
	 */
	public String readString(){
		if(sc.hasNext()){
			return sc.next();
		}
		return null;
	}
	/**
	 * Llegeix un integer per teclar.
	 * @return Integer
	 */
	public int readInt(){
		if(sc.hasNextInt()){
			return sc.nextInt();
		}
		return -1;
	}
}
