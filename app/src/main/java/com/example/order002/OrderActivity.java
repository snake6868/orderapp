package com.example.order002;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;


public class OrderActivity extends AppCompatActivity {

    ImageView mBg;
    ImageView back;

    TextView mTitle,mSubTitle;

    LinearLayout mLists;
    FrameLayout.LayoutParams mListsParams;

    ListView mLeftListView;
    LeftAdapter mLeftAdapter;
    StickyListHeadersListView mRightListView;
    RightAdapter mRightAdapter;
    TextView youhui;
    TextView tv_sum;
    List<Model> mModelList;
    List<Model.SubModel> mSubModelList;
    private MyDBHelper dbHelper;

    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        dbHelper = new MyDBHelper(this, "UserStore.db", null, 1);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.argb(100,0,0,0));
        }


        initData();
        initViews();

    }

    private void initData() {

        mModelList = Model.initData();

        mSubModelList = new ArrayList<>();

        for (Model m : mModelList) {
            for (Model.SubModel sm : m.getSubModelList()) {
                sm.setcId(m.getcId());
                sm.setcName(m.getcName());
                mSubModelList.add(sm);
            }
        }
    }

    private void initViews() {

        tv_sum=findViewById(R.id.tv_sum);
        mBg = (ImageView) findViewById(R.id.bg);
        mBg.setImageBitmap(Tools.blurBitmap(this, BitmapFactory.decodeResource(this.getResources(),R.drawable.bg),20));

        mTitle = (TextView) findViewById(R.id.title);
        mSubTitle = (TextView) findViewById(R.id.subtitle);


        mLists = (LinearLayout) findViewById(R.id.lists);
        mListsParams = (FrameLayout.LayoutParams) mLists.getLayoutParams();

        mLeftListView = (ListView) findViewById(R.id.left_list);
        mLeftAdapter = new LeftAdapter(this,mModelList);
        mLeftListView.setAdapter(mLeftAdapter);


        mRightListView = (StickyListHeadersListView) findViewById(R.id.right_list);

        mRightAdapter = new RightAdapter(this,mSubModelList,mCallback);

        mRightListView.setAdapter(mRightAdapter);

        mLeftListView.setOnTouchListener(new ListParentOnTouchListener(mDelegate));
        mRightListView.setOnTouchListener(new ListParentOnTouchListener(mDelegate));

        youhui = findViewById(R.id.order_youhui);
        SQLiteDatabase db= dbHelper.getReadableDatabase();

        Cursor cursor = db.query("youhuiData1", null, null, null, null, null, null);
        double maxp=0,youhuip=0;

        if(cursor.moveToLast())
        {
            maxp=cursor.getDouble(cursor.getColumnIndex("max"));
            youhuip=cursor.getDouble(cursor.getColumnIndex("youhui"));
            youhui.setText("满"+maxp+"减"+youhuip);
        }

        tv_sum.setText("¥"+mRightAdapter.getSum());





        mLeftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mLeftAdapter.updateSelected(i);
                mRightListView.setSelection(getRightPosition(i));
            }
        });

        mRightListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                try {
                    long cId = mRightAdapter.getHeaderId(firstVisibleItem);
                    mLeftAdapter.updateSelected(getLeftPosition(cId));

                } catch (Exception e) {

                }
            }
        });
        Intent intent =getIntent();
        /*取出Intent中附加的数据*/
        String username = intent.getStringExtra("username");
        String sum = intent.getStringExtra("sum");
        btn_submit=findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Form>form_list = mRightAdapter.getlist();
                Intent intent = new Intent();
                intent.putExtra("formlist1",(Serializable)form_list);
                intent.putExtra("sum",mRightAdapter.getSum()+"");
                intent.putExtra("username",username+"");
                intent.setClass(OrderActivity.this, FormActivity.class);
                startActivity(intent);
                finish();
            }
        });

        back=findViewById(R.id.order_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                List<Form> form = (List<Form>) getIntent().getSerializableExtra("formlist2");
                intent.putExtra("formlist2",(Serializable)form);

                intent.setClass(OrderActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private int getRightPosition(int leftPosition){
        int position = 0;
        String typename = mModelList.get(leftPosition).getcName();
        for(int i=0;i<mSubModelList.size();i++){
            String tm = mSubModelList.get(i).getcName();
            if(tm.equals(typename)){
                position = i;
                break;
            }
        }
        return position;
    }

    private int getLeftPosition(long cId){
        int position = 0;
        for (int i = 0;i<mModelList.size();i++){
            if (mModelList.get(i).getcId() == cId) {
                position = i;
                break;
            }
        }
        return position;
    }

    TouchDelegate mDelegate = new TouchDelegate() {
        @Override
        void onTouchDone(View view,TouchOrientation orientation,float offset) {
            if (orientation == TouchOrientation.DOWN_2_UP) {//往上
                int topMargin = Tools.px2dip(OrderActivity.this,mListsParams.topMargin);
                int configToolBarHeight = Tools.px2dip(OrderActivity.this,getResources().getDimension(R.dimen.toolbar_height));
                float configToolBarHeightPx = getResources().getDimension(R.dimen.toolbar_height);
                float configTopMarginPx = getResources().getDimension(R.dimen.top_margin);
                if (topMargin > configToolBarHeight) {
                    ListParentOnTouchListener.SCROLL_ENABLE = false;
                    float top = (mListsParams.topMargin - offset);
                    if (top < configToolBarHeightPx) top = configToolBarHeightPx;
                    mListsParams.setMargins(mListsParams.leftMargin, (int) top, mListsParams.rightMargin, mListsParams.bottomMargin);
//                    mLists.requestLayout();
                    mLists.setLayoutParams(mListsParams);
                    float scale = top / configTopMarginPx;
                    mTitle.setAlpha(scale);
                    mSubTitle.setAlpha(1-scale);
                }else{
                    mTitle.setAlpha(0);
                    mSubTitle.setAlpha(1);
                    ListParentOnTouchListener.SCROLL_ENABLE = true;
                }
            }else if (orientation == TouchOrientation.UP_2_DOWN) {//往下
                int topMargin = Tools.px2dip(OrderActivity.this,mListsParams.topMargin);
                int configTopMargin = Tools.px2dip(OrderActivity.this,getResources().getDimension(R.dimen.top_margin));
                float configTopMarginPx = getResources().getDimension(R.dimen.top_margin);
                if (topMargin == configTopMargin) {
                    ListParentOnTouchListener.SCROLL_ENABLE = true;
                    mTitle.setAlpha(1);
                    mSubTitle.setAlpha(0);
                }else{
                    ListParentOnTouchListener.SCROLL_ENABLE = false;
                    float top = (mListsParams.topMargin + offset);
                    if (top > configTopMarginPx) top = configTopMarginPx;
                    mListsParams.setMargins(mListsParams.leftMargin, (int) top, mListsParams.rightMargin, mListsParams.bottomMargin);
//                    mLists.requestLayout();
                    mLists.setLayoutParams(mListsParams);
                    float scale = top / configTopMarginPx;
                    mTitle.setAlpha(scale);
                    mSubTitle.setAlpha(1-scale);
                }

            }
        }
    };

    RightAdapterCallback mCallback = new RightAdapterCallback() {
        @Override
        void onClickNumButton(int position, boolean isAdd) {


            tv_sum.setText("¥"+mRightAdapter.getSum()+"");

            mSubModelList.get(position).setNum(mSubModelList.get(position).getNum() + (isAdd ? 1 : -1));
            mRightAdapter.update(position,mSubModelList.get(position));

            int leftPosition = getLeftPosition(mSubModelList.get(position).getcId());
            mModelList.get(leftPosition).setBadge(mModelList.get(leftPosition).getBadge() + (isAdd ? 1 : -1));
            mLeftAdapter.update(leftPosition,mModelList.get(leftPosition));
        }
    };
}