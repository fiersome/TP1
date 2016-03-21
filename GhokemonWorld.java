public class GhokemonWorld
{
	private String[][] terrain;
	private int width;
	private int length;
	private int[] startIndex;

	public GhokemonWorld(int width, int length, String input) 
	{
		startIndex = new int[2];
		this.width = width;
		this.length = length;
		String[][] terrainShape = new String[width][length];

		/** membuat cetakan berupa array 2D sesuai dengan bentuk yang diinginkan **/
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < length; j++) {
				String temp = input.substring(length * i + j, length * i + j + 1);
				terrainShape[i][j] = temp;
				if(temp.equalsIgnoreCase("L")) {
					startIndex[0] = i;
					startIndex[1] = j;
				}
			}
		}

		this.terrain = terrainShape;
	}

	public void start() {
			exploring(startIndex[0], startIndex[1], false);
	}

	private boolean exploring(int startRow, int startColumn, boolean inverse) 
	{
		if(startRow >= this.width || startRow < 0) {
			return inverse;
		}
		if(startColumn >= this.length || startColumn < 0) {
			return inverse;
		}
		String temp = this.terrain[startRow][startColumn];
		if(temp.equals("#") || temp.equals("@")) {
		 	return inverse;
		}

		if(temp.equals("?")) {
			inverse = !inverse;
		}
		this.terrain[startRow][startColumn] = "@";

		while(surroundingIsVisited(startRow, startColumn) == false) {
			inverse = exploring(startRow - 1, startColumn, inverse);
			if(inverse) {
				inverse = exploring(startRow, startColumn - 1, inverse);
			}
			else {
				inverse = exploring(startRow, startColumn + 1, inverse);
			}
			inverse = exploring(startRow + 1, startColumn, inverse);
			if(inverse) {
			 	inverse = exploring(startRow, startColumn + 1, inverse);
			}
			else {
			 	inverse = exploring(startRow, startColumn - 1, inverse);
			}
		}

		return inverse;
	}

	public void print() {
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.length; j++) {
				System.out.print(terrain[i][j]);
			}
			System.out.println("");
		}
	}

	public boolean surroundingIsVisited(int indexRow, int indexColumn) {
		boolean temp = true;
		if(indexRow + 1 < this.width) {
			boolean inBack = this.terrain[indexRow + 1][indexColumn].equals("#") || this.terrain[indexRow + 1][indexColumn].equals("@");
			temp = temp && inBack;
		}

		if(indexRow - 1 >= 0) {
			boolean inFront = this.terrain[indexRow - 1][indexColumn].equals("#") || this.terrain[indexRow - 1][indexColumn].equals("@");
			temp = temp && inFront;

		}
		if(indexColumn + 1 < this.length) {
			boolean inRight = this.terrain[indexRow][indexColumn + 1].equals("#") || this.terrain[indexRow][indexColumn + 1].equals("@");
			temp = temp && inRight;
		}
		if(indexColumn - 1 >= 0) {
			boolean inLeft = this.terrain[indexRow][indexColumn - 1].equals("#") || this.terrain[indexRow][indexColumn - 1].equals("@");
			temp = temp && inLeft;
		}
		
		if(temp) {
			return true;
		}
		else {
			return false;
		}
	}
}
