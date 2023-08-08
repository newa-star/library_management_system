package model;
import java.util.Date;
public class CurrentDate {
	private Date currentDate;
	
	public CurrentDate() {
		currentDate = new Date();
	}
	// used in ReturnController
	public String toString() {
		
		int year = currentDate.getYear()+1900;
		int month = currentDate.getMonth()+1;
		int day = currentDate.getDate()+1;
		String mon = String.format("%02d", month);
		String da = String.format("%02d", day);
		String date = year+"-"+mon+"-"+da;
		return date;
	}
	
}
