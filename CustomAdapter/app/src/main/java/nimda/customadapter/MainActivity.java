package nimda.customadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> mProducts = new ArrayList<>();
    BoxAdapter mBoxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillData();
        mBoxAdapter = new BoxAdapter(this,mProducts);

        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(mBoxAdapter);

    }

    void fillData(){
        for (int i = 0; i < 20; i++) {
            mProducts.add(new Product("Product" + i, i*1000, R.drawable.ic_launcher,false));

        }
    }

    public void showResult(View v){
        String result = "Товары в корзине: ";
        for (Product p : mBoxAdapter.getBox()
             ) {
            if (p.box) result += "\n" + p.name;
        }
        Toast.makeText(this,result, Toast.LENGTH_LONG).show();
    }
}
