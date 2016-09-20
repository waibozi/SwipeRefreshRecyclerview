package com.swiperefreshrecyclerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.waibozi.swiperefreshrecyclerview.SwipeRefreshRecyclerView;

/**
 * Created by Administrator on 2016/9/20.
 */

public class SwipeRefreshActivity extends Activity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_refresh_activity);
        mContext = this;
        SwipeRefreshRecyclerView recycler = (SwipeRefreshRecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(new MyViewHolder());
    }

    private class MyViewHolder extends RecyclerView.Adapter<MyViewHolder.ViewHolder> {

        @Override
        public MyViewHolder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(mContext);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
            textView.setTextColor(Color.BLACK);
            return new ViewHolder(textView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder.ViewHolder holder, int position) {
            holder.textView.setText("我是第" + position + "个Item");
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView;
            }
        }
    }
}
