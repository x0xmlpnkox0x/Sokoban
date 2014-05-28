package com.mygdx.Abstract;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuCreator {    
    public static Table createTable(boolean fillParent, Skin skin){
        Table table = new Table(skin );
        table.setFillParent( fillParent );        
        return table;
    }    
    
    
    public static ButtonGame createCustomGameButton(TextureRegion up, TextureRegion down){
        Drawable dUp = new TextureRegionDrawable(up);
        Drawable dDown = new TextureRegionDrawable(down);
        return new ButtonGame(dUp, dDown);
    }
    
    public static ButtonLevel createCustomLevelButton(TextureRegion up, TextureRegion down) {
    	Drawable dUp = new TextureRegionDrawable(up);
    	Drawable dDown = new TextureRegionDrawable(down);
    	return new ButtonLevel(dUp, dDown);
    }
}


