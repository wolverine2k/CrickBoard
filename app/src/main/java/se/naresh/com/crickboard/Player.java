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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@DatabaseTable (tableName = "OrmPlayerTable")
public class Player {
    private static final String LOG_TAG = Player.class.getName();
    private Dao<Player, String> playerTableDao = null;

    @DatabaseField (useGetSet = true)
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String aName) { name = aName; }

    @DatabaseField (useGetSet = true)
    private Integer age = 20;
    public Integer getAge() { return age; }
    public void setAge(Integer aAge) { age = aAge; }

    @DatabaseField (useGetSet = true)
    private Integer runsScored = 0;
    public void setRunsScored(Integer aRunsScored) {
        runsScored = aRunsScored;
    }
    public Integer getRunsScored() {
        return runsScored;
    }

    @DatabaseField (useGetSet = true)
    private Integer matchesPlayed = 0;
    public void setMatchesPlayed(Integer aMatchesPlayed) {
        matchesPlayed = aMatchesPlayed;
    }
    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    @DatabaseField (useGetSet = true)
    private Integer wicketsTaken = 0;
    public void setWicketsTaken(Integer aWicketsTaken) {
        wicketsTaken = aWicketsTaken;
    }
    public Integer getWicketsTaken() {
        return wicketsTaken;
    }

    @DatabaseField (id = true, canBeNull = false, unique = true)
    private UUID myUUID = null;
    public UUID getMyUUID() { return myUUID; }

    /* Points to different teams if the same player is included in different teams... */
    @ForeignCollectionField(eager = false)
    private ForeignCollection<Team> teamsBelongingTo;

    private Boolean foundInDB = false;

    /* No argument constructor needed by OrmLite... */
    Player() { myUUID = Utility.generateUUID(); }

    private void setPlayerTableDao() {
        if(null == playerTableDao) {
            try {
                playerTableDao = CrickDBHelperOrm.getInstance().getPlayerTableDao();
            } catch (Exception e) {
                Log.e(LOG_TAG, "Error getting handle... " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public List<Player> getAllPlayers() {
        setPlayerTableDao();
        List<Player> playerList = new ArrayList<Player>();
        try {
            playerList = playerTableDao.queryForAll();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error retrieving Players... " + e.getMessage());
            e.printStackTrace();
        }
        return playerList;
    }

}