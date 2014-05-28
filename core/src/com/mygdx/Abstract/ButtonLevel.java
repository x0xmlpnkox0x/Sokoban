package com.mygdx.Abstract;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ButtonLevel extends AbstractButton{

	// Level number (not madatory)
	private int levelNumber = -999;
	
	// Font (For writings and level number)
	private BitmapFont bitMapFont;
	
	private TextureRegion textureRegion;
	
	public ButtonLevel(Drawable up, Drawable down) {
		super(up, down);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// If level locked
		if (isLockActive && textureLocked != null) {
			drawLocked(batch);
		} else if (isLockActive) {
			super.draw(batch, parentAlpha);
		}
		
		// If level number set
		else if (levelNumber != -999 && bitMapFont != null) {
			super.draw(batch, parentAlpha);
			drawLevelNumber(batch);
			drawText(batch);
			
			drawExternalTexture(batch);
		}
		
		// draw default
		else {
			super.draw(batch, parentAlpha);
			drawText(batch);
			drawExternalTexture(batch);
		}
	}
	
	private void drawText(Batch batch) {
		if (isTextActive && bitMapFont != null) {
			bitMapFont.draw(batch, text, getX()+textPosX, getY()+textPosY);
		}
	}
	
	private void drawLocked(Batch batch) {
		batch.draw(textureLocked, getX(), getY(), getWidth(), getHeight());
	}
	
	public void setBgLocked(TextureRegion textureRegion) {
		this.textureLocked = textureRegion;
	}
	
	private void drawExternalTexture(Batch batch) {
		if (isExternalTextureActive && textureExternal != null) {
			batch.draw(textureExternal, getX()+externalTexturePosX, getY()+externalTexturePosY, externalTextureSizeW, externalTextureSizeH);
		}
	}
	
	private void drawLevelNumber(Batch batch) {
		float singePositionArranger = 2.5f;
		float doublePositionArranger = 4.2f;
		
		if (levelNumber < 10) {
			bitMapFont.draw(batch, "" + levelNumber, getX()+getWidth()/singePositionArranger,
					getY()+getHeight()/1.4f);
		} else if (levelNumber >= 100) {
			bitMapFont.draw(batch, "" + levelNumber, getX()+getWidth()/7.8f,
					getY()+getHeight()/1.4f);
		} else {
			bitMapFont.draw(batch, "" + levelNumber, getX()+getWidth()/doublePositionArranger,
					getY()+getHeight()/1.4f);
		}
	}
	
	/**
	 * Get level number
	 * @return
	 */
	public int getLevelNumber() {
		return this.levelNumber;
	}
	
	/**
	 * Set level number and the font to draw that number
	 * @param levelNumber
	 * @param font
	 */
	public void setLevelNumber(int levelNumber, BitmapFont font) {
		this.levelNumber = levelNumber;
		bitMapFont = font;
	}
	
	/**
	 * Set level number changes
	 * @param levelNumber
	 */
	public void setLevelNumberChange(int levelNumber) {
		this.levelNumber = levelNumber;
	}
}


