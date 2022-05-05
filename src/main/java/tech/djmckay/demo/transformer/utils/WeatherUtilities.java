package tech.djmckay.demo.transformer.utils;

import tech.djmckay.demo.model.Period;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.function.Predicate;

public class WeatherUtilities {

    static public  Predicate<? super Period> latest = period -> {
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
}
