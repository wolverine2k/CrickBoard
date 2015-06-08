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

import android.app.ActionBar;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class CrickDBHelperOrm extends OrmLiteSqliteOpenHelper {
    private static final String LOG_TAG = CrickDBHelper.class.getName();
    /* No downgrade possible on this SW for the moment */
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "CrickBoard.sqlite";

    private Dao<Player, String> playerTableDao = null;
    private Dao<Ball, String> ballTableDao = null;
    private Dao<Wicket, String> wicketTableDao = null;
    private Dao<Team, String> teamTableDao = null;
    private Dao<Over, String> overTableDao = null;
    private Dao<Match, String> matchTableDao = null;
    private Dao<MatchPlayers, String> matchPlayersTableDao = null;

    public CrickDBHelperOrm(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Player.class);
            TableUtils.createTable(connectionSource, Ball.class);
            TableUtils.createTable(connectionSource, Wicket.class);
            TableUtils.createTable(connectionSource, Over.class);
            TableUtils.createTable(connectionSource, Team.class);
            TableUtils.createTable(connectionSource, Match.class);
            TableUtils.createTable(connectionSource, MatchPlayers.class);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Error while creating database tables " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Dao<MatchPlayers, String> getMatchPlayersTableDao() {
        if(null == matchPlayersTableDao) {
            try {
                matchPlayersTableDao = getDao(MatchPlayers.class);
            } catch (SQLException e) {
                Log.e(LOG_TAG, "Error while getting MatchPlayersDao" + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return matchPlayersTableDao;
    }

    public Dao<Match, String> getMatchTableDao() {
        if(null == matchTableDao) {
            try {
                matchTableDao = getDao(Match.class);
            } catch (SQLException e) {
                Log.e(LOG_TAG, "Error while getting MatchDao" + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return matchTableDao;
    }

    public Dao<Over, String> getOverTableDao() {
        if(null == overTableDao) {
            try {
                overTableDao = getDao(Over.class);
            } catch (SQLException e) {
                Log.e(LOG_TAG, "Error while getting OverDao" + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return overTableDao;
    }

    public Dao<Team, String> getTeamTableDao() {
        if(null == teamTableDao) {
            try {
                teamTableDao = getDao(Team.class);
            } catch (SQLException e) {
                Log.e(LOG_TAG, "Error while getting TeamDao" + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return teamTableDao;
    }

    public Dao<Wicket, String> getWicketTableDao() {
        if(null == wicketTableDao) {
            try {
                wicketTableDao = getDao(Wicket.class);
            } catch (SQLException e) {
                Log.e(LOG_TAG, "Error while getting WicketDao" + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return wicketTableDao;
    }

    public Dao<Ball, String> getBallTableDao() {
        if(null == ballTableDao) {
            try {
                ballTableDao = getDao(Ball.class);
            } catch (SQLException e) {
                Log.e(LOG_TAG, "Error while getting BallDao" + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return ballTableDao;
    }

    public Dao<Player, String> getPlayerTableDao() {
        if(null == playerTableDao) {
            try {
                playerTableDao = getDao(Player.class);
            } catch (SQLException e) {
                Log.e(LOG_TAG, "Error while getting PlayerDao" + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return playerTableDao;
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        /* TODO: On a DB Upgrade, check the version and provide for utility
        * copy functions that creates new database, copies data from old and
        * removes the old database... NOT USED FOR THE TIME BEING */
    }
}
