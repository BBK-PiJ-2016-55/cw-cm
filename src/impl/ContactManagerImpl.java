package impl;

import spec.*;

import java.util.*;

/**
 * Created by essvee on 17/02/2017.
 */
public class ContactManagerImpl implements ContactManager {
    private ArrayList<Contact> contactList = new ArrayList<>();
    private static int idCounter = 1;


    /**
     * Resets value of ContactManagerImpl
     * For testing purposes - comment out/remove in final sub
     */
    public void resetConManImpl() {
        idCounter = 1;
        contactList.clear();
    }

    /**
     * @return current value of idCounter
     * For testing purposes - comment out/remove in final sub
     */
    public int getIdCount() {
        return idCounter;
    }

    /**
     * @return current size of contactList
     * For testing purposes - comment out/remove in final sub
     */
    public int getContactListSize() {
        return contactList.size();
    }

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
            // Use incrementing id to generate unique ids and assign Contact to corresponding index - 1
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
    public Set<Contact> getContacts(int... ids) throws IllegalArgumentException {
        if (ids.length == 0) {
            throw new IllegalArgumentException("No IDs have been entered");
        }
        Set<Contact> idSet = new HashSet<>();
        for (int id : ids) {
            if (id >= idCounter++ || id < 1) {
                throw new IllegalArgumentException("Invalid ID entered");
            }
            // id - 1 maps to the contact's index in contactList
            idSet.add(contactList.get(id - 1));
        }
        return idSet;
    }

    @Override
    public void flush() {

    }
}
