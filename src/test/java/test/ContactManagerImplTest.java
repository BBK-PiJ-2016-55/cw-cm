package test.java.test;

import main.java.impl.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.java.spec.Contact;
import main.java.spec.Meeting;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by svince04 on 18/02/2017.
 * PiJ Coursework 3
 */
public class ContactManagerImplTest {
   private ContactManagerImpl conManImp;
   private Calendar date = new GregorianCalendar(2017, 4, 5);
   private Calendar pastDate = new GregorianCalendar(2012, 4, 5);


    @Before
   public void startUp() {
       conManImp = new ContactManagerImpl();
       conManImp.addNewContact("Garfield", "Won't meet on a Monday.");
       conManImp.addNewContact("Cat in the Hat", "Doesn't follow through on promises.");
       conManImp.addNewContact("Top Cat", "Authoritarian tendencies.");
   }

   //Contact tests

   @Test (expected = IllegalArgumentException.class)
   public void testAddNewContactEmptyNotes() {
       conManImp.addNewContact("Felix", "");
   }

    @Test (expected = IllegalArgumentException.class)
    public void testAddNewContactEmptyName() {
        conManImp.addNewContact("", "Never speaks.");
    }

    @Test (expected = NullPointerException.class)
    public void testAddNewContactNullName() {
        conManImp.addNewContact(null, "Never speaks.");
    }

    @Test (expected = NullPointerException.class)
    public void testAddNewContactNullNotes() {
        conManImp.addNewContact("Felix", null);
    }

    @Test
    public void testAddNewContactSingle() {
        conManImp.addNewContact("Tom", "Makes grand plans that never work.");
        Set<Contact> testSet = conManImp.getContacts("");
        assertTrue(testSet.size() == 4);
    }

    @Test
    public void testAddNewContactMulti() {
        conManImp.addNewContact("Tom", "Makes grand plans that never work.");
        conManImp.addNewContact("Sylvester", "Difficult to understand.");
        Set<Contact> testSet = conManImp.getContacts("");
        assertTrue(testSet.size() == 5);
    }

    @Test
    public void testAddNewContactUniqueId() {
        int tomId = conManImp.addNewContact("Tom", "Makes grand plans that never work.");
        int sylvesterId = conManImp.addNewContact("Sylvester", "Difficult to understand.");
        assertFalse(tomId == sylvesterId);
    }

    @Test
    public void testGetContactsNamePopulated() {
        Set<Contact> catSet = conManImp.getContacts("Cat");
        assertEquals(2, catSet.size());
    }

    @Test
    public void testGetContactsEmptyString() {
        Set<Contact> fullSet = conManImp.getContacts("");
        assertEquals(3, fullSet.size());
    }

    @Test (expected = NullPointerException.class)
    public void testGetContactsNullInput() {
        String nullName = null;
        conManImp.getContacts(nullName);
    }

    @Test
    public void testGetContactsNoMatches() {
        Set<Contact> fullSet = conManImp.getContacts("Fritz");
        assertEquals(0, fullSet.size());
    }

    @Test
    public void testGetContactsIdsPopulated() {
        int tomId = conManImp.addNewContact("Tom", "Makes grand plans that never work. ");
        Set<Contact> idSet = conManImp.getContacts(tomId);
        for (Contact contact : idSet) {
            assertTrue(contact.getName().equals("Tom"));
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
       Set<Contact> fullSet = conManImp.getContacts("");
       int id = conManImp.addFutureMeeting(fullSet, date);
       assertTrue(conManImp.getFutureMeeting(id).getDate().equals(date));
       assertTrue(conManImp.getFutureMeeting(id).getContacts().equals(fullSet));
    }

    @Test
    public void testAddFutureMeetingMulti() {
        Set<Contact> fullSet = conManImp.getContacts("");
        int id = conManImp.addFutureMeeting(fullSet, date);
        assertTrue(conManImp.getFutureMeeting(id).getDate().equals(date));
        id = conManImp.addFutureMeeting(fullSet, date);
        assertTrue(conManImp.getFutureMeeting(id).getDate().equals(date));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddFutureMeetingPastError() {
        Set<Contact> fullSet = conManImp.getContacts("");
        conManImp.addFutureMeeting(fullSet, pastDate);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddFutureMeetingNonexistentContact() {
        Set<Contact> fullSet = conManImp.getContacts("");
        Contact Lassie = new ContactImpl(4, "Lassie", "Odd one out");
        fullSet.add(Lassie);
        conManImp.addFutureMeeting(fullSet, date);
    }

    @Test (expected = NullPointerException.class)
    public void testAddFutureMeetingNullDate() {
        Set<Contact> fullSet = conManImp.getContacts("");
        conManImp.addFutureMeeting(fullSet, null);
    }

    @Test (expected = NullPointerException.class)
    public void testAddFutureMeetingNullContacts() {
        conManImp.addFutureMeeting(null, date);
    }

    // getFutureMeeting tests

    @Test
    public void testGetFutureMeeting() {
        Set<Contact> fullSet = conManImp.getContacts("");
        int meetingId = conManImp.addFutureMeeting(fullSet, date);
        Meeting returnedMeeting = conManImp.getFutureMeeting(meetingId);
        assertTrue(returnedMeeting.getDate().equals(date));
        assertTrue(returnedMeeting.getContacts().equals(fullSet));
    }

    @Test
    public void testGetFutureMeetingNoMatchReturnsNull() {
        assertNull(conManImp.getFutureMeeting(250));
    }

    @Test (expected = IllegalStateException.class)
    public void testGetFutureMeetingInPastThrowsError() {
        Set<Contact> fullSet = conManImp.getContacts("");
        int id = conManImp.addNewPastMeeting(fullSet, pastDate, "Past meeting notes.");
        conManImp.getFutureMeeting(id);
    }


    // PastMeeting tests
    // addNewPastMeeting tests

    @Test
    public void testAddNewPastMeetingSingle() {
        Set<Contact> fullSet = conManImp.getContacts("");
        assertNotNull(conManImp.addNewPastMeeting(fullSet, pastDate, "Past meeting notes"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddNewPastMeetingFutureDateError() {
        Set<Contact> fullSet = conManImp.getContacts("");
        conManImp.addNewPastMeeting(fullSet, date, "Past meeting notes");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddNewPastMeetingNonexistentContact() {
        Set<Contact> fullSet = conManImp.getContacts("");
        Contact Lassie = new ContactImpl(4, "Lassie", "Odd one out");
        fullSet.add(Lassie);
        conManImp.addNewPastMeeting(fullSet, pastDate, "Past meeting notes");
    }

    @Test (expected = NullPointerException.class)
    public void testAddNewPastMeetingNullDate() {
        Set<Contact> fullSet = conManImp.getContacts("");
        conManImp.addNewPastMeeting(fullSet, null, "Past meeting notes");
    }

    @Test (expected = NullPointerException.class)
    public void testAddNewPastMeetingNullContacts() {
        conManImp.addFutureMeeting(null, date);
    }

    // getPastMeeting tests

    @Test
    public void testGetPastMeeting() {
        Set<Contact> fullSet = conManImp.getContacts("");
        int meetingId = conManImp.addNewPastMeeting(fullSet, pastDate, "Past meeting notes");
        Meeting returnedMeeting = conManImp.getPastMeeting(meetingId);
        assertTrue(returnedMeeting.getDate().equals(pastDate));
        assertTrue(returnedMeeting.getContacts().equals(fullSet));
    }

    @Test
    public void testGetPastMeetingNoMatchReturnsNull() {
        assertNull(conManImp.getPastMeeting(250));
    }

    @After
    public void tearDown() {
       //conManImp.resetCounter();
       conManImp = null;
    }
}