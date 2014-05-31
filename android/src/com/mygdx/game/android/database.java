package com.mygdx.game.android;

import java.nio.file.OpenOption;

import javax.swing.text.AbstractDocument.Content;

import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {

	private static final int DatabaseVersion = 1;
	private static final String DatabaseName = "atomic";
	private static final String TableUser = "user";
	private static final String KeyId = "id";
	private static final String KeyName = "name";
	private static final String KeyPassword = "password";

	public database(Context context) {
		super(context, DatabaseName, null, DatabaseVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String Create_User_Table = "CREATE TABLE " + TableUser + "(" + KeyId
				+ " INTEGER PRIMARY KEY, " + KeyName + " TEXT, " + KeyPassword
				+ " TEXT" + ")";
		db.execSQL(Create_User_Table);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TableUser);
		onCreate(db);
	}

	public void addUser(user user_) {
		SQLiteDatabase db = this.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put(KeyName, user_.getName());
		values.put(KeyPassword, user_.getPassword());

		db.insert(TableUser, null, values);
		db.close();

	}

	public user getUser(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TableUser, new String[] { KeyId, KeyName,
				KeyPassword }, KeyId + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();
		user user_ = new user(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));
		return user_;

	}

	public user getUser(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TableUser, new String[] { KeyId, KeyName,
				KeyPassword }, KeyName + "=?",
				new String[] { String.valueOf(name) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		user user_ = new user(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));
		db.close();
		return user_;
	}

	public void updateUser(user user_) {
		SQLiteDatabase db = this.getReadableDatabase();

		ContentValues values = new ContentValues();
		values.put(KeyName, user_.getName());
		values.put(KeyPassword, user_.getPassword());

		db.update(TableUser, values, KeyId + " = ?",
				new String[] { String.valueOf(user_.getID()) });

	}

	public void deleteUser(user user_) {
		SQLiteDatabase db = this.getReadableDatabase();
		db.delete(TableUser, KeyId + " = ?",
				new String[] { String.valueOf(user_.getID()) });
		db.close();
	}

	public int checkUser(String userName) {
		// TODO Auto-generated method stub
		int check = 0;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TableUser, new String[] { KeyId, KeyName,
				KeyPassword }, KeyName + "=?",
				new String[] { String.valueOf(userName) }, null, null, null,
				null);

		if (cursor != null)
			cursor.moveToFirst();
		user user_ = new user(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));
		db.close();
		if (user_.getName().equals(userName)) {
			check = 1;
		}

		return check;
	}

	
}
