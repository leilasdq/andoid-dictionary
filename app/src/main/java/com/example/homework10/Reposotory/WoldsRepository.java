package com.example.homework10.Reposotory;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.homework10.Controller.DictionaryHelper;
import com.example.homework10.Model.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class WoldsRepository {
    private static WoldsRepository ourInstance;
    private Context mContext;
    private  static  SQLiteDatabase mSQLiteDatabase;
    static DictionaryHelper mDictionaryHelper;
    public static final String TABLE_NAME = "Dictionary";

    public static WoldsRepository getInstance(Context context) {
        if (ourInstance==null){
            ourInstance = new WoldsRepository(context);
        }
        return ourInstance;
    }

    private WoldsRepository(Context context) {
        mContext = context.getApplicationContext();

        mDictionaryHelper = new DictionaryHelper(mContext);
        mSQLiteDatabase = mDictionaryHelper.getReadableDatabase();
    }

    public List<Dictionary> getAllWorlds(){
        List<Dictionary> dictionaries = new ArrayList<>();

        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        try {
            while (cursor.isAfterLast()){
                String engString = cursor.getString(cursor.getColumnIndex("English"));
                String perString = cursor.getString(cursor.getColumnIndex("Persian"));

                Dictionary dictionary = new Dictionary();
                dictionary.setEngWorld(engString);
                dictionary.setPerWorld(perString);

                dictionaries.add(dictionary);
            }
        } finally {
            cursor.close();
        }

        return dictionaries;
    }
}
