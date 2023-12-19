package vn.edu.dlu.ctk45.calories_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FoodItemAdapter extends ArrayAdapter<FoodItem> {
    private Context context;
    private int resourceId;
    private ArrayList<FoodItem> foodItemList;

    public FoodItemAdapter(Context context, int resourceId, ArrayList<FoodItem> foodItemList) {
        super(context, resourceId, foodItemList);
        this.context = context;
        this.resourceId = resourceId;
        this.foodItemList = foodItemList;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resourceId, parent, false);
        }

        // Lấy đối tượng FoodItem tại vị trí position
        FoodItem currentItem = foodItemList.get(position);

        // Ánh xạ các thành phần trong layout ds_thong_tin_thuc_pham.xml
        ImageView foodImageView = convertView.findViewById(R.id.food_image);
        TextView foodNameTextView = convertView.findViewById(R.id.food_name);
        TextView foodCaloTextView = convertView.findViewById(R.id.food_calo);
        TextView foodProteinTextView = convertView.findViewById(R.id.food_protein);
        TextView foodFatTextView = convertView.findViewById(R.id.food_fat);
        TextView foodDetailTextView = convertView.findViewById(R.id.food_info);

        // Hiển thị thông tin từ FoodItem lên các thành phần tương ứng trong layout
        foodImageView.setImageResource(context.getResources().getIdentifier(currentItem.getInfoFoodImageResource(), "drawable", context.getPackageName()));
        foodNameTextView.setText(currentItem.getInfoFoodName());
        foodCaloTextView.setText(currentItem.getInfoFoodCalo());
        foodProteinTextView.setText(currentItem.getInfoFoodProtein());
        foodFatTextView.setText(currentItem.getInfoFoodFat());
        foodDetailTextView.setText(currentItem.getInfoFoodDetail());

        return convertView;
    }
}
