package pepe.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pepe.exception.PepeExceptions;

/**
 * Represents an Event task.
 * <p>
 * An Event task has a name, a marked status, a start date, and an end date.
 * The end date cannot be before today, and the start date cannot be after the end date.
 */
public class Events extends Task {
    private String startTime;
    private String endTime;
    private LocalDate start;
    private LocalDate end;

    /**
     * Constructs a new Event task with the given name, start date, and end date.
     *
     * @param name      the name or description of the task
     * @param startTime the start date in the format yyyy-MM-dd
     * @param endTime   the end date in the format yyyy-MM-dd
     * @throws PepeExceptions if the dates are invalid (format, end before today, or start after end)
     */
    public Events(String name, String startTime, String endTime) throws PepeExceptions {
        super(name);
        assert name != null && !name.isBlank() : "Event name should not be null or empty";
        assert startTime != null && !startTime.isBlank() : "Start time should not be null or empty";
        assert endTime != null && !endTime.isBlank() : "End time should not be null or empty";
        try {
            this.start = LocalDate.parse(startTime);
            this.end = LocalDate.parse(endTime);
            assert start != null : "Parsed start LocalDate should not be null";
            assert end != null : "Parsed end LocalDate should not be null";
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
    /**
     * Returns a string representation of the Event task for display.
     * <p>
     * Format: [E][X] taskName (from: MMM d yyyy to: MMM d yyyy) if marked,
     * [E][ ] taskName (from: MMM d yyyy to: MMM d yyyy) if unmarked.
     *
     * @return a human-readable string representing the Event task
     */
    @Override
    public String toString() {
        assert startTime != null && !startTime.isBlank() : "Start time should be non-null and non-empty for display";
        assert endTime != null && !endTime.isBlank() : "End time should be non-null and non-empty for display";
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
    /**
     * Returns a string representing the Event task in a file-friendly format.
     * <p>
     * Format: E | 1 | taskName | MMM d yyyy - MMM d yyyy (if marked) or
     * E | 0 | taskName | MMM d yyyy - MMM d yyyy (if unmarked)
     *
     * @return the Event task formatted for saving to a file
     */
    @Override
    public String toFileFormat() {
        assert startTime != null && !startTime.isBlank()
                : "Start time should be non-null and non-empty for file format";
        assert endTime != null && !endTime.isBlank()
                : "End time should be non-null and non-empty for file format";
        return "E" + " | " + super.checkMarked() + " | "
                + super.getName()
                + " | "
                + this.startTime
                + " - " + this.endTime;
    }
}
