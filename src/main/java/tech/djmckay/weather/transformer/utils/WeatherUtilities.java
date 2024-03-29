package tech.djmckay.weather.transformer.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Optional;
import java.util.function.Predicate;

import tech.djmckay.weather.model.Period;

public class WeatherUtilities {

	
	private static String THIS_MORNING = "This Morning";
    private static String THIS_AFTERNOON = "This Afternoon";
    private static String TONIGHT = "Tonight";
    private static String TODAY = "Today";
    

    static public  Predicate<? super Period> current = period -> {
        return Integer.valueOf(1).equals(period.getNumber());
    };

    static public Predicate<? super Period> todayAllPeriods = period -> {
        Optional.ofNullable(period.getName()).orElseThrow();

    	return period.getName().equalsIgnoreCase(THIS_MORNING) || 
    	period.getName().equalsIgnoreCase(THIS_AFTERNOON) ||
    	period.getName().equalsIgnoreCase(TONIGHT) ||
    	period.getName().equalsIgnoreCase(TODAY);

    };
    
    static public double convertToCelsius(Period today) {
		if ("C".equalsIgnoreCase(today.getTemperatureUnit())) {
			
			return today.getTemperature();
		}
		double result = (today.getTemperature() - 32) * 5/9;
		BigDecimal c = new BigDecimal(result);

		c = c.setScale(1, RoundingMode.HALF_UP);
		return c.doubleValue();
    }
    
}
