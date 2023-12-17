package vn.edu.dlu.ctk45.calories_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "food_info.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "food_info";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_ENERGY = "energy";
    public static final String COLUMN_UNIT = "unit";
    public static final String COLUMN_SALT = "salt";
    public static final String COLUMN_PROTEIN = "protein";
    public static final String COLUMN_CARB = "carb";
    public static final String COLUMN_FAT = "fat";
    public static final String COLUMN_CALCIUM = "calcium";
    public static final String COLUMN_CHOLESTEROL = "cholesterol";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_AMOUNT + " TEXT," +
                    COLUMN_ENERGY + " TEXT," +
                    COLUMN_UNIT + " TEXT," +
                    COLUMN_SALT + " TEXT," +
                    COLUMN_PROTEIN + " TEXT," +
                    COLUMN_CARB + " TEXT," +
                    COLUMN_FAT + " TEXT," +
                    COLUMN_CALCIUM + " TEXT," +
                    COLUMN_CHOLESTEROL + " TEXT)";

    public FoodDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades here
    }

    // CRUD Operations

    public long addFoodInfo(String name, String amount, String energy, String unit, String salt,
                            String protein, String carb, String fat, String calcium, String cholesterol) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AMOUNT, amount);
        values.put(COLUMN_ENERGY, energy);
        values.put(COLUMN_UNIT, unit);
        values.put(COLUMN_SALT, salt);
        values.put(COLUMN_PROTEIN, protein);
        values.put(COLUMN_CARB, carb);
        values.put(COLUMN_FAT, fat);
        values.put(COLUMN_CALCIUM, calcium);
        values.put(COLUMN_CHOLESTEROL, cholesterol);

        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public Cursor getAllFoodInfo() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_AMOUNT,
                COLUMN_ENERGY,
                COLUMN_UNIT,
                COLUMN_SALT,
                COLUMN_PROTEIN,
                COLUMN_CARB,
                COLUMN_FAT,
                COLUMN_CALCIUM,
                COLUMN_CHOLESTEROL
        };

        return db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }

    public void deleteFoodInfo(long foodId) {
        SQLiteDatabase db = getWritableDatabase();
        String selection = COLUMN_ID + "=?";
        String[] selectionArgs = {String.valueOf(foodId)};
        db.delete(TABLE_NAME, selection, selectionArgs);
        db.close();
    }

    // Add other CRUD operations as needed
}

