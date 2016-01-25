package p0341_simplesqlite.simplesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    Button btnAdd, btnRead, btnClear, btnUpd, btnDel;
    EditText etName, etMail, etID;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnClear = (Button) findViewById(R.id.btnClear);
        etName = (EditText) findViewById(R.id.etName);
        etMail = (EditText) findViewById(R.id.etEmail);
        btnUpd = (Button) findViewById(R.id.btnUpd);
        btnDel = (Button) findViewById(R.id.btnDel);

        etID = (EditText) findViewById(R.id.etID);

        btnAdd.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        dbHelper = new DBHelper(this);

        btnUpd.setOnClickListener(this);
        btnDel.setOnClickListener(this);

    }


    @Override
    //тут обробляємо натиснення кнопок
    public void onClick(View v) {
        //ContentValues використовується для вказання полів таблиці і значень які в ці поля будемо вставляти
        ContentValues cv = new ContentValues();
        //записуємо в змінні значення з полів вводу
        String name = etName.getText().toString();
        String email = etMail.getText().toString();
        String id = etID.getText().toString();

        //за допомогою метода getWritableDatabase() підключаємось до БД і отримуємо обєкт SQLiteDatabase
        //insert, query, delete
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //дивимось яка кнопка була натиснута
        switch (v.getId()) {
            case R.id.btnAdd:
                Log.d(LOG_TAG, "--- Insert in mytable ---");
                cv.put("name", name);
                cv.put("email", email);

                //передаємо імя таблиці та обєкт з вставленними значеннями
                long rowID = db.insert("mytable", null, cv);
                Log.d(LOG_TAG, "row inserted, ID " + rowID);
                break;
            case R.id.btnRead:
                Log.d(LOG_TAG, "---Rows in mytable ---");
                //нам потрібні всі дані , зі всіх таблиць без сортування,
                //метод повертає обєкт типу Cursor, його можна розглядати як таблицю з даними
                Cursor c = db.query("mytable", null, null, null, null, null, null);
                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int emailColIndex = c.getColumnIndex("email");

                    do {
                        Log.d(LOG_TAG, "ID = " + c.getInt(idColIndex) + ", name = " + c.getString(nameColIndex) + ", email =" + c.getString(emailColIndex));
                    } while (c.moveToNext());
                } else
                    Log.d(LOG_TAG, "0 rows");
                c.close();
                break;
            case R.id.btnClear:
                Log.d(LOG_TAG, "--- Clear mytable: ---");
                //видаляються записи, на всіх передаємо імя таблиці і нулл в якості умов видалення, значить видаляємо все
                int clearCount = db.delete("mytable", null, null);
                Log.d(LOG_TAG, "delete rows count = " + clearCount);
                break;
            case R.id.btnUpd:
                if (id.equalsIgnoreCase("")) break;
                Log.d(LOG_TAG, "---Update mytable ---");
                cv.put("name", name);
                cv.put("email", email);

                int updCount = db.update("mytable", cv, "id = ?", new String[]{id});
                Log.d(LOG_TAG, "updated rows count = " + updCount);
                break;
            case R.id.btnDel:
                if (id.equalsIgnoreCase("")) break;
                Log.d(LOG_TAG, "---Delete mytable ---");
                int delCount = db.delete("mytable", "id = " + id, null);
                Log.d(LOG_TAG, "deleted rows count = " + delCount);
                break;
        }
        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            db.execSQL("create table mytable (" + "id integer primary key autoincrement, " + "name text, " + "email text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
