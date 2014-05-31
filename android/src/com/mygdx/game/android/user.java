package com.mygdx.game.android;

public class user {
	int _id;
	String _name;
	String _password;

	public user() {

	}

	public user(int id, String name, String password) {
		this._id = id;
		this._name = name;
		this._password = password;
	}

	public user(String name, String password) {

		this._name = name;
		this._password = password;
	}

	public int getID() {
		return this._id;
	}

	public void setID(int id) {
		this._id = id;
	}

	public String getName() {
		return this._name;
	}

	public void setName(String name) {
		this._name = name;
	}
	public String getPassword() {
		return this._password;
	}
	public void setPassword(String password) {
		this._password = password;
	}

}
