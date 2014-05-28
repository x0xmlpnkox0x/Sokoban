package com.mygdx.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.mygdx.Abstract.AbstractScreen;
import com.mygdx.Abstract.ButtonGame;
import com.mygdx.Abstract.ButtonLevel;
import com.mygdx.Abstract.MenuCreator;
import com.mygdx.Asset.Assets;
import com.mygdx.Option.OptionGame;
import com.mygdx.game.Level;

public class LevelScreen extends AbstractScreen {

	private Level level;
	private int currentLevel;
	private int ScreenDisplay;
	private int ScreenDisplayNow;
	private ArrayList tableDisplayManagerment;
	private ButtonGame btnNextLevel;
	private ButtonGame btnPressLevel;
	private float beginDelay;
	private float lastDelay;
	private boolean activeTime = false;

	private boolean btnLeftActive;
	private boolean btnRightActive;
	private Table tableCurrent;

	private int numLevel;

	private boolean clickNextTable;
	private boolean clickPressTable;

	public LevelScreen(Game game, String screenName, Level level, int currentLv) {
		super(game, screenName);
		this.currentLevel = currentLv;
		this.ScreenDisplayNow = (int) (currentLv / 20);
		this.level = level;
		setUpScreenCustomer();
	}

	@Override
	public void setUpScreenElements() {
		super.setUpScreenElements();
		tableDisplayManagerment = new ArrayList();

		// Thiết lập giao diện màn hình Level Screen
		setBackgroundTexture(Assets.imgMtxBg);
		setBackButtonActive(true);
		setBtnNextPress();
	}

	private void setUpScreenCustomer() {
		setTableLevelScreen();
		setTableDisplay(ScreenDisplayNow);
	}

	private int getDislayNow() {
		Gdx.app.log("ScreenLog", "" + ScreenDisplayNow);
		return this.ScreenDisplayNow;
	}

	private void setTableLevelScreen() {

		// Thiết lập số màn hình có thể hiển thị
		if (OptionGame.NUMBER_LEVEL % 20 == 0) {
			setScreenDisplay(OptionGame.NUMBER_LEVEL
					/ OptionGame.NUM_BUTTON_LEVEL);
		} else {
			setScreenDisplay((OptionGame.NUMBER_LEVEL / OptionGame.NUM_BUTTON_LEVEL) + 1);
		}

		// Thiết lập số nút trong từng màn hình
		for (int iScreenDisplay = 0; iScreenDisplay < getScreenDisplay(); iScreenDisplay++) {
			Table tableLevel = MenuCreator.createTable(true, Assets.skin);
			for (int iButtonLevel = 0; iButtonLevel < OptionGame.NUM_BUTTON_LEVEL; iButtonLevel++) {

				// Thiết lập số level cho từng button
				final ButtonLevel btnLevel;
				if (iButtonLevel == 0
						|| iButtonLevel % OptionGame.AROUND_LEVEL == 0) {
					numLevel = (iButtonLevel / OptionGame.AROUND_LEVEL) + 1;
				} else {
					numLevel = (iButtonLevel % OptionGame.AROUND_LEVEL)
							* OptionGame.NUM_AROUND
							+ (iButtonLevel / OptionGame.AROUND_LEVEL) + 1;
				}
				// Gdx.app.log("ScreenLog", "" + numLevel);
				int setLevel = numLevel
						+ (OptionGame.NUM_BUTTON_LEVEL * iScreenDisplay);
				if (setLevel > OptionGame.NUMBER_LEVEL) {
					btnLevel = MenuCreator.createCustomLevelButton(
							Assets.btnEmpty, Assets.btnEmpty);
				} else {
					btnLevel = MenuCreator.createCustomLevelButton(
							Assets.btnBoxActive, Assets.btnBoxActiveClick);
					btnLevel.setLevelNumber(setLevel, Assets.font5);
					// Thiết lập mở khóa button
					if (currentLevel + 1 < btnLevel.getLevelNumber()) {
						btnLevel.setBgLocked(Assets.btnBoxBlock);
						btnLevel.setLockActive(true);
					}

					// Sự kiện cho button
					btnLevel.addListener(new ActorGestureListener() {

						@Override
						public void touchUp(InputEvent event, float x, float y,
								int pointer, int button) {
							super.touchUp(event, x, y, pointer, button);
							Assets.music_click_btn.play();
							Assets.music_click_btn.setVolume(OptionGame.volume);
							int gameLevel = btnLevel.getLevelNumber() - 1;
//							getGame().setScreen(new TheWareHouse(getGame(), "GAME PLAYING",
//											level, gameLevel));
						}

						@Override
						public void touchDown(InputEvent event, float x,
								float y, int pointer, int button) {
							super.touchDown(event, x, y, pointer, button);
						}
					});
				}

				// Add row after each 5 level button to go down or how many do
				// you need
				if (iButtonLevel % 5 == 0) {
					tableLevel.row();
				}

				// Add to table
				tableLevel.add(btnLevel).size(70, 70).pad(5, 5, 5, 5);
			}
			tableDisplayManagerment.add(tableLevel);
		}
	}

	private void setBtnNextPress() {
		setNextScreen();
		setPressScreen();
	}

	private void setNextScreen() {
		btnNextLevel = MenuCreator.createCustomGameButton(Assets.btnMoveRight,
				Assets.btnMoveRight);
		btnNextLevel.setSize(40, 40);
		btnNextLevel.setPosition(OptionGame.SCREEN_WIDTH - 65,
				OptionGame.SCREEN_HEIGHT / 2 - 10);
		btnNextLevel.setVisible(false);
		btnNextLevel.addListener(new ActorGestureListener() {

			@Override
			public void touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				super.touchDown(event, x, y, pointer, button);
				clickNextTable = true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				tableCurrent.addAction(Actions.moveTo(
						OptionGame.SCREEN_WIDTH / 2, 0, 1.2f));
			}
		});
		getStage().addActor(btnNextLevel);
	}

	private void setPressScreen() {
		btnPressLevel = MenuCreator.createCustomGameButton(Assets.btnMoveLeft,
				Assets.btnMoveLeft);
		btnPressLevel.setSize(40, 40);
		btnPressLevel.setPosition(35, OptionGame.SCREEN_HEIGHT / 2 - 10);
		btnPressLevel.setVisible(false);
		btnPressLevel.addListener(new ActorGestureListener() {

			@Override
			public void touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchDown(event, x, y, pointer, button);
				clickPressTable = true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				tableCurrent.addAction(Actions.moveTo(
						-OptionGame.SCREEN_WIDTH / 2, 0, 1.2f));
			}
		});
		getStage().addActor(btnPressLevel);
	}

	private void setScreenDisplay(int screenDisplay) {
		this.ScreenDisplay = screenDisplay;
	}

	private int getScreenDisplay() {
		return this.ScreenDisplay;
	}

	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
		getGame().setScreen(
				new MenuGame(getGame(), "MAIN GAME", getCurrentLevel()));
	}

	private void setTableDisplay(int now) {
		if (now != 0) {
			btnLeftActive = true;
		} else {
			btnLeftActive = false;
		}
		if (OptionGame.NUMBER_LEVEL % OptionGame.NUM_BUTTON_LEVEL == 0
				&& (OptionGame.NUMBER_LEVEL / OptionGame.NUM_BUTTON_LEVEL) != now + 1) {
			this.btnRightActive = true;
		} else if (OptionGame.NUMBER_LEVEL % OptionGame.NUM_BUTTON_LEVEL != 0
				&& (OptionGame.NUMBER_LEVEL / OptionGame.NUM_BUTTON_LEVEL) + 1 != now + 1) {
			this.btnRightActive = true;
		} else {
			btnRightActive = false;
		}
		setScreenDisplayNow(now);
		Table table = (Table) tableDisplayManagerment.get(now);
		tableCurrent = table;
		getStage().addActor(tableCurrent);
		if (clickNextTable) {
			table.setPosition(-OptionGame.SCREEN_WIDTH / 2, 0);
			table.addAction(Actions.moveTo(0, 0, 1f));
		} else if (clickPressTable) {
			table.setPosition(OptionGame.SCREEN_WIDTH / 2, 0);
			table.addAction(Actions.moveTo(0, 0, 1f));
		}
	}

	private void setScreenDisplayNow(int now) {
		this.ScreenDisplayNow = now;
	}

	private int getCurrentLevel() {
		return this.currentLevel;
	}

	@Override
	public void render(float delta) {

		super.render(delta);
		if (Gdx.input.justTouched()) {
			activeTime = true;
			beginDelay = System.nanoTime() / 1000;

			//
			if (btnLeftActive) {
				btnPressLevel.setVisible(true);
			}

			//
			if (btnRightActive) {
				btnNextLevel.setVisible(true);
			}
		}

		if (activeTime) {
			lastDelay = System.nanoTime() / 1000;
			if (lastDelay - beginDelay > 2000000) {
				beginDelay = 0;
				lastDelay = 0;
				activeTime = false;

				//
				btnPressLevel.setVisible(false);

				//
				btnNextLevel.setVisible(false);
			}
		}

		if (clickNextTable) {
			if (tableCurrent.getX() >= OptionGame.SCREEN_WIDTH / 2 - 10) {
				if (getStage().getActors().size == 4) {
					getStage().getActors().pop();
					setTableDisplay(ScreenDisplayNow + 1);
				}
				clickNextTable = false;
			}
		} else if (clickPressTable) {
			if (tableCurrent.getX() <= -OptionGame.SCREEN_WIDTH / 2 + 10) {
				if (getStage().getActors().size == 4) {
					getStage().getActors().pop();
					setTableDisplay(ScreenDisplayNow - 1);
				}
				clickPressTable = false;
			}
		}
	}

}
