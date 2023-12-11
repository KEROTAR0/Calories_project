package vn.edu.dlu.ctk45.calories_app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThemThucPham extends AppCompatActivity {

    private EditText foodNameInput, foodAmountInput, energyAmountInput, calcUnitInput,
            saltInput, proteinInput, carbInput, fatInput, calciumInput, cholesterolInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_thuc__pham_layout);

        // Initialize your UI components
        foodNameInput = findViewById(R.id.food_nameInput);
        foodAmountInput = findViewById(R.id.food_ammountInput);
        energyAmountInput = findViewById(R.id.energy_amountInput);
        calcUnitInput = findViewById(R.id.calc_unitInput);
        saltInput = findViewById(R.id.saltInput);
        proteinInput = findViewById(R.id.proteinInput);
        carbInput = findViewById(R.id.carbInput);
        fatInput = findViewById(R.id.fattInput);
        calciumInput = findViewById(R.id.calciumInput);
        cholesterolInput = findViewById(R.id.cholesterolInput);

        ImageButton backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle back button click
                finish(); // Close the activity
            }
        });
    }

    // You can add more methods for handling user interactions or data submission if needed
}
