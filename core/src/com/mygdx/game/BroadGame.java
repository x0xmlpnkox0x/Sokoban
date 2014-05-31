package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Gdx;
import com.mygdx.Option.OptionGame;


public class BroadGame {
	
	private int[][] broad;
	private int posBroadX;
	private int posBroadY;
	
	public BroadGame() {
		broad = new int[OptionGame.HEIGHT_BROAD][OptionGame.WIDTH_BROAD];
		reset(); 
	}
	
	private void reset() {
		for (int i = 0; i < OptionGame.HEIGHT_BROAD; i++) {
			for (int j = 0; j < OptionGame.WIDTH_BROAD; j++) {
				broad[i][j] = OptionGame.NULL;
			}
		}
	}
	
	public void setBroad(String map, int width, int height) {
		if ((OptionGame.WIDTH_BROAD - width)%2 != 0) {
			setPosBroad((OptionGame.HEIGHT_BROAD - height)/2, (OptionGame.WIDTH_BROAD - width)/2+1);
		} else {
			setPosBroad((OptionGame.HEIGHT_BROAD - height)/2, (OptionGame.WIDTH_BROAD - width)/2);
		}
		int x = getPosBroadX();
		int y = getPosBroadY();
		
		map = map.replace("\n", "n");
		Gdx.app.log("map", "" + map);
		String[] mapArray = map.split("");
		for (int i = 1; i < mapArray.length; i++) {
			if (mapArray[i].equals(OptionGame.STR_WALL)) {
				broad[x][y] = OptionGame.WALL;
				Gdx.app.log("broad"+x+"."+y, ""+broad[x][y]);
				y ++;

			}else if (mapArray[i].equals(OptionGame.STR_GOAL)) {
				broad[x][y] = OptionGame.GOAL;
				Gdx.app.log("broad"+x+"."+y, ""+broad[x][y]);

				y ++;

			}else if (mapArray[i].equals(OptionGame.STR_BOX)) {

				broad[x][y] = OptionGame.BOX;
				Gdx.app.log("broad"+x+"."+y, ""+broad[x][y]);

				y ++;
			}else if (mapArray[i].equals(OptionGame.STR_PERSON)) {
				broad[x][y] = OptionGame.PERSON;
				Gdx.app.log("broad"+x+"."+y, ""+broad[x][y]);

				y ++;
			}else if (mapArray[i].equals(OptionGame.STR_BOX_GOAL)) {
				broad[x][y] = OptionGame.PLACE_BOX;
				Gdx.app.log("broad"+x+"."+y, ""+broad[x][y]);

				y ++;
			}else if (mapArray[i].equals(OptionGame.STR_PERSON_GOAL)) {
				broad[x][y] = OptionGame.PERSON_ON_GOAL;
				Gdx.app.log("broad"+x+"."+y, ""+broad[x][y]);

				y ++;
			}else if (mapArray[i].equals(OptionGame.STR_FLOOR)) {
				broad[x][y] = OptionGame.FLOOR;
				Gdx.app.log("broad"+x+"."+y, ""+broad[x][y]);

				y ++;
			}else if (mapArray[i].equals(OptionGame.STR_BREAK_LINE)) {
				
				x ++;
				y = getPosBroadY();
			}else {
				y++;
			}
			
//			switch (mapArray[i]) {
//			case OptionGame.STR_WALL:
//				broad[x][y] = OptionGame.WALL;
//				Gdx.app.log("broad"+x+"."+y, ""+broad[x][y]);
//
//				y ++;
//				break;
//
//			case OptionGame.STR_GOAL:
//				broad[x][y] = OptionGame.GOAL;
//				Gdx.app.log("broad"+x+"."+y, ""+broad[x][y]);
//
//				y ++;
//				break;
//				
//			case OptionGame.STR_BOX:
//
//				broad[x][y] = OptionGame.BOX;
//				Gdx.app.log("broad"+x+"."+y, ""+broad[x][y]);
//
//				y ++;
//				break;
//			
//			case OptionGame.STR_PERSON:
//				
//				broad[x][y] = OptionGame.PERSON;
//				Gdx.app.log("broad"+x+"."+y, ""+broad[x][y]);
//
//				y ++;
//				break;
//				
//			case OptionGame.STR_BOX_GOAL:
//				broad[x][y] = OptionGame.PLACE_BOX;
//				Gdx.app.log("broad"+x+"."+y, ""+broad[x][y]);
//
//				y ++;
//				break;
//				
//			case OptionGame.STR_PERSON_GOAL:
//				broad[x][y] = OptionGame.PERSON_ON_GOAL;
//				Gdx.app.log("broad"+x+"."+y, ""+broad[x][y]);
//
//				y ++;
//				break;
//				
//			case OptionGame.STR_FLOOR:
//				broad[x][y] = OptionGame.FLOOR;
//				Gdx.app.log("broad"+x+"."+y, ""+broad[x][y]);
//
//				y ++;
//				break;
//				
//			case OptionGame.STR_BREAK_LINE:
//				
//				x ++;
//				y = getPosBroadY();
//				break;
//			
//			default:
//				y ++;
//				break;
//			}
		}
	}
	
	private void setPosBroad(int x, int y) {
		this.posBroadX = x;
		this.posBroadY = y;
	}
	
	private int getPosBroadX() {
		return this.posBroadX;
	}
	
	private int getPosBroadY() {
		return this.posBroadY;
	}
	
	public int checkScreen(int x, int y){
		return broad[x][y];
	}
	
//	public void moveDownUndo(Person person) {
//		int x = person.getPosX();
//		int y = person.getPosY();
//		if (OptionGame.GOAL == broad[x-1][y]) {
//			broad[x-1][y] = OptionGame.PERSON_ON_GOAL;
//			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
//				broad[x][y] = OptionGame.GOAL;
//			} else {
//				broad[x][y] = OptionGame.FLOOR;
//			}
//		} else {
//			broad[x-1][y] = OptionGame.PERSON;
//			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
//				broad[x][y] = OptionGame.GOAL;
//			} else {
//				broad[x][y] = OptionGame.FLOOR;
//			}
//		}
//		person.setPosX(x-1);
//		person.setPosY(y);
//	}
	
	public boolean checkMoveDown(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.WALL == broad[x-1][y]) {
			return false;
		}
		if (OptionGame.GOAL == broad[x-1][y]) {
			broad[x-1][y] = OptionGame.PERSON_ON_GOAL;
			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
				broad[x][y] = OptionGame.GOAL;
			} else {
				broad[x][y] = OptionGame.FLOOR;
			}
		} else {
			broad[x-1][y] = OptionGame.PERSON;
			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
				broad[x][y] = OptionGame.GOAL;
			} else {
				broad[x][y] = OptionGame.FLOOR;
			}
		}
		person.setPosX(x-1);
		person.setPosY(y);
		return true;
	}
	
	public void moveTopUndo(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.GOAL == broad[x+1][y]) {
			broad[x+1][y] = OptionGame.PERSON_ON_GOAL;
			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
				broad[x][y] = OptionGame.GOAL;
			} else {
				broad[x][y] = OptionGame.FLOOR;
			}
		} else {
			broad[x+1][y] = OptionGame.PERSON;
			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
				broad[x][y] = OptionGame.GOAL;
			} else {
				broad[x][y] = OptionGame.FLOOR;
			}
		}
		person.setPosX(x+1);
		person.setPosY(y);
	}
	
	public boolean checkMoveTop(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.WALL == broad[x+1][y]) {
			return false;
		}
		if (OptionGame.GOAL == broad[x+1][y]) {
			broad[x+1][y] = OptionGame.PERSON_ON_GOAL;
			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
				broad[x][y] = OptionGame.GOAL;
			} else {
				broad[x][y] = OptionGame.FLOOR;
			}
		} else {
			broad[x+1][y] = OptionGame.PERSON;
			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
				broad[x][y] = OptionGame.GOAL;
			} else {
				broad[x][y] = OptionGame.FLOOR;
			}
		}
		person.setPosX(x+1);
		person.setPosY(y);
		return true;
	}
	
	public void moveRightUndo(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.GOAL == broad[x][y-1]) {
			broad[x][y-1] = OptionGame.PERSON_ON_GOAL;
			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
				broad[x][y] = OptionGame.GOAL;
			} else {
				broad[x][y] = OptionGame.FLOOR;
			}
		} else {
			broad[x][y-1] = OptionGame.PERSON;
			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
				broad[x][y] = OptionGame.GOAL;
			} else {
				broad[x][y] = OptionGame.FLOOR;
			}
		}
	}
	
	public boolean checkMoveLeft(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.WALL == broad[x][y-1]) {
			return false;
		}
		if (OptionGame.GOAL == broad[x][y-1]) {
			broad[x][y-1] = OptionGame.PERSON_ON_GOAL;
			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
				broad[x][y] = OptionGame.GOAL;
			} else {
				broad[x][y] = OptionGame.FLOOR;
			}
		} else {
			broad[x][y-1] = OptionGame.PERSON;
			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
				broad[x][y] = OptionGame.GOAL;
			} else {
				broad[x][y] = OptionGame.FLOOR;
			}
		}
		person.setPosX(x);
		person.setPosY(y-1);
		return true;
	}
	
	public void moveLeftUndo(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.GOAL == broad[x][y+1]) {
			broad[x][y+1] = OptionGame.PERSON_ON_GOAL;
			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
				broad[x][y] = OptionGame.GOAL;
			} else {
				broad[x][y] = OptionGame.FLOOR;
			}
		} else {
			broad[x][y+1] = OptionGame.PERSON;
			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
				broad[x][y] = OptionGame.GOAL;
			} else {
				broad[x][y] = OptionGame.FLOOR;
			}
		}
		person.setPosX(x);
		person.setPosY(y+1);
	}
	
	public boolean checkMoveRight(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.WALL == broad[x][y+1]) {
			return false;
		}
		if (OptionGame.GOAL == broad[x][y+1]) {
			broad[x][y+1] = OptionGame.PERSON_ON_GOAL;
			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
				broad[x][y] = OptionGame.GOAL;
			} else {
				broad[x][y] = OptionGame.FLOOR;
			}
		} else {
			broad[x][y+1] = OptionGame.PERSON;
			if (OptionGame.PERSON_ON_GOAL == broad[x][y]) {
				broad[x][y] = OptionGame.GOAL;
			} else {
				broad[x][y] = OptionGame.FLOOR;
			}
		}
		person.setPosX(x);
		person.setPosY(y+1);
		return true;
	}
	
	public boolean checkBoxBottomPerson(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.BOX == broad[x-1][y]
				|| OptionGame.PLACE_BOX == broad[x-1][y]) {
			return true;
		}
		return false;
	}
	
	public boolean checkBoxTopPerson(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.BOX == broad[x+1][y]
				|| OptionGame.PLACE_BOX == broad[x+1][y]) {
			return true;
		}
		return false;
	}
	
	public boolean checkBoxLeftPerson(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.BOX == broad[x][y-1]
				|| OptionGame.PLACE_BOX == broad[x][y-1]) {
			return true;
		}
		return false;
	}
	
	public boolean checkBoxRightPerson(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.BOX == broad[x][y+1]
				|| OptionGame.PLACE_BOX == broad[x][y+1]) {
			return true;
		}
		return false;
	}
	
	public boolean checkMoveBoxDown(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.FLOOR == broad[x-2][y]
				|| OptionGame.GOAL == broad[x-2][y]) {
			return true;
		}
		return false;
	}
	
	public boolean checkMoveBoxTop(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.FLOOR == broad[x+2][y]
				|| OptionGame.GOAL == broad[x+2][y]) {
			return true;
		}
		return false;
	}
	
	public boolean checkMoveBoxLeft(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.FLOOR == broad[x][y-2]
				|| OptionGame.GOAL == broad[x][y-2]) {
			return true;
		}
		return false;
	}
	
	public boolean checkMoveBoxRight(Person person) {
		int x = person.getPosX();
		int y = person.getPosY();
		if (OptionGame.FLOOR == broad[x][y+2]
				|| OptionGame.GOAL == broad[x][y+2]) {
			return true;
		}
		return false;
	}
	
	public void moveBottomUndoBox(Person person, BoxGame box) {
		int xP = person.getPosX();
		int yP = person.getPosY();
		int xB = box.getPosX();
		int yB = box.getPosY();
		if (OptionGame.GOAL == broad[xP-1][yP]) {
			broad[xP-1][yP] = OptionGame.PERSON_ON_GOAL;
			if (OptionGame.PLACE_BOX == broad[xP][yP]) {
				broad[xP][yP] = OptionGame.PLACE_BOX;
			} else {
				broad[xP][yP] = OptionGame.BOX;
 			}
			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
				broad[xB][yB] = OptionGame.GOAL;
			} else {
				broad[xB][yB] = OptionGame.FLOOR;
			}
		} else {
			broad[xP-1][yP] = OptionGame.PERSON;
			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
				broad[xP][yP] = OptionGame.PLACE_BOX;
			} else {
				broad[xP][yP] = OptionGame.BOX;
 			}
			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
				broad[xB][yB] = OptionGame.GOAL;
			} else {
				broad[xB][yB] = OptionGame.FLOOR;
			}
		}
		person.setPosX(xP-1);
		person.setPosY(yP);
		box.setPosX(xB-1);
		box.setPosY(yB);
	}
	
	public void moveWidthBoxBottom(Person person, BoxGame box) {
		int xP = person.getPosX();
		int yP = person.getPosY();
		int xB = box.getPosX();
		int yB = box.getPosY();
		if (OptionGame.GOAL == broad[xB-1][yB]) {
			broad[xB-1][yB] = OptionGame.PLACE_BOX;
			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
				broad[xB][yB] = OptionGame.PERSON_ON_GOAL;
			} else {
				broad[xB][yB] = OptionGame.PERSON;
 			}
			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
				broad[xP][yP] = OptionGame.GOAL;
			} else {
				broad[xP][yP] = OptionGame.FLOOR;
			}
		} else {
			broad[xB-1][yB] = OptionGame.BOX;
			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
				broad[xB][yB] = OptionGame.PERSON_ON_GOAL;
			} else {
				broad[xB][yB] = OptionGame.PERSON;
 			}
			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
				broad[xP][yP] = OptionGame.GOAL;
			} else {
				broad[xP][yP] = OptionGame.FLOOR;
			}
		}
		person.setPosX(xP-1);
		person.setPosY(yP);
		box.setPosX(xB-1);
		box.setPosY(yB);
	}
	
	public void moveTopUndoBox(Person person, BoxGame box) {
		int xP = person.getPosX();
		int yP = person.getPosY();
		int xB = box.getPosX();
		int yB = box.getPosY();
		if (OptionGame.GOAL == broad[xP+1][yP]) {
			broad[xP+1][yP] = OptionGame.PERSON_ON_GOAL;
			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
				broad[xP][yP] = OptionGame.PLACE_BOX;
			} else {
				broad[xP][yP] = OptionGame.BOX;
 			}
			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
				broad[xB][yB] = OptionGame.GOAL;
			} else {
				broad[xB][yB] = OptionGame.FLOOR;
			}
		} else {
			broad[xP+1][yP] = OptionGame.PERSON;
			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
				broad[xP][yP] = OptionGame.PLACE_BOX;
			} else {
				broad[xP][yP] = OptionGame.BOX;
 			}
			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
				broad[xB][yB] = OptionGame.GOAL;
			} else {
				broad[xB][yB] = OptionGame.FLOOR;
			}
		}
		person.setPosX(xP+1);
		person.setPosY(yP);
		box.setPosX(xB+1);
		box.setPosY(yB);
	}
	
	public void moveWidthBoxTop(Person person, BoxGame box) {
		int xP = person.getPosX();
		int yP = person.getPosY();
		int xB = box.getPosX();
		int yB = box.getPosY();
		if (OptionGame.GOAL == broad[xB+1][yB]) {
			broad[xB+1][yB] = OptionGame.PLACE_BOX;
			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
				broad[xB][yB] = OptionGame.PERSON_ON_GOAL;
			} else {
				broad[xB][yB] = OptionGame.PERSON;
 			}
			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
				broad[xP][yP] = OptionGame.GOAL;
			} else {
				broad[xP][yP] = OptionGame.FLOOR;
			}
		} else {
			broad[xB+1][yB] = OptionGame.BOX;
			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
				broad[xB][yB] = OptionGame.PERSON_ON_GOAL;
			} else {
				broad[xB][yB] = OptionGame.PERSON;
 			}
			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
				broad[xP][yP] = OptionGame.GOAL;
			} else {
				broad[xP][yP] = OptionGame.FLOOR;
			}
		}
		person.setPosX(xP+1);
		person.setPosY(yP);
		box.setPosX(xB+1);
		box.setPosY(yB);
	}
	
	public void moveRightUndoBox(Person person, BoxGame box) {
		int xP = person.getPosX();
		int yP = person.getPosY();
		int xB = box.getPosX();
		int yB = box.getPosY();
		if (OptionGame.GOAL == broad[xP][yP - 1]) {
			broad[xP][yP - 1] = OptionGame.PERSON_ON_GOAL;
			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
				broad[xP][yP] = OptionGame.PLACE_BOX;
			} else {
				broad[xP][yP] = OptionGame.BOX;
			}
			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
				broad[xB][yB] = OptionGame.GOAL;
			} else {
				broad[xB][yB] = OptionGame.FLOOR;
			}
		} else {
			broad[xP][yP-1] = OptionGame.PERSON;
			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
				broad[xP][yP] = OptionGame.PLACE_BOX;
			} else {
				broad[xP][yP] = OptionGame.BOX;
 			}
			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
				broad[xB][yB] = OptionGame.GOAL;
			} else {
				broad[xB][yB] = OptionGame.FLOOR;
			}
		}
		person.setPosX(xP);
		person.setPosY(yP-1);
		box.setPosX(xB);
		box.setPosY(yB-1);
	}
	
	public void moveWidthBoxLeft(Person person, BoxGame box) {
		int xP = person.getPosX();
		int yP = person.getPosY();
		int xB = box.getPosX();
		int yB = box.getPosY();
		if (OptionGame.GOAL == broad[xB][yB-1]) {
			broad[xB][yB-1] = OptionGame.PLACE_BOX;
			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
				broad[xB][yB] = OptionGame.PERSON_ON_GOAL;
			} else {
				broad[xB][yB] = OptionGame.PERSON;
 			}
			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
				broad[xP][yP] = OptionGame.GOAL;
			} else {
				broad[xP][yP] = OptionGame.FLOOR;
			}
		} else {
			broad[xB][yB-1] = OptionGame.BOX;
			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
				broad[xB][yB] = OptionGame.PERSON_ON_GOAL;
			} else {
				broad[xB][yB] = OptionGame.PERSON;
 			}
			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
				broad[xP][yP] = OptionGame.GOAL;
			} else {
				broad[xP][yP] = OptionGame.FLOOR;
			}
		}
		person.setPosX(xP);
		person.setPosY(yP-1);
		box.setPosX(xB);
		box.setPosY(yB-1);
	}
	
//	public void moveLeftUndoBox(Person person, BoxGame box) {
//		int xP = person.getPosX();
//		int yP = person.getPosY();
//		int xB = box.getPosX();
//		int yB = box.getPosY();
//		if (OptionGame.GOAL == broad[xP][yP+1]) {
//			broad[xP][yP+1] = OptionGame.PLACE_BOX;
//			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
//				broad[xP][yP] = OptionGame.PLACE_BOX;
//			} else {
//				broad[xP][yP] = OptionGame.BOX;
// 			}
//			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
//				broad[xB][yB] = OptionGame.GOAL;
//			} else {
//				broad[xB][yB] = OptionGame.FLOOR;
//			}
//		} else {
//			broad[xP][yP+1] = OptionGame.PERSON;
//			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
//				broad[xP][yP] = OptionGame.PLACE_BOX;
//			} else {
//				broad[xP][yP] = OptionGame.BOX;
// 			}
//			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
//				broad[xB][yB] = OptionGame.GOAL;
//			} else {
//				broad[xB][yB] = OptionGame.FLOOR;
//			}
//		}
//		person.setPosX(xP);
//		person.setPosY(yP+1);
//		box.setPosX(xB);
//		box.setPosY(yB+1);
//	}
	
	public void moveWidthBoxRight(Person person, BoxGame box) {
		int xP = person.getPosX();
		int yP = person.getPosY();
		int xB = box.getPosX();
		int yB = box.getPosY();
		if (OptionGame.GOAL == broad[xB][yB+1]) {
			broad[xB][yB+1] = OptionGame.PLACE_BOX;
			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
				broad[xB][yB] = OptionGame.PERSON_ON_GOAL;
			} else {
				broad[xB][yB] = OptionGame.PERSON;
 			}
			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
				broad[xP][yP] = OptionGame.GOAL;
			} else {
				broad[xP][yP] = OptionGame.FLOOR;
			}
		} else {
			broad[xB][yB+1] = OptionGame.BOX;
			if (OptionGame.PLACE_BOX == broad[xB][yB]) {
				broad[xB][yB] = OptionGame.PERSON_ON_GOAL;
			} else {
				broad[xB][yB] = OptionGame.PERSON;
 			}
			if (OptionGame.PERSON_ON_GOAL == broad[xP][yP]) {
				broad[xP][yP] = OptionGame.GOAL;
			} else {
				broad[xP][yP] = OptionGame.FLOOR;
			}
		}
		person.setPosX(xP);
		person.setPosY(yP+1);
		box.setPosX(xB);
		box.setPosY(yB+1);
	}
	
	public boolean checkComplete() {
		for (int i = 0; i < OptionGame.HEIGHT_BROAD; i++) {
			for (int j = 0; j < OptionGame.WIDTH_BROAD; j++) {
				if (OptionGame.BOX == broad[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
}
