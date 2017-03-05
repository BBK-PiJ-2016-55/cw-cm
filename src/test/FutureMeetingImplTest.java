package test;

import impl.ContactImpl;
import impl.FutureMeetingImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import spec.Contact;
import spec.Meeting;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by svince04 on 04/03/2017 for cw-cm.
 */
public class FutureMeetingImplTest {
    GregorianCalendar date;
    Set<Contact> attendeeSet;
    Meeting meeting;

    @Before
    public void setUp() {
        attendeeSet = new HashSet<>();
        Contact contact = new ContactImpl(1, "Theresa", "Smelled slightly of cheese");
        attendeeSet.add(contact);
        date = new GregorianCalendar(2017, 04, 05, 11, 30);
    }

    // Constructor test section

    @Test (expected = IllegalArgumentException.class)
    public void testIdBelowZero() {
        meeting = new FutureMeetingImpl(-4, date, attendeeSet);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIdZero() {
        meeting = new FutureMeetingImpl(0, date, attendeeSet);
    }

    @Test (expected = NullPointerException.class)
    public void testDateNull() {
        meeting = new FutureMeetingImpl(1, null, attendeeSet);
    }

    @Test (expected = NullPointerException.class)
    public void testAttendeesNull() {
        meeting = new FutureMeetingImpl(1, date, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAttendeesEmpty() {
        attendeeSet.clear();
        meeting = new FutureMeetingImpl(1, date, attendeeSet);
    }

    // getId() test section

    @Test
    public void testGetId() {
        meeting = new FutureMeetingImpl(1, date, attendeeSet);
        int id = meeting.getId();
        assertTrue(id == 1);
    }

    @Test
    public void testGetMultipleIds() {
        meeting = new FutureMeetingImpl(1, date, attendeeSet);
        Meeting secondMeeting = new FutureMeetingImpl(2, date, attendeeSet);
        int id = meeting.getId();
        assertTrue(id == 1);
        id = secondMeeting.getId();
        assertTrue(id == 2);
    }

//    @Test
//    public void getDate() {
//        Date testDate = new Date();
//        meeting = new FutureMeetingImpl(1, testDate, attendeeSet);
//        assertTrue(testDate, meeting.getDate());
//    }

    @Test
    public void getContacts() {

    }

    @After
    public void tearDown() {
        attendeeSet = null;
        date = null;
    }
}