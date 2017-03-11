package test.java.test;

import main.java.impl.ContactImpl;
import main.java.impl.MockMeetingImpl;
import main.java.spec.Contact;
import main.java.spec.Meeting;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

/**
 * Created by svince04 on 11/03/2017.
 * PiJ Coursework 3.
 */
public class MeetingTest {
    private Calendar date;
    private MockMeetingImpl meeting;
    private MockMeetingImpl secondMeeting;
    private Set<Contact> attendeeSet = new HashSet<>();

    @Before
    public void setUp() {
        attendeeSet.add(new ContactImpl(1, "Daffy", "Fond of white collars."));
        date = new GregorianCalendar(2005, 4, 5, 11, 30);
        Calendar secondDate = new GregorianCalendar(2006, 4, 5, 11, 30);
        meeting = new MockMeetingImpl(1, date, attendeeSet);
        secondMeeting = new MockMeetingImpl(2, secondDate, attendeeSet);
    }

    // Test constructor

    @Test
    public void testConstructor() {
        Meeting meeting = new MockMeetingImpl(3, date, attendeeSet);
        assertNotNull(meeting.getId());
        assertEquals(meeting.getDate(), date);
        assertEquals(meeting.getContacts(), attendeeSet);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIdBelowZero() {
        new MockMeetingImpl(-4, date, attendeeSet);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIdZero() {
        new MockMeetingImpl(0, date, attendeeSet);
    }

    @Test (expected = NullPointerException.class)
    public void testDateNull() {
        new MockMeetingImpl(2, null, attendeeSet);
    }

    @Test (expected = NullPointerException.class)
    public void testAttendeesNull() {
        new MockMeetingImpl(2, date, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAttendeesEmpty() {
        attendeeSet.clear();
        new MockMeetingImpl(2, date, attendeeSet);
    }

    // getId tests

    @Test
    public void testGetId() {
        assertEquals(meeting.getId(), 1);
    }

    @Test
    public void testUniqueIds() {
        assertTrue(meeting.getId() != secondMeeting.getId());
    }

    @Test
    public void testImmutableContacts() {
        Set<Contact> contacts = meeting.getContacts();
        contacts.add(new ContactImpl(4, "Donald", "I wish he'd wear trousers."));
        assertFalse(contacts.size() == meeting.getContacts().size());
    }

    @Test
    public void testImmutableDate() {
        Calendar date = meeting.getDate();
        date.add(Calendar.YEAR, 1);
        assertFalse(date.get(Calendar.YEAR) == meeting.getDate().get(Calendar.YEAR));
    }


    // getDate tests

    @Test
    public void testGetDate() {
        assertEquals(date, meeting.getDate());
    }

    // getContacts tests

    @Test
    public void testGetContactsSingle() {
        assertEquals(attendeeSet, meeting.getContacts());
    }

    }