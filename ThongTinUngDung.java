package vn.edu.dlu.ctk45.calories_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThongTinUngDung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thong_tin_ung_dung_layout);

        // Initialize your UI components
        ImageButton backButton = findViewById(R.id.back);
        ImageView appIcon = findViewById(R.id.app_icon);
        TextView versionText = findViewById(R.id.textView17);
        TextView supportText = findViewById(R.id.textView18);
        TextView recipeText = findViewById(R.id.textView19);
        TextView weightLossText = findViewById(R.id.textView20);

        // Set click listener for back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the activity
            }
        });

        // You can add more functionality or customization as needed
    }
}
