package test.java.test;

import main.java.impl.ContactImpl;
import main.java.impl.FutureMeetingImpl;
import main.java.impl.MockMeetingImpl;
import main.java.impl.PastMeetingImpl;
import main.java.spec.Contact;
import main.java.spec.Meeting;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by svince04 on 11/03/2017.
 * PiJ Coursework 3.
 */
public class MeetingTest {
    private Calendar date;
    private Calendar secondDate;
    private MockMeetingImpl meeting;
    private MockMeetingImpl secondMeeting;
    private Set<Contact> attendeeSet = new HashSet<>();

    @Before
    public void setUp() {
        attendeeSet.add(new ContactImpl(1, "Daffy", "Fond of white collars."));
        date = new GregorianCalendar(2005, 4, 5, 11, 30);
        secondDate = new GregorianCalendar(2006, 4, 5, 11, 30);
        meeting = new MockMeetingImpl(1, date, attendeeSet);
        secondMeeting = new MockMeetingImpl(2, secondDate, attendeeSet);
    }

    // Constructor test section

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
    public void testGetMultipleIds() {
        int id = meeting.getId();
        assertTrue(id == 1);
        id = secondMeeting.getId();
        assertTrue(id == 2);
    }

    @Test
    public void testContactsImmutableFromOutsideObject() {
        Set<Contact> contacts = meeting.getContacts();
        contacts.add(new ContactImpl(4, "Donald", "I wish he'd wear trousers."));
        assertFalse(contacts.size() == meeting.getContacts().size());
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