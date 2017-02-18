package test;

import impl.ContactImpl;
import org.junit.Before;
import org.junit.Test;
import spec.Contact;

import static org.junit.Assert.*;

/**
 * Created by essvee on 17/02/2017.
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
    public void testSetNotes() {
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