package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import impl.ContactImpl;
import org.junit.Test;
import spec.Contact;

/**
 * PiJ Coursework 3.
 * Unit tests for {@see ContactImpl}.
 * @author svince04
 */
public class ContactImplTest {
  private final Contact snoopy = new ContactImpl(3, "Snoopy", "Deluded fantasist.");
  private final Contact odie = new ContactImpl(67, "Odie");
  private static final String lassieName = "Lassie";

  @Test
  public void testFullConstructorGetId() {
    assertEquals(3, snoopy.getId());
  }

  @Test
  public void testBriefConstructorGetId() {
    assertEquals(67, odie.getId());
  }

  @Test
  public void testFullConstructorGetName() {
    assertEquals("Snoopy", snoopy.getName());
  }

  @Test
  public void testBriefConstructorGetName() {
    assertEquals("Odie", odie.getName());
  }

  @Test
  public void testGetNotesPopulated() {
    assertEquals("Deluded fantasist.", snoopy.getNotes());
  }

  @Test
  public void testGetNotesUnpopulated() {
    assertEquals("", odie.getNotes());
  }

  @Test
  public void testSetNotesMultiple() {
    snoopy.addNotes("Owns property.");
    snoopy.addNotes("Large network.");
    assertTrue(snoopy.getNotes().contains("Deluded fantasist."));
    assertTrue(snoopy.getNotes().contains("Owns property."));
    assertTrue(snoopy.getNotes().contains("Large network."));
  }

  @Test
  public void testSetNotesBriefConstructor() {
    odie.addNotes("Harmless.");
    assertEquals("Harmless.", odie.getNotes());
    odie.addNotes("Mostly.");
    assertEquals("Harmless. Mostly.", odie.getNotes());
  }


  @Test (expected = IllegalArgumentException.class)
  public void testFullConstructorZeroNegativeId() {
    new ContactImpl(-7, lassieName, "Makes my teeth hurt.");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBriefConstructorNegativeId() {
    new ContactImpl(-7, lassieName);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBriefConstructorZeroId() {
    new ContactImpl(0, lassieName);
  }

  @Test (expected = NullPointerException.class)
  public void testFullConstructorNullName() {
    new ContactImpl(6, null, "Makes my teeth hurt.");
  }

  @Test (expected = NullPointerException.class)
  public void testBriefConstructorNullName() {
    new ContactImpl(6, null, "Makes my teeth hurt.");
  }

  @Test (expected = NullPointerException.class)
  public void testFullConstructorNullNotes() {
    new ContactImpl(6, lassieName, null);
  }

}