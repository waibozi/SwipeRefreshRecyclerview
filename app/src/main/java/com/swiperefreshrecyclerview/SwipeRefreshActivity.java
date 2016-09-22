package com.swiperefreshrecyclerview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.waibozi.swiperefreshrecyclerview.SwipeRefreshRecyclerView;
import com.waibozi.swiperefreshrecyclerview.recyclervietouchhelper.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */

public class SwipeRefreshActivity extends Activity {

    private Context mContext;
    private RecyclerTouchListener touchListener;
    SwipeRefreshRecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_refresh_activity);
        mContext = this;
        recycler = (SwipeRefreshRecyclerView) findViewById(R.id.recycler);
        recycler.setAdapter(new MyViewHolder(getData()));
        touchListener = new RecyclerTouchListener(this, recycler);
        recycler.setPullRefreshEnabled(false);
        touchListener
                .setIndependentViews(R.id.rowButton)
                .setViewsToFade(R.id.rowButton)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        Toast.makeText(mContext, "Row " + (position + 1) + " clicked!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                        Toast.makeText(mContext, "Button in row " + (position + 1) + " clicked!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setLongClickable(true, new RecyclerTouchListener.OnRowLongClickListener() {
                    @Override
                    public void onRowLongClicked(int position) {
                        Toast.makeText(mContext, "Row " + (position + 1) + " long clicked!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setSwipeOptionViews(R.id.add, R.id.edit, R.id.change)
                .setUnSwipeableRows(R.id.add)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        String message = "";
                        if (viewID == R.id.add) {
                            message += "Add";
                        } else if (viewID == R.id.edit) {
                            message += "Edit";
                        } else if (viewID == R.id.change) {
                            message += "Change";
                        }
                        message += " clicked for row " + (position + 1);
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private List<RowModel> getData() {
        List<RowModel> list = new ArrayList<>(25);
        for (int i = 0; i < 25; i++) {
            list.add(new RowModel("Row " + (i + 1), "Some Text... "));
        }
        return list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        recycler.addOnItemTouchListener(touchListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        recycler.removeOnItemTouchListener(touchListener);
    }

    private class MyViewHolder extends RecyclerView.Adapter<MyViewHolder.MainViewHolder> {

        List<RowModel> modelList;

        public MyViewHolder(List<RowModel> list) {
            modelList = new ArrayList<>(list);
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_row, parent, false);
            return new MainViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            holder.bindData(modelList.get(position));
        }

        @Override
        public int getItemCount() {
            return modelList.size();
        }

        class MainViewHolder extends RecyclerView.ViewHolder {

            TextView mainText, subText;

            public MainViewHolder(View itemView) {
                super(itemView);
                mainText = (TextView) itemView.findViewById(R.id.mainText);
                subText = (TextView) itemView.findViewById(R.id.subText);
            }

            public void bindData(RowModel rowModel) {
                mainText.setText(rowModel.getMainText());
                subText.setText(rowModel.getSubText());
            }
        }
    }
}
