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
   private Calendar pastDate = new GregorianCalendar(2016, 4, 5);


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
        conManImp.addNewContact("Tom", "Makes grand plans that never work. ");
        Set<Contact> idSet = conManImp.getContacts(1, 4);
        String nameString = "";
        for (Contact contact : idSet) {
            nameString = nameString + contact.getName();
        }
        assertTrue(nameString.equals("TomGarfield") || nameString.equals("GarfieldTom"));
    }

    @Test
    public void testGetContactsIdsPopulatedSingleId() {
        Set<Contact> idSet = conManImp.getContacts(1);
        String nameString = "";
        for (Contact contact : idSet) {
            nameString = nameString + contact.getName();
        }
        assertTrue(nameString.equals("Garfield"));
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

    @Test
    public void testAddFutureMeetingSingle() {
       Set<Contact> fullSet = conManImp.getContacts("");
       int id = conManImp.addFutureMeeting(fullSet, date);
       assertTrue(id == 1);
    }

    @Test
    public void testAddFutureMeetingMulti() {
        Set<Contact> fullSet = conManImp.getContacts("");
        int id = conManImp.addFutureMeeting(fullSet, date);
        assertTrue(id == 1);
        id = conManImp.addFutureMeeting(fullSet, date);
        assertTrue(id == 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddFutureMeetingPastError() {
        Calendar pastDate = new GregorianCalendar(2017, 2, 4);
        Set<Contact> fullSet = conManImp.getContacts("");
        conManImp.addFutureMeeting(fullSet, pastDate);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddFutureMeetingInvalidContact() {
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

    @Test
    public void testGetFutureMeeting() {
        Set<Contact> fullSet = conManImp.getContacts("");
        Meeting meeting = new FutureMeetingImpl(1, date, fullSet);
        conManImp.addFutureMeeting(fullSet, date);
        Meeting returnedMeeting = conManImp.getFutureMeeting(1);
        assertTrue(meeting.getId() == returnedMeeting.getId());
        assertTrue(meeting.getDate().equals(returnedMeeting.getDate()));
        assertTrue(meeting.getContacts().equals(returnedMeeting.getContacts()));
    }

    @Test (expected = NullPointerException.class)
    public void testGetFutureMeetingNoMatchReturnsNull() {
        assertNull(conManImp.getFutureMeeting(250));
    }

//    @Test (expected = IllegalStateException.class)
//    public void testGetFutureMeetingInPastThrowsError() {
//        Set<Contact> fullSet = conManImp.getContacts("");
//        conManImp.addNewPastMeeting(fullSet, pastDate, "Past meeting notes.");
//        assertNull(conManImp.getFutureMeeting(5));
//    }

    @After
    public void tearDown() {
       conManImp.resetCounter();
       conManImp = null;
    }
}