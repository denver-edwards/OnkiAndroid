package com.tappuri.onki;

import android.database.Cursor;

public class Phrase {
    public String category;
    public String english;
    public String example1Eng;
    public String example1Jpn;
    public String example2Eng;
    public String example2Jpn;
    public String example3Eng;
    public String example3Jpn;
    public String japanese;
    public int level;
    public String summary;
    public String tag;
    public String title;


    public Phrase(String category, String english, String example1Eng, String example1Jpn, String example2Eng, String example2Jpn, String example3Eng, String example3Jpn, String japanese, int level, String summary, String tag, String title) {
        this.category = category;
        this.english = english;
        this.example1Eng = example1Eng;
        this.example1Jpn = example1Jpn;
        this.example2Eng = example2Eng;
        this.example2Jpn = example2Jpn;
        this.example3Eng = example3Eng;
        this.example3Jpn = example3Jpn;
        this.japanese = japanese;
        this.level = level;
        this.summary = summary;
        this.tag = tag;
        this.title = title;
    }

    public static Phrase getObjectFromCursor(Cursor cursor) {
        String category = getStringFromCursor(cursor, "Category");
        String english = getStringFromCursor(cursor, "English");
        String example1Eng = getStringFromCursor(cursor, "Examples.1.Eng");
        String example1Jpn = getStringFromCursor(cursor, "Examples.1.Jpn");
        String example2Eng = getStringFromCursor(cursor, "Examples.2.Eng");
        String example2Jpn = getStringFromCursor(cursor, "Examples.2.Jpn");
        String example3Eng = getStringFromCursor(cursor, "Examples.3.Eng");
        String example3Jpn = getStringFromCursor(cursor, "Examples.3.Jpn");
        String japanese = getStringFromCursor(cursor, "Japanese");
        int level = getIntFromCursor(cursor, "Level");
        String summary = getStringFromCursor(cursor, "Summary");
        String tag = getStringFromCursor(cursor, "Tag");
        String title = getStringFromCursor(cursor, "Title");

        return new Phrase(category, english, example1Eng, example1Jpn, example2Eng, example2Jpn, example3Eng, example3Jpn, japanese, level, summary, tag, title);
    }

    private static String getStringFromCursor(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex != -1) {
            return cursor.getString(columnIndex);
        }
        return null;
    }

    private static int getIntFromCursor(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex != -1) {
            return cursor.getInt(columnIndex);
        }
        return 0;
    }

}