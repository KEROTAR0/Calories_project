package vn.edu.dlu.ctk45.calories_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

import java.util.ArrayList;

public class HoatDong extends Fragment {

    SharedPreferences sharedPreferences;
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


        String[] actName = new String[]{"Đi bộ", "Chạy bộ", "Chạy", "Đi xe đạp", "Leo cầu thang", "Tập thể dục", "Nhảy dây", "Bơi", "Yoga"};
        int [] actInfo = {
                18,
                30,
                50,
                23,
                34,
                25,
                35,
                45,
                16,
        };
        int[] imgs = new int[]{R.drawable.walking, R.drawable.jogging, R.drawable.run, R.drawable.bike
                , R.drawable.climb_stair, R.drawable.exercise, R.drawable.jumprope, R.drawable.swimming,
                R.drawable.yoga};

        ArrayList<Activity> act = new ArrayList<>();
        for (int i = 0; i < actName.length; i++) {
            act.add(new Activity(actName[i], actInfo[i], imgs[i]));
        }

        ActivitiesList adapter = new ActivitiesList(act);
        listView.setAdapter(adapter);



        Button submitButton = rootView.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            int totalCalories = 0;

            for (int i = 0; i < listView.getCount(); i++) {
                View view = listView.getChildAt(i);
                if (view != null) {
                    TextView caloWasteTextView = view.findViewById(R.id.calo_waste);

                    // Lấy giá trị từ TextView và cộng vào tổng calo
                    String caloText = caloWasteTextView.getText().toString();
                    int caloValue = Integer.parseInt(caloText);
                    totalCalories += caloValue;
                }
            }
            TextView caloConsumed = rootView.findViewById(R.id.calo_consumed);
            caloConsumed.setText(String.valueOf(totalCalories));
            sharedPreferences = getContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("CaloWaste", totalCalories);
            editor.apply();
        });

        ImageButton backButton = rootView.findViewById(R.id.back);
        backButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });


        return rootView;
    }
}