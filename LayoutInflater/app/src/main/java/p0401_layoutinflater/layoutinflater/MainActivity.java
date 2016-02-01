package p0401_layoutinflater.layoutinflater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "MYLOGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //отримуэмо LayoutInflater через метод getLayoutInflater()
        LayoutInflater ltInflater = getLayoutInflater();

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        View view1 = ltInflater.inflate(R.layout.text, linLayout, true);
        ViewGroup.LayoutParams lp1 = view1.getLayoutParams();


        Log.d(LOG_TAG, "Class of views1: " + view1.getClass().toString());
        Log.d(LOG_TAG, "LayoutParams of view1: " + (lp1.getClass().toString()));
      //  Log.d(LOG_TAG, "Text of view1: " + ((TextView) view1).getText());


        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.relLayout);
        View view2 = ltInflater.inflate(R.layout.text, relLayout, true);
        ViewGroup.LayoutParams lp2 = view2.getLayoutParams();

        Log.d(LOG_TAG, "Class of views2: " + view2.getClass().toString());
        Log.d(LOG_TAG, "LayoutParams of view2: " + (lp2.getClass().toString()));
      //  Log.d(LOG_TAG, "Text of view2: " + ((TextView) view2).getText());

    }
}
