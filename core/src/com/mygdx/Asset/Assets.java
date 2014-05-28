package com.mygdx.Asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.Abstract.AnimationCreator;

//import com.mygdx.Abstract.AnimationCreator;

public class Assets {
	private final static String FILE_IMAGE_ATLAS = "data/Atlas_Animation.txt";
	private final static String FILE_UI_SKIN = "skin/uiskin.json";
	public static TextureAtlas imageAtlas;
	public static Skin skin;

	//
	public static Music music_click_btn = Gdx.audio.newMusic(Gdx.files
			.internal("music/click_button.mp3"));

	//
	public static TextureRegion imgMtxBg;
	public static TextureRegion imgTitle;
	public static TextureRegion imgBoxMenu;
	public static TextureRegion imgFloor;
	public static TextureRegion imgLocation;
	public static TextureRegion imgWallTopLeft;
	public static TextureRegion imgWallTopRight;
	public static TextureRegion imgWallBottomLeft;
	public static TextureRegion imgWallBottomRight;
	public static TextureRegion imgBox;
	public static TextureRegion imgLevel;
	public static TextureRegion imgLbLevel;
	public static TextureRegion imgloading;

	public static Texture imgtime;
	public static TextureRegion imglbtime;

	public static TextureRegion imgBHD;
	public static TextureRegion imgSolution;
	public static TextureRegion btnMoveLeft;
	public static TextureRegion btnMoveRight;

	//
	public static Animation AnimPersonTop;
	public static Animation AnimPersonBottom;
	public static Animation AnimPersonLeft;
	public static Animation AnimPersonRight;

	public static Animation AnimPersonRunTop;
	public static Animation AnimPersonRunBottom;
	public static Animation AnimPersonRunLeft;
	public static Animation AnimPersonRunRight;

	public static Animation AnimPersonPushTop;
	public static Animation AnimPersonPushBottom;
	public static Animation AnimPersonPushLeft;
	public static Animation AnimPersonPushRight;

	public static Animation AnimBox;
	public static Animation AnimArrowGoal;

	//
	public static BitmapFont font2;
	public static BitmapFont font5;

	//
	public static TextureRegion btnAllMenu;
	public static TextureRegion btnAllMenuPressed;
	public static TextureRegion btnAllUp;
	public static TextureRegion btnAllDown;

	public static TextureRegion btnBoxBlock;
	public static TextureRegion btnBoxActive;
	public static TextureRegion btnBoxActiveClick;

	public static TextureRegion btnRefrect;
	public static TextureRegion btnEmpty;
	public static TextureRegion btnvolume;

	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static TextureAtlas getAtlas() {
		if (imageAtlas == null) {
			imageAtlas = new TextureAtlas(Gdx.files.internal(FILE_IMAGE_ATLAS));
		}
		return imageAtlas;
	}

	public static Skin getSkin() {
		if (skin == null) {
			FileHandle skinFile = Gdx.files.internal(FILE_UI_SKIN);
			skin = new Skin(skinFile);
		}
		return skin;
	}

	public static void loadAll() {
		relaseResources();
		loadImages();
		loadButtons();
		loadFonts();
		loadAnimation();
	}

	private static void relaseResources() {
		skin = null;
		imageAtlas = null;
	}

	public static void loadImages() {
		imgMtxBg = new TextureRegion(new Texture("data/background.png"), 0, 0,
				600, 390);
		imgTitle = getAtlas().findRegion("textTitle");
		imgBoxMenu = getAtlas().findRegion("boxTitle");
		imgBox = getAtlas().findRegion("box");
		imgLevel = getAtlas().findRegion("level");
		imgLbLevel = getAtlas().findRegion("lbLevel");

		imgloading = new TextureRegion(loadTexture("data/loading.png"));
		imgBHD = new TextureRegion(loadTexture("data/imgBHD.png"));
		// item game
		imgFloor = getAtlas().findRegion("floor");
		imgLocation = getAtlas().findRegion("location");
		// imgWallTopLeft = getAtlas().findRegion("wallTopLeft");
		imgWallTopLeft = new TextureRegion(
				new Texture("wall/wall_top_left.png"));
		// imgWallTopRight = getAtlas().findRegion("wallTopRight");
		imgWallTopRight = new TextureRegion(new Texture(
				"wall/wall_top_right.png"));
		// imgWallBottomLeft = getAtlas().findRegion("wallBottomLeft");
		imgWallBottomLeft = new TextureRegion(new Texture(
				"wall/wall_down_left.png"));
		// imgWallBottomRight = getAtlas().findRegion("wallBottomRight");
		imgWallBottomRight = new TextureRegion(new Texture(
				"wall/wall_down_right.png"));
		imgtime = new Texture("data/time.png");
		imgSolution = new TextureRegion(new Texture("data/solution.png"));
		imglbtime = new TextureRegion(new Texture("data/lbtime.png"));
	}

	public static void loadButtons() {
		btnAllMenu = getAtlas().findRegion("btnallmenu");
		btnAllMenuPressed = getAtlas().findRegion("btnallmenupressed");
		btnAllUp = getAtlas().findRegion("btnStartUp");
		btnAllDown = getAtlas().findRegion("btnStartDown");

		// Screen Level
		btnBoxBlock = getAtlas().findRegion("boxBlock");
		btnBoxActive = getAtlas().findRegion("boxActive");
		btnBoxActiveClick = getAtlas().findRegion("boxActiveClick");
		btnMoveLeft = getAtlas().findRegion("moveLeft");
		btnMoveRight = getAtlas().findRegion("moveRight");

		btnRefrect = getAtlas().findRegion("refect");
		btnEmpty = getAtlas().findRegion("emptybox");
		btnvolume = new TextureRegion(loadTexture("data/sound.png"));
	}

	public static void loadFonts() {
		font2 = new BitmapFont(Gdx.files.internal("data/font2.fnt"),
				Gdx.files.internal("data/font2.png"), false);
		font5 = new BitmapFont(Gdx.files.internal("data/font5.fnt"),
				Gdx.files.internal("data/font5_0.png"), false);
	}

	public static void loadAnimation() {
		// Animation

		AnimPersonTop = AnimationCreator.getAnimationFromSingleTexture(
				getAtlas(), "personTop", 1, 0);
		AnimPersonBottom = AnimationCreator.getAnimationFromSingleTexture(
				getAtlas(), "personBottom", 1, 0);
		AnimPersonLeft = AnimationCreator.getAnimationFromSingleTexture(
				getAtlas(), "personLeft", 1, 0);
		AnimPersonRight = AnimationCreator.getAnimationFromSingleTexture(
				getAtlas(), "personRight", 1, 0);

		//
		AnimPersonRunBottom = AnimationCreator.getAnimationFromSingleTexture(
				getAtlas(), "personRunBottom", 2, 0.25f);
		AnimPersonRunTop = AnimationCreator.getAnimationFromSingleTexture(
				getAtlas(), "personRunTop", 2, 0.25f);
		AnimPersonRunLeft = AnimationCreator.getAnimationFromSingleTexture(
				getAtlas(), "personRunLeft", 2, 0.25f);
		AnimPersonRunRight = AnimationCreator.getAnimationFromSingleTexture(
				getAtlas(), "personRunRight", 2, 0.25f);

		//
		AnimPersonPushBottom = AnimationCreator.getAnimationFromSingleTexture(
				getAtlas(), "personPushBottom", 2, 0.25f);
		AnimPersonPushTop = AnimationCreator.getAnimationFromSingleTexture(
				getAtlas(), "personPushTop", 2, 0.25f);
		AnimPersonPushLeft = AnimationCreator.getAnimationFromSingleTexture(
				getAtlas(), "personPushLeft", 2, 0.25f);
		AnimPersonPushRight = AnimationCreator.getAnimationFromSingleTexture(
				getAtlas(), "personPushRight", 2, 0.25f);

		//
		AnimArrowGoal = AnimationCreator.getAnimationFromSingleTexture(
				getAtlas(), "arrow", 2, 0.25f);
	}
}
