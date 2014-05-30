package com.mygdx.Option;

public class OptionGame {
	// broad
	public final static int WALL = 1;
	public final static int GOAL = 2;
	public final static int BOX = 3;
	public final static int PERSON = 4;
	public final static int PLACE_BOX = 5;
	public final static int PERSON_ON_GOAL = 6;
	public final static int FLOOR = 7;
	public final static int NULL = 0;
	
	// map
	public final static String STR_WALL = "#";
	public final static String STR_GOAL = ".";
	public final static String STR_BOX =  "$";
	public final static String STR_PERSON = "@";
	public final static String STR_BOX_GOAL = "*";
	public final static String STR_PERSON_GOAL = "?";
	public final static String STR_FLOOR = "-";
	public final static String STR_BREAK_LINE = "n";
	
	// 
	public final static int WIDTH_BROAD = 14;
	public final static int HEIGHT_BROAD = 9;
	
	//
	public final static String PART_LEVEL = "data/level.txt";
	public final static int NUMBER_LEVEL = 150;
	
	//
	public final static int SIZE_WIDTH_ANIMATION = 40;
	public final static int SIZE_HEIGHT_ANIMATION = 40;
	
	//
	public final static int NUM_BUTTON_LEVEL = 20;
	public final static int AROUND_LEVEL = 5;
	public final static int NUM_AROUND = 4;
	
	//
	public final static int SCREEN_WIDTH = 600;
	public final static int SCREEN_HEIGHT = 390;
	
	public static float volume = 1f;

	public final static int RIGHT_BOX = 0;
	public final static int LEFT_BOX = 1;
	public final static int TOP_BOX = 2;
	public final static int BOTTOM_BOX = 3;
	public final static int RIGHT = 4;
	public final static int LEFT = 5;
	public final static int TOP = 6;
	public final static int BOTTOM = 7;
	
	
	public static int levelSave=10;
}