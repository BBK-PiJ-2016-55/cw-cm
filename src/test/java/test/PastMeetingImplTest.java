package test.java.test;

import main.java.impl.ContactImpl;
import main.java.impl.FutureMeetingImpl;
import main.java.impl.PastMeetingImpl;
import org.junit.Before;
import org.junit.Test;
import main.java.spec.Contact;
import main.java.spec.Meeting;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by svince04 on 04/03/2017 for cw-cm.
 */
public class PastMeetingImplTest {
    private Calendar pastDate;
    private Set<Contact> attendeeSet = new HashSet<>();
    private Meeting pastMeeting;

    @Before
    public void setUp() {
        Contact contact = new ContactImpl(1, "Pluto", "Tall and not very bright.");
        attendeeSet.add(contact);
        pastDate = new GregorianCalendar(2012, 4, 5, 11, 30);
        pastMeeting = new PastMeetingImpl(1, pastDate, attendeeSet, "I shouldn't have gone into the dog park.");
    }

    // Constructor test section

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
        new PastMeetingImpl(1, null, attendeeSet, "This date is invalid.");
    }

    @Test (expected = NullPointerException.class)
    public void testAttendeesNull() {
        new PastMeetingImpl(1, pastDate, null, "This contact group is invalid.");
    }

    // getId tests

    @Test
    public void getId() {
        int id = pastMeeting.getId();
        assertTrue(id == 1);
    }

    @Test
    public void getDate() {

    }

    @Test
    public void getContacts() {

    }

    @Test
    public void getNotes() {

    }

}