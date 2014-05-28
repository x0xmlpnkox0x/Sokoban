package com.mygdx.screen;

import java.util.ArrayList;
import java.util.Stack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.Abstract.AbstractScreen;
import com.mygdx.Abstract.ButtonGame;
import com.mygdx.Abstract.MenuCreator;
import com.mygdx.Asset.Assets;
import com.mygdx.Option.OptionGame;
import com.mygdx.game.BoxGame;
import com.mygdx.game.BroadGame;
import com.mygdx.game.Goal;
import com.mygdx.game.Level;
import com.mygdx.game.Person;

public class TheWareHouse extends AbstractScreen {

	private BroadGame broad;
	private Level level;
	private int currentLevel;
	private Person person;
	private ArrayList lstBox = new ArrayList();
	private ArrayList lstGoal = new ArrayList();
	private boolean BottomPress = false;
	private boolean TopPress = false;
	private boolean LeftPress = false;
	private boolean RightPress = false;
	private boolean actionComplete = true;

	private boolean completeCheck = false;
	private boolean activeTouch = true;
	private boolean firstTouch = false;
	private boolean lastTouch = false;
	private float firstPos_X = 0;
	private float firstPos_Y = 0;
	private float lastPos_X = 0;
	private float lastPos_Y = 0;
	private boolean processTouch = false;
	private boolean MOVE_LEFT = false;
	private boolean MOVE_RIGHT = false;
	private boolean MOVE_TOP = false;
	private boolean MOVE_DOWN = false;
	private int count = 0;

	private float firstTime;
	private float secondTime;

	private Image imglevel;
	private ButtonGame levelButton;
	private ButtonGame btnUndo;
	private ButtonGame btnRedo;
	private ButtonGame btnRefresh;
	private ButtonGame btnvolume;
	private Image imgtime;

	private Music m_run = Gdx.audio.newMusic(Gdx.files
			.internal("music/run.mp3"));
	private Music m_runbox = Gdx.audio.newMusic(Gdx.files
			.internal("music/run_box.mp3"));
	private Music Winner = Gdx.audio.newMusic(Gdx.files
			.internal("music/Winner.mp3"));

	private Stack stack = new Stack();
	private boolean touchUndo = false;

	public TheWareHouse(Game game, String screenName, Level level, int number) {
		super(game, screenName);
		this.level = level;
		this.currentLevel = number;
		initial();
		setUpGameElements();
		setUpButtons();
	}

	private void initial() {
		imglevel = new Image(Assets.imgLevel);
		imglevel.setPosition(20, 342);
		getStage().addActor(imglevel);
		
		imgtime = new Image(Assets.imgtime);
		imgtime.setPosition(190, 350);
		
		getStage().addActor(imgtime);
		
		levelButton = MenuCreator.createCustomGameButton(Assets.imgLbLevel,
				Assets.imgLbLevel);
		int setText = currentLevel + 1;
		levelButton.setText(Assets.font5, "" + setText, true);
		levelButton.setPosition(115, 350);
		getStage().addActor(levelButton);

		btnUndo = MenuCreator.createCustomGameButton(Assets.btnMoveLeft,
				Assets.btnMoveLeft);
		btnUndo.setSize(35, 35);
		btnUndo.setPosition(OptionGame.SCREEN_WIDTH - 220,
				OptionGame.SCREEN_HEIGHT - 38);
		btnUndo.addListener(new ActorGestureListener() {

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				super.touchUp(event, x, y, pointer, button);
				// if (!touchUndo) {
				// int temp = (int)stack.pop();
				// if (temp == OptionGame.RIGHT) {
				// moveRightUndo();
				// } else if (temp == OptionGame.RIGHT_BOX) {
				// moveRightUndoBox();
				// } else if (temp == OptionGame.LEFT) {
				// moveLeftUndo();
				// } else if (temp == OptionGame.LEFT_BOX) {
				// moveLeftUndoBox();
				// } else if (temp == OptionGame.TOP) {
				// moveTopUndo();
				// } else if (temp == OptionGame.TOP_BOX) {
				// moveTopUndoBox();
				// } else if (temp == OptionGame.BOTTOM) {
				// moveBottomUndo();
				// } else if (temp == OptionGame.BOTTOM_BOX) {
				// moveBottomUndoBox();
				// }
				// }
			}

			private void moveBottomUndoBox() {
				for (int iBox = 0; iBox < lstBox.size(); iBox++) {
					BoxGame box = (BoxGame) lstBox.get(iBox);
					if (box.getPosX() == person.getPosX() - 1
							&& box.getPosY() == person.getPosY()) {
						broad.moveTopUndoBox(person, box);
						person.setAnimation(Assets.AnimPersonPushBottom, true,
								true);
						box.actionMoveTo(box.getPosY() * 40,
								box.getPosX() * 40, 0.5f);
						person.actionMoveTo(person.getPosY() * 40,
								person.getPosX() * 40, 0.5f);
						BottomPress = true;
						m_runbox.play();
						break;
					}
				}
			}

			private void moveBottomUndo() {
				broad.moveTopUndo(person);
				person.setAnimation(Assets.AnimPersonRunBottom, true, true);
				person.actionMoveTo(person.getPosY() * 40,
						person.getPosX() * 40, 0.5f);
				BottomPress = true;
				m_run.play();
			}

			private void moveTopUndoBox() {
				for (int iBox = 0; iBox < lstBox.size(); iBox++) {
					BoxGame box = (BoxGame) lstBox.get(iBox);
					if (box.getPosX() == person.getPosX() + 1
							&& box.getPosY() == person.getPosY()) {
						broad.moveBottomUndoBox(person, box);
						person.setAnimation(Assets.AnimPersonPushTop, true,
								true);
						box.actionMoveTo(box.getPosY() * 40,
								box.getPosX() * 40, 0.5f);
						person.actionMoveTo(person.getPosY() * 40,
								person.getPosX() * 40, 0.5f);
						TopPress = true;
						m_runbox.play();
						break;
					}
				}
			}

			private void moveTopUndo() {
				broad.moveDownUndo(person);
				person.setAnimation(Assets.AnimPersonRunTop, true, true);
				person.actionMoveTo(person.getPosY() * 40,
						person.getPosX() * 40, 0.5f);
				TopPress = true;
				m_run.play();
			}

			private void moveLeftUndoBox() {
				for (int iBox = 0; iBox < lstBox.size(); iBox++) {
					BoxGame box = (BoxGame) lstBox.get(iBox);
					if (box.getPosX() == person.getPosX()
							&& box.getPosY() == person.getPosY() - 1) {
						broad.moveLeftUndoBox(person, box);
						person.setAnimation(Assets.AnimPersonPushLeft, true,
								true);
						box.actionMoveTo(box.getPosY() * 40,
								box.getPosX() * 40, 0.5f);
						person.actionMoveTo(person.getPosY() * 40,
								person.getPosX() * 40, 0.5f);
						LeftPress = true;
						m_runbox.play();
						break;
					}
				}
			}

			private void moveLeftUndo() {
				broad.checkMoveRight(person);
				person.setAnimation(Assets.AnimPersonRunLeft, true, true);
				person.actionMoveTo(person.getPosY() * 40,
						person.getPosX() * 40, 0.5f);
				LeftPress = true;
				m_run.play();
			}

			private void moveRightUndoBox() {
				for (int iBox = 0; iBox < lstBox.size(); iBox++) {
					BoxGame box = (BoxGame) lstBox.get(iBox);
					if (box.getPosX() == person.getPosX()
							&& box.getPosY() == person.getPosY() + 1) {
						broad.moveRightUndoBox(person, box);
						person.setAnimation(Assets.AnimPersonPushRight, true,
								true);
						box.actionMoveTo(box.getPosY() * 40,
								box.getPosX() * 40, 0.5f);
						person.actionMoveTo(person.getPosY() * 40,
								person.getPosX() * 40, 0.5f);
						RightPress = true;
						m_runbox.play();
						break;
					}
				}
			}

			private void moveRightUndo() {
				// TODO Auto-generated method stub
				broad.moveRightUndo(person);
				person.setAnimation(Assets.AnimPersonRunRight, true, true);
				person.actionMoveTo(person.getPosY() * 40,
						person.getPosX() * 40, 0.5f);
				RightPress = true;
				m_run.play();
			}

		});
		getStage().addActor(btnUndo);

		btnRedo = MenuCreator.createCustomGameButton(Assets.btnMoveRight,
				Assets.btnMoveRight);
		btnRedo.setSize(35, 35);
		btnRedo.setPosition(OptionGame.SCREEN_WIDTH - 170,
				OptionGame.SCREEN_HEIGHT - 38);
		getStage().addActor(btnRedo);

		btnRefresh = MenuCreator.createCustomGameButton(Assets.btnRefrect,
				Assets.btnRefrect);
		btnRefresh.setSize(35, 40);
		btnRefresh.setPosition(OptionGame.SCREEN_WIDTH - 110,
				OptionGame.SCREEN_HEIGHT - 36);
		getStage().addActor(btnRefresh);
		btnRefresh.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				super.touchUp(event, x, y, pointer, button);
				getGame().setScreen(
						new TheWareHouse(getGame(), "The WareHouse", level,
								currentLevel));
			}
		});

		btnvolume = new MenuCreator().createCustomGameButton(Assets.btnvolume,
				Assets.btnvolume);
		btnvolume.setPosition(OptionGame.SCREEN_WIDTH - 50,
				OptionGame.SCREEN_HEIGHT - 36);
		getStage().addActor(btnvolume);
		btnvolume.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				super.touchUp(event, x, y, pointer, button);
				if (OptionGame.volume == 1f) {
					OptionGame.volume = 0f;
				} else {
					OptionGame.volume = 1f;
				}
			}
		});
		// Create broad control game
		broad = new BroadGame();
		broad.setBroad(level.getMap(currentLevel),
				level.getWidth(currentLevel), level.getHeight(currentLevel));
	}

	private void reset() {
		broad.setBroad(level.getMap(currentLevel),
				level.getWidth(currentLevel), level.getHeight(currentLevel));
	}

	@Override
	public void setUpScreenElements() {
		super.setUpScreenElements();
		setBackButtonActive(true);
		setBackgroundTexture(Assets.imgMtxBg);
	}

	private void setUpGameElements() {
		for (int i = 0; i < OptionGame.HEIGHT_BROAD; i++) {
			for (int j = 0; j < OptionGame.WIDTH_BROAD; j++) {
				if (OptionGame.WALL == broad.checkScreen(i, j)) {
					try {
						if (OptionGame.NULL != broad.checkScreen(i, j + 1)
								&& OptionGame.WALL != broad.checkScreen(i,
										j + 1)) {
							Image wall1 = new Image(Assets.imgWallBottomRight);
							Image wall2 = new Image(Assets.imgWallTopRight);
							wall1.setPosition(j * 40, i * 40);
							wall2.setPosition(j * 40, i * 40);
							getStage().addActor(wall1);
							getStage().addActor(wall2);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						if (OptionGame.NULL != broad.checkScreen(i, j - 1)
								&& OptionGame.WALL != broad.checkScreen(i,
										j - 1)) {
							Image wall1 = new Image(Assets.imgWallBottomLeft);
							Image wall2 = new Image(Assets.imgWallTopLeft);
							wall1.setPosition(j * 40, i * 40);
							wall2.setPosition(j * 40, i * 40);
							getStage().addActor(wall1);
							getStage().addActor(wall2);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						if (OptionGame.NULL != broad.checkScreen(i + 1, j)
								&& OptionGame.WALL != broad.checkScreen(i + 1,
										j)) {
							Image wall1 = new Image(Assets.imgWallTopLeft);
							Image wall2 = new Image(Assets.imgWallTopRight);
							wall1.setPosition(j * 40, i * 40);
							wall2.setPosition(j * 40, i * 40);
							getStage().addActor(wall1);
							getStage().addActor(wall2);
						} else {
							if (OptionGame.NULL != broad.checkScreen(i + 1,
									j + 1)
									&& OptionGame.WALL != broad.checkScreen(
											i + 1, j + 1)) {
								Image wall = new Image(Assets.imgWallTopRight);
								wall.setPosition(j * 40, i * 40);
								getStage().addActor(wall);
							}
							if (OptionGame.NULL != broad.checkScreen(i + 1,
									j - 1)
									&& OptionGame.WALL != broad.checkScreen(
											i + 1, j - 1)) {
								Image wall = new Image(Assets.imgWallTopLeft);
								wall.setPosition(j * 40, i * 40);
								getStage().addActor(wall);
							}
						}
					} catch (Exception e) {
						Gdx.app.log("ScreenLog", "EXCEPTION: " + e);
						try {
							if (OptionGame.NULL != broad.checkScreen(i + 1,
									j - 1)
									&& OptionGame.WALL != broad.checkScreen(
											i + 1, j - 1)) {
								Image wall = new Image(Assets.imgWallTopLeft);
								wall.setPosition(j * 40, i * 40);
								getStage().addActor(wall);
							}
						} catch (Exception e2) {
							// TODO: handle exception
							Gdx.app.log("ScreenLog", "EXCEPTION TOP LEFT: "
									+ e2);
						}
						try {
							if (OptionGame.NULL != broad.checkScreen(i + 1,
									j + 1)
									&& OptionGame.WALL != broad.checkScreen(
											i + 1, j + 1)) {
								Image wall = new Image(Assets.imgWallTopRight);
								wall.setPosition(j * 40, i * 40);
								getStage().addActor(wall);
							}
						} catch (Exception e2) {
							// TODO: handle exception
							Gdx.app.log("ScreenLog", "EXCEPTION TOP RIGHT: "
									+ e2);
						}
					}
					try {
						if (OptionGame.NULL != broad.checkScreen(i - 1, j)
								&& OptionGame.WALL != broad.checkScreen(i - 1,
										j)) {
							Image wall1 = new Image(Assets.imgWallBottomLeft);
							Image wall2 = new Image(Assets.imgWallBottomRight);
							wall1.setPosition(j * 40, i * 40);
							wall2.setPosition(j * 40, i * 40);
							getStage().addActor(wall1);
							getStage().addActor(wall2);
						} else {
							if (OptionGame.NULL != broad.checkScreen(i - 1,
									j + 1)
									&& OptionGame.WALL != broad.checkScreen(
											i - 1, j + 1)) {
								Image wall = new Image(
										Assets.imgWallBottomRight);
								wall.setPosition(j * 40, i * 40);
								getStage().addActor(wall);
							}
							if (OptionGame.NULL != broad.checkScreen(i - 1,
									j - 1)
									&& OptionGame.WALL != broad.checkScreen(
											i - 1, j - 1)) {
								Image wall = new Image(Assets.imgWallBottomLeft);
								wall.setPosition(j * 40, i * 40);
								getStage().addActor(wall);
							}
						}
					} catch (Exception e) {
						Gdx.app.log("ScreenLog", "EXCEPTION: " + e);
						try {
							if (OptionGame.NULL != broad.checkScreen(i - 1,
									j - 1)
									&& OptionGame.WALL != broad.checkScreen(
											i - 1, j - 1)) {
								Image wall = new Image(Assets.imgWallBottomLeft);
								wall.setPosition(j * 40, i * 40);
								getStage().addActor(wall);
							}
						} catch (Exception e2) {
							Gdx.app.log("ScreenLog", "EXCEPTION BOTTOM LEFT: "
									+ e2);
						}
						try {
							if (OptionGame.NULL != broad.checkScreen(i - 1,
									j + 1)
									&& OptionGame.WALL != broad.checkScreen(
											i - 1, j + 1)) {
								Image wall = new Image(
										Assets.imgWallBottomRight);
								wall.setPosition(j * 40, i * 40);
								getStage().addActor(wall);
							}
						} catch (Exception e2) {
							Gdx.app.log("ScreenLog", "EXCEPTION BOTTOM RIGHT: "
									+ e2);
						}
					}

				}
				if (OptionGame.NULL != broad.checkScreen(i, j)
						&& OptionGame.WALL != broad.checkScreen(i, j)) {
					Image wall = new Image(Assets.imgFloor);
					wall.setPosition(j * 40, i * 40);
					getStage().addActor(wall);
				}
				if (OptionGame.GOAL == broad.checkScreen(i, j)
						|| OptionGame.PLACE_BOX == broad.checkScreen(i, j)
						|| OptionGame.PERSON_ON_GOAL == broad.checkScreen(i, j)) {
					Image goal = new Image(Assets.imgLocation);
					goal.setPosition(j * 40, i * 40);
					getStage().addActor(goal);

					Goal animGoal = new Goal(i, j, 40, 55);
					animGoal.setAnimation(Assets.AnimArrowGoal, true, true);
					lstGoal.add(animGoal);
				}
				if (OptionGame.BOX == broad.checkScreen(i, j)
						|| OptionGame.PLACE_BOX == broad.checkScreen(i, j)) {
					BoxGame box = new BoxGame(Assets.imgBox, true, i, j,
							OptionGame.SIZE_WIDTH_ANIMATION,
							OptionGame.SIZE_HEIGHT_ANIMATION);
					lstBox.add(box);
				}
				if (OptionGame.PERSON == broad.checkScreen(i, j)
						|| OptionGame.PERSON_ON_GOAL == broad.checkScreen(i, j)) {
					person = new Person(i, j, OptionGame.SIZE_WIDTH_ANIMATION,
							OptionGame.SIZE_HEIGHT_ANIMATION);
					person.setAnimation(Assets.AnimPersonBottom, true, false);
				}
			}
		}
		for (int iBox = 0; iBox < lstBox.size(); iBox++) {
			BoxGame box = (BoxGame) lstBox.get(iBox);
			getStage().addActor(box);
		}
		getStage().addActor(person);
		for (int iGoal = 0; iGoal < lstGoal.size(); iGoal++) {
			Goal goal = (Goal) lstGoal.get(iGoal);
			getStage().addActor(goal);
		}

	}

	private void setUpButtons() {

	}

	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
		Gdx.app.log("ScreenLog:", "Key Back");
		getGame()
				.setScreen(
						new LevelScreen(getGame(), "LEVEL SCREEN", level,
								currentLevel));
	}

	private boolean activeClicked;

	@Override
	public synchronized void render(float delta) {
		super.render(delta);
		m_run.setVolume(OptionGame.volume);
		m_runbox.setVolume(OptionGame.volume);
		Winner.setVolume(OptionGame.volume);

		if (activeClicked) {
			if (stack.size() != 0) {
				btnUndo.setLockActive(false);
			} else {
				btnUndo.setLockActive(true);
			}
		}

		secondTime = System.nanoTime() / 1000;
		boolean checkTouched = Gdx.input.isTouched(0);
		if (activeTouch && actionComplete) {
			if (Gdx.input.getY() > (OptionGame.SCREEN_HEIGHT - 340)) {
				if (checkTouched) {
					if (!firstTouch) {
						firstTime = System.nanoTime() / 1000;
						firstPos_X = Gdx.input.getX();
						firstPos_Y = Gdx.input.getY();
						firstTouch = true;
					}
					if (secondTime - firstTime > 500) {
						lastPos_X = Gdx.input.getX();
						lastPos_Y = Gdx.input.getY();
						activeTouch = false;
						processTouch = true;
						activeClicked = false;
						btnUndo.setLockActive(true);
					}
				}
			}
		}

		if (processTouch && actionComplete) {
			if (!activeTouch) {
				activeTouch = false;
				if (Math.abs(lastPos_X - firstPos_X) < Math.abs(lastPos_Y
						- firstPos_Y)) {
					if (lastPos_Y > firstPos_Y) {
						MOVE_DOWN = true;
					} else if (lastPos_Y < firstPos_Y) {
						MOVE_TOP = true;
					}
				} else if (Math.abs(lastPos_X - firstPos_X) > Math
						.abs(lastPos_Y - firstPos_Y)) {
					if (lastPos_X > firstPos_X) {
						MOVE_RIGHT = true;
					} else if (lastPos_X < firstPos_X) {
						MOVE_LEFT = true;
					}
				} else {
					firstTouch = false;
					lastTouch = false;
					activeTouch = true;
					processTouch = false;
					count = 0;
				}
				firstPos_X = 0;
				firstPos_Y = 0;
				lastPos_X = 0;
				lastPos_Y = 0;
			}
		}

		if (actionComplete) {
			if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) || MOVE_DOWN) {
				moveDown();
				MOVE_DOWN = false;
				actionComplete = false;
				activeTouch = false;
				btnUndo.setLockActive(true);
				activeClicked = false;
			} else if (Gdx.input.isKeyPressed(Keys.DPAD_UP) || MOVE_TOP) {
				moveTop();
				MOVE_TOP = false;
				actionComplete = false;
				activeTouch = false;
				btnUndo.setLockActive(true);
				activeClicked = false;
			} else if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) || MOVE_LEFT) {
				moveLeft();
				MOVE_LEFT = false;
				actionComplete = false;
				activeTouch = false;
				btnUndo.setLockActive(true);
				activeClicked = false;
			} else if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) || MOVE_RIGHT) {
				moveRight();
				MOVE_RIGHT = false;
				actionComplete = false;
				activeTouch = false;
				btnUndo.setLockActive(true);
				activeClicked = false;
			}
		}
		if (!actionComplete) {
			if (BottomPress) {
				if (person.getPosY() * 40 == person.getX()
						&& person.getPosX() * 40 == person.getY()
						&& BottomPress) {
					person.setAnimation(Assets.AnimPersonBottom, true, false);
					BottomPress = false;
					actionComplete = true;
					activeTouch = true;
					firstTouch = false;
					lastTouch = false;
					count = 0;
					m_run.stop();
					m_runbox.stop();
					activeClicked = true;
					btnUndo.setLockActive(false);
				}
			} else if (TopPress) {
				if (person.getPosY() * 40 == person.getX()
						&& person.getPosX() * 40 == person.getY() && TopPress) {
					person.setAnimation(Assets.AnimPersonTop, true, false);
					TopPress = false;
					actionComplete = true;
					activeTouch = true;
					firstTouch = false;
					lastTouch = false;
					count = 0;
					m_run.stop();
					m_runbox.stop();
					activeClicked = true;
					btnUndo.setLockActive(false);
				}
			} else if (LeftPress) {
				if (person.getPosY() * 40 == person.getX()
						&& person.getPosX() * 40 == person.getY() && LeftPress) {
					person.setAnimation(Assets.AnimPersonLeft, true, false);
					LeftPress = false;
					actionComplete = true;
					activeTouch = true;
					firstTouch = false;
					lastTouch = false;
					count = 0;
					m_run.stop();
					m_runbox.stop();
					activeClicked = true;
					btnUndo.setLockActive(false);
				}
			} else if (RightPress) {
				if (person.getPosY() * 40 == person.getX()
						&& person.getPosX() * 40 == person.getY() && RightPress) {
					person.setAnimation(Assets.AnimPersonRight, true, false);
					RightPress = false;
					actionComplete = true;
					activeTouch = true;
					firstTouch = false;
					lastTouch = false;
					count = 0;
					m_run.stop();
					m_runbox.stop();
					activeClicked = true;
					btnUndo.setLockActive(false);
				}
			} else {
				actionComplete = true;
				activeTouch = true;
				firstTouch = false;
				lastTouch = false;
				count = 0;
				m_run.stop();
				m_runbox.stop();
				activeClicked = true;
				btnUndo.setLockActive(false);
			}
		}
		if (broad.checkComplete()) {
			actionComplete = false;
			Winner.play();
			if (!completeCheck) {
				firstTime = System.nanoTime() / 1000;
				completeCheck = true;
				Gdx.app.log("COMPLETE", "" + firstTime);
			}
			Gdx.app.log("COMPLETE", "" + firstTime);
			if (secondTime - firstTime > 3000000) {
				getGame().setScreen(
						new LevelScreen(getGame(), "MENU SCREEN", level,
								currentLevel + 1));
			}
		}
	}

	// move
	private void moveDown() {
		if (broad.checkBoxBottomPerson(person)) {
			if (broad.checkMoveBoxDown(person)) {
				for (int iBox = 0; iBox < lstBox.size(); iBox++) {
					BoxGame box = (BoxGame) lstBox.get(iBox);
					if (box.getPosX() == person.getPosX() - 1
							&& box.getPosY() == person.getPosY()) {
						broad.moveWidthBoxBottom(person, box);
						person.setAnimation(Assets.AnimPersonPushBottom, true,
								true);
						box.actionMoveTo(box.getPosY() * 40,
								box.getPosX() * 40, 0.5f);
						person.actionMoveTo(person.getPosY() * 40,
								person.getPosX() * 40, 0.5f);
						BottomPress = true;
						m_runbox.play();
						break;
					}
				}
			}
			stack.push(OptionGame.BOTTOM_BOX);
		} else if (broad.checkMoveDown(person)) {
			person.setAnimation(Assets.AnimPersonRunBottom, true, true);
			person.actionMoveTo(person.getPosY() * 40, person.getPosX() * 40,
					0.5f);
			BottomPress = true;
			m_run.play();
			stack.push(OptionGame.BOTTOM);
		}
	}

	private void moveTop() {
		if (broad.checkBoxTopPerson(person)) {
			if (broad.checkMoveBoxTop(person)) {
				for (int iBox = 0; iBox < lstBox.size(); iBox++) {
					BoxGame box = (BoxGame) lstBox.get(iBox);
					if (box.getPosX() == person.getPosX() + 1
							&& box.getPosY() == person.getPosY()) {
						broad.moveWidthBoxTop(person, box);
						person.setAnimation(Assets.AnimPersonPushTop, true,
								true);
						box.actionMoveTo(box.getPosY() * 40,
								box.getPosX() * 40, 0.5f);
						person.actionMoveTo(person.getPosY() * 40,
								person.getPosX() * 40, 0.5f);
						TopPress = true;
						m_runbox.play();
						break;
					}
				}
			}
			stack.push(OptionGame.TOP_BOX);
		} else if (broad.checkMoveTop(person)) {
			person.setAnimation(Assets.AnimPersonRunTop, true, true);
			person.actionMoveTo(person.getPosY() * 40, person.getPosX() * 40,
					0.5f);
			TopPress = true;
			m_run.play();
			stack.push(OptionGame.TOP);
		}
	}

	private void moveLeft() {
		if (broad.checkBoxLeftPerson(person)) {
			if (broad.checkMoveBoxLeft(person)) {
				for (int iBox = 0; iBox < lstBox.size(); iBox++) {
					BoxGame box = (BoxGame) lstBox.get(iBox);
					if (box.getPosX() == person.getPosX()
							&& box.getPosY() == person.getPosY() - 1) {
						broad.moveWidthBoxLeft(person, box);
						person.setAnimation(Assets.AnimPersonPushLeft, true,
								true);
						box.actionMoveTo(box.getPosY() * 40,
								box.getPosX() * 40, 0.5f);
						person.actionMoveTo(person.getPosY() * 40,
								person.getPosX() * 40, 0.5f);
						LeftPress = true;
						m_runbox.play();
						break;
					}
				}
			}
			stack.push(OptionGame.LEFT_BOX);
		} else if (broad.checkMoveLeft(person)) {
			person.setAnimation(Assets.AnimPersonRunLeft, true, true);
			person.actionMoveTo(person.getPosY() * 40, person.getPosX() * 40,
					0.5f);
			LeftPress = true;
			m_run.play();
			stack.push(OptionGame.LEFT);
		}
	}

	private void moveRight() {
		if (broad.checkBoxRightPerson(person)) {
			if (broad.checkMoveBoxRight(person)) {
				for (int iBox = 0; iBox < lstBox.size(); iBox++) {
					BoxGame box = (BoxGame) lstBox.get(iBox);
					if (box.getPosX() == person.getPosX()
							&& box.getPosY() == person.getPosY() + 1) {
						broad.moveWidthBoxRight(person, box);
						person.setAnimation(Assets.AnimPersonPushRight, true,
								true);
						box.actionMoveTo(box.getPosY() * 40,
								box.getPosX() * 40, 0.5f);
						person.actionMoveTo(person.getPosY() * 40,
								person.getPosX() * 40, 0.5f);
						RightPress = true;
						m_runbox.play();
						break;
					}
				}
			}
			stack.push(OptionGame.RIGHT_BOX);
		} else if (broad.checkMoveRight(person)) {
			person.setAnimation(Assets.AnimPersonRunRight, true, true);
			person.actionMoveTo(person.getPosY() * 40, person.getPosX() * 40,
					0.5f);
			RightPress = true;
			m_run.play();
			stack.push(OptionGame.RIGHT);
		}
	}
}
