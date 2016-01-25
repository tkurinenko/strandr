package p0291_simpleactivityresult.simpleactivityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvName;
    Button btnName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = (TextView) findViewById(R.id.tvName);
        btnName = (Button) findViewById(R.id.btnName);
        btnName.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, NameActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data ==  null)  return;

        String name = data.getStringExtra("name");
        tvName.setText("Your name is " + name);
    }
}
