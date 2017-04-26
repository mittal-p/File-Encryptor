package com.example.priya.chatdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordReset extends Activity {
    EditText password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_reset);
        password=(EditText)findViewById(R.id.pass);
        submit=(Button)findViewById(R.id.submit);

    }

    public void SubmitBtn(View v)
    {
        String p=password.getText().toString();
        setPreferences(p);
        Toast.makeText(getApplicationContext(),"Successfully password changed",Toast.LENGTH_LONG).show();
        Intent in=new Intent(this,MainActivity.class);
        startActivity(in);
        finish();
    }

    void setPreferences(String password )
    {
        SharedPreferences sp=this.getSharedPreferences("password",MODE_WORLD_READABLE);
        SharedPreferences.Editor spEditor=sp.edit();
        spEditor.clear();
        spEditor.putString("pass",password);
        spEditor.commit();
    }
}
