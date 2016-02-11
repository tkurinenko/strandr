package nimda.simplecursortreeadapter;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;

public class MainActivity extends AppCompatActivity {
    ExpandableListView elvMain;

    DB mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDB = new DB(this);
        mDB.open();

        Cursor cursor = mDB.getCompanyData();
        startManagingCursor(cursor);

        String[] groupFrom = {DB.COMPANY_COLUMN_NAME};
        int[] groupTo = {android.R.id.text1};

        String[] childFrom = {DB.PHONE_COLUMN_NAME};
        int[] childTo = {android.R.id.text1};

        SimpleCursorTreeAdapter sctAdapter = new MyAdapter(this, cursor,
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, android.R.layout.simple_list_item_1, childFrom,
                childTo);
        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(sctAdapter);

    }

    protected void onDestroy(){
        super.onDestroy();
        mDB.close();
    }

    private class MyAdapter extends SimpleCursorTreeAdapter {
        public MyAdapter(Context context, Cursor cursor, int groupLayout,
                         String[] groupFrom, int[] groupTo, int childLayout,
                         String[] childFrom, int[] childTo) {
            super(context, cursor, groupLayout, groupFrom, groupTo,
                    childLayout, childFrom, childTo);
        }

        protected Cursor getChildrenCursor(Cursor groupCursor) {
            // получаем курсор по элементам для конкретной группы
            int idColumn = groupCursor.getColumnIndex(DB.COMPANY_COLUMN_ID);
            return mDB.getPhoneData(groupCursor.getInt(idColumn));
        }
    }
}
