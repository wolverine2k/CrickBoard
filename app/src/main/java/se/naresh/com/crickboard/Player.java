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

import java.util.UUID;

public class Player {
    private String name;
    private Integer age;
    private Integer runsScored = 0;
    private Integer matchesPlayed = 0;
    private Integer wicketsTaken = 0;

    private UUID myUUID = null;
    private Boolean foundInDB = false;

    public Player(String aName, int aAge) {
        myUUID = Utility.generateUUID();
        name = aName; age = aAge;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setWicketsTaken(Integer aWicketsTaken) {
        wicketsTaken = aWicketsTaken;
    }

    public Integer getWicketsTaken() {
        return wicketsTaken;
    }

    public void setMatchesPlayed(Integer aMatchesPlayed) {
        matchesPlayed = aMatchesPlayed;
    }

    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setRunsScored(Integer aRunsScored) {
        runsScored = aRunsScored;
    }

    public Integer getRunsScored() {
        return runsScored;
    }
}
