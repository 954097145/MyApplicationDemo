package com.example.administrator.myapplicationdemo.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter{
protected List<T> datalist;

    public List<T> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<T> datalist) {
        this.datalist = datalist;
    }
    public MyBaseAdapter(){
        super();
        datalist=new ArrayList<T>();
    }
    public MyBaseAdapter(List<T> dataList) {
        super();
        this.datalist = dataList;
    }

    @Override
    public int getCount() {
        return datalist==null?0:datalist.size();
    }

    @Override
    public T getItem(int position) {
        return datalist==null?null:datalist.get(position);
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    // TODO Auto-generated method stub
    // �����������ǵ���һ��item���֣����ҽ� ���϶�Ӧλ���ϵĶ�������ԺͲ����еĿؼ����а�

    // �������
    public void addData(T t) {
        datalist.add(t);
    }

    // ɾ������
    public void removeData(T t) {
        datalist.remove(t);
    }

    // ������ݼ���
    public void addDataList(List<T> mList) {
        if (mList == null) {
            return;
        }
        datalist.addAll(mList);
    }

    // ɾ�����ݼ���
    public void removeDataList(List<T> mList) {
        if (mList == null) {
            return;
        }
        datalist.removeAll(mList);
    }

    //���
    public void clear() {
        datalist.clear();
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

}
