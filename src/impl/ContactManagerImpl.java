package impl;

import spec.*;

import java.util.*;

/**
 * Created by svince04 on 17/02/2017 for cw-cm.
 */
public class ContactManagerImpl implements ContactManager {
    private HashMap<Integer, Contact> contactMap = new HashMap<>();
    private static int idCounter = 1;

    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
        return 0;
    }

    @Override
    public PastMeeting getPastMeeting(int id) {
        return null;
    }

    @Override
    public FutureMeeting getFutureMeeting(int id) {
        return null;
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
        if (name.equals("") || notes.equals("")) {
            throw new IllegalArgumentException("Name and/or notes cannot be empty");
        } else {
            // Use incrementing id to generate UIDs and assign Contact to corresponding key
            int id = idCounter;
            Contact newContact = new ContactImpl(id, name, notes);
            contactMap.put(id, newContact);
            // Iterate idCounter in prep for next new Contact
            idCounter++;
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
