package impl;

import spec.Contact;
import spec.PastMeeting;
import java.util.Calendar;
import java.util.Set;

/**
 * Created by svince04 on 17/02/2017
 * PiJ coursework 3
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
    private String notes;

    public PastMeetingImpl(int id, Calendar date, Set<Contact> attendees, String notes) {
        super(id, date, attendees);
        this.notes = notes;
    }

    @Override
    public String getNotes() {
        return null;
    }
}
