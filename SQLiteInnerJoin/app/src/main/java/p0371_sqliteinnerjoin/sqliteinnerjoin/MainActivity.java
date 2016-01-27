package p0371_sqliteinnerjoin.sqliteinnerjoin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    final String LOG_TAG = "myLogs";

    int[] position_id = {1,2,3,4};
    String[] position_name = {"Director","Programmer","Buhalter","Security"};
    int[] polsition_salary = {1500,13000,1000,8000};

    String[] people_name = {"ivan","maria","petr","anton","dasha","boris","kostya","igor"};

    int[] people_posid = {2,3,2,2,3,1,2,4};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connection to database
        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();

        //Load Cursor
        Cursor c;

        Log.d(LOG_TAG,"--- Table position ---");
        c = db.query("position", null,null,null,null,null,null);
        logCursor(c);
        c.close();
        Log.d(LOG_TAG, "--- ---");


        Log.d(LOG_TAG, "--- Table people ---");
        c = db.query("people", null,null,null,null,null,null);
        logCursor(c);
        c.close();
        Log.d(LOG_TAG, "--- ---");

        Log.d(LOG_TAG, "--- INNER JOIN with query ---");
        String table = "people as PL inner  join position as PL on PL.posid = PS.id";
        String[] coluumns = {"PL.name as Name", "PS.anem as Position","salary as Salary"};
        String selection = "salary < ?";
        String[] selectionArgs = {"12000"};
        c = db.query(table, coluumns,selection,selectionArgs,null,null,null);
        logCursor(c);
        c.close();
        Log.d(LOG_TAG, "--- ---");
        db.close();

    }

    void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = " + c.getString(c.getColumnIndex(cn)) + ";");
                    }
                    Log.d(LOG_TAG, str);
                } while (c.moveToNext());
            }
        } else Log.d(LOG_TAG,"Cursor in null");
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG,"--- onCreateDatabase---");

            ContentValues cv = new ContentValues();
            //створюэмо табличку людей
            db.execSQL("create table position (" + "id integer primary key," + "name text," + "salary integer" + ");");
            //заповнюэмо її
            for (int i = 0; i < position_id.length; i++) {
                cv.clear();
                cv.put("id", position_id[i]);
                cv.put("name", position_name[i]);
                db.insert("people", null,cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
