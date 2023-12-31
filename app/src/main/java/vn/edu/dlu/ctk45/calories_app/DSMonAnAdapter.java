package vn.edu.dlu.ctk45.calories_app;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DSMonAnAdapter extends ArrayAdapter<FoodItem> {
    private Context context;
    private int resourceId;
    private ArrayList<FoodItem> foodItemList;
    private String imagePath;
    public DSMonAnAdapter(Context context, int resourceId, ArrayList<FoodItem> foodItemList, String imagePath) {
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

        ImageView foodImageView = convertView.findViewById(R.id.f_image);
        TextView foodNameTextView = convertView.findViewById(R.id.f_name);
        TextView foodCaloTextView = convertView.findViewById(R.id.f_calo);
        TextView foodProteinTextView = convertView.findViewById(R.id.f_protein);
        TextView foodFatTextView = convertView.findViewById(R.id.f_fat);
        TextView foodDetailTextView = convertView.findViewById(R.id.f_info);
        Button increaseBtn = convertView.findViewById(R.id.increase_btn);
        Button decreaseBtn = convertView.findViewById(R.id.decrease_btn);
        TextView foodCount = convertView.findViewById(R.id.food_ammount);
        TextView foodCaloCal = convertView.findViewById(R.id.fCalo_amount);
        TextView foodFatCal = convertView.findViewById(R.id.fFat_amount);
        TextView foodProteinCal = convertView.findViewById(R.id.fProtein_amount);

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

        final int[] amount = {0};
        foodCount.setText(String.valueOf(amount[0]));

        increaseBtn.setOnClickListener(v -> {
            amount[0]++;
            foodCount.setText(String.valueOf(amount[0]));

            int calories = foodItemList.get(position).calculateCalo(amount[0]);
            foodCaloCal.setText(String.valueOf(calories));
            double fats = foodItemList.get(position).calculateFat(amount[0]);
            foodFatCal.setText(String.valueOf(fats));
            double proteins = foodItemList.get(position).calculateProtein(amount[0]);
            foodProteinCal.setText(String.valueOf(proteins));
        });

        decreaseBtn.setOnClickListener(v -> {
            if (amount[0] > 0) {
                amount[0]--;
                foodCount.setText(String.valueOf(amount[0]));

                int calories = foodItemList.get(position).calculateCalo(amount[0]);
                foodCaloCal.setText(String.valueOf(calories));
                double fats = foodItemList.get(position).calculateFat(amount[0]);
                foodFatCal.setText(String.valueOf(fats));
                double proteins = foodItemList.get(position).calculateProtein(amount[0]);
                foodProteinCal.setText(String.valueOf(proteins));

            }
        });

        return convertView;
    }
}
