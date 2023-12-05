package vn.edu.dlu.ctk45.calories_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ThongTinCaNhan_ACT extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
    private void showInputDialog() {
        ((MainActivity) getActivity()).showInputDialog();
    }
    public void updateUserInfo(String name, String age, String gender, String height, String weight) {

        View view = getView();
        if(view != null) {
            TextView textViewName = view.findViewById(R.id.user_name);
            TextView textViewAge = view.findViewById(R.id.user_age);
            TextView textViewGender = view.findViewById(R.id.user_gender);
            TextView textViewHeight = view.findViewById(R.id.user_height);
            TextView textViewWeight = view.findViewById(R.id.user_weight);

            if (name.isEmpty() || age.isEmpty() || gender.isEmpty() || height.isEmpty() || weight.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            }
            else {
                textViewName.setText(name);
                textViewAge.setText(age);
                textViewGender.setText(gender);
                textViewHeight.setText(height);
                textViewWeight.setText(weight);
            }
        }

    }
    public void delUserInfo()
    {
        View view = getView();

        TextView textViewName = view.findViewById(R.id.user_name);
        TextView textViewAge = view.findViewById(R.id.user_age);
        TextView textViewGender = view.findViewById(R.id.user_gender);
        TextView textViewHeight = view.findViewById(R.id.user_height);
        TextView textViewWeight = view.findViewById(R.id.user_weight);

        textViewName.setText(R.string.user_name);
        textViewAge.setText(R.string.user_age);
        textViewGender.setText(R.string.user_gender);
        textViewHeight.setText(R.string.user_heigh);
        textViewWeight.setText(R.string.user_weight);
    }
}