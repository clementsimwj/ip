package pepe.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pepe.exception.PepeExceptions;

public class Deadlines extends Task {
    private String dateline;
    private LocalDate date;

    public Deadlines(String name, String dateline) throws PepeExceptions {
        super(name);
        try {
            this.date = LocalDate.parse(dateline);
            this.dateline = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            if (date.isBefore(LocalDate.now())) {
                throw new PepeExceptions("Invalid Input: Dateline cannot be before today!");
            }
        } catch (DateTimeParseException e) {
            throw new PepeExceptions("Invalid Input: Please check the format of your dates (yyyy-mm-dd)");
        }
    }

    public boolean isDueNextWeek() {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusWeeks(1);
        return ((this.date.isAfter(today) || this.date.isEqual(today)) && this.date.isBefore(nextWeek));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateline +")";
    }

    @Override
    public String toFileFormat() {
        return "D" + " | " + super.isMarked() +" | " + super.getName() + " | " + this.dateline;
    }
}
