package test;

import impl.PastMeetingImpl;
import org.junit.Test;
import spec.Contact;
import spec.Meeting;

import java.util.Date;
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
        Date date = new Date();
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