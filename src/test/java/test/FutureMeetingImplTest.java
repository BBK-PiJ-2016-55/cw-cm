package test.java.test;

import main.java.impl.ContactImpl;
import main.java.impl.FutureMeetingImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.java.spec.Contact;
import main.java.spec.Meeting;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by svince04 on 04/03/2017.
 * PiJ Coursework 3
 */
public class FutureMeetingImplTest {
    private Calendar date;
    private Set<Contact> attendeeSet;
    private Meeting meeting;

    @Before
    public void setUp() {
        attendeeSet = new HashSet<>();
        Contact contact = new ContactImpl(1, "Theresa", "Smells slightly of cheese");
        attendeeSet.add(contact);
        date = new GregorianCalendar(2017, 4, 5, 11, 30);
        meeting = new FutureMeetingImpl(1, date, attendeeSet);
    }

    // Constructor test section

    @Test (expected = IllegalArgumentException.class)
    public void testIdBelowZero() {
        new FutureMeetingImpl(-4, date, attendeeSet);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIdZero() {
        new FutureMeetingImpl(0, date, attendeeSet);
    }

    @Test (expected = NullPointerException.class)
    public void testDateNull() {
        new FutureMeetingImpl(1, null, attendeeSet);
    }

    @Test (expected = NullPointerException.class)
    public void testAttendeesNull() {
        new FutureMeetingImpl(1, date, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAttendeesEmpty() {
        attendeeSet.clear();
        new FutureMeetingImpl(1, date, attendeeSet);
    }

    // getId() test section

    @Test
    public void testGetId() {
        int id = meeting.getId();
        assertTrue(id == 1);
    }

    @Test
    public void testGetMultipleIds() {
        Meeting secondMeeting = new FutureMeetingImpl(2, date, attendeeSet);
        int id = meeting.getId();
        assertTrue(id == 1);
        id = secondMeeting.getId();
        assertTrue(id == 2);
    }

    // getDate() test section

    @Test
    public void testGetDate() {
        assertEquals(date, meeting.getDate());
    }

    // getContacts() test section

    @Test
    public void testGetContactsSingle() {
        assertEquals(attendeeSet, meeting.getContacts());
    }

    @Test
    public void testGetContactsMulti() {
        Contact contact = new ContactImpl(2, "Tiger", "A large, marmalade cat.");
        attendeeSet.add(contact);
        Meeting multiMeet = new FutureMeetingImpl(3, date, attendeeSet);
        assertEquals(attendeeSet, multiMeet.getContacts());
    }

    @After
    public void tearDown() {
        attendeeSet = null;
        date = null;
        meeting = null;
    }
}