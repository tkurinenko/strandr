package nimda.simpleadapterdata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

        private static final int CM_DELETE_ID = 1;

        // имена атрибутов для Map
        final String ATTRIBUTE_NAME_TEXT = "text";
        final String ATTRIBUTE_NAME_IMAGE = "image";

        ListView lvSimple;
        SimpleAdapter sAdapter;
        ArrayList<Map<String, Object>> data;
        Map<String, Object> m;

        /** Called when the activity is first created. */
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // упаковываем данные в понятную для адаптера структуру
            data = new ArrayList<Map<String, Object>>();
            for (int i = 1; i < 5; i++) {
                m = new HashMap<String, Object>();
                m.put(ATTRIBUTE_NAME_TEXT, "sometext " + i);
                m.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher);
                data.add(m);
            }

            // массив имен атрибутов, из которых будут читаться данные
            String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE };
            // массив ID View-компонентов, в которые будут вставлять данные
            int[] to = { R.id.tvText, R.id.ivImg };

            // создаем адаптер
            sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

            // определяем список и присваиваем ему адаптер
            lvSimple = (ListView) findViewById(R.id.lvSimple);
            lvSimple.setAdapter(sAdapter);
            registerForContextMenu(lvSimple);
        }

        public void onButtonClick(View v) {
            // создаем новый Map
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, "sometext " + (data.size() + 1));
            m.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher);
            // добавляем его в коллекцию
            data.add(m);
            // уведомляем, что данные изменились
            sAdapter.notifyDataSetChanged();
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v,
                                        ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
        }

        @Override
        public boolean onContextItemSelected(MenuItem item) {
            if (item.getItemId() == CM_DELETE_ID) {
                // получаем инфу о пункте списка
                AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                // удаляем Map из коллекции, используя позицию пункта в списке
                data.remove(acmi.position);
                // уведомляем, что данные изменились
                sAdapter.notifyDataSetChanged();
                return true;
            }
            return super.onContextItemSelected(item);
        }
    }
