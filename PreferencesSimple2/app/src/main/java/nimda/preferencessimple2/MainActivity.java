package nimda.preferencessimple2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView tvInfo;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
        sp = PreferenceManager.getDefaultSharedPreferences(this);


    }


    @Override
    protected void onResume() {
        String listValue = sp.getString("list", "not selected");
        tvInfo.setText("List values : " + listValue);

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem mi = menu.add(0,1,0, "Preferences");
        mi.setIntent(new Intent(this, PrefActivity.class));
        return super.onCreateOptionsMenu(menu);
    }
}
