package com.smile.livedatademo.room;

import android.content.Context;

import com.smile.livedatademo.MyApplication;
import com.smile.livedatademo.entity.UserEntity;
import com.smile.livedatademo.room.dao.UserDao;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class UserDataBases  extends RoomDatabase {

    private static volatile  UserDataBases dataBases;

    private static final String DATA_TABLE_NAME="userdb";

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
    public abstract UserDao userDao();


    public static UserDataBases getInstance(){
        if (dataBases==null){
            synchronized (UserDataBases.class){
                if (dataBases==null){
                    dataBases=buildDatabase(MyApplication.getContext(), MyApplication.getAppExecutors());
                    dataBases.updateDatabaseCreated(MyApplication.getContext().getApplicationContext());
                }
            }
        }
        return dataBases;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static UserDataBases buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, UserDataBases.class, DATA_TABLE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    /*    executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            //addDelay();
                            // Generate the data for pre-population
                            //UserDataBases database = UserDataBases.getInstance(appContext, executors);

                            //insertData(database, users);
                            // notify that the database was created and it's ready to be used
                            //database.setDatabaseCreated();
                        });*/
                    }
                })
                .build();
    }


    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATA_TABLE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }


}
