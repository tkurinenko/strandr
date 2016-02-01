package p0391_sqliteonupgradedb.sqliteonupgradedb;

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

    final String DB_NAME = "staff";

    final int DB_VERSION = 1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        Log.d(LOG_TAG, " --- Staff db v. " + db.getVersion() + " --- ");
        writeStaff(db);
        dbh.close();
    }


    private void writeStaff(SQLiteDatabase db){
        Cursor c = db.rawQuery("select * from people", null);
        logCursor(c, "Table people");
    }

    void logCursor(Cursor c, String title) {
        if (c !=null) {
            if (c.moveToFirst()){
                Log.d(LOG_TAG, title + ". " + c.getCount() + " rows");
                StringBuilder sb = new StringBuilder();
                do {
                    sb.setLength(0);
                    for (String cn : c.getColumnNames()) {
                        sb.append(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG,sb.toString());
                } while (c.moveToNext());
            }
        } else Log.d(LOG_TAG, title + ". Cursor is null ");
    }


    private class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "onCreate database ");

            String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша",
                    "Борис", "Костя", "Игорь" };
            String[] people_positions = { "Программер", "Бухгалтер",
                    "Программер", "Программер", "Бухгалтер", "Директор",
                    "Программер", "Охранник" };
            ContentValues cv = new ContentValues();

            db.execSQL();


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
