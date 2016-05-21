package es.pagoru.jocdelsvaixells;

/**
 * 
 * @author Pablo
 * @since 21/05/2016
 *
 */
public class Jugador {
	
	private String 		name;
	private int[][] 	ships 					= new int[Joc.dimensions][Joc.dimensions];
	/*
	 * Ships
	 * 0: Aigua
	 * 1: Vaixell
	 */
	private int[][] 	movements 				= new int[Joc.dimensions][Joc.dimensions];
	/*
	 * Movements
	 * 0: Res
	 * 1: Vaixell
	 * 2: Aigua
	 */
	private int 		availableShipsToPut 	= Joc.ships;
	/**
	 * Constructor del jugador.
	 * @param String Nom del jugador.
	 */
	public Jugador(String name){
		setName(name);
	}
	/**
	 * Introdueix un nou vaixell.
	 * @param x Coordenada X del vaixell.
	 * @param y Coordenada Y del vaixell.
	 * @return La resposta al intent de col·locar un vaixell.
	 * <br>2: Ultim vaixell disponible.
	 * <br>1: Vaixell col·locat correctament.
	 * <br>0: Hi ha un vaixell molt a prop.
	 * <br>-1: La coordenada esta fora del mapa.
	 * <br>-2: No hi ha vaixells disponibles per col·locar.
	 */
	public int addNewShip(int x, int y){
		if(availableShipsToPut > 0){
			if((x >= 0 && x < ships.length)
					&& (y >= 0 && y < ships.length)){
				
				boolean shipNear = false;
				for (int k = -1; k <= 1; k++) {
					for (int l = -1; l <= 1; l++) {
						int newX = x + k;
						int newY = y + l;
						
						if((newX >= 0 && newX < ships.length)
								&& (newY >= 0 && newY < ships.length)){
							if(ships[newX][newY] == 1){
								shipNear = true;
							}
						}
					}
				}
				
				if(!shipNear){
					ships[x][y] = 1;
					availableShipsToPut--;
					if(availableShipsToPut == 0){
						return 2;//Últim vaixell disponible
					}
					return 1;//Vaixell colocat
				}
				return 0;//Hi ha un vaixell massa a prop
			}
			return -1;//Fora del mapa
		}
		return -2;//No hi ha vaixells disponibles
	}
	/**
	 * Introdueix un nou moviment sobre el taulell de l'altre jugador.
	 * @param otherPlayer Jugador contrari.
	 * @param x Coordenada X que es vol introduir.
	 * @param y Coordenada Y que es vol introduir.
	 * @return Retorna la resposta al moviment.
	 * <br>1: La posicio es un vaixell.
	 * <br>0: La posicio es aigua.
	 * <br>-1: La posicio esta fora del mapa.
	 */
	public int addMovement(Jugador otherPlayer, int x, int y){
		if((x >= 0 && x <= otherPlayer.getShips().length)
				&& (y >= 0 && y <= otherPlayer.getShips().length)){
			if(otherPlayer.getShips()[x][y] == 1){
				movements[x][y] = 1;
				return 1;//Vaixell
			}
			movements[x][y] = 2;
			return 0;//Aigua
		}
		return -1;//Fora del mapa
	}
	/**
	 * Retorna els vaixells que queden vius de l'usuari actual.
	 * @param otherPlayer Jugador contrari per veure quines posicions ha vist.
	 * @return Retorna el número de vaixells que queden.
	 */
	public int getAliveShips(Jugador otherPlayer){
		int count = 0;
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships.length; j++) {
				if(ships[i][j] == 1 && otherPlayer.getMovements()[i][j] == 0){
					count++;
				}
			}
		}
		return count;
	}
	/**
	 * Retorna les posicions dels vaixells.
	 * @return Array de int
	 */
	public int[][] getShips(){
		return ships;
	}
	/**
	 * Retorna els moviments de l'usuari.
	 * @return Array de int
	 */
	public int[][] getMovements(){
		return movements;
	}
	/**
	 * Retorna el nom de l'usuari.
	 * @return String
	 */
	public String getName(){
		return name;
	}
	/**
	 * Permet cambiar el nom del jugador.
	 * @param n Nom del jugador.
	 */
	public void setName(String n){
		name = n;
	}
	
}
