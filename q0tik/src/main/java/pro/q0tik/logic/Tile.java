package pro.q0tik.logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
	
	public int x; //i in board[][]
	public int y; //j in board[][]

	private boolean isWhite;
	public boolean getIsWhite() {
		return isWhite;
	}
	
	public boolean isEmpty = true;
	
	private Figure figure = null;
	
	public Tile(boolean color) {
		isWhite = color;
		
		this.setWidth(40);
		this.setHeight(40);
		
		if (isWhite) {
			this.setFill(Color.GRAY);
		} else {
			this.setFill(Color.BLACK);
		}
	}
	
	public void setFigure (Figure f) {
		if (f != null) {
			isEmpty = false;
			figure = f;
			figure.setCenterX(this.getLayoutX() + 20);
			figure.setCenterY(this.getLayoutY() + 20);
			figure.setCenter();
		}
	}
	
	public Figure getFigure() {
		return figure;
	}
	
	public void delFigure() {
		isEmpty = true;
		figure = null;
	}
}
