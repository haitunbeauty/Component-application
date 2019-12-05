package com.ultimate.www.myapplication.ui.personcenter;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE id =:id")
    LiveData<UserBean> queryUser(int id);

    @Query("SELECT * FROM user")
    LiveData<List<UserBean>> getAll();

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    List<UserBean> loadAllByIds(int[] userIds);

    @Insert(onConflict = REPLACE)
    void insertAll(UserBean... users);

    @Insert(onConflict = REPLACE)
    void insertAllList(List<UserBean> userBeanList);

    @Delete
    void delete(UserBean user);

    @Query("DELETE  FROM user WHERE id IN (:userIds)")
    void deleteByIds(int[] userIds);


}
