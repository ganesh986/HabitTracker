package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by matteo on 25/06/2017.
 */

public class HabitDBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDBHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "habit.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link HabitDBHelper}.
     *
     * @param context of the app
     */
    public HabitDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * This is called when the database is created for the first time.
     */

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the habits table
        String SQL_CREATE_HABIT_TABLE =  "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_DATE + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_DURATION + " INTEGER NOT NULL DEFAULT 0, "
                + HabitEntry.COLUMN_HABIT_ACTIVITY + " TEXT);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
