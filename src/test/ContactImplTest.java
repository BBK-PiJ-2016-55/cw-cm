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
    private Contact carl;

    @Before
    public void setUp() {
        carl = new ContactImpl(3, "Carl", "Inherently unstable person");
    }

    @Test
    public void testGetId() {
        int result = carl.getId();
        assertEquals(3, result);
    }

    @Test
    public void testGetName() {
        String result = carl.getName();
        assertEquals("Carl", result);
    }

    @Test
    public void testGetNotes() {
        String result = carl.getNotes();
        assertEquals("Inherently unstable person", result);
    }
}