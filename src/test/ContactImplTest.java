package test;

import impl.ContactImpl;
import org.junit.Test;
import spec.Contact;

import static org.junit.Assert.*;

/**
 * Created by svince04 on 17/02/2017 for cw-cm.
 */
public class ContactImplTest {

    @Test
    public void testFullConstructorGetId() {
        Contact carl = new ContactImpl(3, "Carl", "Inherently unstable person");
        int result = carl.getId();
        assertEquals(3, result);
    }

    @Test
    public void testBriefConstructorGetId() {
        Contact viv = new ContactImpl(67, "Viv");
        int result = viv.getId();
        assertEquals(67, result);
    }

    @Test
    public void testFullConstructorGetName() {
        Contact carl = new ContactImpl(3, "Carl", "Inherently unstable person");
        String result = carl.getName();
        assertEquals("Carl", result);
    }

    @Test
    public void testBriefConstructorGetName() {
        Contact viv = new ContactImpl(67, "Viv");
        String result = viv.getName();
        assertEquals("Viv", result);
    }

    @Test
    public void testGetNotesPopulated() {
        Contact carl = new ContactImpl(3, "Carl", "Inherently unstable person");
        String result = carl.getNotes();
        assertEquals("Inherently unstable person", result);
    }

    @Test
    public void testGetNotesUnpopulated() {
        Contact viv = new ContactImpl(3, "Viv");
        String result = viv.getNotes();
        assertEquals("", result);
    }

    @Test
    public void testSetNotesFullConstructor() {
        Contact carl = new ContactImpl(65, "Carl", "Inherently unstable person");
        carl.addNotes("High Horse");
        String result = carl.getNotes();
        assertEquals("High Horse", result);
    }

    @Test
    public void testSetNotesMultiple() {
        Contact carl = new ContactImpl(65, "Carl", "Inherently unstable person. ");
        carl.addNotes("Quite rich. ");
        carl.addNotes("Treat with caution. ");
        String result = carl.getNotes();
        assertTrue(result.contains("Inherently unstable prson. "));
        assertTrue(result.contains("Quite rich. "));
        assertTrue(result.contains("Treat with caution. "));
    }

    @Test
    public void testSetNotesBriefConstructor() {
        Contact viv = new ContactImpl(65, "Viv");
        viv.addNotes("Dark horse");
        String result = viv.getNotes();
        assertEquals("Dark horse", result);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFullConstructorZeroNegativeId() {
        Contact carl = new ContactImpl(-7, "Carl", "Inherently unstable person");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBriefConstructorZeroNegativeId() {
        Contact carl = new ContactImpl(-7, "Carl");
    }

    @Test (expected = NullPointerException.class)
    public void testFullConstructorNullName() {
        Contact carl = new ContactImpl(6, null, "Inherently unstable person");
    }

    @Test (expected = NullPointerException.class)
    public void testBriefConstructorNullName() {
        Contact carl = new ContactImpl(6, null, "Inherently unstable person");
    }

    @Test (expected = NullPointerException.class)
    public void testFullConstructorNullNotes() {
        Contact carl = new ContactImpl(6, "Carl", null);
    }
}