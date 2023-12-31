package vn.edu.dlu.ctk45.calories_app;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FoodItemAdapter extends ArrayAdapter<FoodItem> {
    private Context context;
    private int resourceId;
    private ArrayList<FoodItem> foodItemList;
    private String imagePath;
    public FoodItemAdapter(Context context, int resourceId, ArrayList<FoodItem> foodItemList, String imagePath) {
        super(context, resourceId, foodItemList);
        this.context = context;
        this.resourceId = resourceId;
        this.foodItemList = foodItemList;
        this.imagePath = imagePath;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resourceId, parent, false);
        }

        FoodItem currentItem = foodItemList.get(position);

        ImageView foodImageView = convertView.findViewById(R.id.food_image);
        TextView foodNameTextView = convertView.findViewById(R.id.food_name);
        TextView foodCaloTextView = convertView.findViewById(R.id.food_calo);
        TextView foodProteinTextView = convertView.findViewById(R.id.food_protein);
        TextView foodFatTextView = convertView.findViewById(R.id.food_fat);
        TextView foodDetailTextView = convertView.findViewById(R.id.food_info);

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(imagePath + currentItem.getInfoFoodImageResource());

            Drawable drawable = Drawable.createFromStream(inputStream, null);
            foodImageView.setImageDrawable(drawable);

            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String calo = currentItem.getInfoFoodCalo()+"/100g ";
        String protein = currentItem.getInfoFoodProtein()+"g ";
        String fat = currentItem.getInfoFoodFat()+"g ";

        foodNameTextView.setText(currentItem.getInfoFoodName());
        foodCaloTextView.setText(calo);
        foodProteinTextView.setText(protein);
        foodFatTextView.setText(fat);
        foodDetailTextView.setText(currentItem.getInfoFoodDetail());

        return convertView;
    }
}
