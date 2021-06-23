package com.example.order002;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class FormAdapter extends BaseAdapter {

    private List<Form> fData;
    private Context fContext;
    private LayoutInflater fInflater;


    public FormAdapter(@NonNull Context fContext, @NonNull List<Form> fData) {
        this.fContext = fContext;
        this.fData = fData;
        fInflater = LayoutInflater.from(fContext);
    }
    @Override
    public int getCount() {
        return fData.size();
    }

    @Override
    public Object getItem(int i) {
        return fData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        FormAdapter.ViewHolder holder;
        if (null == view) {
            view = fInflater.inflate(R.layout.form_item,null);
            holder = new FormAdapter.ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (FormAdapter.ViewHolder) view.getTag();
        }

        Form form = fData.get(i);
        holder.fname.setText(form.getFname());
        //holder.fnum.setText(form.getFnum()+1+"");
        holder.fprice.setText(form.getFprice()+"");

        return view;
    }


    class ViewHolder {

        TextView fname;
        TextView fnum;
        TextView fprice;
        public ViewHolder(View view) {
            fname = (TextView) view.findViewById(R.id.fname);
            fnum = (TextView) view.findViewById(R.id.fnum);
            fprice = (TextView) view.findViewById(R.id.fprice);
        }
    }
}
