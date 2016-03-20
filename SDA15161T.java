import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SDA15161T
{
	public static void main (String[] args) 
	{
		try {
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
			String[] input = buffReader.readLine().split(" ");

			int row = Integer.parseInt(input[0]);
			int column = Integer.parseInt(input[1]);
			int maxNumberOfGhokemon = Integer.parseInt(input[2]);
			int maxNumberOfTrainer = Integer.parseInt(input[3]);
			int maxNumberOfParty = Integer.parseInt(input[4]);

			String world = "";
			
			for(int i = 0; i < row; i++) {
				world += buffReader.readLine();
			}

			GhokemonWorld dungeon = new GhokemonWorld(row, column, world);

			dungeon.start();
			dungeon.print();

			
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		
	}
}