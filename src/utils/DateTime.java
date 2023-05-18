package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateTime {
    
    //DateTimeFormatter because it is thread safe
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public static String getLocalDate(){
        String localDate = dtf.format(LocalDate.now());
        return localDate;
    }
    
    public static Date asDate(String date){
        LocalDate localDate = LocalDate.parse(date, dtf);
        //Parse to LocalDateTime then to Date        
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
    
    public static LocalDate convertDate(String date){
        return LocalDate.parse(date, dtf);
    }
    
    public static LocalDate convertDate(Date date){
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDate localDate = localDateTime.toLocalDate();
        
        return localDate;
    }
    
    public static long getDaysBetween (LocalDate startDate, LocalDate endDate){
        
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        
        return daysBetween;
    }
    
    public static long getDaysBetween (String startDate, String endDate){
        
        LocalDate startDateObj = convertDate(startDate);
        LocalDate endDateObj = convertDate(endDate);
        
        long daysBetween = ChronoUnit.DAYS.between(startDateObj, endDateObj);
    
        return daysBetween;
    }
}
