package impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import spec.Contact;
import spec.Meeting;


/**
 * Implementation of {@link Meeting}.
 * @author svince04
 */
public abstract class MeetingImpl implements Meeting, Serializable {
  private int id;
  private Calendar date;
  private Set<Contact> attendees;

  /**
   * Is used by subclasses to construct either a Past or FutureMeetingImpl object
   * instantiated with an id, date and set of Contacts.
   * @param id int - the unique id number of the meeting.
   * @param date Calendar object contain date and time of the meeting.
   * @param attendees Set of Contacts affiliated with the meeting.
   * @throws IllegalArgumentException when ID is 0 or below.
   * @throws NullPointerException when passed empty Contact Set.
   */
  MeetingImpl(int id, Calendar date, Set<Contact> attendees) {
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

  /**
   * {@inheritDoc}.
   */
  @Override
  public int getId() {
    return id;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Calendar getDate() {
    return (Calendar) date.clone();
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Set<Contact> getContacts() {
    return new HashSet<>(attendees);
  }
}
