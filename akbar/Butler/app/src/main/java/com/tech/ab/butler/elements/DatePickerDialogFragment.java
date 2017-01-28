package com.tech.ab.butler.elements;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.WindowManager;

import java.util.Calendar;

/**
 * Created by Ankita on 26-Jan-17.
 */

public class DatePickerDialogFragment extends DialogFragment {

    private Context context;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    public DatePickerDialogFragment() {
    }

    public DatePickerDialogFragment(DatePickerDialog.OnDateSetListener callback, Context context) {
        mDateSetListener = callback;
        this.context = context;
    }
    DatePickerDialog datePickerDialog;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(getActivity(), this.mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        return datePickerDialog;
    }
}