package com.mygdx.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.Abstract.AbstractScreen;
import com.mygdx.Option.OptionGame;
import com.mygdx.game.SolutionGame;

public class screenLoading extends AbstractScreen {
	private Image imgloading;

	private long starttime;
	private SolutionGame solutionGame;
	private String name;

	public screenLoading(Game game, String screenName, String name) {
		super(game, screenName);
		// TODO Auto-generated constructor stub
//		this.name = name;
		solutionGame = new SolutionGame();
		solutionGame.setSolution();
		starttime = TimeUtils.millis();
		setBackgroundTexture(new TextureRegion(new Texture("data/loading.png")));
		initial();
	}

	private void initial() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		if (TimeUtils.millis() > (starttime + 1000)) {
			
			getGame().setScreen(
					new MenuGame(getGame(), "Menu Game", solutionGame, name));
		}
	}

}