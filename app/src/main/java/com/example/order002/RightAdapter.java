package com.example.order002;

import android.content.Context;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by Vin on 08/12/2016.
 */
class Form implements Serializable{
    //private int fid;
    private String fname;
    private double fprice;
    private int fnum;

    public Form(String fname, double fprice, int fnum) {
        this.fname = fname;
        this.fprice = fprice;
        this.fnum = fnum;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public double getFprice() {
        return fprice;
    }

    public void setFprice(double fprice) {
        this.fprice = fprice;
    }

    public int getFnum() {
        return fnum;
    }

    public void setFnum(int fnum) {
        this.fnum = fnum;
    }
}



public class RightAdapter extends BaseAdapter implements StickyListHeadersAdapter {


    List<Form> list_form = new ArrayList<>();
    private int j=0;
    private Context mContext;
    private RightAdapterCallback mCallback;
    private List<Model.SubModel> mData;

    private LayoutInflater mInflater;
    private double sum;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public RightAdapter(@NonNull Context mContext, @NonNull List<Model.SubModel> mData, @NonNull RightAdapterCallback callback) {
        this.mContext = mContext;
        this.mData = mData;
        this.mCallback = callback;
        mInflater = LayoutInflater.from(mContext);
    }

    public void update(int position,Model.SubModel model){
        mData.set(position,model);
        notifyDataSetChanged();
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.right_header,null);
            holder = new HeaderViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (HeaderViewHolder) convertView.getTag();
        }
        holder.title.setText(mData.get(position).getcName());

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return mData.get(position).getcId();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {



        ViewHolder holder;
        if (null == view) {
            view = mInflater.inflate(R.layout.right_item,null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        final Model.SubModel sm = mData.get(i);
        holder.name.setText(sm.getName());
        //holder.price.setText(String.format(holder.price.getText().toString(),sm.getprice()));
        holder.price.setText(sm.getprice()+"元");
        holder.num.setText(String.valueOf(sm.getNum()));


        Form form = new Form(sm.getName(),sm.getprice(),sm.getNum());


        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ckfckf",i+":"+mData.get(i).getName());
                sum+=sm.getprice();

                mCallback.onClickNumButton(i,true);
                list_form.add(form);

            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sm.getNum() > 0) {
                    //Log.d("ckfckf",i+":"+mData.get(i).getName());
                    sum-=sm.getprice();
                    mCallback.onClickNumButton(i, false);
                    //Log.d("ckfckf","num="+mData.get(i).getNum());
                    for(int i=0;i<list_form.size();i++)
                    {
                        if(list_form.get(i).getFname().equals(sm.getName()))
                        {
                            list_form.remove(i);
                            break;
                        }
                    }
                }
            }
        });

        return view;
    }

    List<Form>  getlist(){
        return list_form;
    }



    class ViewHolder {
        TextView name;
        TextView price;
        Button add;
        Button remove;
        TextView num;

        public ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
            add = (Button) view.findViewById(R.id.add);
            remove = (Button) view.findViewById(R.id.remove);
            num = (TextView) view.findViewById(R.id.num);
        }
    }

    class HeaderViewHolder {
        TextView title;
        public HeaderViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.header_title);
        }
    }
}
