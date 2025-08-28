package pepe.parser;

import org.junit.jupiter.api.Test;
import pepe.command.*;
import pepe.exception.PepeExceptions;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testParserReturnsCorrectCommandInstances() throws PepeExceptions {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
        assertTrue(Parser.parse("unmark 1") instanceof UnmarkCommand);
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
        assertTrue(Parser.parse("todo Finish homework") instanceof TodoCommand);
        assertTrue(Parser.parse("deadline Submit report /by 2099-12-31") instanceof DeadlineCommand);
        assertTrue(Parser.parse("event Meeting /from 2099-01-01 /to 2099-01-02") instanceof EventCommand);
    }

    @Test
    void testInvalidCommandThrowsException() {
        PepeExceptions exception = assertThrows(PepeExceptions.class, () -> Parser.parse("foobar"));
        assertEquals("Invalid Command!", exception.getMessage());
    }
}
