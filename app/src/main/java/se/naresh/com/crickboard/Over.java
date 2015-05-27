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

@DatabaseTable(tableName = "OrmOverTable")
public class Over {

    @DatabaseField(id = true, canBeNull = false, unique = true)
    private UUID myUUID = null;
    public UUID getMyUUID() { return myUUID; }

    @ForeignCollectionField
    private ForeignCollection<Ball> ballsInOver;

    /* TODO: Use for reducing DB Access */
    private Integer noOfRunsInOver = 0;
    private Integer noOfWicketsInOver = 0;
    private Integer noOfExtrasInOver = 0;

    public Integer getNoOfExtrasInOver() {
        return 0;
    }

    /* TODO: NoOfRuns should be automatically calculated from ballsInOver */
    public Integer getNoOfRunsInOver() {
        return 0;
    }

    /* TODO: getNoOfWicketsInOver should be automatically calculated from ballsInOver */
    public Integer getNoOfWicketsInOver() {
        return 0;
    }

    /* No argument constructor needed by OrmLite... */
    Over() { myUUID = Utility.generateUUID(); }
}
