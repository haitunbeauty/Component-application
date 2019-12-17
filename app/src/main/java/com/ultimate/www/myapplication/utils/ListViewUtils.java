package com.ultimate.www.myapplication.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewUtils {

    /**
     * @author 李宝
     * @date 2019/9/26
     * @Describe 动态设置ListView的高度
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = ((HeaderViewListAdapter)listView.getAdapter()).getWrappedAdapter();

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i,null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() *
                (listAdapter.getCount() - 1))+144;
        listView.setLayoutParams(params);
        listView.invalidate();
    }


    public static void setListViewHeightNoHeader(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i,null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() *
                (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.invalidate();
    }


    public static void setGridViewHeight(GridView gridview) {
        ListAdapter listAdapter = gridview.getAdapter();
        if (listAdapter == null) {
            return;
        }
        //int numColumns= gridview.getNumColumns(); // 有多少列
        int numColumns= 3;
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i += numColumns) {
            View listItem = listAdapter.getView(i, null, gridview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight()+7;
        }
        // 获取gridview的布局参数
        ViewGroup.LayoutParams params = gridview.getLayoutParams();
        params.height = totalHeight;
        gridview.setLayoutParams(params);
        gridview.invalidate();
    }

}
