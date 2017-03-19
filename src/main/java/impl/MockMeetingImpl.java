package impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import spec.Contact;
import spec.Meeting;

/**
 * PiJ coursework 3.
 * Mock class to facilitate testing of {@see Meeting}.
 * @author svince04
 */
public class MockMeetingImpl extends MeetingImpl implements Meeting, Serializable {

  /**
   * @param id int - the unique id number of the meeting.
   * @param date Calendar object contain date and time of the meeting.
   * @param attendees Set of Contacts affiliated with the meeting.
   * @throws IllegalArgumentException when ID is 0 or below.
   * @throws NullPointerException when passed empty Contact Set.
   */
  public MockMeetingImpl(int id, Calendar date, Set<Contact> attendees) {
    super(id, date, attendees);
  }
}
