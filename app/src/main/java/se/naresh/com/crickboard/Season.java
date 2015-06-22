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

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.lang.reflect.Array;
import java.util.List;
import java.util.UUID;

@DatabaseTable(tableName = "OrmSeasonTable")
public class Season {
    @DatabaseField(id = true, canBeNull = false, unique = true)
    private UUID myUUID = null;
    public UUID getMyUUID() { return myUUID; }

    @DatabaseField(generatedId = true)
    private Integer generatedID;
    public Integer getGeneratedID() { return generatedID; }

    @DatabaseField(useGetSet = true)
    private String name;
    public void setName(String aName) { name = aName; }
    public String getName() { return name; }

    @DatabaseField(useGetSet = true)
    private String year;
    public void setYear(String aYear) { year = aYear; }
    public String getYear() { return year; }

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Match> seasonMatches;

    /* No argument constructor needed by OrmLite... */
    Season() {  }

    public class SeasonsListAdapter extends ArrayAdapter<Season> {

        public SeasonsListAdapter(Context context, int resource) {
            super(context, resource);
        }

        public SeasonsListAdapter(Context context, int resource, List<Season> seasons) {
            super(context, resource, seasons);
        }

        @Override

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            return v;
        }
    }
}
