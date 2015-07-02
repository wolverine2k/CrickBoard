package se.naresh.com.crickboard;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class EditActivity extends ActionBarActivity {
    private static final String LOG_TAG = EditActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle bundle = getIntent().getExtras();
        MainActivity.EINTENT_TYPE type = (MainActivity.EINTENT_TYPE) bundle.get("TYPE");
        switch (type) {
            case INTENT_SEASON:
                String seasonUUID = (String)bundle.get("VALUE");
                Log.d(LOG_TAG, "UUID For Season received: " + seasonUUID);
                break;
            default:
                Log.e(LOG_TAG, "No intent passed! Exiting this activity now...");
                finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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
}
