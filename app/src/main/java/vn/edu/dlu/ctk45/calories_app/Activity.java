package vn.edu.dlu.ctk45.calories_app;

public class Activity {
    public String ten_hoat_dong;
    public int thong_tin_hd;
    public int image;

    public Activity(String ten_hoat_dong, int thong_tin_hd, int image) {
        this.ten_hoat_dong = ten_hoat_dong;
        this.thong_tin_hd = thong_tin_hd;
        this.image = image;
    }
    public int calculateCalories(int amount) {
        // Thay đổi công thức tính calo tùy thuộc vào logic của ứng dụng của bạn.
        // Ví dụ: Giả sử calo được tính dựa trên số lần hoạt động (amount) và thông tin hoạt động (thong_tin_hd).
        return amount * thong_tin_hd;
    }
}
