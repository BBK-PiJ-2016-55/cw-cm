package impl;

import spec.Contact;
import spec.FutureMeeting;

import java.util.Calendar;
import java.util.Set;

/**
 * Created by svince04 on 17/02/2017 for cw-cm.
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {
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
