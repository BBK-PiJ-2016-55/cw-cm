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

    @Override
    public int getId() {
        return 0;
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
