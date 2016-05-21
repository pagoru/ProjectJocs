package es.pagoru.jocdelavida;

import java.util.Scanner;

/**
 * 
 * @author Pablo
 * @since 17/05/2016
 *
 */
public class Joc {
	
	private final static int dimensions = 10;
	
	private Scanner 	sc 			= new Scanner(System.in);
	private boolean[][]map 		= new boolean[dimensions][dimensions];
	private long 		generation 	= 1;
	/**
	 * Crea la primera instancia del joc.
	 */
	public Joc(){
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < 10; j++) {
//				map[i][j] = true;
//			}
//		}
		readInit();
		printMap();
	}
	/**
	 * Executa el joc.
	 */
	public void run(){
		while((readString()) != null) {
			newGeneration();
			printMap();
		}
		sc.close();
	}
	/**
	 * Demana al usuari qu introdueixi les primeres celules.
	 */
	public void readInit() {
		int count = 1;
		int current = -1;
		int x = -1;
		int y = -1;
		System.out.println("Introdueix les primeres celules, per finalitzar, introdueix '-1'.");
		do{
			if(current != -1){
				current -= 1;
				if((current >= 0 && current < dimensions)){
					if(x != -1){
						y = current;
					} else {
						System.out.println("Introdueix la posició Y de la celula [" + count + "]:");
						x = current;
					}
					if(x != -1 && y != -1){
						System.out.println("Celula introduida correctament en la posició " + x + "." + y);
						map[x][y] = true;
						count++;
						x = y = -1;
						System.out.println("Introdueix la posició X de la celula [" + count + "]:");
					}
				} else {
					System.out.println("No es poden superar els limits.");
				}
			} else {
				System.out.println("Introdueix la posició X de la celula [" + count + "]:");
			}
		} while((current = readInt()) != -1);
	}
	/**
	 * Imprimeix el mapa.
	 */
	public void printMap(){
		System.out.println("Generació actual [" + generation + "]");
		for (int x = 0; x < dimensions; x++) {
			for (int y = 0; y < dimensions; y++) {
				if(map[x][y]){
					System.out.print(" x ");
				} else {
					System.out.print(" * ");
				}
			}
			System.out.println();
		}
		System.out.println("Introdueix qualsevol tecla per continuar.");
	}
	/**
	 * Genera una nova generació.
	 */
	public void newGeneration(){
		boolean[][] currentMap = new boolean[dimensions][dimensions];
		for (int x = 0; x < dimensions; x++) {
			for (int y = 0; y < dimensions; y++) {
				
				int life = 0;
				
				for (int k = -1; k <= 1; k++) {
					for (int l = -1; l <= 1; l++) {
						int newX = x + k;
						int newY = y + l;
						
						if((newX >= 0 && newX < dimensions)
								&& (newY >= 0 && newY < dimensions)
								&& (newX != x && newY != y)){
							if(map[newX][newY]){
								life++;
							}
						}
					}
				}
				
				System.out.println(x + "." + y + "<" + map[x][y] + ">" + life);
				if(map[x][y]){
					currentMap[x][y] = true;
					if(life < 2 || life > 3){
						currentMap[x][y] = false;
					}
				} else {
					if(life == 3){
						currentMap[x][y] = true;
					}
				}
				
			}
			System.out.println();
		}
		this.map = currentMap;
		generation++;
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
