package vn.edu.dlu.ctk45.calories_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FoodDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "food_database.db";
    private static final String TABLE_FOOD = "food";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "ten_mon";
    public static final String KEY_INFO = "thong_tin_mon";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_AMOUNT = "amount"; // New column for amount

    public FoodDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_FOOD +
                "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT," +
                KEY_INFO + " TEXT," +
                KEY_IMAGE + " INTEGER," +
                KEY_AMOUNT + " INTEGER" + // New column for amount
                ")";
        Log.d("SQL", "onCreate: " + CREATE_FOOD_TABLE);
        db.execSQL(CREATE_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        Log.d("SQL", "onUpgrade: Table dropped");
        onCreate(db);
    }

    public void addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getTen_mon());
        values.put(KEY_INFO, food.getThong_tin_mon());
        values.put(KEY_IMAGE, food.getImage());
        values.put(KEY_AMOUNT, 0); // Initialize amount to 0

        db.insert(TABLE_FOOD, null, values);
        db.close();
    }

    public Food getFood(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_FOOD,
                new String[]{KEY_ID, KEY_NAME, KEY_INFO, KEY_IMAGE, KEY_AMOUNT},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            cursor.moveToFirst();

            Food food = new Food(
                    cursor.getString(1), // name
                    cursor.getString(2), // info
                    cursor.getInt(3),      // image
                    cursor.getInt(4)      // amount
            );
            food.setId(Integer.parseInt(cursor.getString(0)));

            cursor.close();
            return food;
        } else {
            return null;
        }
    }

    public List<Food> getAllFood() {
        List<Food> foodList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_FOOD,
                new String[]{KEY_ID, KEY_NAME, KEY_INFO, KEY_IMAGE, KEY_AMOUNT},
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME);
            int infoIndex = cursor.getColumnIndex(KEY_INFO);
            int imageIndex = cursor.getColumnIndex(KEY_IMAGE);
            int amountIndex = cursor.getColumnIndex(KEY_AMOUNT);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                String info = cursor.getString(infoIndex);
                int image = cursor.getInt(imageIndex);
                int amount = cursor.getInt(amountIndex);

                Food food = new Food(name, info, image, amount);
                foodList.add(food);
            }

            cursor.close();
        }

        return foodList;
    }

    public void updateFoodAmount(int foodId, int amount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AMOUNT, amount);

        int rowsAffected = db.update(
                TABLE_FOOD,
                values,
                KEY_ID + "=?",
                new String[]{String.valueOf(foodId)}
        );
        Log.d("FoodDatabaseHelper", "Rows affected: " + rowsAffected);

        db.update(
                TABLE_FOOD,
                values,
                KEY_ID + "=?",
                new String[]{String.valueOf(foodId)}
        );

        db.close();
    }

    public void deleteFood(int foodId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_FOOD,
                KEY_ID + "=?",
                new String[]{String.valueOf(foodId)}
        );
        db.close();
    }
}

