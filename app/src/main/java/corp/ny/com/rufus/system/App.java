package corp.ny.com.rufus.system;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import corp.ny.com.rufus.database.Migrations;

import static corp.ny.com.rufus.database.Model.NAME;
import static corp.ny.com.rufus.database.Model.VERSION;


/**
 * Created by yann-yvan on 16/11/17.
 * TO MAKE  THIS WORK PROPERLY YOU SHOULD ADD THIS CLASS
 * IN YOUR AndroidManifest.xml like this
 * <application android:name=".response.App">
 */
public class App extends Application {

    private static Context mContext;
    private static Migrations mHandler = null;
    private static SQLiteDatabase mDb = null;

    public static Context getContext() {
        return mContext;
    }

    public static SQLiteDatabase getDataBaseInstance() {
        // No need to close last data because getWritableDatabase do it
        if (mDb == null) {
            mDb = mHandler.getWritableDatabase();
        }
        return mDb;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mHandler = new Migrations(getApplicationContext(), NAME, null, VERSION);

    }

    @Override
    public void onTerminate() {
        getDataBaseInstance().close();
        super.onTerminate();
    }
}