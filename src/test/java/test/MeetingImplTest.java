package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import impl.ContactImpl;
import impl.MeetingImpl;
import impl.MockMeetingImpl;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import spec.Contact;
import spec.Meeting;

/**
 * Unit tests for {@link MeetingImpl}, using mock object {@link MockMeetingImpl}.
 * @author svince04
 */
public class MeetingImplTest {
  private Calendar date = new GregorianCalendar(2005, 4, 5, 11, 30);
  private Calendar laterDate = new GregorianCalendar(2006, 4, 5, 11, 30);
  private Contact daffy = new ContactImpl(1, "Daffy", "Fond of white collars.");
  private Meeting meeting;
  private Meeting secondMeeting;
  private final Set<Contact> attendeeSet = new HashSet<>();

  /**
   * Sets up two MockMeetingImpl instance to reduce repetition in tests.
   * Adds one Contact to existing set of Contacts.
   */
  @Before
  public void setUp() {
    attendeeSet.add(daffy);
    meeting = new MockMeetingImpl(1, date, attendeeSet);
    secondMeeting = new MockMeetingImpl(2, laterDate, attendeeSet);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIdBelowZero() {
    new MockMeetingImpl(-4, date, attendeeSet);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIdZero() {
    new MockMeetingImpl(0, date, attendeeSet);
  }

  @Test (expected = NullPointerException.class)
  public void testDateNull() {
    new MockMeetingImpl(2, null, attendeeSet);
  }

  @Test (expected = NullPointerException.class)
  public void testAttendeesNull() {
    new MockMeetingImpl(2, date, null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAttendeesEmpty() {
    attendeeSet.clear();
    new MockMeetingImpl(2, date, attendeeSet);
  }

  // getId tests

  @Test
  public void testGetId() {
    assertEquals(meeting.getId(), 1);
  }

  @Test
  public void testUniqueIds() {
    assertNotEquals(meeting.getId(), secondMeeting.getId());
  }

  @Test
  public void testImmutableContacts() {
    Set<Contact> contacts = meeting.getContacts();
    contacts.add(new ContactImpl(4, "Donald", "I wish he'd wear trousers."));
    assertNotEquals(contacts.size(), meeting.getContacts().size());
  }

  // getDate tests

  @Test
  public void testGetDate() {
    assertEquals(date, meeting.getDate());
  }

  @Test
  public void testImmutableDate() {
    Calendar date = meeting.getDate();
    date.add(Calendar.YEAR, 1);
    assertNotEquals(date.get(Calendar.YEAR), meeting.getDate().get(Calendar.YEAR));
  }

  // getContacts tests

  @Test
  public void testGetContactsAll() {
    assertEquals(attendeeSet, meeting.getContacts());
  }
}