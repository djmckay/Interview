package tech.djmckay.weather.transformer.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.function.Predicate;

import tech.djmckay.weather.model.Period;

public class WeatherUtilities {

    static public  Predicate<? super Period> current = period -> {
        return "1".equals(period.getNumber());
    };

    static public Predicate<? super Period> todayAllPeriods = period -> {
        Optional.ofNullable(period.getStartTime()).orElseThrow();
        System.out.println("Period Start Item:" + period.getStartTime());
        System.out.println("Now:" + LocalDate.now());
        LocalDate weatherDate = period.getStartTime().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        System.out.println("Converted Period Start:" + weatherDate);
        return LocalDate.now().datesUntil(weatherDate).count() == 0;
    };
    
    static public String convertToCelsius(Period today) {
		if ("C".equalsIgnoreCase(today.getTemperatureUnit())) {
			return today.getTemperature();
		}
		Double f = Double.valueOf(today.getTemperature());
		Double result = (f - 32) * 5/9;
		return String.format("%.1f", result);
    }
}
