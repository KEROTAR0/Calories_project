package vn.edu.dlu.ctk45.calories_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MucTieu_ACT extends Fragment {
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.muc_tieu, container, false);
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        }
        Spinner spinnerGoal = rootView.findViewById(R.id.spn_goal);
        String[] goals = {"Tăng cân", "Giảm cân", "Cân bằng"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, goals);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGoal.setAdapter(adapter);

        TextView caloNeeded = rootView.findViewById(R.id.calo_needed);
        ImageButton backButton = rootView.findViewById(R.id.back);
        backButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });
        /*
        ImageButton setGoalButton = rootView.findViewById(R.id.goal_btn);
        setGoalButton.setOnClickListener(v -> {
            sharedPreferences = requireContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            String selectedGoal = spinnerGoal.getSelectedItem().toString();

            try {
                String gender = sharedPreferences.getString("Gender", "");
                String age = sharedPreferences.getString("Age", "");
                String height = sharedPreferences.getString("Height", "");
                String weight = sharedPreferences.getString("Weight", "");
                int nAge = Integer.parseInt(age);
                int nHeight = Integer.parseInt(height);
                int nWeight = Integer.parseInt(weight);

                double caloRequired;
                if(gender.equalsIgnoreCase("Nam")) {
                    caloRequired = 66 + (13.7 * nWeight) + (5 * nHeight) - (6.76 * nAge);
                } else {
                    caloRequired = 655 + (9.6 * nWeight) + (1.8 * nHeight) - (4.7 * nAge);
                }
                int nCaloRequired = (int) caloRequired;

                int caloNeededValue;
                switch (selectedGoal) {
                    case "Tăng cân":
                        caloNeededValue = nCaloRequired + 300;
                        break;
                    case "Giảm cân":
                        caloNeededValue = nCaloRequired - 300;
                        break;
                    case "Cân bằng":
                    default:
                        caloNeededValue = nCaloRequired;
                        break;
                }
                caloNeeded.setText(String.valueOf(caloNeededValue));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        */
        return rootView;
    }
}