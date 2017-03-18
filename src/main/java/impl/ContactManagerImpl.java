package main.java.impl;
// todo - make package names shorter!
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import main.java.spec.Contact;
import main.java.spec.ContactManager;
import main.java.spec.FutureMeeting;
import main.java.spec.Meeting;
import main.java.spec.PastMeeting;

/**
 * PiJ coursework 3.
 * Implementation of {@see ContactManager}.
 * @author svince04
 */
public class ContactManagerImpl implements ContactManager, Serializable {
  private Map<Integer, Contact> contactMap = new HashMap<>();
  private Map<Integer, Meeting> meetingMap = new HashMap<>();
  private int contactIdCounter = 1;
  private int meetingIdCounter = 1;

  /**
   * //todo - put proper doc here
   * Constructor.
   */
  @SuppressWarnings("unchecked")
  public ContactManagerImpl() {
    if (new File("contacts.ser").exists()) {
      try (ObjectInputStream on = new ObjectInputStream(new FileInputStream("contacts.ser"))) {
        contactMap = (Map<Integer, Contact>) (on.readObject());
        meetingMap = (Map<Integer, Meeting>) (on.readObject());
        contactIdCounter += contactMap.size();
        meetingIdCounter += meetingMap.size();
      } catch (Exception e) {
        System.out.println("Problem serializing: " + e);
      }
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public int addFutureMeeting(Set<Contact> contacts, Calendar date)
          throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(contacts, "Contacts cannot be null");
    Objects.requireNonNull(date, "Date cannot be null");
    // Checks that future meeting is not in past
    if (!dateInFuture(date)) {
      throw new IllegalArgumentException("FutureMeeting cannot be in the past.");
    }
    // Check that all contacts are known/existent
    checkContactsExist(contacts);
    Meeting newMeeting = new FutureMeetingImpl(meetingIdCounter, date, contacts);
    meetingMap.put(meetingIdCounter, newMeeting);
    // Iterate meetingCounter in prep for next new Meeting
    meetingIdCounter++;
    return newMeeting.getId();
  }

  /**
   * Checks whether each contact in set exists and throws exception if not.
   *
   * @param contacts to be validated
   */
  private void checkContactsExist(Set<Contact> contacts) throws IllegalArgumentException {
    for (Contact contact : contacts) {
      if (!contactMap.containsValue(contact)) {
        throw new IllegalArgumentException("Invalid Contact entered.");
      }
    }
  }

  /**
   * Checks date passed in against current time.
   *
   * @param date to be checked against current date
   * @return true if date in future, false otherwise
   */
  private boolean dateInFuture(Calendar date) {
    return date.compareTo(Calendar.getInstance()) == 1;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public PastMeeting getPastMeeting(int id) throws IllegalStateException {
    if (!meetingMap.containsKey(id)) {
      return null;
    }
    if (dateInFuture(meetingMap.get(id).getDate())) {
      throw new IllegalStateException("Meeting is not in the past.");
    }
    try {
      return (PastMeeting) meetingMap.get(id);
    } catch (ClassCastException exception) {
      throw new IllegalStateException("Meeting is not a past meeting.");
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public FutureMeeting getFutureMeeting(int id) throws IllegalStateException {
    if (!meetingMap.containsKey(id)) {
      return null;
    }
    if (!dateInFuture(meetingMap.get(id).getDate())) {
      throw new IllegalStateException("Meeting is not in the future.");
    }
    return (FutureMeeting) meetingMap.get(id);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Meeting getMeeting(int id) {
    return meetingMap.get(id);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<Meeting> getFutureMeetingList(Contact contact) throws IllegalArgumentException,
          NullPointerException {
    Objects.requireNonNull(contact, "Contact cannot be null");
    if (!contactMap.containsValue(contact)) {
      throw new IllegalArgumentException("Invalid Contact entered.");
    }
    List<Meeting> resultList = new ArrayList<>();
    // Loop through each meeting in meetingMap
    for (Map.Entry<Integer, Meeting> entry : meetingMap.entrySet()) {
      // Add to resultList only if FutureMeeting and matches Contact
      if (entry.getValue().getContacts().contains(contact)
              && entry.getValue() instanceof FutureMeeting) {
        resultList.add(entry.getValue());
      }
    }
    // Sort by meeting date
    resultList.sort(Comparator.comparing(Meeting::getDate));
    return resultList;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<Meeting> getMeetingListOn(Calendar date) throws NullPointerException {
    Objects.requireNonNull(date, "Date cannot be null");
    List<Meeting> resultList = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
    // Loop through each meeting in meetingMap
    for (Map.Entry<Integer, Meeting> entry : meetingMap.entrySet()) {
      // Add to resultList if date matches
      if (dateFormat.format(date.getTime()).equals(dateFormat.format(entry.getValue()
              .getDate().getTime()))) {
        resultList.add(entry.getValue());
      }
    }
    resultList.sort(Comparator.comparing(meeting -> meeting.getDate().getTime()));
    return resultList;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<PastMeeting> getPastMeetingListFor(Contact contact) throws
          NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(contact, "Contact cannot be null");
    if (!contactMap.containsValue(contact)) {
      throw new IllegalArgumentException("Invalid Contact entered.");
    }
    List<PastMeeting> resultList = new ArrayList<>();
    // Loop through each meeting in meetingMap
    for (Map.Entry<Integer, Meeting> entry : meetingMap.entrySet()) {
      // Add to resultList only if PastMeeting and matches Contact
      if (entry.getValue().getContacts().contains(contact)
              && entry.getValue() instanceof PastMeeting) {
        resultList.add((PastMeeting) entry.getValue());
      }
    }
    resultList.sort(Comparator.comparing(Meeting::getDate));
    return resultList;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) throws
          IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(contacts, "Contacts cannot be null");
    Objects.requireNonNull(date, "Date cannot be null");
    // Check that past meeting is in the past
    if (dateInFuture(date)) {
      throw new IllegalArgumentException("Meeting is not in the past.");
    }
    // Check that all contacts are known/existent
    checkContactsExist(contacts);
    Meeting newPastMeeting = new PastMeetingImpl(meetingIdCounter, date, contacts, text);
    meetingMap.put(meetingIdCounter, newPastMeeting);
    // Iterate meetingCounter in prep for next new Meeting
    meetingIdCounter++;
    return newPastMeeting.getId();
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public PastMeeting addMeetingNotes(int id, String text) throws
          IllegalArgumentException, IllegalStateException, NullPointerException {
    Objects.requireNonNull(text, "Notes cannot be null");
    if (!meetingMap.containsKey(id)) {
      throw new IllegalArgumentException("Meeting does not exist");
    }
    if (dateInFuture(getMeeting(id).getDate())) {
      throw new IllegalStateException("Meeting is not in the past.");
    }
    // Create new PastMeeting with the same ID
    PastMeetingImpl returnMeeting = new PastMeetingImpl(id, getMeeting(id).getDate(),
        getMeeting(id).getContacts(), text);
    // Replace outdated or note-free meeting in meetingMap
    meetingMap.put(id, returnMeeting);
    return returnMeeting;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public int addNewContact(String name, String notes)
          throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(name, "Name cannot be null");
    Objects.requireNonNull(notes, "Notes cannot be null");
    if (name.equals("") || notes.equals("")) {
      throw new IllegalArgumentException("Name and/or notes cannot be empty");
    }
    Contact newContact = new ContactImpl(contactIdCounter, name, notes);
    contactMap.put(contactIdCounter, newContact);
    // Iterate idCounter in prep for next new Contact
    contactIdCounter++;
    return newContact.getId();
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Set<Contact> getContacts(String name) throws NullPointerException {
    Objects.requireNonNull(name, "Name cannot be null");
    Set<Contact> nameSet = new HashSet<>();
    // return full list if string is empty
    // todo - can I just return the whole hashmap instead of iterating through?
    if (name.equals("")) {
      for (Map.Entry<Integer, Contact> entry : contactMap.entrySet()) {
        nameSet.add(entry.getValue());
      }
      return nameSet;
    }
    // otherwise just return matching contacts
    for (Map.Entry<Integer, Contact> entry : contactMap.entrySet()) {
      if (entry.getValue().getName().contains(name)) {
        nameSet.add(entry.getValue());
      }
    }
    return nameSet;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Set<Contact> getContacts(int... ids) throws IllegalArgumentException {
    if (ids.length == 0) {
      throw new IllegalArgumentException("No IDs have been entered");
    }
    Set<Contact> idSet = new HashSet<>();
    for (int id : ids) {
      // Check for invalid IDs
      if (!contactMap.containsKey(id)) {
        throw new IllegalArgumentException("Invalid ID entered");
      }
      idSet.add(contactMap.get(id));
    }
    return idSet;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void flush() {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("contacts.ser"))) {
      out.writeObject(contactMap);
      out.writeObject(meetingMap);
    } catch (IOException exception) {
      System.out.println("Couldn't write contacts.ser");
      exception.printStackTrace();
    }
  }
}