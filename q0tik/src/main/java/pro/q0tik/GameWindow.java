package pro.q0tik;

import java.util.LinkedList;

import javafx.scene.layout.Pane;
import pro.q0tik.logic.*;

public class GameWindow extends Pane {
	
	@SuppressWarnings("exports")
	public static Tile[][] board = new Tile[8][8];
	@SuppressWarnings("exports")
	public static LinkedList<Figure> whiteTeam = new LinkedList<Figure>();
	@SuppressWarnings("exports")
	public static LinkedList<Figure> blackTeam = new LinkedList<Figure>();
	
	public static boolean turn = true;
	
	public GameWindow() {
		initField();
		
		this.setWidth(320);
		this.setHeight(320);
	}
	
	private boolean initField() {
		try {
			Field.buildBoard();
			Field.buildTeams();
			
			for (Tile[] t : GameWindow.board) {
				for (Tile a : t) {
					this.getChildren().add(a);
					a.toBack();
					if (a.getFigure() != null) {
						this.getChildren().add(a.getFigure());
						Move.setListeners(a);
					}
				}
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
