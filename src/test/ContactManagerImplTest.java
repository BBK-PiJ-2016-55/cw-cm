package test;

import impl.ContactManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import spec.Contact;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by svince04 on 18/02/2017 for cw-cm.
 */
public class ContactManagerImplTest {
   private ContactManagerImpl conManImp;

   @Before
   public void startUp() {
       conManImp = new ContactManagerImpl();
   }

   @Test (expected = IllegalArgumentException.class)
   public void testAddNewContactEmptyName() {
       int conId = conManImp.addNewContact("Joey", "");
   }

    @Test (expected = IllegalArgumentException.class)
    public void testAddNewContactEmptyNotes() {
        int conId = conManImp.addNewContact("", "A beautiful idiot");
    }

    @Test (expected = NullPointerException.class)
    public void testAddNewContactNullName() {
        int conId = conManImp.addNewContact(null, "A beautiful idiot");
    }

    @Test (expected = NullPointerException.class)
    public void testAddNewContactNullNotes() {
        int conId = conManImp.addNewContact("Joey", null);
    }

   @Test
   public void testAddNewContact() {
        int conId = conManImp.addNewContact("Joey", "A beautiful idiot. ");
        assertEquals(1, conId);
    }

    @Test
    public void testAddNewContactToContactArrayList() {
        int conId = conManImp.addNewContact("Beth", "Beth, Beth, it rhymes with death. ");
        assertTrue(conManImp.getContactListSize() == 1);
        assertEquals(2, conManImp.getIdCount());
    }

    @Test
    public void testAddNewContactToContactArrayListMulti() {
        conManImp.addNewContact("Beth", "Beth, Beth, it rhymes with death. ");
        assertTrue(conManImp.getContactListSize() == 1);
        assertEquals(2, conManImp.getIdCount());
        conManImp.addNewContact("Joey", "A beautiful idiot. ");
        assertTrue(conManImp.getContactListSize() == 2);
        assertEquals(3, conManImp.getIdCount());

    }

    @Test
    public void testAddNewContactUniqueId() {
        int joeyId = conManImp.addNewContact("Joey", "A beautiful idiot. ");
        int kangaId = conManImp.addNewContact("Kanga", "A bigger version of Joey. ");
        assertFalse(joeyId == kangaId);
    }

    @Test
    public void testGetContactsNamePopulated() {
        conManImp.addNewContact("Beth", "Beth, Beth, it rhymes with death. ");
        conManImp.addNewContact("Joey", "A beautiful idiot. ");
        conManImp.addNewContact("Bethan", "A bigger version of Beth. ");
        Set<Contact> bethSet = conManImp.getContacts("Beth");
        assertEquals(2, bethSet.size());
    }

    @Test
    public void testGetContactsEmptyString() {
        conManImp.addNewContact("Beth", "Beth, Beth, it rhymes with death. ");
        conManImp.addNewContact("Bethan", "A bigger version of Beth. ");
        conManImp.addNewContact("Joey", "A beautiful idiot. ");
        Set<Contact> fullSet = conManImp.getContacts("");
        assertEquals(3, fullSet.size());
    }

    @Test (expected = NullPointerException.class)
    public void testGetContactsNullInput() {
        conManImp.addNewContact("Beth", "Beth, Beth, it rhymes with death. ");
        String nullName = null;
        Set<Contact> fullSet = conManImp.getContacts(nullName);
    }

    @Test
    public void testGetContactsNoMatches() {
        conManImp.addNewContact("Beth", "Beth, Beth, it rhymes with death. ");
        conManImp.addNewContact("Bethan", "A bigger version of Beth. ");
        Set<Contact> fullSet = conManImp.getContacts("Jo");
        assertEquals(0, fullSet.size());
    }

    @Test
    public void testGetContactsIdsPopulated() {
        conManImp.addNewContact("Beth", "Beth, Beth, it rhymes with death. ");
        conManImp.addNewContact("Joey", "A beautiful idiot. ");
        conManImp.addNewContact("Bethan", "A bigger version of Beth. ");
        Set<Contact> idSet = conManImp.getContacts(1, 3);
        String nameString = "";
        for (Contact contact : idSet) {
            nameString = nameString + contact.getName();
        }
        assertTrue(nameString.equals("BethBethan") || nameString.equals("BethanBeth"));
    }

    @Test
    public void testGetContactsIdsPopulatedSingleId() {
        conManImp.addNewContact("Beth", "Beth, Beth, it rhymes with death. ");
        Set<Contact> idSet = conManImp.getContacts(1);
        String nameString = "";
        for (Contact contact : idSet) {
            nameString = nameString + contact.getName();
        }
        assertTrue(nameString.equals("Beth"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetContactsIdsNoIds() {
        Set<Contact> idSet = conManImp.getContacts();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetContactsIdsInvalidIds() {
        Set<Contact> idSet = conManImp.getContacts(-1, 9);
    }

}