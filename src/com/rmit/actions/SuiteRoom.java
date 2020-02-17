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
public class SuiteRoom extends HiringRecords implements Rooms {

    HiringRecords rec = null;
    HashMap<String, Object> hmap = null;
    SimpleDateFormat myFormat = null;
    private static String roomId = null;
    static DateTime date;
    private static int SUITERENT = 999;

    /**
     * Constructor with Parameters
     * 
     * @param roomId
     *            roomId
     * @param hmap
     *            hmap
     */
    public SuiteRoom(String roomId, HashMap<String, Object> hmap) {
        rec = new HiringRecords();
        myFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.roomId = roomId;
        this.hmap = hmap;
    }

    /**
     * Default Constructor
     */
    public SuiteRoom() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Rent Suite Room
     */
    @Override
    public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
        if (hmap.containsValue(roomId)) {
            return true;
        }
        return false;
    }

    /**
     * Return booked room
     */
    @Override
    public boolean returnRoom(DateTime returnDate) throws ParseException {
        // TODO Auto-generated method stub
        if (returnDate.equals(hmap.get("EstRetDate"))) {

            System.out.println("actRetDays:" + actRetDays);
            System.out.println("estRetDays:" + estRetDays);
            System.out.println("Fees Calculation:"
                    + ((actRetDays > 0) ? Long
                            .toString(((SUITERENT * (actRetDays)) * 135) / 100)
                            : "0"));
            Date date2 = myFormat.parse(returnDate.toString());
            Date date1 = myFormat.parse(hmap.get("RentDate").toString());
            long diff = date2.getTime() - date1.getTime();
            long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            System.out.println(hmap.get("noOfBed") + " " + days + " "
                    + returnDate + " " + returnDate.getNameOfDay());
            if ("6".equalsIgnoreCase(hmap.get("noOfBed").toString())) {
                super.setRentalFee(Long.toString((SUITERENT * days)));
                super.setLateFee((actRetDays > 0) ? Long
                        .toString(((SUITERENT * (Math.abs(actRetDays))) * 135) / 100)
                        : "0");
            }
        }
        return false;
    }

    /**
     * puts Suite Room under Maintaince
     */
    @Override
    public boolean performMaintenance() {
        // TODO Auto-generated method stub
        date = new DateTime(Integer.parseInt(rec.getLastMainDate().substring(0,
                2)), Integer.parseInt(rec.getLastMainDate().substring(3, 4)),
                Integer.parseInt(rec.getLastMainDate().substring(6, 9)));
        super.setLastMainDate(date.toString());
        return true;
    }

    /**
     * Complete Suite Rooms Maintaince
     */
    @Override
    public boolean completeMaintenance(DateTime completionDate) {
        // TODO Auto-generated method stub
        super.setLastMainDate(date.toString());
        super.setLastMainflag(false);
        return true;
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
