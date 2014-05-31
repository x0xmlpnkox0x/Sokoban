package com.mygdx.Abstract;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.Option.OptionGame;

public abstract class AbstractScreen implements Screen {
	// TODO Set screen size (view port) (For each game it is different)
	// TODO Set game class to access ad manager
	private static final int SCREEN_W = OptionGame.SCREEN_WIDTH;
	private static final int SCREEN_H = OptionGame.SCREEN_HEIGHT;
	private Game game;
	// ---------------------------------------------------------------

	// Initial
	private String screenName = "Untitled Screen";
	private final Stage stage;

	private boolean isBackButtonActive = true;

	public AbstractScreen(Game game, String screenName) {
		super();
		this.game = game;
		this.screenName = screenName;
		stage = new Stage(new FitViewport(600, 390));
		Gdx.input.setInputProcessor(stage);
		Gdx.app.log("ScreenLog", "SCREEN CONSTRUCT: " + screenName);
		setUpScreenElements();
	}

	public void setUpScreenElements() {
		// TODO Set mandatory screen elements such as background textures,
		// initial parameter settings
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);

		stage.draw();
	}

	public void setBackgroundTexture(TextureRegion textureBackground) {
		Drawable tBg = new TextureRegionDrawable(textureBackground);
		Image imgbg = new Image(tBg, Scaling.stretch);
		imgbg.setFillParent(false);
		stage.addActor(imgbg);
		Gdx.app.log("ScreenLog", "SCREEN BACKGROUND SETTED: " + screenName);
	}

	public void keyBackPressed(){}
	
	
	public Game getGame() {
		return game;
	}

	/**
	 * Set the game class
	 * */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Get screen name
	 * */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * Set screen name
	 * */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public void setBackButtonActive(boolean isBackButtonActive) {
		Gdx.input.setCatchBackKey(true);
		this.isBackButtonActive = isBackButtonActive;
		Gdx.app.log("ScreenLog", "CUSTOM BACK BUTTON SETTED");
	}

	public Stage getStage() {
		return stage;
	}

	@Override
	public void resize(int width, int height) {

		Gdx.app.log("ScreenLog", "SCREEN RESIZE: " + screenName);
	}

	@Override
	public void show() {
		Gdx.app.log("ScreenLog", "SCREEN SHOW: " + screenName);
	}

	@Override
	public void hide() {
		Gdx.app.log("ScreenLog", "SCREEN HIDE: " + screenName);
	}

	@Override
	public void pause() {
		Gdx.app.log("ScreenLog", "SCREEN PAUSE: " + screenName);
	}

	@Override
	public void resume() {
		Gdx.app.log("ScreenLog", "SCREEN RESUME: " + screenName);
	}

	@Override
	public void dispose() {
		Gdx.app.log("ScreenLog", "SCREEN DISPOSE: " + screenName);

	}
}
