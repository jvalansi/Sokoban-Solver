import java.awt.Point;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;


public class SokobanSolver {


	private static int maxActions = 40;
	
	
	private static void solve(Board board) {

//		try each possible action
		ArrayList<Board> b = new ArrayList<Board>();
		ArrayList<Action> a = new ArrayList<Action>();
		ArrayList<Tile> t = new ArrayList<Tile>();
		b.add(board);
		t.add(board.getPlayer());
		tryActions(a,b,t);
		
	}


	private static void tryActions(ArrayList<Action> a, ArrayList<Board> b, ArrayList<Tile> t) {
		Board lastBoard = b.get(b.size()-1);
//		if not valid - stop
		if(!isValid(lastBoard)){
			return;
//		if solved - show
		} else if(isSolved(lastBoard)){
			Date d = new Date();
			System.out.println(d);
			System.out.println(" The Answer is:");
			System.out.println(a);
			for (int i = 0; i < b.size(); i++) {
				System.out.println(b.get(i));
				System.out.println("-----------------------------");
			}
			return;
//		action is useless
		} else if (wentBack(b)) {
			return;
//		action array size is > max size stop
		} else if(a.size() > maxActions){
			return;
		}
		for (int i = 0; i < Action.values().length; i++) {
			ArrayList<Action> a1 = new ArrayList<Action>(a);
			ArrayList<Tile> t1 = new ArrayList<Tile>(t);
			ArrayList<Board> b1 = new ArrayList<Board>(b);
			Action action = Action.values()[i];
			Board board = new Board(lastBoard);
			boolean movedBox = board.performAction(action);
			if(movedBox){
				t1.clear();
			}
			t1.add(board.getPlayer());
			a1.add(action);
			b1.add(board);
			tryActions(a1, b1, t1);
		}
	}


	private static boolean wentBack(ArrayList<Board> b) {
		Board lastBoard = b.get(b.size()-1);
		for (int i = 0; i < b.size()-1; i++) {
			if(b.get(i).equals(lastBoard)){
				return true;
			}
		}
//		for (int i = 0; i < b.size(); i++) {
//			for (int j = 0; j < b.size(); j++) {
//				if(i == j){
//					continue;
//				}else if(b.get(i).x == b.get(j).x && b.get(i).y == b.get(j).y){
//					return true;
//				}
//			}
//		}
		return false;
	}

	private static boolean isValid(Board b) {
		for (int i = 0; i < b.getWidth(); i++) {
			for (int j = 0; j < b.getLength(); j++) {
				Tile t = b.getTile(i, j);
				if(t.player && t.box ){
					return false;
				} else if (t.player && t.wall) {
					return false;
				} else if (t.box && t.wall) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isSolved(Board b) {
		for (int i = 0; i < b.getWidth(); i++) {
			for (int j = 0; j < b.getLength(); j++) {
				Tile t = b.getTile(i, j);
				if(t.box && !t.target){
					return false;
				} 
			}
		}
		return true;
	}
	
	private static void printSolution(Action[] a, Board b) {
		for (int i = 0; i < a.length; i++) {
			b.performAction(a[i]);
			System.out.println(b);
			System.out.println("---------------");
		}		
	}

	public static void main(String[] args) {
		Board b = new Board(8,6);
		Tile[] walls = {new Tile(2,0),new Tile(3,0),new Tile(4,0),new Tile(5,0),new Tile(6,0),new Tile(7,0),
				new Tile(0,1),new Tile(1,1),new Tile(2,1),new Tile(7,1),
				new Tile(0,2),new Tile(7,2),
				new Tile(0,3),new Tile(6,3),new Tile(7,3),
				new Tile(0,4),new Tile(4,4),new Tile(5,4),new Tile(6,4),
				new Tile(0,5),new Tile(1,5),new Tile(2,5),new Tile(3,5),new Tile(4,5)};	
		b.addWalls(walls);
		Tile[] boxes = {new Tile(3,2),new Tile(4,2),new Tile(5,2),new Tile(2,4)};
		b.addBoxes(boxes);
		Tile[] targets = {new Tile(5,1),new Tile(3,3),new Tile(4,3),new Tile(3,4)};
		b.addTargets(targets);
		Tile player = new Tile(3,1);
		b.addPlayer(player);
		Action[] a = {Action.RIGHT, Action.UP, Action.DOWN, Action.RIGHT, Action.RIGHT,Action.UP,Action.LEFT,Action.UP,
				Action.LEFT,Action.LEFT,Action.RIGHT,Action.RIGHT,Action.DOWN,Action.RIGHT,Action.DOWN,Action.LEFT,Action.LEFT,Action.LEFT,Action.UP,
				Action.LEFT,Action.LEFT,Action.UP,Action.UP,Action.RIGHT,Action.LEFT,Action.DOWN,Action.LEFT,Action.LEFT,Action.LEFT,Action.LEFT,Action.UP,Action.UP,Action.RIGHT,Action.DOWN,
				Action.LEFT,Action.DOWN,Action.RIGHT,Action.RIGHT,Action.DOWN,Action.RIGHT,Action.UP};
		Date d = new Date(); 
		System.out.println(d);
		System.out.print(b);
		System.out.println("---------------");
//		printSolution(a,b);
		solve(b);
	}




	
}
