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

@DatabaseTable(tableName = "OrmMatchTable")
public class Match {
    enum ETOSS_WON { TEAM1_WINTOSS, TEAM2_WINTOSS, TOSS_NOT_DONE };
    enum EDECSISON_TEAM { BAT, BOWL_FIELD, NO_DECISION_YET };

    /* Team1 vs Team2 as generic names */
    @DatabaseField (foreign = true, canBeNull = false, columnName = "team1UUID")
    private Team team1 = null;

    @DatabaseField (foreign = true, canBeNull = false, columnName = "team2UUID")
    private Team team2 = null;


    @DatabaseField (id = true, canBeNull = false, unique = true)
    private UUID myUUID = null;
    public UUID getMyUUID() { return myUUID; }

    @DatabaseField (useGetSet = true)
    private ETOSS_WON tossWon = ETOSS_WON.TOSS_NOT_DONE;
    public ETOSS_WON getTossWon() { return tossWon; }
    public void setTossWon(ETOSS_WON aTossWonEnum) { tossWon = aTossWonEnum; }

    @DatabaseField (useGetSet = true)
    private EDECSISON_TEAM teamDecision = EDECSISON_TEAM.NO_DECISION_YET;
    public EDECSISON_TEAM getTeamDecision() { return teamDecision; }
    public void setTeamDecision(EDECSISON_TEAM aPlayDecision) { teamDecision = aPlayDecision; }

    @DatabaseField
    private UUID team1UUID = null;
    @DatabaseField
    private UUID team2UUID = null;

    public Team getBattingTeam() {
        Team outTeam = null;
        if((tossWon == ETOSS_WON.TEAM1_WINTOSS &&
                teamDecision == EDECSISON_TEAM.BAT) ||
                (tossWon == ETOSS_WON.TEAM2_WINTOSS &&
                teamDecision == EDECSISON_TEAM.BOWL_FIELD)) {
            outTeam = team1;
        } else if((tossWon == ETOSS_WON.TEAM1_WINTOSS &&
                teamDecision == EDECSISON_TEAM.BOWL_FIELD) ||
                (tossWon == ETOSS_WON.TEAM2_WINTOSS &&
                teamDecision == EDECSISON_TEAM.BAT)) {
            outTeam = team2;
        }
        return outTeam;
    }

    public Team getBowlingTeam() {
        Team outTeam = null;
        if(team1 == getBattingTeam()) {
            outTeam = team2;
        } else {
            outTeam = team1;
        }
        return outTeam;
    }

    public Match(Team aTeam1, Team aTeam2) {
        team1 = aTeam1;
        team2 = aTeam2;
    }

    /* No argument constructor needed by OrmLite... */
    Match() {  }
}
