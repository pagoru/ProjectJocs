package es.pagoru.jocmastermind;

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
		chooseColorsFirst();
		System.out.println("Jugador 2:");
		
		for (int o = MAX_ATTEMPTS; o > 0; o--) {
			chooseColorsSecond();
			
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
					String pos = responseFromColorAttempts()[i - 1][j];
					if(pos != null){
						System.out.print(pos);
					} else {
						System.out.print("N ");
					}
				}
				System.out.println();
			}
		}
		
	}
	private String[][] responseFromColorAttempts(){
		String[][] 	res 	= new String[MAX_ATTEMPTS][MAX_COLORS];
		int[] 		count 	= countColorsFromCurrent();
		//1* Mateixa posició 'V'
		for (int i = 0; i < colors.length; i++) {
			String col = attemptsColors[currentAttempt][i];
			int indexCol = getColorIndex(col);
			if(indexCol != -1){
				if(colors[i].equalsIgnoreCase(col)){
					res[currentAttempt][i] = "V ";
					count[indexCol]--;
				} else {
					if(count[indexCol] > 0){
						//2* No esta en la posició '+'
						res[currentAttempt][i] = "+ ";
					} else {
						//3* No es correcte 'X'
						res[currentAttempt][i] = "X ";
					}
				}
			}	
		}
		return res;
	}
	private int getColorIndex(String color){
		for (int i = 0; i < COLORS_DEFAULT.length; i++) {
			if(COLORS_DEFAULT[i].equalsIgnoreCase(color)){
				return i;
			}
		}
		return -1;
	}
	private int[] countColorsFromCurrent(){
		int[] count = new int[COLORS_DEFAULT.length];
		for (int i = 0; i < COLORS_DEFAULT.length; i++) {
			
			for (int j = 0; j < colors.length; j++) {
				if(COLORS_DEFAULT[i].equalsIgnoreCase(colors[j])){
					count[i]++;
				}
			}
			
		}
		return count;
	}
	private String getFirstUpper(String color){
		return color.substring(0, 1).toUpperCase();
	}
	private void chooseColorsFirst(){
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
	private void chooseColorsSecond(){
		int maxColors = 0;
		do {
			System.out.println("Introdueix el color " + (maxColors + 1) + ":");
			String currentColor = readString();
			if(isColorValid(currentColor)){
				attemptsColors[currentAttempt][maxColors] = currentColor;
				maxColors++;
			} else {
				System.out.println("El color no es valid!");
			}
		} while (maxColors < MAX_COLORS);
		currentAttempt++;
		
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
