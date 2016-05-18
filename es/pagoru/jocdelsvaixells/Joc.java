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
