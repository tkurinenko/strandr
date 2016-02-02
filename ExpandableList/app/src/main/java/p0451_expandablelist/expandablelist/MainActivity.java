package p0451_expandablelist.expandablelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //groups
    String[] groups = new String[]{"HTC", "Samsung", "LG"};
    //elements
    String[] phonesHTC = new String[]{"Sensation", "Desire", "Wildfire", "Hero"};
    String[] phonesSams = new String[]{"Galaxy S II", "Galaxy Nexus", "Wave"};
    String[] phonesLG = new String[]{"Optimus", "Optimus Link", "Optimus Black", "Optimus One"};

    //collections for group
    ArrayList<Map<String,String>> groupData;
    //collections for elements
    ArrayList<Map<String,String>> childDataItem;
    //Map for attributes
    ArrayList<ArrayList<Map<String, String>>> childData;
    //childData = ArrayList<childDataItem>

    Map<String, String> m;
    ExpandableListView elvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //fill groupData this is collection of groups
        //вказуємо всього один атрибут groupName - це назва компанії із масива groups
        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups){
            m = new HashMap<String,String>();
            m.put("groupName", group);
            groupData.add(m);
        }

        String groupFrom[] = new String[]{"groupName"};
        int groupTo[] = new int[]{android.R.id.text1};

        //колекція колекцій
        childData = new ArrayList<ArrayList<Map<String,String>>>();
        //колекція елементів кожної групи
        childDataItem = new ArrayList<Map<String,String>>();
        for (String phone : phonesHTC) {
            m = new HashMap<String,String>();
            m.put("phoneName",phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String,String>>();
        for (String phone : phonesSams) {
            m = new HashMap<String,String>();
            m.put("phoneName",phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String,String>>();
        for (String phone : phonesLG) {
            m = new HashMap<String,String>();
            m.put("phoneName",phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);


        String childFrom[] = new String[] {"phoneName"};
        int childTo[] = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this,groupData,android.R.layout.simple_expandable_list_item_1,
                groupFrom,groupTo,childData,android.R.layout.simple_list_item_1,childFrom,childTo);

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);

    }

}
