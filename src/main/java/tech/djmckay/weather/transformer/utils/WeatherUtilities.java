package tech.djmckay.weather.transformer.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;

import tech.djmckay.weather.model.Period;

public class WeatherUtilities {

	
	private static String THIS_MORNING = "This Morning";
    private static String THIS_AFTERNOON = "This Afternoon";
    private static String TONIGHT = "Tonight";
    private static String TODAY = "Today";

    static public  Predicate<? super Period> current = period -> {
        return "1".equals(period.getNumber());
    };

    static public Predicate<? super Period> todayAllPeriods = period -> {
        Optional.ofNullable(period.getName()).orElseThrow();

    	return period.getName().equalsIgnoreCase(THIS_MORNING) || 
    	period.getName().equalsIgnoreCase(THIS_AFTERNOON) ||
    	period.getName().equalsIgnoreCase(TONIGHT) ||
    	period.getName().equalsIgnoreCase(TODAY);

    };
    
    static public String convertToCelsius(Period today) {
		if ("C".equalsIgnoreCase(today.getTemperatureUnit())) {
			
			return String.valueOf(today.getTemperature());
		}
		Double f = Double.valueOf(today.getTemperature());
		Double result = (f - 32) * 5/9;
		return String.format("%.1f", result);
    }
    
    static public String dayOfWeek(Period item) {	
    	
    	Calendar cal = Calendar.getInstance();
        cal.setTime(item.getStartTime());
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY: 
			return "Sunday";
		case Calendar.MONDAY: 
			return "Monday";
		case Calendar.TUESDAY: 
			return "Tuesday";
		case Calendar.WEDNESDAY: 
			return "Wednesday";
		case Calendar.THURSDAY: 
			return "Thursday";
		case Calendar.FRIDAY: 
			return "Friday";
		case Calendar.SATURDAY: 
			return "Saturday";
		default:
			return "";
		}
		
        
//        String dayName = LocalDate.now().getDayOfWeek().name();
//        return dayName.substring(0, 1) + dayName.substring(1).toLowerCase();
	    
	}
}
