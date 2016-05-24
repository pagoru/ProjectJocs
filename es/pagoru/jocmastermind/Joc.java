package es.pagoru.jocmastermind;

import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Pablo
 * @since /05/2016
 *
 */
public class Joc {
	
	public static final int 		MAX_ATTEMPTS		= 15;
	public static final int 		MAX_COLORS 			= 4;
	public static final String[] 	COLORS_DEFAULT 	= {
			"BLANC", "NEGRE", 
			"VERMELL", "LILA", 
			"GROC", "CREMA"
			};
	public static final String[]	RESPONSES 			= {
			"V",
			"+",
			"X"
	};
	
	private Scanner 	sc 				= new Scanner(System.in);
	private String[] 	colors 			= new String[MAX_COLORS];
	private String[][]	attemptsColors  = new String[MAX_ATTEMPTS][MAX_COLORS];
	private int			currentAttempt	= 0;
	
	/**
	 * Executa el joc
	 */
	public void run(){
		
		System.out.println("Jugador 1:");
		chooseColors();
		System.out.println("Jugador 2:");
		
		for (int i = MAX_ATTEMPTS; i > 0; i--) {
			String out = "[" + i + "] ";
			if(i < 10){
				out += " ";
			}
			System.out.print(out);
			for (int j = 0; j < MAX_COLORS; j++) {
				System.out.print(getFirstUpper(colors[j]) + " ");
			}
			System.out.print("- ");
			for (int j = 0; j < MAX_COLORS; j++) {
				System.out.print("X ");
			}
			System.out.println();
		}
		
	}
	/*
	private String[] getResponseFromAttempt(int attempt){
		int[] countColors = getRecountColors(colors);
		for (int i = 0; i < colors.length; i++) {
			
			for (int j = 0; j < COLORS_DEFAULT.length; j++) {
				if(countColors[j] > 0){
					if(COLORS_DEFAULT[j].equalsIgnoreCase(colors[i])){
						countColors[j]--;
					}
				}
			}
			
		}
	}*/
	private int[] getRecountColors(String[] colors){
		int[] countColors = new int[COLORS_DEFAULT.length];
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < COLORS_DEFAULT.length; j++) {
				if(COLORS_DEFAULT[j].equalsIgnoreCase(attemptsColors[currentAttempt][i])){
					countColors[j]++;
				}
			}
		}
		return countColors;
	}
	private String getFirstUpper(String color){
		return color.substring(0, 1).toUpperCase();
	}
	private void chooseColors(){
		int maxColors = 0;
		do {
			System.out.println("Introdueix el color " + (maxColors + 1) + ":");
			String currentColor = readString();
			if(isColorValid(currentColor)){
				colors[maxColors] = currentColor;
				maxColors++;
			} else {
				System.out.println("El color no es valid!");
			}
		} while (maxColors < MAX_COLORS);
		
	}
	public boolean isColorValid(String color){
		for (int i = 0; i < COLORS_DEFAULT.length; i++) {
			if(COLORS_DEFAULT[i].equalsIgnoreCase(color)){
				return true;
			}
		}
		return false;
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
