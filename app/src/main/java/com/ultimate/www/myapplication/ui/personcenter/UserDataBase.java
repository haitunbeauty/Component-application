package com.ultimate.www.myapplication.ui.personcenter;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * @author 李宝
 * @date 2019/11/22
 * @Describe sql语句参考  ： https://www.sqlitetutorial.net/sqlite-alter-table/
 * tips:  不支持删除列和重命名列
 */

@Database(entities = {UserBean.class},version = 4,exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {

    public abstract UserDao userDao();

    /**
    * @日期 2019/11/22 14:52
    * @Processor libao
    * @Description 插入列 新增字段...
    * @Parameter
    */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            //新增字段
            database.execSQL("ALTER TABLE user ADD COLUMN addData TEXT NOT NULL DEFAULT ''");

            //创建新表
            database.execSQL("CREATE TABLE new_user (" +
                    "id INTEGER PRIMARY KEY NOT NULL," +
                    "name TEXT," +
                    "phone TEXT," +
                    "address TEXT,"+
                    "addData TEXT NOT NULL DEFAULT '')");

            //复制旧表到新表
            database.execSQL("INSERT INTO new_user (id, name, phone,address,addData) " +
                    "SELECT id, name, phone,address,addData FROM user");

            //移除旧表
            database.execSQL("DROP TABLE user");

            //将新表重命名旧表
            database.execSQL("ALTER TABLE new_user RENAME TO user");
        }
    };

    /**
    * @日期 2019/11/22 14:53
    * @Processor libao
    * @Description 删除列...
    * @Parameter
    */
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            //新增字段
            //database.execSQL("ALTER TABLE user DROP COLUMN addData");

            //创建新表
            database.execSQL("CREATE TABLE new_user (" +
                    "id INTEGER PRIMARY KEY NOT NULL," +
                    "name TEXT," +
                    "phone TEXT," +
                    "address TEXT)");

            //复制旧表到新表
            database.execSQL("INSERT INTO new_user (id, name, phone,address) " +
                    "SELECT id, name, phone,address FROM user");

            //移除旧表
            database.execSQL("DROP TABLE user");

            //将新表重命名旧表
            database.execSQL("ALTER TABLE new_user RENAME TO user");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            //新增字段
            database.execSQL("ALTER TABLE user ADD COLUMN addData TEXT NOT NULL DEFAULT ''");

            //创建新表
            database.execSQL("CREATE TABLE new_user (" +
                    "id INTEGER PRIMARY KEY NOT NULL," +
                    "name TEXT," +
                    "phone TEXT," +
                    "address TEXT,"+
                    "addData TEXT NOT NULL DEFAULT 'test')");

            //复制旧表到新表
            database.execSQL("INSERT INTO new_user (id, name, phone,address,addData) " +
                    "SELECT id, name, phone,address,addData FROM user");

            //移除旧表
            database.execSQL("DROP TABLE user");

            //将新表重命名旧表
            database.execSQL("ALTER TABLE new_user RENAME TO user");
        }
    };

}
