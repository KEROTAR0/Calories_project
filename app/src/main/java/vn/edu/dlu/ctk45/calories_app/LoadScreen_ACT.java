package vn.edu.dlu.ctk45.calories_app;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class LoadScreen_ACT extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        }
        initViews();
        return inflater.inflate(R.layout.load_screen, container, false);
    }
    private void initViews() {
        new Handler().postDelayed(this::gotoMainScreen,
                2000);
    }

    private void gotoMainScreen() {
        FragmentActivity activity = getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).gotoMainScreen();
        }
    }
}
