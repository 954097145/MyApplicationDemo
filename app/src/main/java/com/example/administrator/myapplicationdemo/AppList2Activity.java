package com.example.administrator.myapplicationdemo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.myapplicationdemo.base.AppAdapter;
import com.example.administrator.myapplicationdemo.base.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */

public class AppList2Activity extends AppCompatActivity {
    ListView listView;
    AppAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list2);
        listView= (ListView) findViewById(R.id.listView);
        adapter=new AppAdapter();
        listView.setAdapter(adapter);
        LoadAppListTask task = new LoadAppListTask();
    }

    private class LoadAppListTask extends AsyncTask<Integer,AppInfo,ArrayList<AppInfo>>{

        @Override
        protected void onProgressUpdate(AppInfo... values) {
            adapter.addData(values[0]);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(ArrayList<AppInfo> appInfos) {
            Toast.makeText(AppList2Activity.this,""+appInfos.size(),Toast.LENGTH_SHORT).show();
        }

        @Override
        protected ArrayList<AppInfo> doInBackground(Integer... params) {
            PackageManager pm=getPackageManager();
            ArrayList<AppInfo> list=new ArrayList<>();
            List<PackageInfo> packages=pm.getInstalledPackages(PackageManager.GET_ACTIVITIES);
            for (int i=0;i<packages.size();i++){
                PackageInfo packageInfo = packages.get(i);
                String packageName = packageInfo.packageName;
                String label = packageInfo.applicationInfo.loadLabel(pm).toString();
                Drawable icon = packageInfo.applicationInfo.loadIcon(pm);
                long firstInstallTime = packageInfo.firstInstallTime;
                int versionCode = packageInfo.versionCode;
                String versionName = packageInfo.versionName;
                AppInfo appInfo = new AppInfo(packageName, label, firstInstallTime, versionName, versionCode, icon);
                list.add(appInfo);
                publishProgress(appInfo);//可选，非必须
            }
            return list;
        }
    }
}
