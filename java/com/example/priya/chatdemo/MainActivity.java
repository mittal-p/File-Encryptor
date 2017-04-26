package com.example.priya.chatdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    View v;
    ListView listView;
    EditText chat_text;
    Button SEND;
    boolean position=false;
    ChatAdapter adapter;
    Context ctx=this;
    EditText password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = getLayoutInflater().inflate(R.layout.activity_main, null, false);
        setContentView(v);
        getPreferences();

        listView = (ListView) findViewById(R.id.chat_list_view);
        chat_text = (EditText) findViewById(R.id.chatTxt);
        SEND = (Button) findViewById(R.id.send_button);
        adapter = new ChatAdapter(ctx, R.layout.single_message_layout);
        listView.setAdapter(adapter);
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(adapter.getCount() - 1);
            }
        });

        SEND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add(new DataProvider(position, chat_text.getText().toString()));
                position = !position;
                chat_text.setText("");
            }
        });
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu)
        {
            getMenuInflater().inflate(R.menu.main,menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int color=0;
            View v1;

            switch (item.getItemId())
            {
                case R.id.password:
                    Intent in=new Intent(MainActivity.this,PasswordReset.class);
                    startActivity(in);
                    getPreferences();
                    finish();

                   /* v1= getLayoutInflater().inflate(R.layout.password_reset, null, false);
                    setContentView(v1);

                    password=(EditText)findViewById(R.id.pass);
                    submit=(Button)findViewById(R.id.submit);
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String p = password.getText().toString();
                            Encryption e = new Encryption();
                            e.setPass(p);
                            Toast.makeText(getApplicationContext(),"Successfully password changed",Toast.LENGTH_LONG).show();
                        }

                    });*/

                    break;

                case R.id.decrypt:
                    String plainText=Decryption.plain;
                    Toast.makeText(getApplicationContext(),plainText,Toast.LENGTH_LONG).show();
                    break;
            }
            return  true;
        }
    private void getPreferences()
    {
        SharedPreferences sp=this.getSharedPreferences("password", Context.MODE_WORLD_READABLE);
        Password.password= sp.getString("pass","xyz");
        Log.d("message","get Pref called. pass is : "+ sp.getString("pass","xyz"));
    }
    }

