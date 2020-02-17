package com.rmit.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.rmit.common.DateTime;
import com.rmit.implementations.Rooms;

/**
 * 
 * @author bhattj
 *
 */
public class StandardRoom extends HiringRecords implements Rooms {

    HiringRecords rec = null;
    HashMap<String, Object> hmap = null;
    SimpleDateFormat myFormat = null;
    private static String roomId = null;

    private static int ONEBED = 59;
    private static int TWOBED = 99;
    private static int FOURBED = 199;

    /**
     * Constructor with Parameter
     * 
     * @param roomId
     *            roomId
     * @param hmap
     *            hmap
     */
    public StandardRoom(String roomId, HashMap<String, Object> hmap) {
        // TODO Auto-generated constructor stub
        rec = new HiringRecords();
        myFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.roomId = roomId;
        this.hmap = hmap;
    }

    /**
     * Default Constructor
     */
    public StandardRoom() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Rent the room
     */
    @Override
    public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
        if (hmap.containsValue(roomId)) {
            return true;
        }
        return false;
    }

    /**
     * Return Room
     */
    @Override
    public boolean returnRoom(DateTime returnDate) throws ParseException {
        // TODO Auto-generated method stub
        System.out.println("actRetDays:" + actRetDays);
        System.out.println("estRetDays:" + estRetDays);
        System.out
                .println("Fees Calculation:"
                        + ((actRetDays > 0) ? Long
                                .toString(((ONEBED * (actRetDays)) * 135) / 100)
                                : "0"));
        if (returnDate.equals(hmap.get("EstRetDate"))) {
            Date date2 = myFormat.parse(returnDate.toString());
            Date date1 = myFormat.parse(hmap.get("RentDate").toString());
            long diff = date2.getTime() - date1.getTime();
            long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            if ("1".equalsIgnoreCase(hmap.get("noOfBed").toString())) {
                super.setRentalFee(Long.toString((ONEBED * days)));
                super.setLateFee((actRetDays > 0) ? Long
                        .toString(((ONEBED * (Math.abs(actRetDays))) * 135) / 100)
                        : "0");
            } else if ("2".equalsIgnoreCase(hmap.get("noOfBed").toString())) {
                super.setRentalFee(Long.toString((TWOBED * days)));
                super.setLateFee((actRetDays > 0) ? Long
                        .toString(((TWOBED * (Math.abs(actRetDays))) * 135) / 100)
                        : "0");
            } else if ("4".equalsIgnoreCase(hmap.get("noOfBed").toString())) {

                super.setRentalFee(Long.toString((FOURBED * days)));
                super.setLateFee((actRetDays > 0) ? Long
                        .toString(((FOURBED * (Math.abs(actRetDays))) * 135) / 100)
                        : "0");
            }
        }
        return false;
    }

    /**
     * Empty Method from Room Implementation
     */
    @Override
    public boolean performMaintenance() {
        return true;
    }

    /**
     * Empty Method from Room Implementation
     */
    @Override
    public boolean completeMaintenance(DateTime completionDate) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * return toString()
     */
    public String toString() {
        return super.getRoomId() + ":" + super.getNooFBeds() + ":"
                + super.getRoomType() + ":" + super.getRoomStatus() + ":"
                + super.getFeatures();
    }
}
