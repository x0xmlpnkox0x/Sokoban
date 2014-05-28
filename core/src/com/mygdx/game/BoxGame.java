package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.mygdx.Abstract.AbstractActor;

public class BoxGame extends AbstractActor {
	private int x;
	private int y;
	
	public BoxGame(TextureRegion textureRegion, boolean isTextureRegionActive,
			int x, int y, float width, float height) {
		super(textureRegion, isTextureRegionActive, (y)*40, (x)*40, width, height);
		this.x = x;
		this.y = y;
	}
	
	public BoxGame(int x, int y, float width, float height) {
		super((y)*40, (x)*40, width, height);
		this.x = x;
		this.y = y;
	}
	
	public int getPosX() {
		return this.x;
	}
	
	public int getPosY() {
		return this.y;
	}
	
	public void setPosX(int x) {
		this.x = x;
	}
	
	public void setPosY(int y) {
		this.y = y;
	}
}


