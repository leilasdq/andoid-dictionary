package com.example.homework10.Controller;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.annotation.Nullable;

public class DictionaryHelper extends SQLiteOpenHelper {

    String DB_PATH ;
    Context mContext;
    public static final String DATABASE_NAME = "Database.db";
    public static final int  VERSION = 1;

    public DictionaryHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        mContext = context.getApplicationContext();
        DB_PATH = "data/data/" + context.getPackageName() + "/databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { copyDatabase(); }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }

    public void checkDatabase(){
        SQLiteDatabase sqLiteDatabase = null;
        String filePath = DB_PATH + DATABASE_NAME;

        try {
            sqLiteDatabase = SQLiteDatabase.openDatabase(filePath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e){ }

        if (sqLiteDatabase==null) copyDatabase();
        else openDatabase();
    }

    private void copyDatabase(){
        String filePath = DB_PATH + DATABASE_NAME;

        try {
            InputStream inputStream = mContext.getAssets().open(DATABASE_NAME);
            OutputStream outputStream  = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024];
            int len;

            while ((len=inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDatabase(){
        String filePath = DB_PATH + DATABASE_NAME;
        SQLiteDatabase.openDatabase(filePath, null, SQLiteDatabase.OPEN_READONLY);
    }
}
