package impl;

import spec.Contact;
import spec.PastMeeting;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * Created by svince04 on 17/02/2017 for cw-cm.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
    private String notes;

    public PastMeetingImpl(int id, Date date, Set<Contact> attendees, String notes) {
        super(id, date, attendees);
        this.notes = notes;
    }

    @Override
    public Calendar getDate() {
        return null;
    }

    @Override
    public Set<Contact> getContacts() {
        return null;
    }

    @Override
    public String getNotes() {
        return null;
    }
}
