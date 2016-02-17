package nimda.alertdialogitemssingle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {

    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "mytab";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CHK = "checked";
    public static final String COLUMN_TXT = "txt";

    private static final String DB_CREATE = "create table " + DB_TABLE + "(" + COLUMN_ID + " integer primary key, " +
            COLUMN_CHK + " integer, " + COLUMN_TXT + " text" + ");";
    private final Context mContext;

    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DB(Context ctx) {
        mContext = ctx;
    }

    public void open(){
        mDBHelper = new DBHelper(mContext, DB_NAME, null,DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    public void close(){
        if (mDBHelper !=null) mDBHelper.close();
    }

    public void changeRec(int pos, boolean isChecked) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CHK, (isChecked) ? 1 : 0);
        mDB.update(DB_TABLE, cv, COLUMN_ID + " = " + (pos + 1), null);
    }

    public Cursor getAllData() {
        return mDB.query(DB_TABLE, null, null, null, null, null, null);
    }

    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
            ContentValues cv = new ContentValues();
            for (int i = 0; i < 5; i++) {
                cv.put(COLUMN_ID,i);
                cv.put(COLUMN_TXT,"sometext " + i);
                cv.put(COLUMN_CHK,0);
                db.insert(DB_TABLE, null,cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}