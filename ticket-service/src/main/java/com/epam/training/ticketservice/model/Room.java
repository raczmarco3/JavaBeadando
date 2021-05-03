package com.epam.training.ticketservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {
    @Id
    @Column
    private String name;

    @Column
    private int rows;

    @Column
    private int columns;

    public Room(){}

    public Room(String name, int rows, int columns)
    {
        this.name = name;
        this.rows = rows;
        this.columns = columns;
    }

    public String getName()
    {
        return this.name;
    }

    public int getRows()
    {
        return this.rows;
    }

    public int getColumns()
    {
        return this.columns;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public void setColumns(int columns)
    {
        this.columns = columns;
    }
}
