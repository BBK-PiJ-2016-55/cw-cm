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

    // todo - add javadoc or get rid of this before submission
    public void resetCounter() {
        contactIdCounter = 1;
        meetingIdCounter = 1;
    }

    @Override
    public int addFutureMeeting(Set<Contact> attendees, Calendar date) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(attendees, "Contacts cannot be null");
        Objects.requireNonNull(date, "Date cannot be null");
        // Checks that future meeting is not in past
        if ((checkMeetingDate(date)) < 0) {
            throw new IllegalArgumentException("FutureMeeting cannot be in the past.");
        }
        // Checks that all contacts are known/existent
        for (Contact contact : attendees) {
            if (!checkContactsExist(contact)) {
                throw new IllegalArgumentException("Invalid Contact entered.");
            }
        }
        int id = meetingIdCounter;
        Meeting newMeeting = new FutureMeetingImpl(id, date, attendees);
        meetingMap.put(id, newMeeting);
        // Iterate meetingCounter in prep for next new Meeting
        meetingIdCounter++;
        return id;
    }

    /**
     * Checks whether contact exists.
     *
     * @param contact to be validated
     * @return true if valid, false otherwise.
     */
    private boolean checkContactsExist(Contact contact) {
            return (contactMap.containsValue(contact));
    }

    /**
     * Checks date passed in against current time.
     *
     * @param date to be checked against current date
     * @return int -1 if date is in past, 0 if present and 1 if in future compared to current time
     *
     */
    private int checkMeetingDate(Calendar date) {
        Calendar currentTime = new GregorianCalendar();
        return date.compareTo(currentTime);
    }

    @Override
    public PastMeeting getPastMeeting(int id) {
        return null;
    }

    @Override
    public FutureMeeting getFutureMeeting(int id) throws IllegalStateException {
        if (checkMeetingDate(meetingMap.get(id).getDate()) < 0) {
            throw new IllegalStateException("Meeting is not in the future.");
        }
        return (FutureMeeting) meetingMap.get(id);
    }

    @Override
    public Meeting getMeeting(int id) {
        return null;
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
    public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
        return 0;
    }

    @Override
    public PastMeeting addMeetingNotes(int id, String text) {
        return null;
    }

    @Override
    public int addNewContact(String name, String notes) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(notes, "Notes cannot be null");
        if (name.equals("") || notes.equals("")) {
            throw new IllegalArgumentException("Name and/or notes cannot be empty");
        } else {
            // Use incrementing id to generate UIDs and assign Contact to corresponding key
            int id = contactIdCounter;
            Contact newContact = new ContactImpl(id, name, notes);
            contactMap.put(id, newContact);
            // Iterate idCounter in prep for next new Contact
            contactIdCounter++;
            return id;
        }
    }

    @Override
    public Set<Contact> getContacts(String name) throws NullPointerException {
        Set<Contact> nameSet = new HashSet<>();
        // return full list if string is empty
        if (name.equals("")) {
            for (Map.Entry<Integer, Contact> entry : contactMap.entrySet()) {
                nameSet.add(entry.getValue());
            }
        } else {
            // otherwise just return matching contacts
            for (Map.Entry<Integer, Contact> entry : contactMap.entrySet()) {
                if (entry.getValue().getName().contains(name)) {
                    nameSet.add(entry.getValue());
                }
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
