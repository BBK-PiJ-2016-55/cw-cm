package test.java.test;

import main.java.impl.ContactImpl;
import main.java.impl.FutureMeetingImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.java.spec.Contact;

import java.util.*;


/**
 * Created by svince04 on 04/03/2017.
 * PiJ Coursework 3
 */
public class FutureMeetingImplTest {
  private Calendar date;
  private Set<Contact> attendeeSet;

  @Before
  public void setUp() {
    attendeeSet = new HashSet<>();
    Contact contact = new ContactImpl(1, "Theresa", "Smells slightly of cheese");
    attendeeSet.add(contact);
    date = new GregorianCalendar(2017, 4, 5, 11, 30);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIdBelowZero() {
    new FutureMeetingImpl(-4, date, attendeeSet);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIdZero() {
    new FutureMeetingImpl(0, date, attendeeSet);
  }

  @Test (expected = NullPointerException.class)
  public void testDateNull() {
    new FutureMeetingImpl(1, null, attendeeSet);
  }

  @Test (expected = NullPointerException.class)
  public void testAttendeesNull() {
    new FutureMeetingImpl(1, date, null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAttendeesEmpty() {
    attendeeSet.clear();
    new FutureMeetingImpl(1, date, attendeeSet);
  }

  @After
  public void tearDown() {
    attendeeSet = null;
    date = null;
  }
}