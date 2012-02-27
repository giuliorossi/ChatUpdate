package chatlv.namespace;


import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class ChatLVActivity extends Activity
{
	ArrayAdapter<String> adapter;
	EditText etext;
	ListView lv;
	Connection connection;
	String username;
	String password;
	String touser;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        username = getIntent().getExtras().getString("username");
        password = getIntent().getExtras().getString("password");
        touser = getIntent().getExtras().getString("touser");
        etext = (EditText) findViewById(R.id.editText1);
        lv = (ListView) findViewById(android.R.id.list);
        adapter = new ArrayAdapter<String>(ChatLVActivity.this, android.R.layout.simple_list_item_1);
        lv.setAdapter(adapter);
        adapter.add("inizio chat");
		Button btn = (Button) findViewById(R.id.button1);
		try {
			ConnectionConfiguration config = new ConnectionConfiguration("ppl.eln.uniroma2.it", 5222);
			config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
			connection = new XMPPConnection(config);
			connection.connect();
			connection.login(username, password);
			
			connection.addPacketListener(new PacketListener() {

				@Override
				public void processPacket(Packet pkt) {
					Message msg = (Message) pkt;
					String from = msg.getFrom();
					String body = msg.getBody();
					Log.d("MSG RICV", msg.getFrom()+" : "+msg.getBody());
					adapter.add(from + " : " + body + "\n");
					lv.setSelection(adapter.getCount()-1);
				}
			}, new MessageTypeFilter(Message.Type.normal));
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				adapter.add("ME: " + etext.getText().toString() + "\n");
				lv.setSelection(adapter.getCount()-1);
				try
				{
					Message msg = new Message();
					//msg.setTo("loreti@ppl.eln.uniroma2.it");
					msg.setTo(touser);
					msg.setBody(etext.getText().toString());
					Log.d("MSG SENT", msg.getTo()+" : "+msg.getBody());
					connection.sendPacket(msg);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "IMPOSSIBILE STABILIRE UNA CONNESSIONE", Toast.LENGTH_LONG).show();
				}

			}
		});
       


    }
    
}