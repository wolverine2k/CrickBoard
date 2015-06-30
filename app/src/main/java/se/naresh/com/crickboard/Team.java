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

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.UUID;

@DatabaseTable (tableName = "OrmTeamTable")
public class Team {
    private static final String LOG_TAG = Team.class.getName();
    private Dao<Team, String> teamTableDao = null;

    @DatabaseField (useGetSet = true)
    private String name = null;
    public String getName() { return name; }
    public void setName(String aName) { name = aName; }

    @DatabaseField (useGetSet = true)
    private Integer matchesPlayed = 0;
    public Integer getMatchesPlayed() { return matchesPlayed; }
    public void setMatchesPlayed(Integer aMatchesPlayed) { matchesPlayed = aMatchesPlayed; }

    @DatabaseField (useGetSet = true)
    private Integer matchesWon = 0;
    public Integer getMatchesWon() { return matchesWon; }
    public void setMatchesWon(Integer aMatchesWon) { matchesWon = aMatchesWon; }

    @DatabaseField (useGetSet = true)
    private Integer matchesLost = 0;
    public Integer getMatchesLost() { return matchesLost; }
    public void setMatchesLost(Integer aMatchesLost) { matchesLost = aMatchesLost; }

    @DatabaseField (useGetSet = true)
    private Integer matchesForfeited = 0;
    public Integer getMatchesForfeited() { return matchesForfeited; }
    public void setMatchesForfeited(Integer aMatchesForfeited) { matchesForfeited = aMatchesForfeited; }

    @DatabaseField (useGetSet = true)
    private Integer matchesDraw = 0;
    public Integer getMatchesDraw() { return matchesDraw; }
    public void setMatchesDraw(Integer aMatchesDraw) { matchesDraw = aMatchesDraw; }

    @DatabaseField (id = true, canBeNull = false, unique = true)
    private UUID myUUID = null;
    public UUID getMyUUID() { return myUUID; }

    @DatabaseField (foreign = true, columnName = "playerUUID")
    private Player player;
    public void setPlayer(Player aPlayer) { player = aPlayer; }

    /* Points to different matches as the same team is plays different matches... */
    @ForeignCollectionField(eager = false)
    private ForeignCollection<Match> matches;

    /* No argument constructor needed by OrmLite... */
    Team() { myUUID = Utility.generateUUID(); }

    private void setTeamTableDao() {
        if(null == teamTableDao) {
            try {
                teamTableDao = CrickDBHelperOrm.getInstance().getTeamTableDao();
            } catch (Exception e) {
                Log.e(LOG_TAG, "Error getting handle... " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void insertDataIntoTable(Team team) {
        setTeamTableDao();
        try {
            teamTableDao.createOrUpdate(team);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Unable to insert data into Team table... " + e.getMessage());
            e.printStackTrace();
        }
    }

    static public void insertDummyDataInTable(Integer noOfItems) {
        while(noOfItems > 0) {
            Team team = new Team();
            team.setName("TestTeam" + Integer.toString(noOfItems));
            team.insertDataIntoTable(team);
            --noOfItems;
        }
    }
}
