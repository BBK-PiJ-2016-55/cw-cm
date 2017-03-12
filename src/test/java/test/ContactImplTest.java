package test.java.test;

import main.java.impl.ContactImpl;
import org.junit.Test;
import main.java.spec.Contact;

import static org.junit.Assert.*;

/**
 * Created by svince04 on 17/02/2017.
 * PiJ Coursework 3
 */
public class ContactImplTest {
    private final static String nameCarl = "Carl";
    private final Contact carl = new ContactImpl(3, nameCarl, "Inherently unstable person. ");
    private final Contact viv = new ContactImpl(67, "Viv");

    @Test
    public void testFullConstructorGetId() {
        int result = carl.getId();
        assertEquals(3, result);
    }

    @Test
    public void testBriefConstructorGetId() {
        int result = viv.getId();
        assertEquals(67, result);
    }

    @Test
    public void testFullConstructorGetName() {
        String result = carl.getName();
        assertEquals(nameCarl, result);
    }

    @Test
    public void testBriefConstructorGetName() {
        String result = viv.getName();
        assertEquals("Viv", result);
    }

    @Test
    public void testGetNotesPopulated() {
        String result = carl.getNotes();
        assertEquals("Inherently unstable person. ", result);
    }

    @Test
    public void testGetNotesUnpopulated() {
        String result = viv.getNotes();
        assertEquals("", result);
    }

    @Test
    public void testSetNotesMultiple() {
        carl.addNotes("Quite rich. ");
        carl.addNotes("Treat with caution. ");
        String result = carl.getNotes();
        assertTrue(result.contains("Inherently unstable person. "));
        assertTrue(result.contains("Quite rich. "));
        assertTrue(result.contains("Treat with caution. "));
    }

    @Test
    public void testSetNotesBriefConstructor() {
        viv.addNotes("Dark horse");
        String result = viv.getNotes();
        assertEquals("Dark horse", result);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFullConstructorZeroNegativeId() {
        new ContactImpl(-7, nameCarl, "Inherently unstable person");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBriefConstructorNegativeId() {
        new ContactImpl(-7, nameCarl);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBriefConstructorZeroId() {
        new ContactImpl(0, nameCarl);
    }

    @Test (expected = NullPointerException.class)
    public void testFullConstructorNullName() {
        new ContactImpl(6, null, "Inherently unstable person");
    }

    @Test (expected = NullPointerException.class)
    public void testBriefConstructorNullName() {
        new ContactImpl(6, null, "Inherently unstable person");
    }

    @Test (expected = NullPointerException.class)
    public void testFullConstructorNullNotes() {
        new ContactImpl(6, nameCarl, null);
    }

}