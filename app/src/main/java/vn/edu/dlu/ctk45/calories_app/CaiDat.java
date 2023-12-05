package vn.edu.dlu.ctk45.calories_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CaiDat extends Fragment {

    private Switch dayNightSwitch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cai_dat_layout, container,
                false);
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        }

        /*
        //đang lỗi
        dayNightSwitch = rootView.findViewById(R.id.dayNightSwitch);

        dayNightSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveNightModeState(isChecked);
            applyNightMode(isChecked);
        });

        boolean isNightMode = getNightModeState();
        dayNightSwitch.setChecked(isNightMode);
        applyNightMode(isNightMode);

        */
        ImageButton backButton = rootView.findViewById(R.id.back);
        backButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });

        return rootView;
    }

    private void saveNightModeState(boolean isNightMode) {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isNightMode", isNightMode);
            editor.apply();
        }
    }

    private boolean getNightModeState() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            return sharedPreferences.getBoolean("isNightMode", false);
        }
        return false;
    }

    private void applyNightMode(boolean isNightMode) {
        if (getActivity() != null) {
            if (isNightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            getActivity().recreate();
        }
    }
}


