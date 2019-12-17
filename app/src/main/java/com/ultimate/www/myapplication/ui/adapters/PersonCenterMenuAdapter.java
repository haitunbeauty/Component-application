package com.ultimate.www.myapplication.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ultimate.www.myapplication.R;
import com.ultimate.www.myapplication.data.PersonCenterMenuBean;

import java.util.ArrayList;
import java.util.List;

public class PersonCenterMenuAdapter extends BaseAdapter {

    private Context context = null;
    private LayoutInflater inflater = null;
    private List<PersonCenterMenuBean> list = new ArrayList<>();

    public PersonCenterMenuAdapter(Context context) {
        // 布局装载器对象
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    //设置菜单数据
    public void setMenuData(List<PersonCenterMenuBean> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.person_center_menus, null);
            viewHolder.menuIcon = (ImageView) convertView.findViewById(R.id.menu_icon_iv);
            viewHolder.menuNext = (ImageView) convertView.findViewById(R.id.menu_next_iv);
            viewHolder.menuNames = (TextView) convertView.findViewById(R.id.menu_name_tv);
            convertView.setTag(viewHolder);
        }  else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.menuNames.setText(list.get(position).getMenuName());
        int menuIcon = list.get(position).getMenuIcon();
        Glide.with(context).load(context.getResources().getDrawable(menuIcon)).into(viewHolder.menuIcon);
        Glide.with(context).load(context.getResources().getDrawable(R.mipmap.ic_common_next)).into(viewHolder.menuNext);
        return convertView;
    }

    class ViewHolder {
        ImageView menuIcon;
        ImageView menuNext;
        TextView menuNames;
    }

}
