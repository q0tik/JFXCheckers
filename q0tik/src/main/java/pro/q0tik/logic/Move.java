package pro.q0tik.logic;

import java.util.LinkedList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pro.q0tik.GameWindow;
import pro.q0tik.MainApp;

public class Move {
	
	public static void setListeners(Tile enter) {
		
		Figure f = enter.getFigure();
		
		f.setOnMousePressed(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	        	f.setCenter();
	        	f.toFront();
	        }
	    });
		
		f.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				f.setCenterX(event.getSceneX());
				f.setCenterY(event.getSceneY());
			}
		});
		
		f.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				move(getTileByCoord(f.cX, f.cY) , getTileByCoord(event.getSceneX(), event.getSceneY()));
				kill(getTileByCoord(f.cX, f.cY) , getTileByCoord(event.getSceneX(), event.getSceneY()));
			}
		});
	}
	
	private static void move(Tile from, Tile to) {
		
		System.out.println(GameWindow.turn);
		
		Figure f = from.getFigure();
		
		if (GameWindow.turn == f.getIsWhite() && !checkAbilityToKill(GameWindow.turn)) {
			//simple
			if (!f.isKing && to != null) {
				if ((from.y - to.y) == (f.getIsWhite() == true ? 1 : -1) /* 1 4 w -1 4 b */ && Math.abs(from.x - to.x) == 1) {
					try {
						if (to.isEmpty) {
							to.setFigure(f);
							from.delFigure();
							becomeAking(to);
							GameWindow.turn = !GameWindow.turn;
						} else {
							f.setCenterX(f.cX);
							f.setCenterY(f.cY);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					f.setCenterX(f.cX);
					f.setCenterY(f.cY);
				}
			}
			
			//king
			if (f.isKing && to != null) {
				if (Math.abs(from.x - to.x) == Math.abs(from.y - to.y)) {
					try {
						LinkedList<Tile> onPath = new LinkedList<Tile>();
						
						if (!to.isEmpty) {
							onPath.add(to);
						}
						
						for (int i = from.x, j = from.y; i != to.x && j != to.y; 
								i = i + (from.x < to.x ? 1 : -1), j = j + (from.y < to.y ? 1 : -1)) {
							if (!GameWindow.board[i][j].isEmpty) {
								onPath.add(GameWindow.board[i][j]);
							}
						}
						onPath.remove(from);
						
						if (onPath.isEmpty()) {
							to.setFigure(f);
							from.delFigure();
							becomeAking(to);
							GameWindow.turn = !GameWindow.turn;
						} else {
							f.setCenterX(f.cX);
							f.setCenterY(f.cY);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					f.setCenterX(f.cX);
					f.setCenterY(f.cY);
				}
			}
		} else {
			f.setCenterX(f.cX);
			f.setCenterY(f.cY);
		}
	}
	
	private static void kill(Tile from, Tile to) {
		
		boolean wasKilled = false;
		Figure f = from.getFigure();
		
		if (GameWindow.turn == f.getIsWhite()) {
			
			//king
			if (f.isKing && to != null) {
				if (Math.abs(from.x - to.x) == Math.abs(from.y - to.y)) {
					try {
						LinkedList<Tile> onPath = new LinkedList<Tile>();
						
						if (!to.isEmpty) {
							onPath.add(to);
						}
						
						for (int i = from.x, j = from.y; i != to.x && j != to.y; 
								i = i + (from.x < to.x ? 1 : -1), j = j + (from.y < to.y ? 1 : -1)) {
							if (!GameWindow.board[i][j].isEmpty) {
								onPath.add(GameWindow.board[i][j]);
							}
						}
						
						onPath.remove(from);
						
						if (onPath.size() == 1 && onPath.get(0).getFigure().getIsWhite() != from.getFigure().getIsWhite()) {
							to.setFigure(f);
							from.delFigure();
							
							if (onPath.get(0).getFigure().getIsWhite()) {
								GameWindow.whiteTeam.remove(onPath.get(0).getFigure());
							} else {
								GameWindow.blackTeam.remove(onPath.get(0).getFigure());
							}
							
							onPath.get(0).toFront();
							onPath.get(0).getFigure().toBack();
							
							onPath.get(0).delFigure();
							
							wasKilled = true;
						} else {
							f.setCenterX(f.cX);
							f.setCenterY(f.cY);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					f.setCenterX(f.cX);
					f.setCenterY(f.cY);
				}
			} else {
				f.setCenterX(f.cX);
				f.setCenterY(f.cY);
			}
			
			//simple
			if (!f.isKing && to != null) {
				
				Tile between = GameWindow.board[(from.x + to.x) / 2][(from.y + to.y) / 2];
				
				if (Math.abs(from.x - to.x) == Math.abs(from.y - to.y) && Math.abs(from.x - to.x) == 2 && !between.isEmpty) {
					try {
						if (to.isEmpty && between.getFigure().getIsWhite() != f.getIsWhite()) {
							to.setFigure(f);
							from.delFigure();
							becomeAking(to);
							
							if (between.getFigure().getIsWhite()) {
								GameWindow.whiteTeam.remove(between.getFigure());
							} else {
								GameWindow.blackTeam.remove(between.getFigure());
							}
							
							between.toFront();
							between.getFigure().toBack();
							
							between.delFigure();
							
							wasKilled = true;
						} else {
							f.setCenterX(f.cX);
							f.setCenterY(f.cY);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					f.setCenterX(f.cX);
					f.setCenterY(f.cY);
				}
			} else {
				f.setCenterX(f.cX);
				f.setCenterY(f.cY);
			}
			
			
			if (checkGameOver()) {
				System.out.println("game over");
				MainApp.restart();
			}
			
			
			if (!checkAbilityToKill(GameWindow.turn) && wasKilled) {
				GameWindow.turn = !GameWindow.turn;
			}
		}
	}
	
	private static void becomeAking(Tile t) {
		
		Figure f = t.getFigure();

		if (!f.isKing && t.y == (f.getIsWhite() == true ? 0 : 7)) {
			f.isKing = true;
			f.setStroke(Color.BLUE);
			f.setStrokeWidth(5);
		}
	}
	
	private static Tile getTileByCoord(double x, double y) {
		int i = (int) (x / 40);
		int j = (int) (y / 40);
		
		try {
			return GameWindow.board[i][j];
		} catch (Exception e) {
			return null;
		}
		
	}
	
	private static boolean checkAbilityToKill(boolean turn) {
		for (Figure f : (turn == true ? GameWindow.whiteTeam : GameWindow.blackTeam)) {
			Tile from = getTileByCoord(f.cX, f.cY);
			
			LinkedList<Tile> toTileList = new LinkedList<Tile>();
			
			if (!f.isKing) {
				// left top corner
				try {
					toTileList.add(GameWindow.board[from.x - 2][from.y - 2]); 
				} catch (Exception e) { }
				// right top corner
				try {
					toTileList.add(GameWindow.board[from.x + 2][from.y - 2]); 
				} catch (Exception e) { }
				// right bottom corner
				try {
					toTileList.add(GameWindow.board[from.x + 2][from.y + 2]); 
				} catch (Exception e) { }
				// left bottom corner
				try {
					toTileList.add(GameWindow.board[from.x - 2][from.y + 2]); 
				} catch (Exception e) { }
				
				for (Tile to : toTileList) {
					Tile between = GameWindow.board[(from.x + to.x) / 2][(from.y + to.y) / 2];
					
					if (!between.isEmpty) {
						if (to.isEmpty && between.getFigure().getIsWhite() != f.getIsWhite()) {
							return true;
						}
					}
				}
		} else { //TODO: remake this shit!!!
				Tile stopPoint = null;
				
				//rb
				for (int i = from.x + 1, j = from.y + 1; i < 8 && j < 8; i++, j++) {
					try {
						if (!GameWindow.board[i][j].isEmpty) {
							stopPoint = GameWindow.board[i][j];
							break;
						}
					} catch (Exception e) { }
				}
				
				try {
					if (stopPoint.getFigure().getIsWhite() != f.getIsWhite()) {
						if (GameWindow.board[stopPoint.x + 1][stopPoint.y + 1].isEmpty) {
							return true;
						}
					}
				} catch (Exception e) { }
				
				//lb
				for (int i = from.x - 1, j = from.y + 1; i >= 0 && j < 8; i--, j++) {
					try {
						if (!GameWindow.board[i][j].isEmpty) {
							stopPoint = GameWindow.board[i][j];
							break;
						}
					} catch (Exception e) { }
				}
				
				try {
					if (stopPoint.getFigure().getIsWhite() != f.getIsWhite()) {
						if (GameWindow.board[stopPoint.x - 1][stopPoint.y + 1].isEmpty) {
							return true;
						}
					}
				} catch (Exception e) { }
				
				//lt
				for (int i = from.x - 1, j = from.y - 1; i >=0 && j >= 0; i--, j--) {
					try {
						if (!GameWindow.board[i][j].isEmpty) {
							stopPoint = GameWindow.board[i][j];
							break;
						}
					} catch (Exception e) { }
				}
				
				try {
					if (stopPoint.getFigure().getIsWhite() != f.getIsWhite()) {
						if (GameWindow.board[stopPoint.x - 1][stopPoint.y - 1].isEmpty) {
							return true;
						}
					}
				} catch (Exception e) { }
				
				//rt
				for (int i = from.x + 1, j = from.y - 1; i < 8 && j >= 0; i++, j--) {
					try {
						if (!GameWindow.board[i][j].isEmpty) {
							stopPoint = GameWindow.board[i][j];
							break;
						}
					} catch (Exception e) { }
				}
				
				try {
					if (stopPoint.getFigure().getIsWhite() != f.getIsWhite()) {
						if (GameWindow.board[stopPoint.x + 1][stopPoint.y - 1].isEmpty) {
							return true;
						}
					}
				} catch (Exception e) { }
			}
		}
		
		return false;
	}
	
	private static boolean checkGameOver() {
		return (GameWindow.blackTeam.isEmpty() || GameWindow.whiteTeam.isEmpty() ? true : false);
	}
}
