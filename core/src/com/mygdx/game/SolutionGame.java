package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.Option.OptionGame;

public class SolutionGame {
	private String[] solution;

	private String lines;
	private FileHandle files;

	public SolutionGame() {
		solution = new String[OptionGame.NUMBER_LEVEL];
		files = Gdx.files.internal("data/solution.txt");

	}

	public String getSolution(int level) {
		return solution[level];
	}

	public void setSolution() {
		int y = 0;
		lines = files.readString();

		for (int i = 0; i < OptionGame.NUMBER_LEVEL; i++) {

			y = lines.indexOf("\n");
			if (y != -1) {
				solution[i] = lines.substring(0, y - 1);

				lines = lines.substring(y + 1, lines.length());

			} else if (y == -1) {
				solution[i] = lines;
			}

		}
	}

}
