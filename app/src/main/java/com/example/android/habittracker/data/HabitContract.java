package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by matteo on 25/06/2017.
 */

public final class HabitContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class HabitEntry implements BaseColumns {

        /** Name of database table for habits */
        public final static String TABLE_NAME = "habits";

        /**
         * Unique ID number (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the pet.
         *
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_DATE ="date";

        /**
         * Breed of the pet.
         *
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_ACTIVITY = "activity";
    }

}
