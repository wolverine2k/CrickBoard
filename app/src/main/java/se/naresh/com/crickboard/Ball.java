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

@DatabaseTable(tableName = "OrmBallTable")
public class Ball {
    @DatabaseField(id = true, canBeNull = false, unique = true)
    private UUID myUUID = null;
    public UUID getMyUUID() { return myUUID; }

    @DatabaseField
    public Boolean isWide = false;

    @DatabaseField
    public Boolean isNoBall = false;

    @DatabaseField
    public Integer runsOnThisBall = 0;

    @DatabaseField
    public Boolean isValidBall = true;

    @DatabaseField
    public Boolean wicketTaken = false;

    @DatabaseField
    public Boolean isDeadBall = false;

    @DatabaseField
    public Integer ballSeqNumber = 0;

    @DatabaseField(foreign = true, canBeNull = false, columnName = "ballerUUID")
    public Player bowledBy;

    /* Runs can be scored by a noBall/wide, etc. Hence the player can be NULL
    * TODO: Confirm with cricketers for a NULL possibility for player...
    * */
    @DatabaseField(foreign = true, canBeNull = true, columnName = "batsmanUUID")
    public Player battedBy;

    /* No argument constructor needed by OrmLite... */
    Ball() { myUUID = Utility.generateUUID(); }
}
