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

@DatabaseTable(tableName = "OrmWicketTable")
public class Wicket {
    enum EWICKET_TYPE { CLEAN_BOLD, CAUGHT, RUN_OUT, STUMPED, RETIRED_HURT, NOT_SET };

    @DatabaseField(id = true, canBeNull = false, unique = true)
    private UUID myUUID = null;
    public UUID getMyUUID() { return myUUID; }

    @DatabaseField
    public EWICKET_TYPE wicketType = EWICKET_TYPE.NOT_SET;

    @DatabaseField (foreign = true, canBeNull = false, columnName = "ballUUID")
    private Ball wicketOnBall = null;

    /* Used for the caught player. Bold player is always the bowler from the ballUUID
    * Can also be the same as bold player from ball */
    @DatabaseField (foreign = true, canBeNull = false, columnName = "caughtUUID", useGetSet = true)
    private Player caughtPlayer = null;
    public Player getCaughtPlayer() { return caughtPlayer; }
    public void setCaughtPlayer(Player aCaughtPlayer) { caughtPlayer = aCaughtPlayer; }

    /* No argument constructor needed by OrmLite... */
    Wicket() { myUUID = Utility.generateUUID();  }
}
