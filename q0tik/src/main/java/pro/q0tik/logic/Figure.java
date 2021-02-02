package pro.q0tik.logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Figure extends Circle {
	
	public double cX;
	public double cY;
	
	private boolean isWhite;
	public boolean getIsWhite() {
		return isWhite;
	}
	
	public boolean isKing = false;
	
	public Figure(boolean color) {
		isWhite = color;
		
		this.setRadius(16);
		
		if (isWhite) {
			this.setFill(Color.LIGHTBLUE);
		} else {
			this.setFill(Color.DARKGREEN);
		}
	}
	
	public Figure(Figure figure) {
		isWhite = figure.getIsWhite();
		
		this.setFill(figure.getFill());
		
		this.setRadius(figure.getRadius());
		
		this.setCenterX(figure.getCenterX());
		
		this.setCenterY(figure.getCenterY());
		
		setCenter();
	}
	
	public void setCenter() {
		cX = this.getCenterX();
		cY = this.getCenterY();
	}
	
	@Override
	public boolean equals(Object o) {	
		if (o != null && o.getClass() == this.getClass()) {
			Figure f = (Figure) o;
			
			if (f.isWhite == this.isWhite 
					&& f.getCenterX() == this.getCenterX()
					&& f.getCenterY() == this.getCenterY())
			{
				return true;
			}
		}

		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 144;
		int result = 1;
		
		result = prime * result + ((this == null) ? 0 : this.getFill().hashCode()); 
		result = prime * result + ((this == null) ? 0 : (int) this.getCenterX());
		result = prime * result + ((this == null) ? 0 : (int) this.getCenterY());
		
		return result;
	}
}
