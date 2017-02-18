package impl;

import spec.Contact;

/**
 * Created by essvee on 17/02/2017.
 */
public class ContactImpl implements Contact {
    private int id;
    private String name;
    private String notes;

    public ContactImpl(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @param id the id of the contact
     * @param name the name of the contact
     * @param notes notes relating to the contact
     * @throws IllegalArgumentException if ID is 0 or below
     */
    public ContactImpl(int id, String name, String notes) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID cannot be 0 or less");
        } else {
            this.id = id;
            this.name = name;
            this.notes = notes;
        }
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
        this.notes = notes;
    }
}
