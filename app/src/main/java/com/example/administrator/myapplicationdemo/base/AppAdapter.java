package com.example.administrator.myapplicationdemo.base;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplicationdemo.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */

public class AppAdapter extends MyBaseAdapter<AppInfo>{
    LruCache<String, Bitmap> lruCache; //

    public AppAdapter() {
        super();
        initCache();
    }

    public AppAdapter(List<AppInfo> dataList) {
        super(dataList);
        initCache();
    }

    private void initCache() {
        lruCache = new LruCache<String, Bitmap>(5 * 1024 * 1024) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                // TODO Auto-generated method stub
                return value.getByteCount();
            }
        };

    }
    private boolean isFling;//

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.layout_item_app, null);
            holder.imageView1 = (ImageView) convertView.findViewById(R.id.imageView1);
            holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
            holder.textView2 = (TextView) convertView.findViewById(R.id.textView2);
            holder.textView3 = (TextView) convertView.findViewById(R.id.textView3);
            holder.checkBox1 = (CheckBox) convertView.findViewById(R.id.checkBox1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AppInfo appInfo = getItem(position);

        holder.checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                appInfo.setChecked(isChecked);
            }
        });
        holder.checkBox1.setChecked(appInfo.isChecked());
        if (isFling) {
        } else {



            Bitmap bitmap = getBitmap(appInfo);
            holder.imageView1.setImageBitmap(bitmap);

        }
        holder.textView1.setText(appInfo.getLabel());
        holder.textView2.setText(appInfo.getPackageName());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        holder.textView3.setText(df.format(new Date(appInfo.getFirstInstallTime())));
        return convertView;
    }

    private Bitmap getBitmap(final AppInfo appInfo) {
        String packageName=appInfo.getPackageName();
        Bitmap bitmap=lruCache.get(packageName);
        if (bitmap==null) {
            //Դ��ַȥ��ȡͼƬ
            Drawable icon = appInfo.getIcon();
            BitmapDrawable bd=(BitmapDrawable) icon;
            bitmap = bd.getBitmap();
            lruCache.put(packageName, bitmap);
        }else{
        }
        return bitmap;
    }
    public boolean isFling() {
        return isFling;
    }

    public void setFling(boolean isFling) {
        this.isFling = isFling;
    }

    private class ViewHolder {
        ImageView imageView1;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        CheckBox checkBox1;
    }
}
