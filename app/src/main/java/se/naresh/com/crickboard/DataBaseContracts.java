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

    public static abstract class MatchTable implements BaseColumns {
        public static final String TABLE_NAME = "Match";
        public static final String COLUMN_UUID = "Uuid";
        public static final String COLUMN_FR_TEAM1 = "FR_MatchPlayersTable_ID_Team1";
        public static final String COLUMN_FR_TEAM2 = "FR_MatchPlayersTable_ID_Team2";
        /* Integer type 0 = team1 won, 1 = team2 won */
        public static final String COLUMN_TOSS_WON = "TossWon";
        /* Integer type 0 = batting, 1 = balling, correlated with TossWon */
        public static final String COLUMN_DECISION = "TeamDecision";

        public static final String SQL_CREATE_TABLE =
                CREATE_TABLE + MatchTable.TABLE_NAME + " (" + MatchTable._ID +
                INT_PRIMARY_KEY + COLUMN_UUID + TEXT_TYPE + COMMA_SEP +
                COLUMN_FR_TEAM1 + INT_TYPE + COMMA_SEP +
                COLUMN_FR_TEAM2 + INT_TYPE + COMMA_SEP +
                COLUMN_TOSS_WON + INT_TYPE + COMMA_SEP +
                COLUMN_DECISION + INT_TYPE + " )";

        public static final String SQL_DROP_TABLE =
                DROP_TABLE_IF_EXISTS + MatchTable.TABLE_NAME;
    }

    public static abstract class MatchPlayersTable implements BaseColumns {
        public static final String TABLE_NAME = "MatchPlayers";
        public static final String COLUMN_UUID = "Uuid";
        public static final String COLUMN_NOOFPLAYERS = "NoOfPlayers";

        public static final String SQL_CREATE_TABLE =
                CREATE_TABLE + MatchPlayersTable.TABLE_NAME + " (" + MatchPlayersTable._ID +
                INT_PRIMARY_KEY + COLUMN_UUID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NOOFPLAYERS + INT_TYPE + " )";

        public static final String SQL_DROP_TABLE =
                DROP_TABLE_IF_EXISTS + MatchPlayersTable.TABLE_NAME;
    }

    public static abstract class TeamTable implements BaseColumns {
        public static final String TABLE_NAME = "Team";
        public static final String COLUMN_UUID = "Uuid";
        public static final String COLUMN_MATCHESPLAYED = "MatchesPlayed";
        public static final String COLUMN_MATCHESWON = "MatchesWon";
        public static final String COLUMN_MATCHESLOST = "MatchesLost";
        public static final String COLUMN_MATCHESFORFEITED = "MatchesForfeited";
        public static final String COLUMN_MATCHESDRAW = "MatchesDraw";
        public static final String COLUMN_FR_MATCHPLAYERLISTING = "FR_MatchPlayersListingTable_ID";

        public static final String SQL_CREATE_TABLE =
                CREATE_TABLE + TeamTable.TABLE_NAME + " (" + TeamTable._ID +
                INT_PRIMARY_KEY + COLUMN_UUID + TEXT_TYPE + COMMA_SEP +
                COLUMN_FR_MATCHPLAYERLISTING + INT_TYPE + COMMA_SEP +
                COLUMN_MATCHESDRAW + INT_TYPE + COMMA_SEP +
                COLUMN_MATCHESFORFEITED + INT_TYPE + COMMA_SEP +
                COLUMN_MATCHESLOST + INT_TYPE + COMMA_SEP +
                COLUMN_MATCHESPLAYED + INT_TYPE + COMMA_SEP +
                COLUMN_MATCHESWON + INT_TYPE + " )";

        public static final String SQL_DROP_TABLE =
                DROP_TABLE_IF_EXISTS + TeamTable.TABLE_NAME;
    }

    public static abstract class MatchPlayersListingTable implements BaseColumns {
        public static final String TABLE_NAME = "MatchPlayersListing";
        public static final String COLUMN_FR_MATCHPLAYERS = "FR_MatchPlayersTable_ID";
        public static final String COLUMN_FR_PLAYERS = "FR_PlayerTable_ID";

        public static final String SQL_CREATE_TABLE =
            CREATE_TABLE + MatchPlayersListingTable.TABLE_NAME + " (" + MatchPlayersListingTable._ID +
            INT_PRIMARY_KEY + COLUMN_FR_MATCHPLAYERS + INT_TYPE + COMMA_SEP +
            COLUMN_FR_PLAYERS + INT_TYPE + " )";

        public static final String SQL_DROP_TABLE =
                DROP_TABLE_IF_EXISTS + MatchPlayersListingTable.TABLE_NAME;
    }

    public static abstract class PlayerTable implements BaseColumns {
        public static final String TABLE_NAME = "Player";
        public static final String COLUMN_UUID = "Uuid";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_AGE = "Age";
        public static final String COLUMN_RUNS_SCORED = "RunsScored";
        public static final String COLUMN_MATCHES_PLAYED = "MatchesPlayed";
        public static final String COLUMN_WICKETS_TAKEN = "WicketsTaken";

        public static final String SQL_CREATE_TABLE =
                CREATE_TABLE + PlayerTable.TABLE_NAME + " (" + PlayerTable._ID +
                INT_PRIMARY_KEY + COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_AGE + INT_TYPE + COMMA_SEP +
                COLUMN_MATCHES_PLAYED + INT_TYPE + COMMA_SEP +
                COLUMN_RUNS_SCORED + INT_TYPE + COMMA_SEP +
                COLUMN_WICKETS_TAKEN + INT_TYPE + COMMA_SEP +
                COLUMN_UUID + TEXT_TYPE + " )";

        public static final String SQL_DROP_TABLE =
                DROP_TABLE_IF_EXISTS + PlayerTable.TABLE_NAME;
    }
}
