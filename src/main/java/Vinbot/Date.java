package Vinbot;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Date {
    private LocalDate localDate;

    public Date(String date){
        localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    //For local usage
    public String getDate() {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }


}
