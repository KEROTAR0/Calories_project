package vn.edu.dlu.ctk45.calories_app;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class FruitFragment extends Fragment {
    private SQLiteDatabase database;
    ListView lv;
    private ArrayList<FoodItem> foodItemList;
    private FoodItemAdapter foodItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.trai_cay_layout, container, false);
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        }
        lv = rootView.findViewById(R.id.food_list);
        ImageButton backButton = rootView.findViewById(R.id.back);
        backButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });
        loadDatabaseData();
        return rootView;
    }

    private void loadDatabaseData() {
        foodItemList = new ArrayList<>();

        // Mở hoặc tạo cơ sở dữ liệu
        database = SQLiteDatabase.openDatabase(((MainActivity) getActivity()).getDatabasePath(), null, SQLiteDatabase.OPEN_READONLY);

        // Truy vấn dữ liệu từ bảng db_mon_fruit
        Cursor cursor = database.rawQuery("SELECT * FROM db_mon_fruit", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                // Đọc thông tin từ Cursor
                String foodName = cursor.getString(1);
                String foodCalo = cursor.getString(2);
                String foodProtein = cursor.getString(3);
                String foodFat = cursor.getString(4);
                String foodDetail = cursor.getString(5);
                String imageFileName = cursor.getString(6); // Assume file name without ".png"

                // Thêm đuôi ".png" cho tên file ảnh
                String imageResource = imageFileName + ".png";

                // Tạo đối tượng FoodItem và thêm vào danh sách
                try {
                    AssetManager assetManager = requireContext().getAssets();
                    InputStream inputStream = assetManager.open("fruit/" + imageFileName + ".png");

                    // Tạo đối tượng FoodItem và thêm vào danh sách
                    FoodItem foodItem = new FoodItem(foodName, foodCalo, foodProtein, foodFat, foodDetail, imageResource);
                    foodItemList.add(foodItem);

                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());

            cursor.close();
        }

        // Tạo adapter và gán cho ListView
        foodItemAdapter = new FoodItemAdapter(requireContext(), R.layout.ds_thong_tin_thuc_pham, foodItemList);
        lv.setAdapter(foodItemAdapter);
    }
}
