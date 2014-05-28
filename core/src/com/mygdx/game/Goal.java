package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.Abstract.AbstractActor;


public class Goal extends AbstractActor {
	public Goal(TextureRegion textureRegion, boolean isTextureRegionActive, 
			float posX, float posY, int width, int height) {
		super(textureRegion, isTextureRegionActive, posX, posY, width, height);
	}
	
	public Goal(float posX, float posY, int width, int height) {
		super(posY*40, posX*40, width, height);
	}
}

