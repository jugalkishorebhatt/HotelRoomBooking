package com.rmit.implementations;

import java.text.ParseException;

import com.rmit.common.DateTime;

/**
 * 
 * @author bhattj
 *
 */
public interface Rooms {
    public boolean rent(String customerId, DateTime rentDate, int numOfRentDay);

    public boolean returnRoom(DateTime returnDate) throws ParseException;

    public boolean performMaintenance();

    public boolean completeMaintenance(DateTime completionDate);

}
