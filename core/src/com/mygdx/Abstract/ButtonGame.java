package com.mygdx.Abstract;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ButtonGame extends AbstractButton {

	public ButtonGame(Drawable up, Drawable down) {
		super(up, down);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// If button locked
		// ##################################################################
		if (isLockActive && textureLocked != null) {
			
			drawLocked(batch);
		}

		// If text set and intented to be used
		// ##################################################################
		else if (text != "" && bitMapFont != null && isTextActive) {
			super.draw(batch, parentAlpha);
			drawText(batch);
			drawExternalTexture(batch);
		}

		// Draw default
		// ##################################################################
		else {
			super.draw(batch, parentAlpha);
			drawExternalTexture(batch);
		}
	}
	


	private void drawExternalTexture(Batch batch) {
		if(isExternalTextureActive && textureExternal != null){
			batch.draw(textureExternal, getX() + externalTexturePosX, getY() + externalTexturePosY, externalTextureSizeW, externalTextureSizeH);
		}
	}

	private void drawLocked(Batch batch) {
		batch.draw(textureLocked, getX(), getY(), getWidth(), getHeight());
	}
	
	private void drawText(Batch batch) {

		if (text.length()<2) {
			bitMapFont.draw(batch, text, getX() + 25, getY() + 30);
		}
		else if (text.length()==2) {
			bitMapFont.draw(batch, text, getX() + 15, getY() + 30);
		}
		else if (text.length() == 5) {
			bitMapFont.draw(batch, text, getX() + 15, getY() + 30);
		}
		else {
			bitMapFont.draw(batch, text, getX() + 10, getY() + 3);
		}
	}
}


