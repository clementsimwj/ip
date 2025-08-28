package pepe.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pepe.exception.PepeExceptions;

public class Events extends Task {
    private String startTime;
    private String endTime;
    private LocalDate start;
    private LocalDate end;

    public Events(String name, String startTime, String endTime) throws PepeExceptions {
        super(name);
        try {
            this.start = LocalDate.parse(startTime);
            this.end = LocalDate.parse(endTime);
            this.startTime = start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.endTime = end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            if (end.isBefore(LocalDate.now())) {
                throw new PepeExceptions("Invalid Input: End Date cannot be before today");
            }
            if (start.isAfter(end)) {
                throw new PepeExceptions("Invalid Input: Start Date cannot be after End Date");
            }
        } catch (DateTimeParseException e) {
            throw new PepeExceptions("Invalid Input: Please check the format of your dates (yyyy-mm-dd)");
        }
    }

    public boolean isDueNextWeek() {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusWeeks(1);
        return (this.end.isAfter(today) && this.end.isBefore(nextWeek));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String toFileFormat() {
        return "E" + " | " + super.isMarked() +" | " + super.getName() + " | " + this.startTime + " - " + this.endTime;
    }
}
