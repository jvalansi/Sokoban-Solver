import java.awt.Point;


public class Board {

	private Tile[][] b;
	private int width;
	private int length;
	
	public Board(int x, int y) {
		width = x;
		length = y;
		b = new Tile[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				b[i][j] = new Tile(i,j);
			}
		}
		
	}

	public Board(Board b2) {
		width = b2.width;
		length = b2.length;		
		b = new Tile[width][length];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				b[i][j] = new Tile(b2.getTile(i,j));
			}
		}
		
	}

	Tile getTile(int i, int j) {
		return b[i][j];
	}

	int getLength() {
		return this.length;
	}

	int getWidth() {
		return this.width;
	}

	public void addWalls(Tile[] walls) {
		for (int i = 0; i < walls.length; i++) {
			getTile(walls[i].x, walls[i].y).setWall();
		}
		
	}

	public void addBoxes(Tile[] boxes) {
		for (int i = 0; i < boxes.length; i++) {
			getTile(boxes[i].x, boxes[i].y).setBox();
		}
		
	}

	public void addTargets(Tile[] targets) {
		for (int i = 0; i < targets.length; i++) {
			getTile(targets[i].x, targets[i].y).setTerget();
		}
	}

	public void addPlayer(Tile player) {
		getTile(player.x, player.y).setPlayer();
	}

	public void movePlayer(Action a1) {
		Tile t1 = getPlayer();
		Tile t2 = getTarget(t1, a1);
		t1.player = false;
		t2.player = true;
	}

	Tile getPlayer() {
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.length; j++) {
				Tile t = getTile(i, j);
				if(t.player){
					return t; 
				}
			}
		}
		return null;
	}

	public void moveBox(Tile t1, Action a1) {
		Tile t2 = getTarget(t1, a1);
		if(!t2.box && !t2.wall){
			t1.box = false;
			t2.box = true;
		}
	}

	private Tile getTarget(Tile t1, Action a1) {
		Tile t2 = null;
		switch (a1) {
		case DOWN:
			t2 = getTile(t1.x, t1.y-1);
			break;
		case UP:
			t2 = getTile(t1.x, t1.y+1);
			break;
		case LEFT:
			t2 = getTile(t1.x-1, t1.y);
			break;
		case RIGHT:
			t2 = getTile(t1.x+1, t1.y);
			break;
		default:
			break;
		}
		return t2;
	}

	public boolean performAction(Action a1) {
		movePlayer(a1);
		Tile target = getPlayer();
		if(target.isBox()){
			moveBox(target,a1);
			return true;
		}		
		return false;
	}
	
	@Override
	public String toString() {
		String s ="";
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				if(b[j][i].player){
					s+="P";
				}else if(b[j][i].wall){
					s+="X";
				}else if (b[j][i].target && b[j][i].box) {
					s+="@";
				}else if(b[j][i].target){
					s+="0";
				}else if(b[j][i].box){
					s+="O";
				} else {
					s+=" ";
				}
			}
			s+="\n";
		}
		return s;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Board)){
			return false;
		}
		Board board = (Board)obj;
		if(board.length != this.length || board.width != this.width){
			return false;
		}
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				if(!this.getTile(j, i).equals(board.getTile(j, i))){
					return false;
				}
			}
		}
		return true;
	}
	
}
