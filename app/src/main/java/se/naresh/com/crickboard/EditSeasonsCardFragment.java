package se.naresh.com.crickboard;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class EditSeasonsCardFragment extends Fragment {
    private static final String LOG_TAG = EditSeasonsCardFragment.class.getName();

    public EditSeasonsCardFragment() {
    }

    public void showStartDatePickerDialog(View v) {
        DialogFragment datePickerFragment = new Utility.DatePickerFragment();
        Bundle bundle = new Bundle();
        datePickerFragment.setTargetFragment(this, 0);
        datePickerFragment.show(this.getFragmentManager(), getString(R.string.text_set_start_date));
    }

    public void showEndDatePickerDialog(View v) {
        DialogFragment datePickerFragment = new Utility.DatePickerFragment();
        Bundle bundle = new Bundle();
        datePickerFragment.setTargetFragment(this, 1);
        datePickerFragment.show(this.getFragmentManager(), getString(R.string.text_set_start_date));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Make sure fragment codes match up
        Log.d(LOG_TAG, "Back to my Fragment Naresh...");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_season_edit, container, false);
    }
}
