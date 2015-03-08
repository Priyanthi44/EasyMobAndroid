package com.valet.easy.easyvaletparking;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;


public class GetQuote extends ActionBarActivity implements View.OnFocusChangeListener, DialogInterface.OnClickListener,View.OnClickListener{
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.arrivalDate){
            arrivalset=true;
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");
            arrtime.setEnabled(true);
        }
        if(v.getId()==R.id.departureDate){
            arrivalset=false;
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");
           deptime.setEnabled(true);
        }
        if(v.getId()==R.id.arrivalTime){
            arrtimeset=true;
            DialogFragment newFragment = new TimePickerFragment();
            newFragment.show(getSupportFragmentManager(), "timePicker");
            departure.setEnabled(true);
        }
        if(v.getId()==R.id.departureTime){
            arrtimeset=false;
            DialogFragment newFragment = new TimePickerFragment();
            newFragment.show(getSupportFragmentManager(), "timePicker");
        }
    }

    static Button arrival, departure,arrtime,deptime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_quote);
        arrival = (Button) findViewById(R.id.arrivalDate);
        arrival.setOnClickListener(this);
        arrival.setOnFocusChangeListener(this);


        departure = (Button) findViewById(R.id.departureDate);
        departure.setOnClickListener(this);
        departure.setOnFocusChangeListener(this);
        departure.setEnabled(false);

        arrtime = (Button) findViewById(R.id.arrivalTime);
        arrtime.setOnClickListener(this);
        arrtime.setOnFocusChangeListener(this);
        arrtime.setEnabled(false);

        deptime = (Button) findViewById(R.id.departureTime);
        deptime.setOnClickListener(this);
        deptime.setOnFocusChangeListener(this);
        deptime.setEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_quote, menu);
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
    static String caption;

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if(v.getId()==R.id.arrivalDate){
            if(!hasFocus){
                arrival.setText(caption);
            }

        }
        if(v.getId()==R.id.departureDate){
            if(!hasFocus){
                departure.setText(caption);
            }

        }
        }
public static void setCaption(String cap){
    if(arrivalset)
    arrival.setText(cap);
    else
        departure.setText(cap);
}
    public static void setTimeCaption(String cap){
        if(arrivalset)
            arrtime.setText(cap);
        else
            deptime.setText(cap);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {


    }
     static boolean arrivalset, arrtimeset;
   static int year,month,day;
    public  static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            if (arrivalset){
        /* Use the current date as the default date in the picker */

            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
        }else{
                c.set(Calendar.YEAR,year);
                c.set(Calendar.MONTH, month);
              c.set(Calendar.DAY_OF_MONTH, day);
            }
            // Create a new instance of DatePickerDialog and return it

            DatePickerDialog dp =new DatePickerDialog(getActivity(), this, year, month, day);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                DatePicker d = dp.getDatePicker();
                d.setMinDate(c.getTimeInMillis());
            }
            return dp;
        }

        public void onDateSet(DatePicker view, int yr, int mth, int dy) {
            if(arrivalset) {
                year = yr;
                month = mth;
                day = dy; 
            }
            mth =(mth+1);
            if ((mth<10)&&(dy<10))
                setCaption("0"+dy +"/0" +mth +"/" +yr);
            else if(mth<10) {
                setCaption(dy +"/0" + mth+"/" +yr);

            }else{
                setCaption(dy +"/" + mth+"/" +yr);
            }
            // Do something with the date chosen by the user




        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            if ((minute<10)&&(hourOfDay<10))
                setTimeCaption("0"+hourOfDay+":"+"0"+minute);
            else if(minute<10) {
                setTimeCaption(hourOfDay+":"+"0"+minute);
            }else{
                setTimeCaption(hourOfDay+":"+minute);
            }

        }
    }
}
