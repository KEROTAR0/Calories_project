package vn.edu.dlu.ctk45.calories_app;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class VegetableFragment extends Fragment {
    ListView lv;
    ArrayList<FoodItem> foodItemList;
    FoodItemAdapter foodItemAdapter;
    String DB_NAME = "qlmonan.db";
    String DB_PATH = "/databases/";
    SQLiteDatabase database = null;
    String imagePath = "vegetable_img/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.haisan_layout, container, false);
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
        themDatabase();

        foodItemList = new ArrayList<>();
        database = requireContext().openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        Cursor cursor = database.query("VEGETABLE",null,null,null,null,null,null);
        foodItemList.clear();
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
        foodItemAdapter = new FoodItemAdapter(requireContext(), R.layout.ds_thong_tin_thuc_pham, foodItemList, imagePath);
        lv.setAdapter(foodItemAdapter);
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
