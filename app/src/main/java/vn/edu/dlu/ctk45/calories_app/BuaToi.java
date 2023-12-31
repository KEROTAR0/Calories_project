package vn.edu.dlu.ctk45.calories_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class BuaToi extends Fragment {
    ListView lv;
    ArrayList<FoodItem> foodItemList;
    DSMonAnAdapter foodItemAdapter;
    String DB_NAME = "qlmonan.db";
    String DB_PATH = "/databases/";
    SQLiteDatabase database = null;
    String imagePath = "monan_png/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bua_toi_layout, container, false);
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        }
        lv = rootView.findViewById(R.id.lv_food);
        ImageButton backButton = rootView.findViewById(R.id.back);
        backButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });
        Drawable divider = ContextCompat.getDrawable(requireContext(), R.drawable.divider);
        lv.setDivider(divider);
        lv.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.divider_height));

        themDatabase();

        foodItemList = new ArrayList<>();
        database = requireContext().openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        String[] tables = {"FRUIT", "VEGETABLE", "MEAT", "SEAFOOD", "RICE", "THUCAN"};
        foodItemList.clear();
        for (String tableName : tables) {
            Cursor cursor = database.query(tableName, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                String foodName = cursor.getString(1);
                String foodCalo = cursor.getString(2);
                String foodProtein = cursor.getString(3);
                String foodFat = cursor.getString(4);
                String foodDetail = cursor.getString(5);
                String imageFileName = cursor.getString(6);
                String imageResource = imageFileName + ".png";
                try {
                    AssetManager assetManager = requireContext().getAssets();
                    InputStream inputStream = assetManager.open(imagePath + imageResource);

                    // Tạo đối tượng FoodItem và thêm vào danh sách
                    FoodItem foodItem = new FoodItem(foodName, foodCalo, foodProtein, foodFat, foodDetail, imageResource);
                    foodItemList.add(foodItem);

                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            cursor.close();
        }
        foodItemAdapter = new DSMonAnAdapter(requireContext(), R.layout.ds_mon_layout, foodItemList, imagePath);
        lv.setAdapter(foodItemAdapter);

        Button submitButton = rootView.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            int totalCalories = 0;
            double totalFats = 0;
            double totalProteins = 0;

            for (int i = 0; i < lv.getCount(); i++) {
                View view = lv.getChildAt(i);
                if (view != null) {
                    TextView caloCalTextView = view.findViewById(R.id.fCalo_amount);
                    TextView fatCalTextView = view.findViewById(R.id.fFat_amount);
                    TextView proteinCalTextView = view.findViewById(R.id.fProtein_amount);

                    // Lấy giá trị từ TextView và cộng vào tổng calo
                    String caloTxt = caloCalTextView.getText().toString();
                    String[] parts = caloTxt.split("/");
                    String caloText = parts[0];
                    String fatText = fatCalTextView.getText().toString().replaceAll("[^\\d.]", "");;
                    String proteinText = proteinCalTextView.getText().toString().replaceAll("[^\\d.]", "");;
                    if (!caloText.isEmpty() && !fatText.isEmpty() && !proteinText.isEmpty()) {
                        try {
                            int caloValue = Integer.parseInt(caloText);
                            double fatValue = Double.parseDouble(fatText);
                            double proteinValue = Double.parseDouble(proteinText);

                            totalCalories += caloValue;
                            totalFats += fatValue;
                            totalProteins += proteinValue;

                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }


                }
            }
            TextView caloConsumed = rootView.findViewById(R.id.food_calo);
            caloConsumed.setText(String.valueOf(totalCalories));
            TextView proteinConsumed = rootView.findViewById(R.id.food_protein);
            proteinConsumed.setText(String.valueOf(totalFats));
            TextView fatConsumed = rootView.findViewById(R.id.food_fat);
            fatConsumed.setText(String.valueOf(totalProteins));

            SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt("totalCaloriesDinner", totalCalories);
            editor.putFloat("totalFatsDinner", (float) totalFats);
            editor.putFloat("totalProteinsDinner", (float) totalProteins);

            editor.apply();

        });

        return rootView;
    }

    private void themDatabase() {
        File dbFile = getContext().getDatabasePath(DB_NAME);

        if(!dbFile.exists()) {
            copyDB();
        }
        else {
            dbFile.delete();
            copyDB();
        }
    }

    private void copyDB() {
        try {
            InputStream myInput = getContext().getAssets().open(DB_NAME);
            String outFileName = getContext().getApplicationInfo().dataDir+DB_PATH+DB_NAME;
            File f = new File(getContext().getApplicationInfo().dataDir+DB_PATH);

            if(!f.exists()) {
                f.mkdir();
            }

            OutputStream myOutPut = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int len;
            while((len=myInput.read(buffer))>0) {
                myOutPut.write(buffer,0,len);
            }
            myOutPut.flush();

            myInput.close();
            myOutPut.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Lỗi sao chép dữ liệu", e.toString());
        }
    }
}
