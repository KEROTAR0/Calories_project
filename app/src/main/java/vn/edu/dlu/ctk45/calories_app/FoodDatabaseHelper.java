package vn.edu.dlu.ctk45.calories_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class FoodDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "qlmonan.db";
    public static final String TABLE_FOOD_FRUIT = "FRUIT";
    public static final String TABLE_FOOD_MEAT = "MEAT";
    public static final String TABLE_FOOD_RICE = "RICE";
    public static final String TABLE_FOOD_SEAFOOD = "SEAFOOD";
    public static final String TABLE_FOOD_VEGETABLE = "VEGETABLE";

    //fruit
    public static final String TABLE_FOOD_FRUIT_ID = "ma_mon";
    public static final String TABLE_FOOD_FRUIT_NAME = "ten_mon";
    public static final String TABLE_FOOD_FRUIT_CALO = "calo_mon";
    public static final String TABLE_FOOD_FRUIT_PROTEIN = "protein_mon";
    public static final String TABLE_FOOD_FRUIT_FAT = "chat_beo_mon";
    public static final String TABLE_FOOD_FRUIT_DETAIL = "mota_mon";
    public static final String TABLE_FOOD_FRUIT_IMG = "hinh_anh";

    //meat
    public static final String TABLE_FOOD_MEAT_ID = "ma_mon";
    public static final String TABLE_FOOD_MEAT_NAME = "ten_mon";
    public static final String TABLE_FOOD_MEAT_CALO = "calo_mon";
    public static final String TABLE_FOOD_MEAT_PROTEIN = "protein_mon";
    public static final String TABLE_FOOD_MEAT_FAT = "chat_beo_mon";
    public static final String TABLE_FOOD_MEAT_DETAIL = "mota_mon";
    public static final String TABLE_FOOD_MEAT_IMG = "hinh_anh";

    //rice
    public static final String TABLE_FOOD_RICE_ID = "ma_mon";
    public static final String TABLE_FOOD_RICE_NAME = "ten_mon";
    public static final String TABLE_FOOD_RICE_CALO = "calo_mon";
    public static final String TABLE_FOOD_RICE_PROTEIN = "protein_mon";
    public static final String TABLE_FOOD_RICE_FAT = "chat_beo_mon";
    public static final String TABLE_FOOD_RICE_DETAIL = "mota_mon";
    public static final String TABLE_FOOD_RICE_IMG = "hinh_anh";

    //seafood
    public static final String TABLE_FOOD_SEAFOOD_ID = "ma_mon";
    public static final String TABLE_FOOD_SEAFOOD_NAME = "ten_mon";
    public static final String TABLE_FOOD_SEAFOOD_CALO = "calo_mon";
    public static final String TABLE_FOOD_SEAFOOD_PROTEIN = "protein_mon";
    public static final String TABLE_FOOD_SEAFOOD_FAT = "chat_beo_mon";
    public static final String TABLE_FOOD_SEAFOOD_DETAIL = "mota_mon";
    public static final String TABLE_FOOD_SEAFOOD_IMG = "hinh_anh";

    //vegetable
    public static final String TABLE_FOOD_VEGETABLE_ID = "ma_mon";
    public static final String TABLE_FOOD_VEGETABLE_NAME = "ten_mon";
    public static final String TABLE_FOOD_VEGETABLE_CALO = "calo_mon";
    public static final String TABLE_FOOD_VEGETABLE_PROTEIN = "protein_mon";
    public static final String TABLE_FOOD_VEGETABLE_FAT = "chat_beo_mon";
    public static final String TABLE_FOOD_VEGETABLE_DETAIL = "mota_mon";
    public static final String TABLE_FOOD_VEGETABLE_IMG = "hinh_anh";



    public FoodDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbFRUIT = "CREATE TABLE " + TABLE_FOOD_FRUIT +
                "(" +
                TABLE_FOOD_FRUIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TABLE_FOOD_FRUIT_NAME + " TEXT," +
                TABLE_FOOD_FRUIT_CALO + " TEXT," +
                TABLE_FOOD_FRUIT_PROTEIN + " TEXT," +
                TABLE_FOOD_FRUIT_FAT + " TEXT," +
                TABLE_FOOD_FRUIT_DETAIL + " TEXT," +
                TABLE_FOOD_FRUIT_IMG + " TEXT )";
        String tbMEAT = "CREATE TABLE " + TABLE_FOOD_MEAT +
                "(" +
                TABLE_FOOD_MEAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TABLE_FOOD_MEAT_NAME + " TEXT," +
                TABLE_FOOD_MEAT_CALO + " TEXT," +
                TABLE_FOOD_MEAT_PROTEIN + " TEXT," +
                TABLE_FOOD_MEAT_FAT + " TEXT," +
                TABLE_FOOD_MEAT_DETAIL + " TEXT," +
                TABLE_FOOD_MEAT_IMG + " TEXT )";
        String tbRICE = "CREATE TABLE " + TABLE_FOOD_RICE +
                "(" +
                TABLE_FOOD_RICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TABLE_FOOD_RICE_NAME + " TEXT," +
                TABLE_FOOD_RICE_CALO + " TEXT," +
                TABLE_FOOD_RICE_PROTEIN + " TEXT," +
                TABLE_FOOD_RICE_FAT + " TEXT," +
                TABLE_FOOD_RICE_DETAIL + " TEXT," +
                TABLE_FOOD_RICE_IMG + " TEXT )";
        String tbSEAFOOD = "CREATE TABLE " + TABLE_FOOD_SEAFOOD +
                "(" +
                TABLE_FOOD_SEAFOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TABLE_FOOD_SEAFOOD_NAME + " TEXT," +
                TABLE_FOOD_SEAFOOD_CALO + " TEXT," +
                TABLE_FOOD_SEAFOOD_PROTEIN + " TEXT," +
                TABLE_FOOD_SEAFOOD_FAT + " TEXT," +
                TABLE_FOOD_SEAFOOD_DETAIL + " TEXT," +
                TABLE_FOOD_SEAFOOD_IMG + " TEXT )";
        String tbVEGETABLE = "CREATE TABLE " + TABLE_FOOD_VEGETABLE +
                "(" +
                TABLE_FOOD_VEGETABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TABLE_FOOD_VEGETABLE_NAME + " TEXT," +
                TABLE_FOOD_VEGETABLE_CALO + " TEXT," +
                TABLE_FOOD_VEGETABLE_PROTEIN + " TEXT," +
                TABLE_FOOD_VEGETABLE_FAT + " TEXT," +
                TABLE_FOOD_VEGETABLE_DETAIL + " TEXT," +
                TABLE_FOOD_VEGETABLE_IMG + " TEXT )";

        db.execSQL(tbFRUIT);
        db.execSQL(tbMEAT);
        db.execSQL(tbRICE);
        db.execSQL(tbSEAFOOD);
        db.execSQL(tbVEGETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public SQLiteDatabase open() {
        return  this.getWritableDatabase();
    }
}

