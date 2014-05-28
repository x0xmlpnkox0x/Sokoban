package com.mygdx.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.Option.OptionGame;


public class Level {
	
	private String[] map;
	private int[][] size;
	private InputStream inputStream;
	private BufferedReader buffer;
	private String lines;
	private FileHandle files;
	
	public Level() {
		map = new String[OptionGame.NUMBER_LEVEL];
		size = new int[OptionGame.NUMBER_LEVEL][2];
		files = Gdx.files.internal(OptionGame.PART_LEVEL);
	}
	
	public String getMap(int level) {
		return map[level];
	}
	
	public void setMap() {
		int widthMax = 0;
		int heightMax = 0;
		int temp = -1;
		String str_map = "";
		inputStream = files.read();
		buffer = new BufferedReader(new InputStreamReader(inputStream));
		try {
			lines = buffer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 0;
		while (lines != null) {
			if (lines.contains(";")) {
				count ++;
				switch (temp) {
				case -1:
					str_map = "";
					break;

				default:
					setMapWithLevel(temp, str_map);	
					setSizeLevel(temp, widthMax, heightMax);
					widthMax = 0;
					heightMax = 0;
					str_map = "";
					break;
				}
				temp += 1;
			} else {
				if (widthMax < lines.length()) {
					widthMax = lines.length();
				}
				heightMax += 1;
				str_map += lines + "\n";
			}
			try {
				lines = buffer.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void setMapWithLevel(int level, String map) {
		this.map[level] = map;
	}
	
	private void setSizeLevel(int level, int width, int height) {
		this.size[level][0] = width;
		this.size[level][1] = height;
	}
	
	public int getWidth(int level) {
		return this.size[level][0];
	}
	
	public int getHeight(int level) {
		return this.size[level][1];
	}
}
