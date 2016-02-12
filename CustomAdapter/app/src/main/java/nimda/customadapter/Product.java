package nimda.customadapter;

/**
 * Created by tkurinenko on 11.02.2016.
 */
public class Product {

    String name;
    int price;
    int image;
    boolean box;

    public Product(String _describe, int _price, int _image, boolean _box) {
        this.box = _box;
        this.image = _image;
        this.name = _describe;
        this.price = _price;
    }
}
