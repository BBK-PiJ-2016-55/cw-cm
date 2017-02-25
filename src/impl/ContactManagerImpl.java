package impl;

import spec.*;

import java.util.*;

/**
 * Created by essvee on 17/02/2017.
 */
public class ContactManagerImpl implements ContactManager {
    // fields below temporarily set to public to enable testing
    public ArrayList<Contact> contactList = new ArrayList<>();
    public static int idCounter = 1;

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
        if (name == null || notes == null) {
            throw new NullPointerException("Name and/or notes cannot be null");
        } else if (name.equals("") || notes.equals("")) {
            throw new IllegalArgumentException("Name and/or notes cannot be empty");
        } else {
            // Use incrementing id to generate unique ids and assign Contact to corresponding index
            int id = idCounter;
            Contact newContact = new ContactImpl(id, name, notes);
            contactList.add(newContact);
            idCounter++;
            return id;
        }
    }

    @Override
    public Set<Contact> getContacts(String name) throws NullPointerException {
        Set<Contact> nameSet = new HashSet<>();
        // return full list if string is empty
        if (name.equals(" ")) {
            for (Contact contact: contactList) {
                nameSet.add(contact);
            }
        } else {
            for (Contact contact : contactList) {
                if (contact.getName().contains(name)) {
                    nameSet.add(contact);
                }
            }
        }
        return nameSet;
    }

    @Override
    public Set<Contact> getContacts(int... ids) {
        Set<Contact> idSet = new HashSet<>();
        for (int id : ids) {
            idSet.add(contactList.get(id - 1));
        }
        return idSet;
    }

    @Override
    public void flush() {

    }
}
