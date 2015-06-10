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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();
    private Drawer navigationDrawer = null;
    private Toolbar toolbar = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(navigationDrawer == null) {
            navigationDrawer = new DrawerBuilder().withActivity(this).withToolbar(toolbar)
                    .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.new_cric_board),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.load_prev_cric_board),
                        new PrimaryDrawerItem().withName(R.string.launch_team_manager),
                        new PrimaryDrawerItem().withName(R.string.tbd),
                        new PrimaryDrawerItem().withName(R.string.exit_app)
                    )
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                            if(drawerItem != null) {
                                Log.d(LOG_TAG, "ItemID is: " + drawerItem.getIdentifier() + "Position:" + position);

                                switch (position) {
                                    case 0:
                                        /* TODO: Fill up code to show new crickBoard */
                                        break;
                                    /* Case1: is a DividerDrawItem() which will never will clicked */
                                    case 2:
                                        /* TODO: Load the previous crickBoard */
                                        break;
                                    case 3:
                                        /* TODO: Load team manager stuff... */
                                        break;
                                    case 5:
                                        /* Exit the Application... */
                                        onExitButtonClicked(view);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            return false;
                        }
                    })
            .withSavedInstance(savedInstanceState)
            .withShowDrawerOnFirstLaunch(true)
            .build();
        }
        Log.d(LOG_TAG, "MainActivity Started...");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onExitButtonClicked(View v) {
        this.finish();
        System.exit(0);
    }
}
