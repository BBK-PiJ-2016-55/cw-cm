package impl;

import java.io.Serializable;
import java.util.Objects;
import spec.Contact;

/**
 * Implementation of {@link Contact}.
 * @author svince04
 */
public class ContactImpl implements Contact, Serializable {
  private int id;
  private String name;
  private String notes;

  /**
   * Brief constructor for ContactImpl, which constructs a new ContactImpl
   * object with an id and a contact name. The Notes field
   * of the instance will be initialised to the empty string.
   * @param id the id of the contact. Must be unique and greater than 0.
   * @param name string name of the contact.
   * @throws IllegalArgumentException if id is 0 or below.
   * @throws NullPointerException if name is null.
   */
  public ContactImpl(int id, String name) {
        this(id, name, "");
  }

  /**
   * Full constructor for ContactImpl, which constructs a new ContactImpl
   * object with an id, a contact name and some notes.
   * @param id the id of the contact. Must be unique and greater than 0.
   * @param name string name of the contact.
   * @param notes string notes relating to the contact.
   * @throws IllegalArgumentException if ID is 0 or below.
   * @throws NullPointerException if name or notes are null.
   */
  public ContactImpl(int id, String name, String notes) {
    Objects.requireNonNull(name, "Name cannot be null");
    Objects.requireNonNull(notes, "Notes cannot be null");
    if (id <= 0) {
      throw new IllegalArgumentException("ID cannot be 0 or less");
    }
    this.id = id;
    this.name = name;
    this.notes = notes;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public int getId() {
    return id;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public String getNotes() {
    return notes;
  }

  /**
   * {@inheritDoc}.
   * If there are already notes attached to this contact, the two sets with
   * be delimited by a space.
   */
  @Override
  public void addNotes(String notes) {
    // If there are existing notes, delimit with a space.
    if (!this.notes.equals("")) {
      this.notes += " ";
    }
    this.notes += notes;
  }
}
