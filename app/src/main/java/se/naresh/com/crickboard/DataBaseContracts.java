/*
 *   CrickBoard Application
 *       Copyright (c) 2015 Naresh Mehta, All rights reserved.
 *       https://www.naresh.se/
 *
 *       This library is free software; you can redistribute it and/or
 *       modify it under the terms of the GNU Lesser General Public
 *       License as published by the Free Software Foundation; either
 *       version 3.0 of the License, or (at your option) any later version.
 *
 *       This library is distributed in the hope that it will be useful,
 *       but WITHOUT ANY WARRANTY; without even the implied warranty of
 *       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *       Lesser General Public License for more details.
 *
 *       You should have received a copy of the GNU Lesser General Public
 *       License along with this library.
 */
package se.naresh.com.crickboard;

import android.provider.BaseColumns;

public final class DataBaseContracts {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String INT_TYPE = " INTEGER";
    private static final String CREATE_TABLE = "CREATE TABLE ";
    private static final String INT_PRIMARY_KEY = " INTEGER PRIMARY KEY,";
    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";
    /* Empty Constructor to avoid unwanted instantiations */
    public DataBaseContracts() {}

    public static abstract class PlayerTable implements BaseColumns {
        public static final String TABLE_NAME = "Player";
        public static final String COLUMN_UUID = "Uuid";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_AGE = "Age";
        public static final String COLUMN_RUNS_SCORED = "RunsScored";
        public static final String COLUMN_MATCHES_PLAYED = "MatchesPlayed";
        public static final String COLUMN_WICKETS_TAKEN = "WicketsTaken";

        private static final String SQL_CREATE_TABLE =
                CREATE_TABLE + PlayerTable.TABLE_NAME + " (" + PlayerTable._ID +
                INT_PRIMARY_KEY + COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_AGE + INT_TYPE + COMMA_SEP +
                COLUMN_MATCHES_PLAYED + INT_TYPE + COMMA_SEP +
                COLUMN_RUNS_SCORED + INT_TYPE + COMMA_SEP +
                COLUMN_WICKETS_TAKEN + INT_TYPE + COMMA_SEP +
                COLUMN_UUID + TEXT_TYPE + " )";

        private static final String SQL_DROP_TABLE =
                DROP_TABLE_IF_EXISTS + PlayerTable.TABLE_NAME;
    }
}
