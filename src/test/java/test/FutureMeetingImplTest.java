package test;

import impl.ContactImpl;
import impl.FutureMeetingImpl;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import spec.Contact;

/**
 * Unit tests for {@link FutureMeetingImpl}.
 * @author svince04
 */
public class FutureMeetingImplTest {
  private Calendar date = new GregorianCalendar(2017, 4, 5, 11, 30);
  private Set<Contact> attendeeSet = new HashSet<>();
  private Contact iago = new ContactImpl(1, "Iago", "Powerful friends.");

  @Before
  public void setUp() {
    attendeeSet.add(iago);
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
}