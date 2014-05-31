package com.mygdx.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class AndroidLauncher extends AndroidApplication {
	String name;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		 
        //Nếu không có dữ liệu thì không làm gì nữa
        if (extras == null) {
            return;
        }
 
        //Nếu có dữ liệu thì lấy đưa vào 2 chuỗi với 2 key tương ứng
        String name = extras.getString("UserName");
        
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGdxGame(name), config);
	}
}
