package test.java.test;

import main.java.impl.*;
import main.java.spec.PastMeeting;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.java.spec.Contact;
import main.java.spec.Meeting;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by svince04 on 18/02/2017.
 * PiJ Coursework 3
 */
public class ContactManagerImplTest {
   private ContactManagerImpl conManImp;
   private final Calendar date = new GregorianCalendar(2017, 4, 5, 12, 10);
   private final Calendar futureDateDistant = new GregorianCalendar(2022, 4, 5);
   private final Calendar pastDate = new GregorianCalendar(2012, 4, 5);
   private final Calendar pastDateDistant = new GregorianCalendar(1812, 4, 5);
   private Set<Contact> fullContactSet;
   private Set<Contact> partContactSet;
   private Set<Contact> tempContactSet;
   private List<Contact> fullContactList;
   private final static String tom = "Tom";
   private final static String hobbes = "Hobbes";
   private final static String nameLassie = "Lassie";
   private final static String notesLassie = "Odd dog out";
   private final static String genericNotes = "I wish I had something interesting to say";


    @Before
   public void startUp() {
       conManImp = new ContactManagerImpl();
       conManImp.addNewContact("Garfield", "Won't meet on a Monday.");
       conManImp.addNewContact("Cat in the Hat", "Doesn't follow through on promises.");
       conManImp.addNewContact("Top Cat", "Authoritarian tendencies.");
       fullContactSet = conManImp.getContacts("");
       partContactSet = conManImp.getContacts("Cat");
       fullContactList = new ArrayList<>(fullContactSet);
   }

   //Contact tests

   @Test (expected = IllegalArgumentException.class)
   public void testAddNewContactEmptyNotes() {
       conManImp.addNewContact("Felix", "");
   }

    @Test (expected = IllegalArgumentException.class)
    public void testAddNewContactEmptyName() {
        conManImp.addNewContact("", genericNotes);
    }

    @Test (expected = NullPointerException.class)
    public void testAddNewContactNullName() {
        conManImp.addNewContact(null, genericNotes);
    }

    @Test (expected = NullPointerException.class)
    public void testAddNewContactNullNotes() {
        conManImp.addNewContact("Felix", null);
    }

    @Test
    public void testAddNewContactSingle() {
        conManImp.addNewContact(tom, genericNotes);
        tempContactSet = conManImp.getContacts("");
        assertSame(tempContactSet.size(),4);
    }

    @Test
    public void testAddNewContactMulti() {
        conManImp.addNewContact(tom, genericNotes);
        conManImp.addNewContact("Sylvester", genericNotes);
        tempContactSet = conManImp.getContacts("");
        assertSame(tempContactSet.size(), 5);
    }

    @Test
    public void testAddNewContactUniqueId() {
        int tomId = conManImp.addNewContact(tom, genericNotes);
        int sylvesterId = conManImp.addNewContact("Sylvester", genericNotes);
        assertNotSame(tomId, sylvesterId);
    }

    @Test
    public void testGetContactsNamePopulated() {
        tempContactSet = conManImp.getContacts("Cat");
        assertEquals(2, tempContactSet.size());
    }

    @Test
    public void testGetContactsEmptyString() {
        tempContactSet = conManImp.getContacts("");
        assertEquals(3, tempContactSet.size());
    }

    @Test (expected = NullPointerException.class)
    public void testGetContactsNullInput() {
        String nullName = null;
        conManImp.getContacts(nullName);
    }

    @Test
    public void testGetContactsNoMatches() {
        tempContactSet = conManImp.getContacts("Fritz");
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

    //todo - add test for multiple ids

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
       assertTrue(conManImp.getFutureMeeting(id).getDate().equals(date));
       assertTrue(conManImp.getFutureMeeting(id).getContacts().equals(fullContactSet));
    }

    @Test
    public void testAddFutureMeetingMulti() {
        int id = conManImp.addFutureMeeting(fullContactSet, date);
        assertTrue(conManImp.getFutureMeeting(id).getDate().equals(date));
        id = conManImp.addFutureMeeting(fullContactSet, date);
        assertTrue(conManImp.getFutureMeeting(id).getDate().equals(date));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddFutureMeetingPastError() {
        conManImp.addFutureMeeting(fullContactSet, pastDate);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddFutureMeetingNonexistentContact() {
        Contact lassie = new ContactImpl(4, nameLassie, notesLassie);
        fullContactSet.add(lassie);
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

    // getFutureMeeting tests

    @Test
    public void testGetFutureMeeting() {
        int meetingId = conManImp.addFutureMeeting(fullContactSet, date);
        Meeting returnedMeeting = conManImp.getFutureMeeting(meetingId);
        assertTrue(returnedMeeting.getDate().equals(date));
        assertTrue(returnedMeeting.getContacts().equals(fullContactSet));
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
        Contact lassie = new ContactImpl(4, nameLassie, notesLassie);
        fullContactSet.add(lassie);
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
        Meeting returnedMeeting = conManImp.getPastMeeting(meetingId);
        assertTrue(returnedMeeting.getDate().equals(pastDate));
        assertTrue(returnedMeeting.getContacts().equals(fullContactSet));
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
        Meeting returnedMeeting = conManImp.getMeeting(meetingId);
        assertTrue(returnedMeeting.getDate().equals(pastDate));
        assertTrue(returnedMeeting.getContacts().equals(fullContactSet));
    }

    @Test
    public void testGetMeetingNoMatch() {
        assertNull(conManImp.getMeeting(534));
    }

    // addMeetingNotes tests

    // This test can't run successfully after date-based exception handling for addMeetingNotes is in place.
//    @Test
//    public void testAddMeetingNotesFutureMeeting() {
//        int id = conManImp.addFutureMeeting(fullContactSet, date);
//        PastMeeting returnedMeeting = conManImp.addMeetingNotes(id, "11/10 would meet again");
//        PastMeeting changedMeeting = (PastMeeting) conManImp.getMeeting(id);
//        assertTrue(returnedMeeting instanceof PastMeetingImpl);
//        assertEquals(returnedMeeting.getNotes(), changedMeeting.getNotes());
//    }

    @Test
    public void testAddMeetingNotesPastMeeting() {
        int id = conManImp.addNewPastMeeting(fullContactSet, pastDate, "");
        PastMeeting returnedMeeting = conManImp.addMeetingNotes(id, "11/10 would meet again.");
        assertEquals("11/10 would meet again.", returnedMeeting.getNotes());
        assertTrue(returnedMeeting instanceof PastMeetingImpl);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddMeetingNotesNonexistentMeeting() {
        conManImp.addMeetingNotes(765, genericNotes);
    }

    @Test (expected = IllegalStateException.class)
    public void testAddMeetingNotesFutureDateMeeting() {
        int id = conManImp.addFutureMeeting(fullContactSet, date);
        conManImp.addMeetingNotes(id, genericNotes);
    }

    @Test (expected = NullPointerException.class)
    public void testAddMeetingNotesNullNotes() {
        int id = conManImp.addFutureMeeting(fullContactSet, date);
        conManImp.addMeetingNotes(id, null);
    }

    // getFutureMeetingList tests

    @Test
    public void testGetFutureMeetingList() {
        conManImp.addFutureMeeting(fullContactSet, futureDateDistant);
        conManImp.addFutureMeeting(fullContactSet, date);
        Contact garfield = fullContactList.get(0);
        List<Meeting> resultList = conManImp.getFutureMeetingList(garfield);
        assertSame(resultList.size(), 2);
    }

    @Test
    public void testGetFutureMeetingListNoMeetings() {
        conManImp.addFutureMeeting(partContactSet, futureDateDistant);
        int id = conManImp.addNewContact(hobbes, genericNotes);
        Set<Contact> hobbesSet = conManImp.getContacts(id);
        ArrayList<Contact> hobbesList = new ArrayList<>(hobbesSet);
        List<Meeting> resultList = conManImp.getFutureMeetingList(hobbesList.get(0));
        assertSame(resultList.size(), 0);
    }

    @Test
    public void testGetFutureMeetingListDateSorting() {
        conManImp.addFutureMeeting(fullContactSet, futureDateDistant);
        conManImp.addFutureMeeting(fullContactSet, date);
        Contact garfield = fullContactList.get(0);
        List<Meeting> resultList = conManImp.getFutureMeetingList(garfield);
        assertTrue(resultList.get(0).getDate().equals(date));
    }

    @Test
    public void testGetFutureMeetingListNoPastMeetings() {
        conManImp.addFutureMeeting(fullContactSet, date);
        conManImp.addFutureMeeting(fullContactSet, futureDateDistant);
        conManImp.addNewPastMeeting(fullContactSet, pastDate, genericNotes);
        Contact garfield = fullContactList.get(0);
        List<Meeting> resultList = conManImp.getFutureMeetingList(garfield);
        assertSame(resultList.size(), 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetFutureMeetingListNonexistentContact() {
        Contact lassie = new ContactImpl(2, nameLassie, notesLassie);
        conManImp.getFutureMeetingList(lassie);
    }

    @Test (expected = NullPointerException.class)
    public void testGetFutureMeetingListNullContact() {
        conManImp.getFutureMeetingList(null);
    }

    // getPastMeetingList tests

    @Test
    public void testGetPastMeetingListFor() {
        int id = conManImp.addNewContact(hobbes, genericNotes);
        tempContactSet = conManImp.getContacts("");
        conManImp.addNewPastMeeting(tempContactSet, pastDateDistant, genericNotes);
        conManImp.addNewPastMeeting(tempContactSet, pastDate, genericNotes);
        Set<Contact> hobbesSet = conManImp.getContacts(id);
        ArrayList<Contact> hobbesList = new ArrayList<>(hobbesSet);
        List<PastMeeting> resultList = conManImp.getPastMeetingListFor(hobbesList.get(0));
        assertSame(resultList.size(), 2);
    }

    @Test
    public void testGetPastMeetingListForNoMeetings() {
        conManImp.addNewPastMeeting(partContactSet, pastDateDistant, genericNotes);
        int id = conManImp.addNewContact(hobbes, genericNotes);
        Set<Contact> hobbesSet = conManImp.getContacts(id);
        ArrayList<Contact> hobbesList = new ArrayList<>(hobbesSet);
        List<PastMeeting> resultList = conManImp.getPastMeetingListFor(hobbesList.get(0));
        assertSame(resultList.size(), 0);
    }

    @Test
    public void testGetPastMeetingListForDateSorting() {
        int id = conManImp.addNewContact(hobbes, genericNotes);
        tempContactSet = conManImp.getContacts("");
        conManImp.addNewPastMeeting(tempContactSet, pastDateDistant, genericNotes);
        conManImp.addNewPastMeeting(tempContactSet, pastDate, genericNotes);
        Set<Contact> hobbesSet = conManImp.getContacts(id);
        ArrayList<Contact> hobbesList = new ArrayList<>(hobbesSet);
        List<PastMeeting> resultList = conManImp.getPastMeetingListFor(hobbesList.get(0));
        assertTrue(resultList.get(0).getDate().equals(pastDateDistant));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetPastMeetingListForNonexistentContact() {
        Contact lassie = new ContactImpl(2, nameLassie, notesLassie);
        conManImp.getPastMeetingListFor(lassie);
    }

    @Test (expected = NullPointerException.class)
    public void testGetPastMeetingListForNullContact() {
        conManImp.getPastMeetingListFor(null);
    }

    // getMeetingListOn tests

    @Test
    public void testGetMeetingListOn() {
        conManImp.addFutureMeeting(fullContactSet, date);
        conManImp.addFutureMeeting(fullContactSet, date);
        conManImp.addNewPastMeeting(fullContactSet, pastDate, genericNotes);
        List<Meeting> resultList = conManImp.getMeetingListOn(date);
        assertSame(resultList.size(), 2);
    }

    @Test
    public void testGetMeetingListOnDateSorting() {
        Calendar dateLaterHour = new GregorianCalendar(2017, 4, 5, 14, 10);
        Calendar dateEarlierHour = new GregorianCalendar(2017, 4, 5, 9, 10);
        conManImp.addFutureMeeting(fullContactSet, dateLaterHour);
        conManImp.addFutureMeeting(fullContactSet, date);
        conManImp.addFutureMeeting(fullContactSet, dateEarlierHour);
        List<Meeting> resultList = conManImp.getMeetingListOn(date);
        assertTrue(resultList.get(0).getDate().equals(dateEarlierHour));
        assertTrue(resultList.get(1).getDate().equals(date));
        assertTrue(resultList.get(2).getDate().equals(dateLaterHour));
    }

    @Test
    public void testGetMeetingListOnNoMeetings() {
        conManImp.addNewPastMeeting(partContactSet, pastDateDistant, genericNotes);
        List<Meeting> resultList = conManImp.getMeetingListOn(date);
        assertSame(resultList.size(), 0);
    }

    @Test (expected = NullPointerException.class)
    public void testGetMeetingListOnNullDate() {
        conManImp.getMeetingListOn(null);
    }

    @After
    public void tearDown() {
       conManImp = null;
       tempContactSet = null;
       partContactSet = null;
       fullContactSet = null;
       fullContactList.clear();

    }
}