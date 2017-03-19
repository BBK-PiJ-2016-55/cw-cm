package impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import spec.Contact;
import spec.Meeting;

/**
 * Mock class to facilitate testing of {@link Meeting}. Implements {@link Meeting}.
 * @author svince04
 */
public class MockMeetingImpl extends MeetingImpl implements Meeting, Serializable {

  /**
   * Constructs a MockMeetingImpl object with an id, date and set of Contacts.
   * @param id the unique id number of the meeting.
   * @param date calendar object contain date and time of the meeting.
   * @param attendees set of Contacts affiliated with the meeting.
   * @throws IllegalArgumentException when ID is 0 or below.
   * @throws NullPointerException when passed empty Contact Set.
   */
  public MockMeetingImpl(int id, Calendar date, Set<Contact> attendees) {
    super(id, date, attendees);
  }
}
