package chatlv.namespace;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity
{
	EditText etusr;
	EditText etpwd;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		etusr = (EditText) findViewById(R.id.editText1);
		etpwd = (EditText) findViewById(R.id.editText2);
		Button go = (Button) findViewById(R.id.button1);
		etusr.setHint("Username");
		etpwd.setHint("Password");
		go.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) 
			{
				Intent intent = new Intent(Login.this, User.class);
				String username = etusr.getText().toString();
				String password = etpwd.getText().toString();
				intent.putExtra("username", username);
				intent.putExtra("password", password);
				startActivity(intent);
				
			}
		});
		
	}

}
