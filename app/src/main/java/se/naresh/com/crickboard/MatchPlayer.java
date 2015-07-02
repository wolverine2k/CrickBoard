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

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

@DatabaseTable(tableName = "OrmMatchPlayerTable")
/*
* MatchPlayer has a one-2-one mapping with Player. Team contains a foreignCollectionField of
* MatchPlayers instead of Player so the following can be satisfied.
* 1. A team can have multiple players
* 2. A player can be a part of multiple teams i.e. we duplicate the players inside the
*    matchPlayer so we can still aggregate the data for the player from multiple
*    teams into one.
* */
public class MatchPlayer {

    @DatabaseField(foreign = true, canBeNull = false, columnName = "playerUUID")
    private Player player = null;
    public void setPlayer(Player aPlayer) { player = aPlayer; }

    @DatabaseField(foreign = true, canBeNull = false, columnName = "teamUUID", unique = true)
    private Team team = null;
    public void setTeam(Team aTeam) { team = aTeam; }

    @DatabaseField (id = true, canBeNull = false, unique = true)
    private UUID myUUID = null;
    public UUID getMyUUID() { return myUUID; }

    /* No argument constructor needed by OrmLite... */
    MatchPlayer() { myUUID = Utility.generateUUID(); }
}