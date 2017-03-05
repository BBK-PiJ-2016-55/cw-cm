package impl;

import spec.Contact;
import spec.FutureMeeting;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * Created by svince04 on 17/02/2017 for cw-cm.
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

    public FutureMeetingImpl(int id, GregorianCalendar date, Set<Contact> attendees) {
        super(id, date, attendees);
    }

    @Override
    public Calendar getDate() {
        return null;
    }

    @Override
    public Set<Contact> getContacts() {
        return null;
    }
}
