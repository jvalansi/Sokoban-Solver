
public class Tile {
	int x;
	int y;
	boolean wall;
	boolean box;
	boolean player;
	boolean target;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		wall = false;
		box = false;
		player = false;
		target = false;
	}
	
	public Tile(Tile tile) {
		x = tile.x;
		y = tile.y;
		wall = tile.wall;
		box = tile.box;
		player = tile.player;
		target = tile.target;
	}

	public void setWall() {
		wall = true;
	}

	public void setBox() {
		box = true;
	}

	public void setTerget() {
		target = true;
	}

	public void setPlayer() {
		player = true;
	}

	public boolean isBox() {
		return box;
	}
	
	public String toString(){
		return "(" + this.x +"," + this.y + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Tile)){
			System.out.println("not tile");
			return false;
		}
		Tile tile = (Tile)obj;
		if(	tile.box != this.box ||
			tile.player != this.player||
			tile.target != this.target||
			tile.wall != this.wall){
			return false;
		}
		return true;
	}
	
}
