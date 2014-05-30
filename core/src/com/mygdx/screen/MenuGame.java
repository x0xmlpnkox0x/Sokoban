package com.mygdx.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.mygdx.Abstract.AbstractScreen;
import com.mygdx.Abstract.ButtonGame;
import com.mygdx.Abstract.MenuCreator;
import com.mygdx.Asset.Assets;
import com.mygdx.Option.OptionGame;
import com.mygdx.game.Level;
import com.mygdx.game.SolutionGame;


public class MenuGame extends AbstractScreen {
	
	// Background
	private Image imgTitle;
	private Image imgBoxMenu;
	
	// Button
	private ButtonGame buttonMenu; 
	
	public Level level;
	private int currenLevel;
	public Preferences prefs;
	private SolutionGame solutionGame;
	private int highScore;
	
	public MenuGame(Game game, String screenName, SolutionGame solutionGame) {
		super(game, screenName);
		this.solutionGame = solutionGame;
		preferences();
		currenLevel = highScore;
		initial();
		setUpGameElements();
		setUpButtons();
		
	}
	
	void preferences(){
		// Create (or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("thien");

		// Provide default high score of 0
		if (!prefs.contains("highScore")) {
		    prefs.putInteger("highScore", 0);
		}
		// Retrieves the current high score
		
		 highScore = prefs.getInteger("highScore");
		
	}
	
	
	private void initial() {
		// Create level map
		level = new Level();
		level.setMap();
	}
	
	@Override
	public void setUpScreenElements() {
		super.setUpScreenElements();
//		Gdx.app.log("ScreenLog", "This is background");
		setBackgroundTexture(Assets.imgMtxBg);
	}
	
	private void setUpGameElements() {
		
		
		
		
		// Set image title game
		imgTitle = new Image(Assets.imgTitle);
		imgTitle.setPosition(99, 260);
		getStage().addActor(imgTitle);
		
		// Set image box menu
		imgBoxMenu = new Image(Assets.imgBoxMenu);
		imgBoxMenu.setPosition(429, 30);
		getStage().addActor(imgBoxMenu);
	}
	
	private void setUpButtons() {
//		tableButtons = MenuCreator.createTable(true, Assets.getSkin());
		buttonMenu = MenuCreator.createCustomGameButton(Assets.btnAllUp, Assets.btnAllDown);
		buttonMenu.setText(Assets.font2, "start", false);
		buttonMenu.setTextPosXY(60, 47);
		buttonMenu.setPosition(120, 100);
		buttonMenu.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				Assets.music_click_btn.play();
				Assets.music_click_btn.setVolume(OptionGame.volume);
				getGame().setScreen(new Instruction(getGame(), "Instruction", level, currenLevel, solutionGame));
			}
		});
		getStage().addActor(buttonMenu);
	}
	
	private Level getLevel() {
		return this.level;
	}
}
