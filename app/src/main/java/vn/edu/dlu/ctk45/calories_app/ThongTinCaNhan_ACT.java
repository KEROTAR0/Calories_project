package vn.edu.dlu.ctk45.calories_app;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ThongTinCaNhan_ACT extends Fragment {
    private DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbHelper = new DatabaseHelper(requireContext());
        View rootView = inflater.inflate(R.layout.thong_tin_ca_nhan, container,
                false);
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        }

        Button btn_change_info = rootView.findViewById(R.id.btn_doi_info);
        Button btn_del_info = rootView.findViewById(R.id.btn_xoa_info);

        btn_change_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }

        });
        btn_del_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delUserInfo();
            }
        });
        ImageButton backButton = rootView.findViewById(R.id.back);
        backButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });

        return rootView;
    }

    public void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.user_info_input, null);
        builder.setView(dialogView);

        EditText editTextName = dialogView.findViewById(R.id.nameInput);
        EditText editTextAge = dialogView.findViewById(R.id.ageInput);
        EditText editHeightGender = dialogView.findViewById(R.id.heightInput);
        EditText editWeightGender = dialogView.findViewById(R.id.weightInput);

        Spinner spinnerGender = dialogView.findViewById(R.id.spn_gender);
        String[] genders = {"Nam", "Nữ", "Giới tính khác"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editTextName.getText().toString();
                String age = editTextAge.getText().toString();
                String gender = spinnerGender.getSelectedItem().toString();
                String height = editHeightGender.getText().toString();
                String weight = editWeightGender.getText().toString();

                updateUserInfo(name, age, gender, height, weight);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public void updateUserInfo(String name, String age, String gender, String height, String weight) {
        Log.d("TAG", "updateUserInfo Called");
        View view = getView();
        if (view != null) {
            TextView textViewName = view.findViewById(R.id.user_name);
            TextView textViewAge = view.findViewById(R.id.user_age);
            TextView textViewGender = view.findViewById(R.id.user_gender);
            TextView textViewHeight = view.findViewById(R.id.user_height);
            TextView textViewWeight = view.findViewById(R.id.user_weight);

            if (name.isEmpty() || age.isEmpty() || gender.isEmpty() || height.isEmpty() || weight.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                saveUserInfoToDatabase(name, age, gender, height, weight);
                textViewName.setText(name);
                textViewAge.setText(age);
                textViewGender.setText(gender);
                textViewHeight.setText(height);
                textViewWeight.setText(weight);

                // Calculate and display calories needed
                double caloriesNeeded = calculateCaloriesNeeded(age, gender, height, weight);
                // You can display or use the calculated value as needed
                // For example, you might show it in a TextView or store it in the database
            }
        }
    }

    private void saveUserInfoToDatabase(String name, String age, String gender, String height, String weight) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_AGE, age);
        values.put(DatabaseHelper.COLUMN_GENDER, gender);
        values.put(DatabaseHelper.COLUMN_HEIGHT, height);
        values.put(DatabaseHelper.COLUMN_WEIGHT, weight);

        db.update(DatabaseHelper.TABLE_NAME, values, null, null);

        db.close();
    }


    public void delUserInfo() {
        View view = getView();
        if (view != null) {
            TextView textViewName = view.findViewById(R.id.user_name);
            TextView textViewAge = view.findViewById(R.id.user_age);
            TextView textViewGender = view.findViewById(R.id.user_gender);
            TextView textViewHeight = view.findViewById(R.id.user_height);
            TextView textViewWeight = view.findViewById(R.id.user_weight);

            textViewName.setText(R.string.user_name);
            textViewAge.setText(R.string.user_age);
            textViewGender.setText(R.string.user_gender);
            textViewHeight.setText(R.string.user_height);
            textViewWeight.setText(R.string.user_weight);

            deleteUserInfoFromDatabase();
        }
    }

    private void deleteUserInfoFromDatabase() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_NAME, null, null);
        db.close();
    }

    public double calculateCaloriesNeeded(String age, String gender, String height, String weight) {
        // Check if any of the input values are empty
        if (age.isEmpty() || gender.isEmpty() || height.isEmpty() || weight.isEmpty()) {
            Toast.makeText(requireContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return 0.0; // Return 0 if there is missing information
        }

        // Convert input values to appropriate data types
        int userAge = Integer.parseInt(age);
        int userHeight = Integer.parseInt(height);
        int userWeight = Integer.parseInt(weight);

        // Harris-Benedict equation to calculate BMR
        double bmr;
        if (gender.equals("Nam")) {
            bmr = 88.362 + (13.397 * userWeight) + (4.799 * userHeight) - (5.677 * userAge);
        } else if (gender.equals("Nữ")) {
            bmr = 447.593 + (9.247 * userWeight) + (3.098 * userHeight) - (4.330 * userAge);
        } else {
            // Use a default value or handle other gender options
            bmr = 0.0;
        }

        // Activity factor to estimate TDEE
        double activityFactor = 1.2; // Assume a sedentary lifestyle as a default
        double tdee = bmr * activityFactor;

        // You can return TDEE or use it further based on your application logic
        return tdee;
    }
}

