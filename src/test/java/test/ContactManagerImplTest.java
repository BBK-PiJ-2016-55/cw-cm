package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import impl.ContactImpl;
import impl.ContactManagerImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import spec.Contact;
import spec.ContactManager;
import spec.PastMeeting;

/**
 * Unit tests for {@see ContactManagerImpl}.
 * @author svince04
 */
public class ContactManagerImplTest {
  private ContactManager conManImp;
  private final Calendar date = new GregorianCalendar(2017, 4, 5, 12, 10);
  private final Calendar futureDateDistant = new GregorianCalendar(2022, 4, 5);
  private final Calendar pastDate = new GregorianCalendar(2012, 4, 5);
  private final Calendar pastDateDistant = new GregorianCalendar(1812, 4, 5);
  private Set<Contact> fullContactSet;
  private Set<Contact> partContactSet;
  private Set<Contact> tempContactSet;
  private Contact garfield;
  private static final String empty = "";
  private static final String tom = "Tom";
  private static final String tommy = "Tommy";
  private static final String hobbes = "Hobbes";
  private static final String genericNotes = "I wish I had something interesting to say";

  /**
   * Sets up a ContactManagerImpl instance to reduce repetition in tests.
   * Adds three contacts to ContactManager + creates a full and partial contact set
   * for easier access to Contact objects.
   * Retrieves Contact garfield and stores in an instance variable.
   */
  @Before
  public void startUp() {
    conManImp = new ContactManagerImpl();
    conManImp.addNewContact("Garfield", "Won't meet on a Monday.");
    conManImp.addNewContact("Cat in the Hat", "Doesn't follow through on promises.");
    conManImp.addNewContact("Top Cat", "Authoritarian tendencies.");
    fullContactSet = conManImp.getContacts(empty);
    partContactSet = conManImp.getContacts("Cat");
    for (Contact contact : fullContactSet) {
      if (contact.getName().equals("Garfield")) {
        garfield = contact;
      }
    }
  }

  //Contact tests

  @Test (expected = IllegalArgumentException.class)
  public void testAddNewContactEmptyNotes() {
    conManImp.addNewContact(tom, empty);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddNewContactEmptyName() {
    conManImp.addNewContact(empty, genericNotes);
  }

  @Test (expected = NullPointerException.class)
  public void testAddNewContactNullName() {
    conManImp.addNewContact(null, genericNotes);
  }

  @Test (expected = NullPointerException.class)
  public void testAddNewContactNullNotes() {
    conManImp.addNewContact(tom, null);
  }

  @Test
  public void testAddNewContactSingle() {
    conManImp.addNewContact(tom, genericNotes);
    tempContactSet = conManImp.getContacts(empty);
    assertEquals(4, tempContactSet.size());
  }

  @Test
  public void testAddNewContactMulti() {
    conManImp.addNewContact(tom, genericNotes);
    conManImp.addNewContact(tommy, genericNotes);
    tempContactSet = conManImp.getContacts(empty);
    assertEquals(5, tempContactSet.size());
  }

  @Test
  public void testAddNewContactUniqueId() {
    int tomId = conManImp.addNewContact(tom, genericNotes);
    int hobbesId = conManImp.addNewContact(hobbes, genericNotes);
    assertNotEquals(tomId, hobbesId);
  }

  @Test
  public void testGetContactsNamePopulated() {
    tempContactSet = conManImp.getContacts("Cat");
    assertEquals(2, tempContactSet.size());
  }

  @Test
  public void testGetContactsEmptyString() {
    tempContactSet = conManImp.getContacts(empty);
    assertEquals(3, tempContactSet.size());
  }

  @Test (expected = NullPointerException.class)
  public void testGetContactsNullInput() {
    String nullName = null;
    conManImp.getContacts(nullName);
  }

  @Test
  public void testGetContactsNoMatches() {
    tempContactSet = conManImp.getContacts(hobbes);
    assertEquals(0, tempContactSet.size());
  }

  @Test
  public void testGetContactsIdsPopulated() {
    int tomId = conManImp.addNewContact(tom, genericNotes);
    tempContactSet = conManImp.getContacts(tomId);
    for (Contact contact : tempContactSet) {
      assertTrue(contact.getName().equals(tom));
    }

  }

  @Test
  public void testGetContactsFromIdsMulti() {
    conManImp.addNewContact(tom, genericNotes);
    conManImp.addNewContact(tommy, genericNotes);
    tempContactSet = conManImp.getContacts("Tom");
    Iterator<Contact> it = tempContactSet.iterator();
    int firstId = it.next().getId();
    int secondId = it.next().getId();
    Set<Contact> returnedContacts = conManImp.getContacts(firstId, secondId);
    assertEquals(tempContactSet, returnedContacts);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetContactsIdsNoIds() {
    conManImp.getContacts();
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetContactsIdsInvalidIds() {
    conManImp.getContacts(-1, 9);
  }

  //FutureMeeting tests
  // AddFutureMeeting tests

  @Test
  public void testAddFutureMeetingSingle() {
    int id = conManImp.addFutureMeeting(fullContactSet, date);
    assertEquals(date, conManImp.getFutureMeeting(id).getDate());
    assertEquals(fullContactSet, conManImp.getFutureMeeting(id).getContacts());
  }

  @Test
  public void testAddFutureMeetingMulti() {
    int id = conManImp.addFutureMeeting(fullContactSet, date);
    assertEquals(date, conManImp.getFutureMeeting(id).getDate());
    id = conManImp.addFutureMeeting(fullContactSet, futureDateDistant);
    assertEquals(futureDateDistant, conManImp.getFutureMeeting(id).getDate());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddFutureMeetingPastError() {
    conManImp.addFutureMeeting(fullContactSet, pastDate);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddFutureMeetingNonexistentContact() {
    fullContactSet.add(new ContactImpl(4, tom, genericNotes));
    conManImp.addFutureMeeting(fullContactSet, date);
  }

  @Test (expected = NullPointerException.class)
  public void testAddFutureMeetingNullDate() {
    conManImp.addFutureMeeting(fullContactSet, null);
  }

  @Test (expected = NullPointerException.class)
  public void testAddFutureMeetingNullContacts() {
    conManImp.addFutureMeeting(null, date);
  }

  @Test (expected = IllegalStateException.class)
  public void testFutureMeetingInThePast() {
    Calendar nowCal = Calendar.getInstance();
    nowCal.add(Calendar.MILLISECOND, 1);
    int id = conManImp.addFutureMeeting(fullContactSet, nowCal);
    try {
      Thread.sleep(20);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    conManImp.getPastMeeting(id);
  }

  // getFutureMeeting tests

  @Test
  public void testGetFutureMeeting() {
    int meetingId = conManImp.addFutureMeeting(fullContactSet, date);
    assertEquals(date, conManImp.getFutureMeeting(meetingId).getDate());
    assertEquals(fullContactSet, conManImp.getFutureMeeting(meetingId).getContacts());
  }

  @Test
  public void testGetFutureMeetingNoMatchReturnsNull() {
    assertNull(conManImp.getFutureMeeting(250));
  }

  @Test (expected = IllegalStateException.class)
  public void testGetFutureMeetingInPastThrowsError() {
    int id = conManImp.addNewPastMeeting(fullContactSet, pastDate, genericNotes);
    conManImp.getFutureMeeting(id);
  }

  // PastMeeting tests

  // addNewPastMeeting tests

  @Test
  public void testAddNewPastMeetingSingle() {
    assertNotNull(conManImp.addNewPastMeeting(fullContactSet, pastDate, genericNotes));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddNewPastMeetingFutureDateError() {
    conManImp.addNewPastMeeting(fullContactSet, date, genericNotes);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddNewPastMeetingNonexistentContact() {
    fullContactSet.add(new ContactImpl(4, tom, genericNotes));
    conManImp.addNewPastMeeting(fullContactSet, pastDate, genericNotes);
  }

  @Test (expected = NullPointerException.class)
  public void testAddNewPastMeetingNullDate() {
    conManImp.addNewPastMeeting(fullContactSet, null, genericNotes);
  }

  @Test (expected = NullPointerException.class)
  public void testAddNewPastMeetingNullContacts() {
    conManImp.addFutureMeeting(null, date);
  }

  // getPastMeeting tests

  @Test
  public void testGetPastMeeting() {
    int meetingId = conManImp.addNewPastMeeting(fullContactSet, pastDate, genericNotes);
    assertEquals(pastDate, conManImp.getPastMeeting(meetingId).getDate());
    assertEquals(fullContactSet, conManImp.getPastMeeting(meetingId).getContacts());
  }

  @Test
  public void testGetPastMeetingNoMatchReturnsNull() {
    assertNull(conManImp.getPastMeeting(250));
  }

  @Test (expected = IllegalStateException.class)
  public void testGetPastMeetingInFutureThrowsError() {
    int id = conManImp.addFutureMeeting(fullContactSet, date);
    conManImp.getPastMeeting(id);
  }

  // getMeeting tests

  @Test
  public void testGetMeeting() {
    int meetingId = conManImp.addNewPastMeeting(fullContactSet, pastDate, genericNotes);
    assertEquals(pastDate, conManImp.getMeeting(meetingId).getDate());
    assertEquals(fullContactSet, conManImp.getMeeting(meetingId).getContacts());
  }

  @Test
  public void testGetMeetingNoMatch() {
    assertNull(conManImp.getMeeting(534));
  }

  // addMeetingNotes tests
  // The test below can't run successfully after date-based exception handling
  // for addMeetingNotes is in place.

  /* @Test
  public void testAddMeetingNotesFutureMeeting() {
    int id = conManImp.addFutureMeeting(fullContactSet, date);
    PastMeeting returnedMeeting = conManImp.addMeetingNotes(id, "11/10 would meet again");
    PastMeeting changedMeeting = (PastMeeting) conManImp.getMeeting(id);
    assertTrue(returnedMeeting instanceof PastMeetingImpl);
    assertEquals(returnedMeeting.getNotes(), changedMeeting.getNotes());
  } */

  @Test
  public void testAddMeetingNotesPastMeeting() {
    int id = conManImp.addNewPastMeeting(fullContactSet, pastDate, empty);
    PastMeeting returnedMeeting = conManImp.addMeetingNotes(id, genericNotes);
    assertEquals(genericNotes, returnedMeeting.getNotes());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMeetingNotesNonexistentMeeting() {
    conManImp.addMeetingNotes(765, genericNotes);
  }

  @Test(expected = IllegalStateException.class)
  public void testAddMeetingNotesFutureDateMeeting() {
    int id = conManImp.addFutureMeeting(fullContactSet, date);
    conManImp.addMeetingNotes(id, genericNotes);
  }

  @Test(expected = NullPointerException.class)
  public void testAddMeetingNotesNullNotes() {
    int id = conManImp.addFutureMeeting(fullContactSet, date);
    conManImp.addMeetingNotes(id, null);
  }

  // getFutureMeetingList tests
  @Test
  public void testGetFutureMeetingList() {
    conManImp.addFutureMeeting(fullContactSet, futureDateDistant);
    conManImp.addFutureMeeting(partContactSet, date);
    assertEquals(1, conManImp.getFutureMeetingList(garfield).size());
  }

  @Test
  public void testGetFutureMeetingListNoMeetings() {
    conManImp.addFutureMeeting(partContactSet, futureDateDistant);
    int id = conManImp.addNewContact(hobbes, genericNotes);
    ArrayList<Contact> hobbesList = new ArrayList<>(conManImp.getContacts(id));
    assertEquals(0, conManImp.getFutureMeetingList(hobbesList.get(0)).size());
  }

  @Test
  public void testGetFutureMeetingListDateSorting() {
    conManImp.addFutureMeeting(fullContactSet, date);
    conManImp.addFutureMeeting(fullContactSet, futureDateDistant);
    assertEquals(date, conManImp.getFutureMeetingList(garfield).get(0).getDate());
  }

  @Test
  public void testGetFutureMeetingListNoPastMeetings() {
    conManImp.addFutureMeeting(fullContactSet, date);
    conManImp.addFutureMeeting(fullContactSet, futureDateDistant);
    conManImp.addNewPastMeeting(fullContactSet, pastDate, genericNotes);
    assertEquals(2, conManImp.getFutureMeetingList(garfield).size());
    assertNotEquals(pastDate, conManImp.getFutureMeetingList(garfield).get(0).getDate());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFutureMeetingListNonexistentContact() {
    conManImp.getFutureMeetingList(new ContactImpl(2, tom, genericNotes));
  }

  @Test(expected = NullPointerException.class)
  public void testGetFutureMeetingListNullContact() {
    conManImp.getFutureMeetingList(null);
  }

  // getPastMeetingList tests

  @Test
  public void testGetPastMeetingListFor() {
    conManImp.addNewPastMeeting(partContactSet, pastDate, genericNotes);
    conManImp.addNewPastMeeting(fullContactSet, pastDateDistant, genericNotes);
    conManImp.addNewPastMeeting(fullContactSet, pastDate, genericNotes);
    assertEquals(2, conManImp.getPastMeetingListFor(garfield).size());
  }

  @Test
  public void testGetPastMeetingListForNoMeetings() {
    conManImp.addNewPastMeeting(partContactSet, pastDateDistant, genericNotes);
    assertEquals(0, conManImp.getPastMeetingListFor(garfield).size());
  }

  @Test
  public void testGetPastMeetingListForDateSorting() {
    conManImp.addNewPastMeeting(fullContactSet, pastDateDistant, genericNotes);
    conManImp.addNewPastMeeting(fullContactSet, pastDate, genericNotes);
    assertEquals(pastDateDistant, conManImp.getPastMeetingListFor(garfield).get(0).getDate());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPastMeetingListForNonexistentContact() {
    conManImp.getPastMeetingListFor(new ContactImpl(2, tom, genericNotes));
  }

  @Test(expected = NullPointerException.class)
  public void testGetPastMeetingListForNullContact() {
    conManImp.getPastMeetingListFor(null);
  }

  // getMeetingListOn tests

  @Test
  public void testGetMeetingListOn() {
    conManImp.addFutureMeeting(fullContactSet, date);
    conManImp.addFutureMeeting(fullContactSet, date);
    conManImp.addNewPastMeeting(fullContactSet, pastDate, genericNotes);
    assertEquals(2, conManImp.getMeetingListOn(date).size());
  }

  @Test
  public void testGetMeetingListOnDateSortingByTime() {
    Calendar dateLaterHour = new GregorianCalendar(2017, 4, 5, 14, 10);
    Calendar dateEarlierHour = new GregorianCalendar(2017, 4, 5, 9, 10);
    conManImp.addFutureMeeting(fullContactSet, dateLaterHour);
    conManImp.addFutureMeeting(fullContactSet, date);
    conManImp.addFutureMeeting(fullContactSet, dateEarlierHour);
    assertEquals(dateEarlierHour, conManImp.getMeetingListOn(date).get(0).getDate());
    assertEquals(date, conManImp.getMeetingListOn(date).get(1).getDate());
    assertEquals(dateLaterHour, conManImp.getMeetingListOn(date).get(2).getDate());
  }

  @Test
  public void testGetMeetingListOnNoMeetings() {
    conManImp.addNewPastMeeting(partContactSet, pastDateDistant, genericNotes);
    assertEquals(0, conManImp.getMeetingListOn(date).size());
  }

  @Test(expected = NullPointerException.class)
  public void testGetMeetingListOnNullDate() {
    conManImp.getMeetingListOn(null);
  }

  // flush() tests

  @Test
  public void testFlushFileCreation() {
    conManImp.flush();
    File file = new File("contacts.ser");
    assertTrue(file.exists());
    assertTrue(file.delete());
  }

  @Test
  public void testFlushContactList() {
    conManImp.addNewPastMeeting(partContactSet, pastDateDistant, genericNotes);
    conManImp.flush();
    assertEquals(new ContactManagerImpl().getContacts("Cat").size(), partContactSet.size());
  }

  @Test
  public void testFlushContactGetById() {
    int id = conManImp.addNewContact("Salem", "Magical.");
    conManImp.flush();
    Set<Contact> returnedContactSet = new ContactManagerImpl().getContacts(id);
    for (Contact contact : returnedContactSet) {
      assertTrue(contact.getName().equals("Salem"));
      assertTrue(contact.getNotes().equals("Magical."));
    }
  }

  @Test
  public void testFlushContactListContiguousIds() {
    conManImp.flush();
    ContactManager reloadedConManImp = new ContactManagerImpl();
    assertEquals(4, reloadedConManImp.addNewContact("Salem", "Magical."));
  }

  @Test
  public void testFlushContactListMultiFlush() {
    conManImp.addNewPastMeeting(fullContactSet, pastDateDistant, genericNotes);
    conManImp.flush();
    assertEquals(3, new ContactManagerImpl().getContacts(empty).size());
    conManImp.addNewContact("Salem", "Magical.");
    conManImp.flush();
    assertEquals(4, new ContactManagerImpl().getContacts(empty).size());
  }

  @Test
  public void testFlushMeetingListMultiFlush() {
    conManImp.addFutureMeeting(partContactSet, futureDateDistant);
    conManImp.addFutureMeeting(fullContactSet, futureDateDistant);
    conManImp.flush();
    Calendar futureDateDistantEve = new GregorianCalendar(2022, 4, 5, 18, 5);
    conManImp.addFutureMeeting(partContactSet, futureDateDistantEve);
    conManImp.flush();
    assertEquals(3, new ContactManagerImpl().getMeetingListOn(futureDateDistant).size());
  }

  /**
   * Deletes file created by serialization, if present.
   */
  @After
  public void cleanUp() {
    File file = new File("contacts.ser");
    if (file.exists()) {
      assertTrue(file.delete());
    }
  }
}

