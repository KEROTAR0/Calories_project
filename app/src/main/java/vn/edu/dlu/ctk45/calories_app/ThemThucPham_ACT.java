package vn.edu.dlu.ctk45.calories_app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ThemThucPham_ACT extends LinearLayout {

    private ImageButton backButton;
    private TextView titleTextView;
    private TextView nutritionTitleTextView;

    private EditText foodNameInput;
    private EditText foodAmountInput;
    private EditText energyAmountInput;
    private EditText calcUnitInput;
    private EditText saltInput;
    private EditText proteinInput;
    private EditText carbInput;
    private EditText fatInput;
    private EditText calciumInput;
    private EditText cholesterolInput;

    private FoodDatabaseHelper foodDatabaseHelper;

    public ThemThucPham_ACT(Context context, FoodDatabaseHelper foodDatabaseHelper) {
        super(context);
        this.foodDatabaseHelper = foodDatabaseHelper;
        initializeViews(context);
    }

    public ThemThucPham_ACT(Context context, AttributeSet attrs, FoodDatabaseHelper foodDatabaseHelper) {
        super(context, attrs);
        this.foodDatabaseHelper = foodDatabaseHelper;
        initializeViews(context);
    }

    public ThemThucPham_ACT(Context context, AttributeSet attrs, int defStyle, FoodDatabaseHelper foodDatabaseHelper) {
        super(context, attrs, defStyle);
        this.foodDatabaseHelper = foodDatabaseHelper;
        initializeViews(context);
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.them_thuc__pham_layout, this);

        // Initialize views
        backButton = findViewById(R.id.back);
        titleTextView = findViewById(R.id.textView2);
        nutritionTitleTextView = findViewById(R.id.textView5);

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
    }

    // Getter methods to retrieve the values entered by the user
    public String getFoodName() {
        return foodNameInput.getText().toString();
    }

    public String getFoodAmount() {
        return foodAmountInput.getText().toString();
    }

    public String getEnergyAmount() {
        return energyAmountInput.getText().toString();
    }

    public String getCalcUnit() {
        return calcUnitInput.getText().toString();
    }

    public String getSalt() {
        return saltInput.getText().toString();
    }

    public String getProtein() {
        return proteinInput.getText().toString();
    }

    public String getCarb() {
        return carbInput.getText().toString();
    }

    public String getFat() {
        return fatInput.getText().toString();
    }

    public String getCalcium() {
        return calciumInput.getText().toString();
    }

    public String getCholesterol() {
        return cholesterolInput.getText().toString();
    }
}
