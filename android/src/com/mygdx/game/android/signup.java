package com.mygdx.game.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends Activity implements OnClickListener {

	private EditText nameEditText;
	private EditText passEditText;
	private EditText verifyPassEditText;
	private Button btnSignUp;
	private Button btnBackLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logon);
		nameEditText = (EditText) findViewById(R.id.editText_UseName);
		passEditText = (EditText) findViewById(R.id.editText_pass_2);
		verifyPassEditText = (EditText) findViewById(R.id.editText_verifyPassword);
		btnSignUp = (Button) findViewById(R.id.btnSignup);
		btnSignUp.setOnClickListener(this);
		btnBackLogin = (Button) findViewById(R.id.btnBackLogin);
		btnBackLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnSignup:

			database db = new database(this);
			String userName = nameEditText.getText().toString();
			String userPassword = passEditText.getText().toString();
			String userVerify = verifyPassEditText.getText().toString();
			user user_ = new user(userName, userPassword);

			int check = 0;
			if (userName.equals("") | userPassword.equals("")) {
				Toast toast3 = Toast.makeText(signup.this,
						"Name or Password: empty", Toast.LENGTH_SHORT);
				toast3.show();
			} else {
				try {
					check = db.checkUser(userName);
				} catch (Exception e) {
					// TODO: handle exception
					// co the tao nick
				}
				if (check == 1) {
					Toast toast2 = Toast.makeText(signup.this,
							"This name is already registered !!!",
							Toast.LENGTH_SHORT);
					toast2.show();
				} else {
					if (!userPassword.equals(userVerify)) {
						Toast toast = Toast.makeText(signup.this,
								"Password not same Verify Password",
								Toast.LENGTH_SHORT);
						toast.show();
					} else {
						db.addUser(user_);
						Toast toast3 = Toast.makeText(signup.this,
								"Registered !!!", Toast.LENGTH_SHORT);
						toast3.show();
						Intent in = new Intent(getApplicationContext(),
								login.class);
						startActivity(in);
					}
				}
			}
			break;
		case R.id.btnBackLogin:
			Intent in = new Intent(getApplicationContext(),
					login.class);
			startActivity(in);
			break;
		}
	}
}
