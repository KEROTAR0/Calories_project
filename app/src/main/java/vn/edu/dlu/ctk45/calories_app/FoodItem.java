package vn.edu.dlu.ctk45.calories_app;

public class FoodItem {
    private String foodName;
    private String foodCalo;
    private String foodProtein;
    private String foodFat;
    private String foodDetail;
    private String foodImageResource;

    public FoodItem(String foodName, String foodCalo, String foodProtein, String foodFat, String foodDetail, String foodImageResource) {
        this.foodName = foodName;
        this.foodCalo = foodCalo;
        this.foodProtein = foodProtein;
        this.foodFat = foodFat;
        this.foodDetail = foodDetail;
        this.foodImageResource = foodImageResource;
    }
    public String getInfoFoodName(){return foodName;}
    public String getInfoFoodCalo(){return foodCalo;}
    public String getInfoFoodProtein(){return foodProtein;}
    public String getInfoFoodFat(){return foodFat;}
    public String getInfoFoodDetail(){return foodDetail;}
    public String getInfoFoodImageResource(){return foodImageResource;}
    public int calculateCalo(int amount) {
        return amount * Integer.valueOf(foodCalo);
    }
    public double calculateFat(int amount) {
        return Math.round(amount * Double.parseDouble(foodFat) * 10.0) / 10.0;
    }
    public double calculateProtein(int amount) {
        return Math.round(amount * Double.parseDouble(foodProtein) * 10.0) / 10.0;
    }
}
