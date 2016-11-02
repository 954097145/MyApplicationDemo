package com.example.administrator.myapplicationdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.administrator.myapplicationdemo.base.AppAdapter;
import com.example.administrator.myapplicationdemo.base.AppInfo;
import com.example.administrator.myapplicationdemo.base.AppManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/20.
 */

public class AppListActivity extends AppCompatActivity implements View.OnClickListener {
    private AppAdapter appAdapter;
    private Handler mhandler;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        findViewById(R.id.button).setOnClickListener(this);
        ListView listView= (ListView) findViewById(R.id.listView);
        appAdapter=new AppAdapter();
        listView.setAdapter(appAdapter);
        mhandler=new MyHandler(this);
        asyncLoadData();
    }

    private void asyncLoadData() {
        dialog=new ProgressDialog(this);
        dialog.setMessage("正在加载");
        dialog.setCancelable(false);
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<AppInfo> alllist= AppManager.getInstance(AppListActivity.this).getAllList(false);
                Message msg=Message.obtain();
                Bundle bundle=new Bundle();
                bundle.putSerializable("key", alllist);
                msg.what = 1;
                msg.setData(bundle);
                mhandler.sendMessage(msg);
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,AppList2Activity.class));
    }


    private static class MyHandler extends Handler {
        WeakReference<AppListActivity> mActivityReference;
        MyHandler(AppListActivity activity){
            mActivityReference=new WeakReference<AppListActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final AppListActivity activity=mActivityReference.get();
            if (activity!=null){
                if (msg.what==1){
                    activity.dialog.dismiss();
                    ArrayList<AppInfo> appInfos= (ArrayList<AppInfo>) msg.getData().getSerializable("key");
                    activity.appAdapter.setDatalist(appInfos);
                    activity.appAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
