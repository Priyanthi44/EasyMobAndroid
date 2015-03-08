package com.code.qr.events.process;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SubString {
	
	public static String[] getDetails(String str){
		String[] event =new  String[2];
		//event =str.split("\\="); //[data],[event:date]
		event = str.split("\\:");//[event][date]
		return event;		
		
	} 
	static SimpleDateFormat format = new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH);
	public static Date getDate(String datestring) throws ParseException{
		String string = datestring;
		return  format.parse(string); 
	}
	public static String getStringDate(String date){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format.format(c.getTime());
    }
	public static String setReminder(String date, int remindby){
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, remindby);  // number of days to add
		return format.format(c.getTime());
		
		
	}
	
	public static int dateDiff(String dateOld) throws ParseException{
		double dateo=format.parse(dateOld).getTime();
        double daten =new Date().getTime();
        int diff =(int)(daten-dateo)/(1000*60*60*24);
		return diff;
		
	}

}
