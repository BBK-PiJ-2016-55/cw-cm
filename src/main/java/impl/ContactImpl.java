package main.java.impl;

import main.java.spec.Contact;

import java.util.Objects;

/**
 * Created by svince04 on 17/02/2017 for cw-cm.
 */
public class ContactImpl implements Contact {
    private int id;
    private String name;
    private String notes;

    /**
     * @param id the id of the contact
     * @param name the name of the contact
     * @throws IllegalArgumentException if id is 0 or below
     * @throws NullPointerException if name is null
     */
    public ContactImpl(int id, String name) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(name, "Name cannot be null");
        if (id <= 0) {
            throw new IllegalArgumentException("ID cannot be 0 or less");
        }
        this.id = id;
        this.name = name;
        this.notes = "";
    }

    /**
     * @param id the id of the contact
     * @param name the name of the contact
     * @param notes notes relating to the contact
     * @throws IllegalArgumentException if ID is 0 or below
     * @throws NullPointerException if name or notes are null
     */
    public ContactImpl(int id, String name, String notes) throws IllegalArgumentException, NullPointerException {
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
        this.notes += notes;
    }
}
