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
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/* TODO: Implement Parcelable interface so objects can be passed directly between
*  activities and dbqueries can be reduced. */
@DatabaseTable(tableName = "OrmSeasonTable")
public class Season {
    private static final String LOG_TAG = Season.class.getName();
    private Dao<Season, UUID> seasonTableDao = null;

    @DatabaseField(id = true, canBeNull = false, unique = true)
    private UUID myUUID = null;
    public UUID getMyUUID() { return myUUID; }

    @DatabaseField(useGetSet = true)
    private String name;
    public void setName(String aName) { name = aName; }
    public String getName() { return name; }

    @DatabaseField
    private String year;
    public String getYear() { return year; }

    @DatabaseField(dataType = DataType.DATE_STRING, useGetSet = true)
    private Date startDate;
    public void setStartDate(Date aDate) {
        startDate = aDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        year = Integer.toString(cal.get(Calendar.YEAR));
    }
    public Date getStartDate() { return startDate; }

    @DatabaseField(dataType = DataType.DATE_STRING, useGetSet = true)
    private Date endDate;
    public void setEndDate(Date aDate) { endDate = aDate; }
    public Date getEndDate() { return endDate; }

    @ForeignCollectionField(eager = false)
    private ForeignCollection<Match> seasonMatches;

    @DatabaseField(dataType = DataType.BYTE_ARRAY, useGetSet = true)
    private byte[] pngImage;
    public void setPngImage(byte[] aPngImage) { pngImage = aPngImage; }
    public byte[] getPngImage() { return pngImage; }

    /* No argument constructor needed by OrmLite... */
    Season() { myUUID = Utility.generateUUID(); }

    public Integer getNumberOfMatches() {
        return seasonMatches.size();
    }

    private void setSeasonTableDao() {
        if(null == seasonTableDao) {
            try {
                seasonTableDao = CrickDBHelperOrm.getInstance().getSeasonTableDao();
            } catch (Exception e) {
                Log.e(LOG_TAG, "Error getting handle... " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public List<Season> getAllSeasons() {
        setSeasonTableDao();
        List<Season> seasonList = new ArrayList<Season>();
        try {
            seasonList = seasonTableDao.queryForAll();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error retrieving Seasons... " + e.getMessage());
            e.printStackTrace();
        }
        return seasonList;
    }

    public Season getSeason(UUID seasonUUID) {
        setSeasonTableDao();
        Season season = null;
        try {
            season = seasonTableDao.queryForId(seasonUUID);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Error retrieving season from UUID " + seasonUUID + e.getMessage());
            e.printStackTrace();
        }
        return season;
    }

    public void insertDataIntoTable(Season season) {
        setSeasonTableDao();
        try {
            seasonTableDao.createOrUpdate(season);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Unable to insert data into Season table... " + e.getMessage());
            e.printStackTrace();
        }
    }
}
