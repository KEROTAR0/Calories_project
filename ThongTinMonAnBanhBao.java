package vn.edu.dlu.ctk45.calories_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThongTinMonAnBanhBao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thong_tin_mon_an_banh_bao);

        // Initialize your UI components
        ImageButton backButton = findViewById(R.id.imageButton2);
        TextView foodNameTextView = findViewById(R.id.textView3);
        TextView foodDescriptionTextView = findViewById(R.id.textView4);
        Button addFoodButton = findViewById(R.id.button2);

        // Set click listener for back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the activity
            }
        });

        // Set up your food details
        foodNameTextView.setText("Bánh bao");
        foodDescriptionTextView.setText("Bánh bao nhân thịt là món ăn vặt tuyệt vời cho tất cả mọi người, nhất là với những lúc bụng đói. Vỏ bánh trắng ngần hòa cùng nhân thịt nạc, trứng cút, nấm mèo đậm đà hương vị bên trong đã khiến bánh bao trở thành món khoái khẩu của nhiều người.");}}

        //
