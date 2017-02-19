package impl;

import spec.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * Created by essvee on 17/02/2017.
 */
public class ContactManagerImpl implements ContactManager {
    private ArrayList<Contact> contactList = new ArrayList<>();
    private static int listSize = 1;

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
            int id = listSize;
            Contact newContact = new ContactImpl(id, name, notes);
            listSize++;
            return id;
        }
    }

    @Override
    public Set<Contact> getContacts(String name) {
        return null;
    }

    @Override
    public Set<Contact> getContacts(int... ids) {
        return null;
    }

    @Override
    public void flush() {

    }
}
