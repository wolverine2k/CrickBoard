package se.naresh.com.crickboard;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * A placeholder fragment containing a simple view.
 */
public class EditSeasonsCardFragment extends Fragment {
    private static enum EREQUEST_CODE { REQUEST_START_DATE, REQUEST_END_DATE,
        REQUEST_SELECT_IMAGE, REQUEST_DEFAULT }
    private static final String LOG_TAG = EditSeasonsCardFragment.class.getName();
    private Season seasonInst = null;

    public EditSeasonsCardFragment() { seasonInst = new Season(); }

    /* If set to true, function will set otherwise will get */
    private String seasonNameSetting(String aSeasonName, Boolean aSet) {
        String result = null;
        EditText editText = (EditText) getActivity().findViewById(R.id.editSeasonText);
        if(aSet) {
            editText.setText(seasonInst.getName(), TextView.BufferType.EDITABLE);
        } else {
            result = editText.getText().toString();
        }
        return result;
    }

    private Date seasonDateSetting(int id, Date aDate, Boolean aSet) throws ParseException {
        Date result = null;
        TextView textView = (TextView) getActivity().findViewById(id);
        if(aSet) {
            textView.setText(aDate.toString());
        } else {
            result = SimpleDateFormat.getInstance().parse(textView.getText().toString());
        }
        return result;
    }

    private void setDataInUI() {
        seasonNameSetting(seasonInst.getName(), true);
        try {
            seasonDateSetting(R.id.seasonStartDate, seasonInst.getStartDate(), true);
            seasonDateSetting(R.id.seasonEndDate, seasonInst.getEndDate(), true);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Error while setting data in UI in EditSeasonsCardFragment, " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setSeasonInstance(UUID aUUID) {
        seasonInst = seasonInst.getSeason(aUUID);
        Log.d(LOG_TAG, "Selected Season is: " + seasonInst.toString());
    }

    @Override
    public void onStart() {
        super.onStart();
        setDataInUI();
    }

    public void showStartDatePickerDialog(View v) {
        DialogFragment datePickerFragment = new Utility.DatePickerFragment();
        Bundle bundle = new Bundle();
        datePickerFragment.setTargetFragment(this, EREQUEST_CODE.REQUEST_START_DATE.ordinal());
        datePickerFragment.show(this.getFragmentManager(), getString(R.string.text_set_start_date));
    }

    public void showEndDatePickerDialog(View v) {
        DialogFragment datePickerFragment = new Utility.DatePickerFragment();
        Bundle bundle = new Bundle();
        datePickerFragment.setTargetFragment(this, EREQUEST_CODE.REQUEST_END_DATE.ordinal());
        datePickerFragment.show(this.getFragmentManager(), getString(R.string.text_set_start_date));
    }

    public boolean saveChangesToSeasons() {
        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Make sure fragment codes match up
        if(EREQUEST_CODE.REQUEST_START_DATE.ordinal() == requestCode) {

        }else if(EREQUEST_CODE.REQUEST_END_DATE.ordinal() == requestCode) {

        }else if(EREQUEST_CODE.REQUEST_SELECT_IMAGE.ordinal() == requestCode) {

        } else {
            /* Print an error as the case should never occur... */
            Log.e(LOG_TAG, "OnActivityResult in EditSeasonsCardFragment called with requestCode " + requestCode);
        }
        Log.d(LOG_TAG, "Back to EditSeasonsCardFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_season_edit, container, false);
    }
}
