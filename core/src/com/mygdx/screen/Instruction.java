package com.mygdx.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.mygdx.Abstract.AbstractScreen;
import com.mygdx.Abstract.ButtonGame;
import com.mygdx.Abstract.MenuCreator;
import com.mygdx.Asset.*;
import com.mygdx.Option.OptionGame;
import com.mygdx.game.Level;


public class Instruction extends AbstractScreen {
	private ButtonGame buttonMenu;
	private Level level;
	private int number;
	private Image imgBHD;
	public Instruction(Game game, String screenName, Level level, int number) {
		super(game, screenName);
		// TODO Auto-generated constructor stub
		this.level = level;
		this.number = number;
		setUpScreenElements();
		initial();
		setUpButtons();
	}
	
	@Override
	public void setUpScreenElements() {
		// TODO Auto-generated method stub
		super.setUpScreenElements();

		setBackgroundTexture(Assets.imgMtxBg);
		setBackButtonActive(true);
	}
	private void setUpButtons() {
		// TODO Auto-generated method stub
		buttonMenu = MenuCreator.createCustomGameButton(Assets.btnAllUp, Assets.btnAllDown);
		buttonMenu.setText(Assets.font2, "start", false);
		
		buttonMenu.setPosition(200, 60);
		buttonMenu.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				getGame().setScreen(new LevelScreen(getGame(), "Level Screen", level, number));
//				_music.stop();
//				OptionGame.musicbutton.setVolume(OptionGame.volume);
//				OptionGame.musicbutton.play();
				Assets.music_click_btn.play();
				Assets.music_click_btn.setVolume(OptionGame.volume);
			}
		});
		getStage().addActor(buttonMenu);
	}
	private void initial() {
		// TODO Auto-generated method stub
		imgBHD = new Image(Assets.imgBHD);
		imgBHD.setPosition(0, 0);
		getStage().addActor(imgBHD);
	}

}
