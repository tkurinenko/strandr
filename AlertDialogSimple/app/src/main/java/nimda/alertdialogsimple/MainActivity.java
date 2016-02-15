package nimda.alertdialogsimple;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int DIALOG_EXIT = 1;
    final int CLEAR_HISTORY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onclick(View view) {
        showDialog(DIALOG_EXIT);
    }

    public void onclickClear(View view) {
        showDialog(CLEAR_HISTORY);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_EXIT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle(R.string.exit);
            adb.setMessage(R.string.save_data);
            adb.setIcon(android.R.drawable.ic_dialog_info);
            adb.setNegativeButton(R.string.no, myClickListener);
            adb.setPositiveButton(R.string.yes, myClickListener);
            adb.setNeutralButton(R.string.cancel, myClickListener);
            return adb.create();
        } else if (id == CLEAR_HISTORY) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle(R.string.clear_data);
            adb.setMessage(R.string.clear);
            adb.setPositiveButton(R.string.ok, myClickListenerCleared);
            adb.setNeutralButton(R.string.cancelL, myClickListener);
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case Dialog.BUTTON_POSITIVE:
                    saveData();
                    finish();
                    break;
                case Dialog.BUTTON_NEGATIVE:
                    finish();
                    break;
                case Dialog.BUTTON_NEUTRAL:
                    break;
            }
        }
    };
    DialogInterface.OnClickListener myClickListenerCleared = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case Dialog.BUTTON_POSITIVE:
                    clearedData();
                    finish();
                    break;
            }
        }
    };

    public void onBackPressed(){
        showDialog(DIALOG_EXIT);
    }

    void saveData() {
        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
    }

    void clearedData(){
        Toast.makeText(this, R.string.cleared, Toast.LENGTH_SHORT).show();
    }
}
