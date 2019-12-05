package com.ultimate.www.myapplication.ui.personcenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

/**
 * @author 李宝
 * @date 2019/12/4
 * @Describe ViewModel中不要持有任何的资源对象（上下文、组件...）防止内存泄漏
 */
public class PersonCenterViewModel extends ViewModel {

    // TODO: Implement the ViewModel
    private PersoncenterRepertory personcenterRepertory;
    private LiveData<List<UserBean>> personInfo;

    public PersonCenterViewModel() {
        personcenterRepertory = PersoncenterRepertory.instant();
    }

    public LiveData<List<UserBean>> getPersonInfo(UserDao userDao) {
        init(userDao);
        return this.personInfo;
    }

    public void init( UserDao userDao){
        if (personInfo != null){
            return ;
        }
        personInfo = personcenterRepertory.getPersonInfo(userDao);
    }

}
