package com.example.priya.chatdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import java.io.IOException;


public class Encryption {
    private String pass;
    private String input;
    private int passLen;
    public Encryption() {
        pass=Password.password;
        input="";
        passLen=0;
    }
    public Encryption(String p) {
        pass=p;
        input="";
        passLen=0;
    }

    public String getPass()
    {
        return pass;
    }
    public void setPass(String pass)
    {
        this.pass=pass;
    }
    public void setPassLen(String pass)
    {
        this.passLen=pass.length();
    }
    public int passLen(String pass)
    {
        return pass.length();
    }
    public String getInput()
    {
        return input;
    }
    public void setInput(String input)
    {
        this.input=input;
    }

    public String encrypt(String contents,String password) throws IOException
    {
        String inputText="";
        contents = new StringBuffer(contents).reverse().toString();
        inputText=contents+password;
        int passLen=password.length();
        int len2=inputText.length();

        char chr;
        String cipher="",str="",res="";

        Fibonacci f=new Fibonacci();
        int fibo[]=new int[passLen];
        fibo=f.fiboArray(passLen);

        cipher=inputText;
        for(int i=0;i<passLen;i++)
        {
            int num=fibo[i];
            for(int j=1;j<=num;j++)
            {
                str="";
                for(int k=0;k<len2;k++)
                {
                    if(k%2==0)
                    {
                        if(((char)(cipher.charAt(k)))=='z')
                            chr='a';
                        else if(((char)(cipher.charAt(k)))=='Z')
                            chr='A';
                        else
                            chr=(char)(cipher.charAt(k)+1);
                    }
                    else
                    {
                        if(((char)(cipher.charAt(k)))=='a')
                            chr='z';
                        else if(((char)(cipher.charAt(k)))=='A')
                            chr='Z';
                        else
                            chr=(char)(cipher.charAt(k)-1);
                    }
                    str+=chr;
                }
                cipher=str;
            }
            res=cipher;
        }

        return res;
    }
}

