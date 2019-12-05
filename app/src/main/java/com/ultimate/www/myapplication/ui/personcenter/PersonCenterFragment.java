package com.ultimate.www.myapplication.ui.personcenter;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.Manifest;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ultimate.www.myapplication.R;
import java.util.List;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import static com.ultimate.www.myapplication.ui.personcenter.UserDataBase.MIGRATION_1_2;
import static com.ultimate.www.myapplication.ui.personcenter.UserDataBase.MIGRATION_2_3;
import static com.ultimate.www.myapplication.ui.personcenter.UserDataBase.MIGRATION_3_4;
//import static com.ultimate.www.myapplication.ui.personcenter.UserDataBase.MIGRATION_2_3;


public class PersonCenterFragment extends Fragment {

    private static final int READ_AND_WRITE = 88;//获取权限的标志

    private PersonCenterViewModel personCenterViewModel;
    private UserDataBase userDataBase;

    public static PersonCenterFragment newInstance() {
        return new PersonCenterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.person_center_fragment, container, false);
        final TextView textView1 = root.findViewById(R.id.text_id);
        final TextView textView2 = root.findViewById(R.id.text_name);
        final TextView textView3 = root.findViewById(R.id.text_phone);
        final TextView textView4 = root.findViewById(R.id.text_address);
        final TextView textView5 = root.findViewById(R.id.text_adddata);
        RecyclerView recyclerView = root.findViewById(R.id.user_list);
        methodRequiresTwoPermission();

        personCenterViewModel = ViewModelProviders.of(this).get(PersonCenterViewModel.class);
        userDataBase = Room.databaseBuilder(getActivity(), UserDataBase.class,"personcenterbean.db").build();
        final UserDao userDao = userDataBase.userDao();

        //TODO 你可以将列表展示在 recyclerView 中，我没有实现
        personCenterViewModel.getPersonInfo(userDao).observe(this, new Observer<List<UserBean>>() {
            @Override
            public void onChanged(@Nullable List<UserBean> userBeanList) {
                if (userBeanList == null || userBeanList.size() == 0) return;
                textView1.setText(userBeanList.get(0).getId()+"");
                textView2.setText(userBeanList.get(1).getName()+"");
                textView3.setText(userBeanList.get(2).getPhone()+"");
                textView4.setText(userBeanList.get(3).getAddress()+"");
                textView5.setText(userBeanList.get(1).getAddData()+"");
            }
        });


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                UserBean personcenterBean = new UserBean();
//                personcenterBean.setId(1);
//                personcenterBean.setName("张三");
//                personcenterBean.setPhone("17623666501");
//                personcenterBean.setAddress("渝北黄山大道");
//                userDao.insertUser(personcenterBean);
//            }
//        }).start();

//        userDataBase.userDao().queryUser(1).observe(this, new Observer<UserBean>() {
//            @Override
//            public void onChanged(UserBean personcenterBean) {
//                textView1.setText(personcenterBean.getId()+"");
//                textView2.setText(personcenterBean.getName()+"");
//                textView3.setText(personcenterBean.getPhone()+"");
//                textView4.setText(personcenterBean.getAddress()+"");
//            }
//        });

        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(READ_AND_WRITE)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(getActivity(), perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            EasyPermissions.requestPermissions(this, "读写权限",
                    READ_AND_WRITE, perms);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (userDataBase != null){
            userDataBase.close();
        }
    }








}
