package com.mygdx.game.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends Activity implements OnClickListener {

	private EditText nameEditText;
	private EditText passEditText;
	private Button loginButton;
	private Button createAccount;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		nameEditText = (EditText) findViewById(R.id.editText_name);
		passEditText = (EditText) findViewById(R.id.editText_pass);

		loginButton = (Button) findViewById(R.id.btnlogin);
		loginButton.setOnClickListener(this);
		createAccount = (Button) findViewById(R.id.btnCreateAccount);
		createAccount.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnlogin:
			database db = new database(this);
			user user_ = new user();
			String userName = nameEditText.getText().toString();
			String userPassword = passEditText.getText().toString();
			// check user name da dang ky chua
			int check = 0;
			if (userName.equals("") | userPassword.equals("")) {
				Toast toast3 = Toast.makeText(login.this,
						"Name or Password: empty", Toast.LENGTH_SHORT);
				toast3.show();
			} else {
				try {
					check = db.checkUser(userName);
				} catch (Exception e) {
					// TODO: handle exception
					// sai ten dang nhap
					Toast toast = Toast
							.makeText(
									login.this,
									"Name Not Registered !!! Create new account please !!!",
									Toast.LENGTH_SHORT);
					toast.show();
				}
				if (check == 1) {
					user_ = db.getUser(userName);
					String text = user_.getPassword();
					if (userPassword.equals(text)) {
						Intent in = new Intent(getApplicationContext(),
								AndroidLauncher.class);
//						in.putExtra("UserName", userName);
						startActivity(in);
					} else {
						Toast toast2 = Toast.makeText(login.this,
								"Wrong Password", Toast.LENGTH_SHORT);
						toast2.show();
					}
				}
			}

			break;
		case R.id.btnCreateAccount:
			Intent in_2 = new Intent(getApplicationContext(), signup.class);
			startActivity(in_2);
			break;
		}
	}
}
