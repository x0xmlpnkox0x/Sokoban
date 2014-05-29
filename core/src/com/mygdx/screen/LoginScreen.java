package com.mygdx.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.Abstract.AbstractScreen;
import com.mygdx.Asset.Assets;

public class LoginScreen extends AbstractScreen{
private TextField nameTextfield;
private Label nameLabel;
private Skin skin;

	public LoginScreen(Game game, String screenName) {
		super(game, screenName);
		// TODO Auto-generated constructor stub
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		setBackgroundTexture(new TextureRegion(new Texture("data/background.png")));
//		nameLabel = new Label("fdsfds", Assets.skin);
//		nameLabel.setPosition(200, 200);
//		nameLabel.setSize(100, 50);


		nameTextfield = new TextField("chan", Assets.skin);
		nameLabel.setPosition(200, 100);
		nameLabel.setSize(100, 50);
		
		
		
	}

}
