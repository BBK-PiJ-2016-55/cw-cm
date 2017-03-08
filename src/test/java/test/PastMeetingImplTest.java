package test.java.test;

import main.java.impl.PastMeetingImpl;
import org.junit.Test;
import main.java.spec.Contact;
import main.java.spec.Meeting;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by svince04 on 04/03/2017 for cw-cm.
 */
public class PastMeetingImplTest {
    @Test
    public void getId() {
        Set<Contact> attendeeSet = new HashSet<>();
        GregorianCalendar date = new GregorianCalendar();
        Meeting meeting = new PastMeetingImpl(1, date, attendeeSet, "A big waste of time.");
        int id = meeting.getId();
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