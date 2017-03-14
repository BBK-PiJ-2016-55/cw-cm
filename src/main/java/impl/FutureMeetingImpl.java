package main.java.impl;

import java.util.Calendar;
import java.util.Set;
import main.java.spec.Contact;
import main.java.spec.FutureMeeting;

/**
 * Created by svince04 on 17/02/2017.
 * PiJ coursework 3.
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

  /**
   * @param id int. The id of the meeting.
   * @param date Calendar object containing the future date and time of the meeting.
   * @param attendees Set of Contacts who will be attending the meeting.
   */
  public FutureMeetingImpl(int id, Calendar date, Set<Contact> attendees) {
    super(id, date, attendees);
  }

}
