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

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class DataBaseContracts {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String INT_TYPE = " INTEGER";
    private static final String CREATE_TABLE = "CREATE TABLE ";
    private static final String INT_PRIMARY_KEY = " INTEGER PRIMARY KEY,";
    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";
    private static final String FOREIGN_KEY = " FOREIGN KEY(";
    private static final String REFERENCES = " REFERENCES ";
    private static final String ON_DELETE_CASCADE = " ON DELETE CASCADE ";
    private static final String ON_UPDATE_CASCADE = " ON UPDATE CASCADE ";

    private static CrickDBHelper dbPointer = null;
    private static SQLiteDatabase db = null;

    public DataBaseContracts(CrickDBHelper dbHelper) {
        dbPointer = dbHelper;
        db = dbHelper.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
    }

    public static void createSQLTables(SQLiteDatabase db) {
        db.execSQL(PlayerTable.SQL_CREATE_TABLE);
        db.execSQL(MatchPlayersTable.SQL_CREATE_TABLE);
        db.execSQL(MatchTable.SQL_CREATE_TABLE);
        db.execSQL(MatchPlayersListingTable.SQL_CREATE_TABLE);
        db.execSQL(TeamTable.SQL_CREATE_TABLE);
    }

    public static abstract class MatchTable implements BaseColumns {
        public static final String TABLE_NAME = "Match";
        public static final String COLUMN_UUID = "Uuid";
        public static final String COLUMN_FR_TEAM1 = "FR_MatchPlayersTable_ID_Team1";
        public static final String COLUMN_FR_TEAM2 = "FR_MatchPlayersTable_ID_Team2";
        /* Integer type 0 = team1 won, 1 = team2 won */
        public static final String COLUMN_TOSS_WON = "TossWon";
        /* Integer type 0 = batting, 1 = balling, correlated with TossWon */
        public static final String COLUMN_DECISION = "TeamDecision";

        private static final String SQL_CREATE_TABLE =
                CREATE_TABLE + MatchTable.TABLE_NAME + " (" + MatchTable._ID +
                INT_PRIMARY_KEY + COLUMN_UUID + TEXT_TYPE + COMMA_SEP +
                COLUMN_FR_TEAM1 + INT_TYPE + COMMA_SEP +
                COLUMN_FR_TEAM2 + INT_TYPE + COMMA_SEP +
                COLUMN_TOSS_WON + INT_TYPE + COMMA_SEP +
                COLUMN_DECISION + INT_TYPE + " )";

        private static final String SQL_DROP_TABLE =
                DROP_TABLE_IF_EXISTS + MatchTable.TABLE_NAME;

        /* Returns -1 if the insertion fails. DB should be in writable
        * i.e. CrickDBHelper.getWritableDatabase()*/
        public static long insertMatch(ContentValues values) {
            /* Query if COLUMN_FR_TEAM1 exists, if not, return -1 */
            Integer check = values.getAsInteger(COLUMN_FR_TEAM1);
            /* TODO: Check for the proper values before insertion
            * range checks, data validity, etc. here */
            return db.insert(TABLE_NAME, null, values);
        }
    }

    public static abstract class MatchPlayersTable implements BaseColumns {
        public static final String TABLE_NAME = "MatchPlayers";
        public static final String COLUMN_UUID = "Uuid";
        public static final String COLUMN_NO_OF_PLAYERS = "NoOfPlayers";

        private static final String SQL_CREATE_TABLE =
                CREATE_TABLE + MatchPlayersTable.TABLE_NAME + " (" + MatchPlayersTable._ID +
                INT_PRIMARY_KEY + COLUMN_UUID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NO_OF_PLAYERS + INT_TYPE + " )";

        private static final String SQL_DROP_TABLE =
                DROP_TABLE_IF_EXISTS + MatchPlayersTable.TABLE_NAME;

        /* Returns -1 if the insertion fails. DB should be in writable
        * i.e. CrickDBHelper.getWritableDatabase()*/
        public static long insertMatchPlayers(ContentValues values) {
            /* TODO: Check for the proper values before insertion
            * range checks, data validity, etc. here */
            return db.insert(TABLE_NAME, null, values);
        }
    }

    public static abstract class TeamTable implements BaseColumns {
        public static final String TABLE_NAME = "Team";
        public static final String COLUMN_UUID = "Uuid";
        public static final String COLUMN_MATCHES_PLAYED = "MatchesPlayed";
        public static final String COLUMN_MATCHES_WON = "MatchesWon";
        public static final String COLUMN_MATCHES_LOST = "MatchesLost";
        public static final String COLUMN_MATCHES_FORFEITED = "MatchesForfeited";
        public static final String COLUMN_MATCHES_DRAW = "MatchesDraw";
        public static final String COLUMN_FR_MATCHPLAYERLISTING = "FR_MatchPlayersListingTable_ID";

        private static final String SQL_CREATE_TABLE =
                CREATE_TABLE + TeamTable.TABLE_NAME + " (" + TeamTable._ID +
                INT_PRIMARY_KEY + COLUMN_UUID + TEXT_TYPE + COMMA_SEP +
                COLUMN_FR_MATCHPLAYERLISTING + INT_TYPE + COMMA_SEP +
                COLUMN_MATCHES_DRAW + INT_TYPE + COMMA_SEP +
                COLUMN_MATCHES_FORFEITED + INT_TYPE + COMMA_SEP +
                COLUMN_MATCHES_LOST + INT_TYPE + COMMA_SEP +
                COLUMN_MATCHES_PLAYED + INT_TYPE + COMMA_SEP +
                COLUMN_MATCHES_WON + INT_TYPE + " )";

        private static final String SQL_DROP_TABLE =
                DROP_TABLE_IF_EXISTS + TeamTable.TABLE_NAME;

        public static boolean checkIfTeamExists(Integer key) {
            boolean result = false;
            //Cursor curson = db.query(TABLE_NAME, new String[] { COLUMN_FR_MATCHPLAYERLISTING });
            return result;
        }

        /* Returns -1 if the insertion fails. DB should be in writable
        * i.e. CrickDBHelper.getWritableDatabase()*/
        public static long insertTeam(ContentValues values) {
            /* TODO: Check for the proper values before insertion
            * range checks, data validity, etc. here */
            return db.insert(TABLE_NAME, null, values);
        }
    }

    public static abstract class MatchPlayersListingTable implements BaseColumns {
        public static final String TABLE_NAME = "MatchPlayersListing";
        public static final String COLUMN_FR_MATCHPLAYERS = "FR_MatchPlayersTable_ID";
        public static final String COLUMN_FR_PLAYERS = "FR_PlayerTable_ID";

        private static final String SQL_CREATE_TABLE =
            CREATE_TABLE + MatchPlayersListingTable.TABLE_NAME + " (" + MatchPlayersListingTable._ID +
            INT_PRIMARY_KEY + COLUMN_FR_MATCHPLAYERS + INT_TYPE + COMMA_SEP +
            FOREIGN_KEY + COLUMN_FR_MATCHPLAYERS + ")" + REFERENCES +
                    MatchPlayersTable.TABLE_NAME + "(" + MatchPlayersTable._ID + ")" + COMMA_SEP +
            COLUMN_FR_PLAYERS + INT_TYPE + COMMA_SEP +
            FOREIGN_KEY + COLUMN_FR_PLAYERS + ")" + REFERENCES +
                    PlayerTable.TABLE_NAME + "(" + PlayerTable._ID + ")" + " )";

        private static final String SQL_DROP_TABLE =
                DROP_TABLE_IF_EXISTS + MatchPlayersListingTable.TABLE_NAME;

        /* Returns -1 if the insertion fails. DB should be in writable
        * i.e. CrickDBHelper.getWritableDatabase()*/
        public static long insertMatchPlayersListing(SQLiteDatabase db, ContentValues values) {
            /* TODO: Check for the proper values before insertion
            * range checks, data validity, etc. here */
            return db.insert(TABLE_NAME, null, values);
        }
    }

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

        /* Returns -1 if the insertion fails. DB should be in writable
        * i.e. CrickDBHelper.getWritableDatabase()*/
        public static long insertPlayer(ContentValues values) {
            /* TODO: Check for the proper values before insertion
            * range checks, data validity, etc. here */
            return db.insert(TABLE_NAME, null, values);
        }
    }
}
