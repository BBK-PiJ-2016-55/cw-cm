package test.java.test;

import main.java.impl.ContactImpl;
import main.java.impl.PastMeetingImpl;
import org.junit.Before;
import org.junit.Test;
import main.java.spec.Contact;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by svince04 on 04/03/2017.
 * PiJ Coursework 3.
 */
public class PastMeetingImplTest {
    private Calendar pastDate;
    private final Set<Contact> attendeeSet = new HashSet<>();
    private PastMeetingImpl pastMeeting;

    @Before
    public void setUp() {
        Contact contact = new ContactImpl(1, "Pluto", "Tall and not very bright.");
        attendeeSet.add(contact);
        pastDate = new GregorianCalendar(2012, 4, 5, 11, 30);
        pastMeeting = new PastMeetingImpl(1, pastDate, attendeeSet, "I shouldn't have gone into the dog park.");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIdBelowZero() {
        new PastMeetingImpl(-4, pastDate, attendeeSet, "This id is invalid.");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIdZero() {
        new PastMeetingImpl(0, pastDate, attendeeSet, "This id is invalid.");
    }

    @Test (expected = NullPointerException.class)
    public void testDateNull() {
        new PastMeetingImpl(2, null, attendeeSet, "This date is invalid.");
    }

    @Test (expected = NullPointerException.class)
    public void testAttendeesNull() {
        new PastMeetingImpl(2, pastDate, null, "This contact group is invalid.");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAttendeesEmpty() {
        attendeeSet.clear();
        new PastMeetingImpl(2, pastDate, attendeeSet, "Contact set is empty.");
    }

    // getContacts tests
   @Test
    public void testGetNotes() {
        assertEquals("I shouldn't have gone into the dog park.", pastMeeting.getNotes());
    }

}