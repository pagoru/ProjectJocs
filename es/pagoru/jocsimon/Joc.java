package es.pagoru.jocsimon;

import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Pablo
 * @since 23/05/2016
 *
 */
public class Joc {
	
	public static final int 		MAX_COLORS 			= 4;
	public static final String[] 	COLORS_DEFAULT 	= {"VERMELL", "GROC", "BLAU", "VERD"};
	public static final int[] 		DIFFICULTIES		= {5, 3, 1};
	
	private Scanner 	sc 				= new Scanner(System.in);
	private String[] 	colors 			= new String[MAX_COLORS];
	private int			currentIndex	= 0;
	private int 		difficulty		= 0;
	private int			points			= 0;
	
	/**
	 * Executa el joc
	 */
	public void run(){
		
		printSeparation();
		System.out.println("Benvingut al joc de 'Simon diu'");
		sleep(2000);
		chooseDifficulty();
		
		do{
			generateColors();
			sleep(2000);
			printSeparation();
			
			String color;
			while((color = getNextColor()) != null){
				System.out.println(color);
				sleep(DIFFICULTIES[difficulty] * 1000);
				printSeparation();
			}
			currentIndex = 0;
			boolean win = true;
			do{
				System.out.println("Introdueix el color " + (currentIndex + 1) + ".");
				color = readString();
				if(isValid(color, colors[currentIndex])){
					System.out.println("Color correcte.");
					currentIndex++;
				} else {
					win = false;
					break;
				}
			} while(color != null && currentIndex < MAX_COLORS);
			sleep(2000);
			printSeparation();
			if(win){
				points++;
				System.out.println(getPoints());
			} else {
				System.out.println("Has perdut.");
				System.out.println(getPoints());
				break;
			}
		} while(true);
		
	}
	/**
	 * Retorna el seguent color a jugar.
	 * @return String Retorna un color.
	 */
	private String getNextColor(){
		if(currentIndex < MAX_COLORS){
			currentIndex++;
			return colors[currentIndex - 1];
		}
		return null;
	}
	/**
	 * Comprova que els dos colors siguin valids
	 * @param color1 Color número 1.
	 * @param color2 Color número 2.
	 * @return Boolean 
	 */
	private boolean isValid(String color1, String color2){
		return color1.equalsIgnoreCase(color2);
	}
	/**
	 * Genera nous colors en el array actual de colors.
	 */
	private void generateColors(){
		Random r = new Random();
		currentIndex = 0;
		for (int i = 0; i < colors.length; i++) {
			colors[i] = COLORS_DEFAULT[r.nextInt(COLORS_DEFAULT.length)];
		}
	}
	/**
	 * Permet elegir la dificultat al jugador.
	 */
	private void chooseDifficulty(){
		int dif = -1;
		do {
			printSeparation();
			System.out.println("Elegeix una dificultat entre 1 i 3.");
			dif = readInt();
		} while (dif <= 0 || dif >= 4);
		difficulty = dif - 1;
		printSeparation();
		System.out.println("S'ha elegit la dificultat " + dif + ".");
	}
	/**
	 * Dorm el thread actual.
	 * @param millis Milisegons que es vol dormir el programa.
	 */
	private void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Retorna els punts actuals.
	 * @return String Retorna el text per printar en pantalla.
	 */
	private String getPoints(){
		String pu = " punts.";
		if(points == 1){
			pu = " punt.";
		}
		return "Puntuació de " + points + pu;
	}
	/**
	 * Imprimeix una separacio en la consola perque sigui mes maco.
	 */
	private void printSeparation(){
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
	/**
	 * Llegeix un string per teclat.
	 * @return String
	 */
	public String readString(){
		if(sc.hasNext()){
			return sc.next();
		}
		sc.nextLine();
		return null;
	}
	/**
	 * Llegeix un integer per teclat.
	 * @return Integer
	 */
	public int readInt(){
		if(sc.hasNextInt()){
			return sc.nextInt();
		}
		sc.nextLine();
		return -1;
	}
	
}
