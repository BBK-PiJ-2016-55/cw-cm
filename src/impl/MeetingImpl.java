package impl;

import spec.Contact;
import spec.Meeting;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * Created by svince04 on 17/02/2017 for cw-cm.
 */
public abstract class MeetingImpl implements Meeting {
    private int id;
    private GregorianCalendar date;
    private Set<Contact> attendees;

    public MeetingImpl(int id, GregorianCalendar date, Set<Contact> attendees) throws IllegalArgumentException, NullPointerException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        } else if (attendees.isEmpty()) {
            throw new IllegalArgumentException("Contact set cannot be empty");
        } else if (date == null || attendees == null) {
            throw new NullPointerException("Date and Contact set cannot be null");
        } else {
                this.id = id;
                this.date = date;
                this.attendees = attendees;
        }
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
