package test;

import impl.ContactManagerImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
        assertEquals(3, conId);
    }

    @Test
    public void testAddNewContactUniqueId() {
        int joeyId = conManImp.addNewContact("Joey", "A beautiful idiot. ");
        int kangaId = conManImp.addNewContact("Kanga", "A bigger version of Joey. ");
        assertFalse(joeyId == kangaId);
    }


}