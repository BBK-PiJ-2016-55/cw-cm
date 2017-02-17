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
    private Contact conImp;

    @Before
    public void setUp() {
        conImp = new ContactImpl (5, "Bobbert", "");
    }

    @Test
    public void testGetId() {
        int result = conImp.getId();
        assertEquals(5, result);
    }

}