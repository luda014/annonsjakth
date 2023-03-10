package course.learn.annonsjakth;

public class FavoriteItem {
    private String imageUrl;
    private String type;
    private String amount;

    public FavoriteItem(String imageUrl, String type, String amount) {
        this.imageUrl = imageUrl;
        this.type = type;
        this.amount = amount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
