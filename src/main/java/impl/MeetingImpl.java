package main.java.impl;

import main.java.spec.Contact;
import main.java.spec.Meeting;

import java.util.*;

/**
 * Created by svince04 on 17/02/2017
 * PiJ coursework 3
 */
public abstract class MeetingImpl implements Meeting {
  private int id;
  private Calendar date;
  private Set<Contact> attendees;

  MeetingImpl(int id, Calendar date, Set<Contact> attendees) throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(date, "Date cannot be null");
    Objects.requireNonNull(attendees, "Contact set cannot be null");
    if (id <= 0) {
      throw new IllegalArgumentException("ID cannot be negative");
    } else if (attendees.isEmpty()) {
      throw new IllegalArgumentException("Contact set cannot be empty");
    }
    this.id = id;
    this.date = date;
    this.attendees = attendees;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public Calendar getDate() {
    return (Calendar) date.clone();
  }

  @Override
  public Set<Contact> getContacts() {
    return new HashSet<>(attendees);
  }
}
