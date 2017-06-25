package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDBHelper;

public class MainActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private HabitDBHelper mDbHelper;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDBHelper(this);
        insertHabit();
        Cursor Cursor_habit = createCursor();
        readCursor(Cursor_habit);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Cursor Cursor_habit = createCursor();
        readCursor(Cursor_habit);
    }

    /**
     * Temporary helper method to display information in the onscreen TextView
     */
    private Cursor createCursor() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_DATE,
                HabitEntry.COLUMN_HABIT_DURATION,
                HabitEntry.COLUMN_HABIT_ACTIVITY};

        // Perform a query on the pets table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        return cursor;
    }

    private void readCursor(Cursor cursor){

        TextView displayView = (TextView) findViewById(R.id.text_view_pet);
        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_DATE + " - " +
                    HabitEntry.COLUMN_HABIT_DURATION + " - " +
                    HabitEntry.COLUMN_HABIT_ACTIVITY + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DATE);
            int durationColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DURATION);
            int anctivityColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_ACTIVITY );

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                int currentDuration = cursor.getInt(durationColumnIndex);
                String currentActivity = cursor.getString(anctivityColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentDate + " - " +
                        currentDuration + "[min] - " +
                        currentActivity));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded HABIT data into the database.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void insertHabit() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //Get date now
        DateFormat df = new SimpleDateFormat("d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_DATE, date);
        values.put(HabitEntry.COLUMN_HABIT_DURATION, 120);
        values.put(HabitEntry.COLUMN_HABIT_ACTIVITY, "Jogging");


        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }
}
