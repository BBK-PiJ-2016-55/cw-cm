package main.java.impl;

import main.java.spec.*;

import java.util.*;

/**
 * Created by svince04 on 17/02/2017.
 * PiJ coursework 3
 */
public class ContactManagerImpl implements ContactManager {
    private HashMap<Integer, Contact> contactMap = new HashMap<>();
    private HashMap<Integer, Meeting> meetingMap = new HashMap<>();
    private static int contactIdCounter = 1;
    private static int meetingIdCounter = 1;

    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(contacts, "Contacts cannot be null");
        Objects.requireNonNull(date, "Date cannot be null");
        // Check that future meeting is not in past
        if ((checkMeetingDate(date)) < 0) {
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

    //todo - add javadoc
    private boolean checkMeetingExists(int id) {
        return (meetingMap.containsKey(id));
    }

    /**
     * Checks date passed in against current time.
     *
     * @param date to be checked against current date
     * @return int -1 if date is in past, 0 if present and 1 if in future compared to current time
     */
    private int checkMeetingDate(Calendar date) {
        return date.compareTo(Calendar.getInstance());
    }

    @Override
    public PastMeeting getPastMeeting(int id) throws IllegalStateException {
        if (!checkMeetingExists(id)) {
            return null;
        }
        if (checkMeetingDate(meetingMap.get(id).getDate()) > -1) {
            throw new IllegalStateException("Meeting is not in the past.");
        }
        return (PastMeeting) meetingMap.get(id);
    }

    @Override
    public FutureMeeting getFutureMeeting(int id) throws IllegalStateException {
        if (!checkMeetingExists(id)) {
            return null;
        }
        if (checkMeetingDate(meetingMap.get(id).getDate()) < 0) {
            throw new IllegalStateException("Meeting is not in the future.");
        }
        return (FutureMeeting) meetingMap.get(id);
    }

    @Override
    public Meeting getMeeting(int id) {
        if (!checkMeetingExists(id)) {
            return null;
        }
        return meetingMap.get(id);
    }

    @Override
    public List<Meeting> getFutureMeetingList(Contact contact) {
        return null;
    }

    @Override
    public List<Meeting> getMeetingListOn(Calendar date) {
        return null;
    }

    @Override
    public List<PastMeeting> getPastMeetingListFor(Contact contact) {
        return null;
    }

    @Override
    public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(contacts, "Contacts cannot be null");
        Objects.requireNonNull(date, "Date cannot be null");
        // Check that past meeting is in the past
        if ((checkMeetingDate(date)) != -1) {
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

    @Override
    public PastMeeting addMeetingNotes(int id, String text) {
        // Create new PastMeeting with the same ID
        PastMeetingImpl returnMeeting = new PastMeetingImpl(id, getMeeting(id).getDate(),
                getMeeting(id).getContacts(), text);
        // Replace outdated or note-free meeting in meetingMap
        meetingMap.put(id, returnMeeting);
        return returnMeeting;
    }

    @Override
    public int addNewContact(String name, String notes) throws IllegalArgumentException, NullPointerException {
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

    @Override
    public Set<Contact> getContacts(String name) throws NullPointerException {
        Set<Contact> nameSet = new HashSet<>();
        // return full list if string is empty
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

    @Override
    public void flush() {

    }
}
