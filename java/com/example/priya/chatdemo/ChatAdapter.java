package com.example.priya.chatdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends ArrayAdapter<DataProvider> {

    private List<DataProvider> chat_list=new ArrayList<DataProvider>();
    private TextView CHAT_TXT;
    Context CTX;
    String msg;
    public ChatAdapter(Context context, int resource) {
        super(context, resource);
        CTX=context;
    }

    public void add(DataProvider object)
    {
        msg=object.message;
        Log.d("Input",msg);
        Encryption e=new Encryption();
        Decryption d=new Decryption();
        String pass=e.getPass();
        Log.d("Pass",pass);
        int passLen=e.passLen(pass);
        Log.d("passLen", String.valueOf(passLen));
        try {
            object.message=e.encrypt(msg,pass);
            Log.d("Encrypt:",object.message);
            String encryptedText=object.message;
            String plain=d.decrypt(encryptedText,passLen);
            Log.d("Decrypt:",plain);
            d.setPlain(plain);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        chat_list.add(object);
        super.add(object);
    }
    public int getCount()
    {
        return chat_list.size();
    }

    @Override
    public DataProvider getItem(int position) {
        return chat_list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater= (LayoutInflater) CTX.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.single_message_layout,parent,false);
        }
        CHAT_TXT=(TextView)convertView.findViewById(R.id.singleMessage);
        String Message;
        boolean POSITION;
        DataProvider provider=getItem(position);
        Message=provider.message;
        POSITION=provider.position;
        CHAT_TXT.setText(Message);
        CHAT_TXT.setBackgroundResource(POSITION ? R.color.lightBlue : R.color.lightPink);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if(!POSITION)
        {
            params.gravity= Gravity.RIGHT;
        }
        else
        {
            params.gravity=Gravity.LEFT;
        }
        CHAT_TXT.setLayoutParams(params);
        return convertView;
    }
}
