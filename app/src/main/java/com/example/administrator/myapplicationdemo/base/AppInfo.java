package com.example.administrator.myapplicationdemo.base;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/20.
 */

public class AppInfo implements Serializable {
    private String packageName;//����
    private String label;//����
    private long firstInstallTime;//��װʱ��
    private String versionName;//�汾��
    private int versionCode;//�汾��
    private Drawable icon;//Ӧ��ͼ��
    private boolean ischecked;

    public AppInfo(String packageName, String label, long firstInstallTime, String versionName, int versionCode, Drawable icon) {
        this.packageName = packageName;
        this.label = label;
        this.firstInstallTime = firstInstallTime;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.icon = icon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getFirstInstallTime() {
        return firstInstallTime;
    }

    public void setFirstInstallTime(long firstInstallTime) {
        this.firstInstallTime = firstInstallTime;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public boolean isChecked() {
        return ischecked;
    }

    public void setChecked(boolean isChecked) {
        this.ischecked = isChecked;
    }
}
