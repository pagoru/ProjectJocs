package es.pagoru.jocdelsvaixells;

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
	
	public Jugador(String name){
		setName(name);
	}
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
				return 0;//Hi ha un vaixell massa aprop
			}
			return -1;//Fora del mapa
		}
		return -2;//No hi ha vaixells disponibles
	}
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
	public int[][] getShips(){
		return ships;
	}
	public int[][] getMovements(){
		return movements;
	}
	public String getName(){
		return name;
	}
	public void setName(String n){
		name = n;
	}
	
}
