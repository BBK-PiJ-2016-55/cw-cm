package impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import spec.Contact;
import spec.FutureMeeting;

/**
 * Implementation of {@link FutureMeeting}.
 * @author svince04
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting, Serializable {

  /**
   * Constructs a FutureMeetingImpl with an id, future date and set of Contacts.
   * @param id the unique id of the meeting.
   * @param date calendar object containing the future date and time of the meeting.
   * @param attendees set of Contacts who will be attending the meeting
   * @throws IllegalArgumentException when ID is 0 or below.
   * @throws NullPointerException when passed empty Contact Set.
   */
  public FutureMeetingImpl(int id, Calendar date, Set<Contact> attendees) {
    super(id, date, attendees);
  }
}
