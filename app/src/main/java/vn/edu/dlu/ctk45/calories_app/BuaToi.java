package vn.edu.dlu.ctk45.calories_app;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.database.Cursor;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class BuaToi extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bua_toi_layout, container, false);
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        ListView listView = rootView.findViewById(R.id.lv_food);

        Drawable divider = ContextCompat.getDrawable(requireContext(), R.drawable.divider);

        listView.setDivider(divider);
        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.divider_height));

        FoodDatabaseHelper dbHelper = new FoodDatabaseHelper(requireContext());
        List<Food> foodList = getAllFoodFromDatabase(dbHelper);


        String[] foodName = new String[]{"Bánh bao", "Bánh mì", "Xôi", "Cơm chiên", "Bún bò Huế", "Bún đậu mắm tôm", "Cà ri", "Cá viên chiên", "Đùi gà rán",
                "Hamburger", "Phở bò", "Xíu mại"};
        String[] foodInfo = new String[]{
                "328kcal, Protein: 16.1g, Fat: 8.4g",
                "431kcal, Protein: 20.1g, Fat: 14.2g",
                "562kcal, Protein: 12.7g, Fat: 14g",
                "530kcal, Protein: 11.3g, Fat: 11.3g",
                "622kcal, Protein: 30.2g, Fat: 30.6g",
                "829kcal, Protein: 58.3g, Fat: 44.5g",
                "278kcal, Protein: 7.8g, Fat: 11.4g",
                "10kcal, Protein: 1.5g, Fat: 0.28g",
                "173kcal, Protein: 11g, Fat: 12.3g",
                "488kcal, Protein: 28g, Fat: 20.2g",
                "422kcal, Protein: 18.2g, Fat: 13.1g",
                "52kcal, Protein: 6g, Fat: 2.1g"

        };
        int[] imgs = new int[]{R.drawable.food_banh_bao, R.drawable.food_banh_mi, R.drawable.food_xoi, R.drawable.food_com_chien
                , R.drawable.food_bunbohue, R.drawable.food_bundaumantom, R.drawable.food_cari, R.drawable.food_cavienchien,
                R.drawable.food_duigaran, R.drawable.food_hamburger, R.drawable.food_phobo, R.drawable.food_xiumai};

    for (int i = 0; i < foodName.length; i++) {
        Food food = new Food(foodName[i], foodInfo[i], imgs[i], 0);
        foodList.add(food);

        dbHelper.addFood(food);
    }

        DSMonAn adapter = new DSMonAn(foodList, dbHelper);
        listView.setAdapter(adapter);

        ImageButton backButton = rootView.findViewById(R.id.back);
        backButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });
        return rootView;
    }
    private List<Food> getAllFoodFromDatabase(FoodDatabaseHelper dbHelper) {
        return dbHelper.getAllFood();
    }


}

