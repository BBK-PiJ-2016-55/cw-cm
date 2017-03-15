package main.java.impl;

import java.util.Calendar;
import java.util.Set;

import main.java.spec.Contact;
import main.java.spec.Meeting;

/**
 * Created by svince04 on 17/02/2017.
 * PiJ coursework 3.
 * Mock class to facilitate testing of abstract class MeetingImpl.
 */
public class MockMeetingImpl extends MeetingImpl implements Meeting {

  /**
   * @param id int - the unique id number of the meeting.
   * @param date Calendar object contain date and time of the meeting.
   * @param attendees Set of Contacts affiliated with the meeting.
   * @throws IllegalArgumentException when ID is 0 or below.
   * @throws NullPointerException when passed empty Contact Set.
   */
  public MockMeetingImpl(int id, Calendar date, Set<Contact> attendees)
          throws IllegalArgumentException, NullPointerException {
    super(id, date, attendees);
  }
}
