package main.java.impl;

import main.java.spec.Contact;
import main.java.spec.Meeting;

import java.util.Calendar;
import java.util.Set;

/**
 * Created by svince04 on 17/02/2017
 * PiJ coursework 3
 * Mock class to facilitate testing of abstract class MeetingImpl
 */
public class MockMeetingImpl extends MeetingImpl implements Meeting {
  public MockMeetingImpl(int id, Calendar date, Set<Contact> attendees) throws IllegalArgumentException, NullPointerException {
    super(id, date, attendees);
  }
}
