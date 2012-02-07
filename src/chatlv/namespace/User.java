package chatlv.namespace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class User extends Activity
{
	ArrayAdapter<String> adapter;
	ListView lv;
	EditText et;
	String user2chat;
	String username;
	String password;
	Button ok;
	Button go;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user);
		username = getIntent().getExtras().getString("username");
        password = getIntent().getExtras().getString("password");
		adapter =  new ArrayAdapter<String>(User.this, android.R.layout.simple_list_item_single_choice);
		adapter.add("loreti@ppl.eln.uniroma2.it");
		et = (EditText)findViewById(R.id.editText1);
		lv = (ListView) findViewById(android.R.id.list);
		lv.setAdapter(adapter);
		ok = (Button) findViewById(R.id.button1);
		go = (Button) findViewById(R.id.button2);
		ok.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				adapter.add(et.getText().toString());
			}
		});
		go.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(User.this, ChatLVActivity.class);
		        intent.putExtra("username", username);
		        intent.putExtra("password", password);
		        TextView user = (TextView)findViewById(lv.getCheckedItemPosition());
		        intent.putExtra("touser", user.getText().toString());
			}
		});
	}
}
