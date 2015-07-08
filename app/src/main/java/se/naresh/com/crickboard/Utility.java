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

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

public class Utility {
    public static UUID generateUUID() {
        return UUID.randomUUID();
    }

    public static byte[] readFileIntoByteArray(String aFilePath) throws IOException {
        File file = new File(aFilePath);
        FileInputStream fin = new FileInputStream(file);
        byte result[] = new byte[(int)file.length()];
        fin.read(result);
        fin.close();
        return result;
    }

    public static class MyDate {
        public int year = 0;
        public int month = 0;
        public int day = 0;
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int aYear, int aMonth, int aDay) {
            // Do something with the date chosen by the user
            MyDate selectedDate = new MyDate();
            selectedDate.year = aYear;
            selectedDate.month = aMonth;
            selectedDate.day = aDay;
            view.setTag(selectedDate);
            getTargetFragment().onActivityResult(0, 0, new Intent());
        }
    }
}