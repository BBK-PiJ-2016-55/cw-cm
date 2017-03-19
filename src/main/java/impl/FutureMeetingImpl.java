package impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import spec.Contact;
import spec.FutureMeeting;

/**
 * PiJ coursework 3.
 * Implementation of {@see FutureMeeting}.
 * @author svince04
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting, Serializable {

  /**
   * @param id the unique id of the meeting.
   * @param date Calendar object containing the future date and time of the meeting.
   * @param attendees Set of Contacts who will be attending the meeting
   * @throws IllegalArgumentException when ID is 0 or below.
   * @throws NullPointerException when passed empty Contact Set.
   */
  public FutureMeetingImpl(int id, Calendar date, Set<Contact> attendees) {
    super(id, date, attendees);
  }

}
