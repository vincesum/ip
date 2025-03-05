package Vinbot;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Date {
    private LocalDate localDate;

    //Constructor taking in a date of format dd/MM/yyyy, throws exception if format not followed
    public Date(String date){
        localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    //For UI to print out with specified formatting
    public String getDate() {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }


}
