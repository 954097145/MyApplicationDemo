package com.example.administrator.myapplicationdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Handler handler;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1= (TextView) findViewById(R.id.textview1);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        textView1.setText("当前i="+msg.arg1);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        };
        asyncSendData();
    }

    private void asyncSendData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i<200){
                    try {
                        Thread.sleep(10); i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Message msg=Message.obtain();
                    msg.what=1;
                    msg.arg1=i;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }
}
