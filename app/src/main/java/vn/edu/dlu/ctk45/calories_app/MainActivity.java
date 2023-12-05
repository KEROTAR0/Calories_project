package vn.edu.dlu.ctk45.calories_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFrg(new LoadScreen_ACT());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
    private void showFrg(Fragment frg) {
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, frg, null).commit();
    }
    public void gotoMainScreen() {
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, new MainScreen(), null).commit();

    }
    public void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.user_info_input, null);
        builder.setView(dialogView);

        EditText editTextName = dialogView.findViewById(R.id.nameInput);
        EditText editTextAge = dialogView.findViewById(R.id.ageInput);
        EditText editTextGender = dialogView.findViewById(R.id.genderInput);
        EditText editHeightGender = dialogView.findViewById(R.id.heightInput);
        EditText editWeightGender = dialogView.findViewById(R.id.weightInput);


        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String name = editTextName.getText().toString();
                String age = editTextAge.getText().toString();
                String gender = editTextGender.getText().toString();
                String height = editHeightGender.getText().toString();
                String weight = editWeightGender.getText().toString();

                ThongTinCaNhan_ACT thongTinCaNhan = (ThongTinCaNhan_ACT) getSupportFragmentManager().findFragmentByTag("thong_tin_ca_nhan");
                if (thongTinCaNhan != null) {
                    thongTinCaNhan.updateUserInfo(name, age, gender, height, weight);
                }

                SlideMenu menuUserName = (SlideMenu) getSupportFragmentManager().findFragmentByTag("slide_menu_layout");
                if (menuUserName != null) {
                    menuUserName.changeMenuUserName(name);
                }

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

}