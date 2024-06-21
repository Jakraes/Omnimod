package jakraes.omnimod.utils;

public class Position {
	public int x;
	public int y;
	public int z;
	public int dimension;

	public Position(int x, int y, int z, int dimension) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.dimension = dimension;
	}

	@Override
	public String toString() {
		return x + " " + y + " " + z + " " + dimension;
	}

	public static Position fromString(String position) {
		String[] parsed = position.split(" ");

		int x = Integer.parseInt(parsed[0]);
		int y = Integer.parseInt(parsed[1]);
		int z = Integer.parseInt(parsed[2]);
		int dimension = Integer.parseInt(parsed[3]);

		return new Position(x, y, z, dimension);
	}
}
