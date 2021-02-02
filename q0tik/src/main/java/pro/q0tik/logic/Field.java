package pro.q0tik.logic;

import pro.q0tik.GameWindow;

public class Field {
	
	public static void buildBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				GameWindow.board[i][j] = new Tile((i + j) % 2 == 0);
				GameWindow.board[i][j].setLayoutX(40 * i);
				GameWindow.board[i][j].setLayoutY(40 * j);
				GameWindow.board[i][j].x = i;
				GameWindow.board[i][j].y = j;
			}
		}
	}
	
	public static void buildTeams() {
		//white
		for (int i = 0; i < 8; i++) {
			for (int j = 5; j < 8; j++) {
				if (!GameWindow.board[i][j].getIsWhite()) {
					Figure f = new Figure(true);
					GameWindow.whiteTeam.add(f);
					GameWindow.board[i][j].setFigure(f);
				}
			}
		}
		
		//black
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 3; j++) {
				if (!GameWindow.board[i][j].getIsWhite()) {
					Figure f = new Figure(false);
					GameWindow.blackTeam.add(f);
					GameWindow.board[i][j].setFigure(f);
				}
			}
		}
	}
}
