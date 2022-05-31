package chatapp;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Status {

    private LocalDate date;
    private LocalTime time;
    private int seen;
    private Time previousTime;
    private Date PreviousDate;

    public Status() {

    }

    public Time getPreviousTime() {
        return previousTime;
    }

    public void setPreviousTime(Time previousTime) {
        this.previousTime = previousTime;
    }

    public Date getPreviousDate() {
        return PreviousDate;
    }

    public void setPreviousDate(Date PreviousDate) {
        this.PreviousDate = PreviousDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

}
