package se.naresh.com.crickboard;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.UUID;

public class EditActivity extends AppCompatActivity {
    private static final String LOG_TAG = EditActivity.class.getName();
    private static Fragment fragmentInstancePointer = null;
    private static MainActivity.EINTENT_TYPE intentLaunched = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle bundle = getIntent().getExtras();
        intentLaunched = (MainActivity.EINTENT_TYPE) bundle.get("TYPE");
        switch (intentLaunched) {
            case INTENT_SEASON:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                EditSeasonsCardFragment seasonsCardFragment = new EditSeasonsCardFragment();
                seasonsCardFragment.setSeasonInstance((UUID)bundle.get("VALUE"));
                fragmentInstancePointer = seasonsCardFragment;
                ft.replace(R.id.editActivityFragmentHolder, seasonsCardFragment);
                ft.commit();
                break;
            case INTENT_NONE:
            default:
                Log.e(LOG_TAG, "No intent passed! Exiting this activity now...");
                finish();
        }
    }

    public void selectImageFromGallery(View v) {
        //create the intent for ImageGallery
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        //Start new activity with the LOAD_IMAGE_RESULT to handle back the result when the image is picked from the Image Gallery
        startActivityForResult(i, 0);
        Log.d(LOG_TAG, "Trying to pick an image!");
    }

    public void showStartDatePickerDialog(View v) {
        switch (intentLaunched) {
            case INTENT_SEASON:
                EditSeasonsCardFragment seasonsCardFragment = (EditSeasonsCardFragment)fragmentInstancePointer;
                seasonsCardFragment.showStartDatePickerDialog(v);
                break;
            case INTENT_NONE:
            default:
                Log.e(LOG_TAG, "No intent set! Exiting this activity now...");
                finish();
        }
    }

    public void showEndDatePickerDialog(View v) {
        switch (intentLaunched) {
            case INTENT_SEASON:
                EditSeasonsCardFragment seasonsCardFragment = (EditSeasonsCardFragment)fragmentInstancePointer;
                seasonsCardFragment.showEndDatePickerDialog(v);
                break;
            case INTENT_NONE:
            default:
                Log.e(LOG_TAG, "No intent set! Exiting this activity now...");
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
