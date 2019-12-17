package com.ultimate.www.myapplication.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ultimate.www.myapplication.R;
import com.ultimate.www.myapplication.data.PersonCenterMenuBean;
import com.ultimate.www.myapplication.ui.adapters.PersonCenterMenuAdapter;
import com.ultimate.www.myapplication.utils.ListViewUtils;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private List<PersonCenterMenuBean> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        ListView menusLv = root.findViewById(R.id.memu_lv);

        PersonCenterMenuAdapter personCenterMenuAdapter = new PersonCenterMenuAdapter(getActivity());
        String[] names = {"修改密码","修改绑定手机","消息推送","修改组名","清除缓存","加入白名单","关于我们","意见反馈"};
        Integer[] ids = new Integer[]{R.mipmap.component_tomatoes_logo,R.mipmap.component_tomatoes_logo,R.mipmap.component_tomatoes_logo,R.mipmap.component_tomatoes_logo,R.mipmap.component_tomatoes_logo,R.mipmap.component_tomatoes_logo,R.mipmap.component_tomatoes_logo,R.mipmap.component_tomatoes_logo};
        for (int i=0;i<8;i++){
            PersonCenterMenuBean personCenterMenuBean = new PersonCenterMenuBean();
            personCenterMenuBean.setMenuIcon(ids[i]);
            personCenterMenuBean.setMenuName(names[i]);
            list.add(personCenterMenuBean);
        }
        personCenterMenuAdapter.setMenuData(list);
        menusLv.setAdapter(personCenterMenuAdapter);

        return root;
    }
}