package vn.edu.dlu.ctk45.calories_app;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class HoatDong extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hoat_dong_layout, container, false);
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        ListView listView = rootView.findViewById(R.id.lv_activity);

        Drawable divider = ContextCompat.getDrawable(requireContext(), R.drawable.divider);

        listView.setDivider(divider);
        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.divider_height));


        String[] foodName = new String[]{"Đi bộ", "Chạy bộ", "Chạy", "Đi xe đạp", "Leo cầu thang", "Tập thể dục", "Nhảy dây", "Bơi", "Yoga"};
        String[] foodInfo = new String[]{
                "3.5 calo / 1 phút",
                "6 calo / 1 phút",
                "10 calo / 1 phút",
                "4.5 calo / 1 phút",
                "6 calo / 1 phút",
                "5 calo / 1 phút",
                "7 calo / 1 phút",
                "8 calo / 1 phút",
                "3 calo / 1 phút",
        };
        int[] imgs = new int[]{R.drawable.walking, R.drawable.jogging, R.drawable.run, R.drawable.bike
                , R.drawable.climb_stair, R.drawable.exercise, R.drawable.jumprope, R.drawable.swimming,
                R.drawable.yoga};

        ArrayList<Food> foodList = new ArrayList<>();
        for (int i = 0; i < foodName.length; i++) {
            foodList.add(new Food(foodName[i], foodInfo[i], imgs[i]));
        }

        DSMonAn adapter = new DSMonAn(foodList);
        listView.setAdapter(adapter);

        ImageButton backButton = rootView.findViewById(R.id.back);
        backButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });
        return rootView;
    }
}