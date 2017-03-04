package impl;

import spec.Contact;
import spec.Meeting;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * Created by svince04 on 17/02/2017 for cw-cm.
 */
public abstract class MeetingImpl implements Meeting {
    private int id;
    private Date date;
    private Set<Contact> attendees;

    public MeetingImpl(int id, Date date, Set<Contact> attendees) {
        this.id = id;
        this.date = date;
        this.attendees = attendees;
    }

    @Override
    public int getId() {
        return id;
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
