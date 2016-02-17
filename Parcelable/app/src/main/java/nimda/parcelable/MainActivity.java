package nimda.parcelable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onclick(View view){
        MyObject myObject = new MyObject("text",1);
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra(MyObject.class.getCanonicalName(), myObject);
        Log.d(LOG_TAG, "startActivity");
        startActivity(intent);

    }


}
