package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Asset.Assets;
import com.mygdx.screen.screenLoading;

public class MyGdxGame extends Game implements ApplicationListener {

	@Override
	public void create() {

		setScreen(new screenLoading(this, "Name"));
		Assets.loadAll();
	}
}
