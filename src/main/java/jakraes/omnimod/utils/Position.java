package jakraes.omnimod.utils;

public class Position {
	public int x;
	public int y;
	public int z;

	public Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return x + " " + y + " " + z;
	}

	public Position fromString(String position) {
		String[] parsed = position.split(" ");

		int x = Integer.parseInt(parsed[0]);
		int y = Integer.parseInt(parsed[1]);
		int z = Integer.parseInt(parsed[2]);

		return new Position(x, y, z);
	}
}
