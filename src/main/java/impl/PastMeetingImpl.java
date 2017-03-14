package main.java.impl;

import java.util.Calendar;
import java.util.Set;

import main.java.spec.Contact;
import main.java.spec.PastMeeting;

/**
 * Created by svince04 on 17/02/2017.
 * PiJ coursework 3.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
  private String notes;

  /**
   * @param id int - the unique id number of the meeting.
   * @param date Calendar object contain past date and time of the meeting.
   * @param attendees Set of Contacts affiliated with the meeting.
   * @param notes String notes about the meeting.
   * @throws IllegalArgumentException when ID is 0 or below.
   * @throws NullPointerException when passed empty Contact Set.
   */
  public PastMeetingImpl(int id, Calendar date, Set<Contact> attendees, String notes) {
    super(id, date, attendees);
    this.notes = notes;
  }

  @Override
  public String getNotes() {
    return notes;
  }
}
