package impl;

import java.io.Serializable;
import java.util.Objects;
import spec.Contact;

/**
 * PiJ Coursework 3.
 * Implementation of {@see Contact}.
 * @author svince04
 */
public class ContactImpl implements Contact, Serializable {

  private int id;
  private String name;
  private String notes;

  /**
   * @param id the id of the contact.
   * @param name string name of the contact
   * @throws IllegalArgumentException if id is 0 or below.
   * @throws NullPointerException if name is null.
   */
  public ContactImpl(int id, String name) {
        this(id, name, "");
  }

  /**
   * @param id the id of the contact.
   * @param name string name of the contact.
   * @param notes notes relating to the contact
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
