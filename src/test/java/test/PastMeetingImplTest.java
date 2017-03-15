package test.java.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import main.java.impl.ContactImpl;
import main.java.impl.PastMeetingImpl;
import main.java.spec.Contact;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by svince04 on 04/03/2017.
 * PiJ Coursework 3.
 */
public class PastMeetingImplTest {
  private Calendar pastDate = new GregorianCalendar(2012, 4, 5, 11, 30);
  private Contact mrEd = new ContactImpl(1, "Mr. Ed", "Tall and not very bright.");
  private final Set<Contact> attendeeSet = new HashSet<>();

  @Before
  public void setUp() {
    attendeeSet.add(mrEd);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIdBelowZero() {
    new PastMeetingImpl(-4, pastDate, attendeeSet, "This id is invalid.");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIdZero() {
    new PastMeetingImpl(0, pastDate, attendeeSet, "This id is invalid.");
  }

  @Test (expected = NullPointerException.class)
  public void testDateNull() {
    new PastMeetingImpl(2, null, attendeeSet, "This date is invalid.");
  }

  @Test (expected = NullPointerException.class)
  public void testAttendeesNull() {
    new PastMeetingImpl(2, pastDate, null, "This contact group is invalid.");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAttendeesEmpty() {
    attendeeSet.clear();
    new PastMeetingImpl(2, pastDate, attendeeSet, "Contact set is empty.");
  }
}