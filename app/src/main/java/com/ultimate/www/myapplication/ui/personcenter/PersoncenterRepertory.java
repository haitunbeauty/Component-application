package com.ultimate.www.myapplication.ui.personcenter;

import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;

public class PersoncenterRepertory {

    public static PersoncenterRepertory  instant(){
        return new PersoncenterRepertory();
    }

    /**
     * @author 李宝
     * @date 2019/11/19
     * @Describe 这里去获取数据（from net or database or others）
     */
    public LiveData<List<UserBean>> getPersonInfo(final UserDao userDao) {

        //TODO 先检测local是否有数据 没有数据再去remote获取数据 待实现
        if (false){
            return null;
        }

        //Todo  模拟网络数据请求
        final ArrayList<UserBean> userBeans = new ArrayList<>();
        new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<50;i++) {
                        UserBean userBean = new UserBean();
                        userBean.setId(i);
                        userBean.setName("赵"+i);
                        userBean.setPhone("17623666501");
                        userBean.setAddress("渝北黄山大道");
                        userBean.setAddData("数据库新增字段test"+i);
                        userBeans.add(userBean);
                        //userDao.insertAll(userBean); 插入一个对象
                    }
                    userDao.insertAllList(userBeans);

                }
            }).start();

        //return userDao.queryUser(1); 查询一个对象
        return userDao.getAll();
    }

}
