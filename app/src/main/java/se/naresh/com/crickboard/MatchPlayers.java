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

import java.util.List;
import java.util.UUID;

@DatabaseTable(tableName = "OrmMatchPlayersTable")
public class MatchPlayers {

    @ForeignCollectionField(eager = false)
    private ForeignCollection<Player> matchPlayers;

    @DatabaseField(foreign = true, canBeNull = false, columnName = "matchUUID")
    private Match match = null;

    @DatabaseField(foreign = true, canBeNull = false, columnName = "teamUUID")
    private Team team = null;

    @DatabaseField (id = true, canBeNull = false, unique = true)
    private UUID myUUID = null;
    public UUID getMyUUID() { return myUUID; }

    /* No argument constructor needed by OrmLite... */
    MatchPlayers() { myUUID = Utility.generateUUID(); }
}
