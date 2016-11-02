package com.example.administrator.myapplicationdemo.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */

public class AppManager {
    private ArrayList<AppInfo> alllist,systemlist,userlist;
    private PackageManager pm;

    private AppManager(Context context) {
        super();
        pm = context.getPackageManager();
    }

    // 2.�ṩһ����̬��˽�еı�����󣬺͹��е�һ����ȡ����
    private static AppManager am;

    public static AppManager getInstance(Context context) {
        if (am == null) {
            am = new AppManager(context);
        }
        return am;
    }
    // 2

    public ArrayList<AppInfo> getAllList(boolean isUpdate) {
        if (alllist == null||isUpdate) {
            initList();
        }
        return alllist;
    }

    public ArrayList<AppInfo> getSystemList(boolean isUpdate) {
        if (systemlist == null||isUpdate) {
            initList();
        }
        return systemlist;
    }

    public ArrayList<AppInfo> getUserList(boolean isUpdate) {
        if (userlist == null||isUpdate) {
            initList();
        }
        return userlist;
    }

    // ��ʼ�� ����һ����ʱ����
    private void initList() {
        alllist = new ArrayList<AppInfo>();
        systemlist = new ArrayList<AppInfo>();
        userlist = new ArrayList<AppInfo>();
        //
        List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        for (PackageInfo packageInfo : packages) {

            // ����
            String packageName = packageInfo.packageName;
            // Ӧ�õı���
            String label = packageInfo.applicationInfo.loadLabel(pm).toString();
            // ͼ��
            Drawable icon = packageInfo.applicationInfo.loadIcon(pm);
            // �״ΰ�װʱ��
            long firstInstallTime = packageInfo.firstInstallTime;
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;

            AppInfo appInfo = new AppInfo(packageName, label, firstInstallTime, versionName, versionCode, icon);
            alllist.add(appInfo);
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                systemlist.add(appInfo);
            } else {
                userlist.add(appInfo);
            }
        }
    }
}
