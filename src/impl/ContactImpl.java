package impl;

import spec.Contact;

/**
 * Created by essvee on 17/02/2017.
 */
public class ContactImpl implements Contact {
    private int id;
    private String name;
    private String notes;

    public ContactImpl(int id, String name, String notes) {
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
        return null;
    }

    @Override
    public String getNotes() {
        return null;
    }

    @Override
    public void addNotes(String note) {

    }
}
