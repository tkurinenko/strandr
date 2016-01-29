package p0371_sqliteinnerjoin.sqliteinnerjoin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*
*
* SQLite helper class  - manage database creation and version management
* To use override onCreate(), onUpgrade() methods of SQLiteOpenHelper
* Use subclass to create a readable or writable database insert(), execSQL(), update(), delete()
*
* */



public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DBName";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_CREATE = "create table MyEmployees(_id integer primary key, name text not null);";


    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MyDatabaseHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS MyEmployees");
        onCreate(db);
    }
}
