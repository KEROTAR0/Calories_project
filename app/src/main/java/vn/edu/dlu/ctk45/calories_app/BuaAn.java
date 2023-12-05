package vn.edu.dlu.ctk45.calories_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class BuaAn extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bua_an, container,
                false);
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        ImageButton backButton = rootView.findViewById(R.id.back);
        backButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });
        rootView.findViewById(R.id.add_breakfast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.ln_main, new BuaSang());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        rootView.findViewById(R.id.add_lunch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.ln_main, new BuaTrua());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        rootView.findViewById(R.id.add_dinner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.ln_main, new BuaToi());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return rootView;
    }
}
