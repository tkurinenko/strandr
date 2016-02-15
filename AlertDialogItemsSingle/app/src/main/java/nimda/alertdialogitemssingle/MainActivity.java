package nimda.alertdialogitemssingle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    final int DIALOG_ITEMS = 1;
    final int DIALOG_CURSOR = 3;
    DB db;
    Cursor cursor;

    String data[] = {"one", "two", "three", "four"};
    boolean chkd[] = new boolean[data.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);

    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnItems:
                showDialog(DIALOG_ITEMS);
                break;
            case R.id.btnCursor:
                showDialog(DIALOG_CURSOR);
                break;
            default:
                break;
        }
    }

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id) {
            case DIALOG_ITEMS:
                adb.setTitle(R.string.items);
                adb.setMultiChoiceItems(data, chkd, myMultiChoiceClickListener);
                break;
            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor);
                //передаємо курсор БД, обробник натиснень, імя поля , значення якого буде показано в списку.
                adb.setMultiChoiceItems(cursor, DB.COLUMN_CHK, DB.COLUMN_TXT, myCursorMultiClickListener );
                break;
        }
        adb.setPositiveButton(R.string.ok,myBtnClickListener );
        return adb.create();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    DialogInterface.OnMultiChoiceClickListener myCursorMultiClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            ListView lv = ((AlertDialog) dialog).getListView();
            db.changeRec(which,isChecked);
            cursor.requery();
        }
    };


    DialogInterface.OnMultiChoiceClickListener myMultiChoiceClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
          /*  chkd[which] = isChecked;*/
            Log.d(LOG_TAG,"");
        }
    };

    DialogInterface.OnClickListener myClickListener =new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ListView lv = ((AlertDialog)dialog).getListView();
            if (which == Dialog.BUTTON_POSITIVE)
                Log.d(LOG_TAG, "pos = " + lv.getCheckedItemPosition());
            else
                Log.d(LOG_TAG, "which = " + which);
        }
    };


    DialogInterface.OnClickListener myBtnClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            SparseBooleanArray sbArray = ((AlertDialog)dialog).getListView().getCheckedItemPositions();
            for (int i = 0; i < sbArray.size(); i++) {
                int key = sbArray.keyAt(i);
                if (sbArray.get(key))
                    Log.d("qwe", "checked: " + key);
            }
        }
    };
}
