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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

@DatabaseTable (tableName = "OrmPlayerTable")
public class Player {

    @DatabaseField (useGetSet = true)
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String aName) { name = aName; }

    @DatabaseField (useGetSet = true)
    private Integer age;
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

    private Boolean foundInDB = false;

    /* No argument constructor needed by OrmLite... */
    Player() { }


}