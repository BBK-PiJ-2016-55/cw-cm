package main.java.impl;

import java.io.Serializable;
import java.util.Objects;
import main.java.spec.Contact;

/**
 * Created by svince04 on 17/02/2017.
 * PiJ Coursework 3.
 */
public class ContactImpl implements Contact, Serializable {

  private int id;
  private String name;
  private String notes;

  /**
   * @param id the id of the contact.
   * @param name the name of the contact.
   * @throws IllegalArgumentException if id is 0 or below.
   * @throws NullPointerException if name is null.
   */
  public ContactImpl(int id, String name) throws IllegalArgumentException, NullPointerException {
        this(id, name, "");
  }

  /**
   * @param id the id of the contact.
   * @param name the name of the contact.
   * @param notes notes relating to the contact.
   * @throws IllegalArgumentException if ID is 0 or below.
   * @throws NullPointerException if name or notes are null.
   */
  public ContactImpl(int id, String name, String notes)
          throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(name, "Name cannot be null");
    Objects.requireNonNull(notes, "Notes cannot be null");
    if (id <= 0) {
      throw new IllegalArgumentException("ID cannot be 0 or less");
    }
    this.id = id;
    this.name = name;
    this.notes = notes;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getNotes() {
    return notes;
  }

  @Override
  public void addNotes(String notes) {
    // todo - add delimiter.
    this.notes += notes;
  }
}
