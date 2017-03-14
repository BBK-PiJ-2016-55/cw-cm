package main.java.impl;

import main.java.spec.Contact;
import main.java.spec.FutureMeeting;

import java.util.Calendar;
import java.util.Set;

/**
 * Created by svince04 on 17/02/2017
 * PiJ coursework 3
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

  public FutureMeetingImpl(int id, Calendar date, Set<Contact> attendees) {
    super(id, date, attendees);
  }

}
