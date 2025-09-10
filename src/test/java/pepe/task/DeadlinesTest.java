package pepe.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import pepe.exception.PepeExceptions;


class DeadlinesTest {

    @Test
    void testToString_unmarked() throws PepeExceptions {
        Deadlines deadline = new Deadlines("Submit report", "2099-12-31");
        assertEquals("[D][ ] Submit report (by: Dec 31 2099)", deadline.toString());
    }

    @Test
    void testToString_marked() throws PepeExceptions {
        Deadlines deadline = new Deadlines("Submit report", "2099-12-31");
        deadline.markTask();
        assertEquals("[D][X] Submit report (by: Dec 31 2099)", deadline.toString());
    }

    @Test
    void testToFileFormat_unmarked() throws PepeExceptions {
        Deadlines deadline = new Deadlines("Submit report", "2099-12-31");
        assertEquals("D | 0 | Submit report | Dec 31 2099", deadline.toFileFormat());
    }

    @Test
    void testToFileFormat_marked() throws PepeExceptions {
        Deadlines deadline = new Deadlines("Submit report", "2099-12-31");
        deadline.markTask();
        assertEquals("D | 1 | Submit report | Dec 31 2099", deadline.toFileFormat());
    }

    @Test
    void testIsDueNextWeek_true() throws PepeExceptions {
        LocalDate today = LocalDate.now();
        LocalDate inFiveDays = today.plusDays(5);

        Deadlines deadline = new Deadlines("Upcoming Task",
                inFiveDays.format(DateTimeFormatter.ISO_DATE));
        assertTrue(deadline.isDueNextWeek());
    }

    @Test
    void testIsDueNextWeek_false() throws PepeExceptions {
        LocalDate today = LocalDate.now();
        LocalDate inTenDays = today.plusDays(10);

        Deadlines deadline = new Deadlines("Future Task",
                inTenDays.format(DateTimeFormatter.ISO_DATE));
        assertFalse(deadline.isDueNextWeek());
    }

    @Test
    void testConstructor_invalidDateFormat() {
        PepeExceptions exception = assertThrows(PepeExceptions.class, () ->
                new Deadlines("Bad Task", "31-12-2099"));
        assertTrue(exception.getMessage().contains("Invalid Input: Please check the format of your dates"));
    }

    @Test
    void testConstructor_dateBeforeToday() {
        LocalDate yesterday = LocalDate.now().minusDays(1);

        PepeExceptions exception = assertThrows(PepeExceptions.class, () ->
                new Deadlines("Past Task", yesterday.toString()));
        assertTrue(exception.getMessage().contains("Dateline cannot be before today"));
    }
}
