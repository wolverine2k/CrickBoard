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
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class CrickDBHelper extends SQLiteOpenHelper {
    /* No downgrade possible on this SW for the moment */
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "CrickBoard";

    private DataBaseContracts dbContracts = null;

    public CrickDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        dbContracts = new DataBaseContracts(this);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /* Create all the necessary DB tables... */
        DataBaseContracts.createSQLTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* TODO: On a DB Upgrade, check the version and provide for utility
        * copy functions that creates new database, copies data from old and
        * removes the old database... NOT USED FOR THE TIME BEING */
    }
}
