package com.example.priya.chatdemo;

/**
 * Created by Shubham on 24-11-2016.
 */
public class Fibonacci {

    public int[] fiboArray(int passLen)
    {
        int a=1,b=1,c;
        int fibo[]=new int[passLen];

        fibo[0]=a;
        fibo[1]=b;
        for(int i=2;i<passLen;i++)
        {
            c=a+b;
            fibo[i]=c;
            a=b;
            b=c;
        }
        return fibo;
    }
}
