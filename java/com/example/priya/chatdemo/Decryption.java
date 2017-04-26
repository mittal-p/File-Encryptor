package com.example.priya.chatdemo;

import java.io.IOException;


public class Decryption {
    public static String plain;

    public Decryption() {
      plain="";
    }

    public String getPlain()
    {
        return plain;
    }
    public void setPlain(String plain)
    {
        this.plain=plain;
    }


    public String decrypt(String cipher,int passLen) throws IOException
    {

        int len2=cipher.length();


        char chr;
        String plain="",str="",res="";

        Fibonacci f=new Fibonacci();
        int fibo[]=new int[passLen];
        fibo=f.fiboArray(passLen);

        for(int i=0;i<passLen;i++)
        {
            int num=fibo[i];
            for(int j=1;j<=num;j++)
            {
                str="";
                for(int k=0;k<len2;k++)
                {
                    if(k%2!=0)
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

        //System.out.println("cipher text:"+res);

        int i,k;
        for(i=0;i<(len2-passLen);i++)
            plain+=res.charAt(i);
        plain = new StringBuffer(plain).reverse().toString();

        return  plain;

    }
}
