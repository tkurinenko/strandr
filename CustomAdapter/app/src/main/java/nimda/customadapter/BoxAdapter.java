package nimda.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tkurinenko on 11.02.2016.
 */
public class BoxAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<Product> object;

    public BoxAdapter(Context context, ArrayList<Product> products) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        object = products;
    }

    //кількість елементів
    @Override
    public int getCount() {
        return object.size();
    }

    //елемент по позиції
    @Override
    public Object getItem(int position) {
        return object.get(position);
    }

    //id по позиції
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item, parent,false);
        }

        Product p = getProduct(position);
        ((TextView)view.findViewById(R.id.tvDescr)).setText(p.name);
        ((TextView)view.findViewById(R.id.tvPrice)).setText(p.price + "");
        ((ImageView)view.findViewById(R.id.ivImage)).setImageResource(p.image);

        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
        cbBuy.setOnCheckedChangeListener(myCheckChangList);
        cbBuy.setTag(position);
        cbBuy.setChecked(p.box);
        return view;
    }

    Product getProduct(int position){
        return (Product) getItem(position);
    }

    ArrayList<Product> getBox() {
        ArrayList<Product> box = new ArrayList<Product>();
        for (Product p : object) {
            // если в корзине
            if (p.box)
                box.add(p);
        }
        return box;
    }
    CompoundButton.OnCheckedChangeListener myCheckChangList = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // меняем данные товара (в корзине или нет)
            getProduct((Integer) buttonView.getTag()).box = isChecked;
        }
    };
}
