package vn.edu.dlu.ctk45.calories_app;

public class Food {
    private int id;
    private String ten_mon;
    private String thong_tin_mon;
    private int image;
    private int amount;

    public Food(String ten_mon, String thong_tin_mon, int image, int amount) {
        this.id = id;
        this.ten_mon = ten_mon;
        this.thong_tin_mon = thong_tin_mon;
        this.image = image;
        this.amount = 0;
    }

    public String getTen_mon() {
        return ten_mon;
    }

    public void setTen_mon(String ten_mon) {
        this.ten_mon = ten_mon;
    }

    public String getThong_tin_mon() {
        return thong_tin_mon;
    }

    public void setThong_tin_mon(String thong_tin_mon) {
        this.thong_tin_mon = thong_tin_mon;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
