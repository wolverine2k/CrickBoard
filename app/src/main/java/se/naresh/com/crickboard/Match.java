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

public class Match {
    enum ETOSS_WON { TEAM1_WINTOSS, TEAM2_WINTOSS, TOSS_NOT_DONE };
    enum EDECSISON_TEAM { BAT, BOWL_FIELD, NO_DECISION_YET };

    /* Team1 vs Team2 as generic names */
    private Team team1 = null;
    private Team team2 = null;
    private ETOSS_WON toss_won = ETOSS_WON.TOSS_NOT_DONE;
    private EDECSISON_TEAM team_decision = EDECSISON_TEAM.NO_DECISION_YET;

    public Team getBattingTeam() {
        Team outTeam = null;
        if((toss_won == ETOSS_WON.TEAM1_WINTOSS &&
                team_decision == EDECSISON_TEAM.BAT) ||
                (toss_won == ETOSS_WON.TEAM2_WINTOSS &&
                team_decision == EDECSISON_TEAM.BOWL_FIELD)) {
            outTeam = team1;
        } else if((toss_won == ETOSS_WON.TEAM1_WINTOSS &&
                team_decision == EDECSISON_TEAM.BOWL_FIELD) ||
                (toss_won == ETOSS_WON.TEAM2_WINTOSS &&
                team_decision == EDECSISON_TEAM.BAT)) {
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

    public void setTossWon(ETOSS_WON aTossWonEnum) {
        toss_won = aTossWonEnum;
    }

    public void setTeamDecision(EDECSISON_TEAM aPlayDecision) {
        team_decision = aPlayDecision;
    }

    public ETOSS_WON getTossWon() {
        return toss_won;
    }

    public EDECSISON_TEAM getTeamDecision() {
        return team_decision;
    }
}
