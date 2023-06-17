package com.tappuri.onki;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "onki_phrases.db";
    private static final int DATABASE_VERSION = 1;
    private final Context mContext;
    private SQLiteDatabase database;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // The database file is already provided, so you can leave this method empty
//        this.copyDatabaseFromAssets();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DATABASE_NAME).getPath();
        if (database != null && database.isOpen()) {
            return;
        }
        database = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void copyDatabaseFromAssets() {
        String dbPath = mContext.getDatabasePath(DATABASE_NAME).getPath();

        try {
            InputStream inputStream = mContext.getAssets().open("onki_phrases.db");
            OutputStream outputStream = new FileOutputStream(dbPath);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> searchRecords(String searchText) {
        List<String> results = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();

        openDatabase();

        String selection = "English LIKE ?";
        String[] selectionArgs = new String[]{"%" + searchText + "%"};
        String[] columnArgs = new String[]{"English", "Japanese"};
        Cursor cursor = database.query("Phrases", columnArgs, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("English"));
                results.add(name);
            }
            cursor.close();
        }

        database.close();
        return results;
    }

    public Phrase findPhrase(String phraseName) {

        openDatabase();

        String selection = "English = ? OR Japanese = ?";
        String[] selectionArgs = new String[]{phraseName};
        String[] columnArgs = new String[]{"*"};
        Cursor cursor = database.query("Phrases", columnArgs, selection, selectionArgs, null, null, null, "1");

        Phrase selectedPhrase = null;

        if (cursor != null) {
            while (cursor.moveToNext()) {
                selectedPhrase = Phrase.getObjectFromCursor(cursor);
            }
            cursor.close();
        }

        database.close();
        return selectedPhrase;
    }


    @Override
    public synchronized void close() {
        if (database != null && database.isOpen()) {
            database.close();
        }
        super.close();
    }
}