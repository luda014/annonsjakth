package course.learn.annonsjakth;

import java.util.List;

public class Item {
    public String name;
    public int price;
    public String currency;
    public String type;
    public List<String> images;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getType() {
        return type;
    }

    public List<String> getImages() {
        return images;
    }
}