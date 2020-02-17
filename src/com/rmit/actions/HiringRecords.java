package com.rmit.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rmit.common.DataLogger;
import com.rmit.common.DateTime;

/**
 * @author bhattj
 */
public class HiringRecords extends Hotel {

    HashMap<String, Object> hmap = null;
    SimpleDateFormat myFormat = null;

    public DataLogger data = null;
    private StandardRoom stdRooms = null;
    private static SuiteRoom stRooms = null;

    private static String recordId = null;
    private static String roomId = null;
    private static String startDate = null;
    private static DateTime estRetDate = null;
    private static String actRetDate = "none";
    private static String rentalFee = "none";
    private static String lateFee = "none";
    private static DateTime today = null;
    private static String crtRecRoom = null;
    private static String lastMainDate = "00/00/0000";

    public static int actRetDays = 0;
    public static int estRetDays = 0;
    public long days = 0;

    private static boolean lastMainflag = false;

    List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

    /**
     * Get Record Id
     * 
     * @return recordId
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * set Record Id
     * 
     * @param recordId
     */
    public void setRecordId(String recordId) {
        HiringRecords.recordId = recordId;
    }

    /**
     * Get Start Date
     * 
     * @return startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Set Start Date
     * 
     * @param startDate
     */
    public void setStartDate(String startDate) {
        HiringRecords.startDate = startDate;
    }

    /**
     * Get Estimated Return Date
     * 
     * @return estRetDate
     */
    public DateTime getEstRetDate() {
        return estRetDate;
    }

    /**
     * Set Estimated Return Date
     * 
     * @param estRetDate
     */
    public void setEstRetDate(DateTime estRetDate) {
        HiringRecords.estRetDate = estRetDate;
    }

    /**
     * Get Actual Return Date
     * 
     * @return actRetDate
     */
    public String getActRetDate() {
        return actRetDate;
    }

    /**
     * Set Actual Return Date
     * 
     * @param actRetDate
     */
    public void setActRetDate(String actRetDate) {
        HiringRecords.actRetDate = actRetDate;
    }

    /**
     * Returns todays date
     * @return today
     */
    public static DateTime getToday() {
        return today;
    }

    /**
     * Set Todays return date
     * @param today
     */
    public static void setToday(DateTime today) {
        HiringRecords.today = today;
    }

    /**
     * Get Last Maintaince Date
     * @return lastMainDate
     */
    public static String getLastMainDate() {
        return lastMainDate;
    }

    /**
     * Set Last Maintaince Date
     * @param lastMainDate
     */
    public static void setLastMainDate(String lastMainDate) {
        HiringRecords.lastMainDate = lastMainDate;
    }

    /**
     * Checks last Maintaince Field Flag
     * @return lastMainflag
     */
    public static boolean isLastMainflag() {
        return lastMainflag;
    }

    /**
     * Set last Maintaince Field Flag
     * @param lastMainflag
     */
    public static void setLastMainflag(boolean lastMainflag) {
        HiringRecords.lastMainflag = lastMainflag;
    }

    /**
     * Get Created Record Room Combination
     * @return crtRecRoom
     */
    public static String getCrtRecRoom() {
        return crtRecRoom;
    }

    /**
     * Set Created Record Room Combination
     * @param crtRecRoom
     */
    public static void setCrtRecRoom(String crtRecRoom) {
        HiringRecords.crtRecRoom = crtRecRoom;
    }

    /**
     *  Get Rental Fee
     * @return rentalFee
     */
    public String getRentalFee() {
        return rentalFee;
    }

    /**
     * Set Rental Fee
     * @param rentalFee
     */
    public void setRentalFee(String rentalFee) {
        HiringRecords.rentalFee = rentalFee;
    }
    
    /**
     * Get Late Fee
     * @return lateFee
     */
    public String getLateFee() {
        return lateFee;
    }

    /**
     * Set Late Fee
     * @param lateFee
     */
    public void setLateFee(String lateFee) {
        HiringRecords.lateFee = lateFee;
    }

    /**
     * Default Constructor
     */
    public HiringRecords() {
        // TODO Auto-generated constructor stub
        super();
    }

    /**
     * Constructor with parameters with Actual Return Date
     * @param estRetDate estRetDate
     * @param actRetDate actRetDate
     * @throws ParseException parse exception
     */
    public HiringRecords(String roomId, String recordId, int noOfBeds,
            String features, String roomType, String roomStatus, String date,
            int estRetDate, int actRetDate) throws ParseException {
        super(noOfBeds, features, roomType, roomStatus);
        today = new DateTime();
        myFormat = new SimpleDateFormat("dd/MM/yyyy");
        data = new DataLogger();
        HiringRecords.roomId = roomId;// createHotelRoomCount(super.getRoomType());
        setRoomId(HiringRecords.roomId);
        HiringRecords.recordId = recordId;
        setRecordId(HiringRecords.recordId);
        this.crtRecRoom = roomId + "_" + recordId + "_"
                + today.getEightDigitDate();
        this.estRetDays = estRetDate;
        this.actRetDays = actRetDate;
        HiringRecords.startDate = getDate(date);
        setStartDate(HiringRecords.startDate);
        HiringRecords.estRetDate = getDate(estRetDate);
        setEstRetDate(HiringRecords.estRetDate);
        HiringRecords.actRetDate = getDate(actRetDate).toString();
        setActRetDate(HiringRecords.actRetDate);
        data.putData();
        callRooms();
        data.putData();
    }

    /**
     * Constructor with parameters 
     * @param estRetDate estRetDate
     * @param actRetDate actRetDate
     * @throws ParseException parse exception
     */
    public HiringRecords(String roomId, String recordId, int noOfBeds,
            String features, String roomType, String roomStatus, String date,
            int estRetDate) throws ParseException {
        super(noOfBeds, features, roomType, roomStatus);
        today = new DateTime();
        myFormat = new SimpleDateFormat("dd/MM/yyyy");
        data = new DataLogger();
        HiringRecords.roomId = roomId;// createHotelRoomCount(super.getRoomType());
        setRoomId(HiringRecords.roomId);
        HiringRecords.recordId = recordId;
        setRecordId(HiringRecords.recordId);
        this.crtRecRoom = roomId + "_" + recordId + "_"
                + today.getEightDigitDate();
        this.estRetDays = estRetDate;
        HiringRecords.startDate = getDate(date);
        setStartDate(HiringRecords.startDate);
        HiringRecords.estRetDate = getDate(estRetDate);
        setEstRetDate(HiringRecords.estRetDate);
        data.putData();
        callRooms();
        data.putData();
    }

    /**
     * Call Standard or Suite Room
     * @throws ParseException parse exception
     */
    public void callRooms() throws ParseException {
        if (super.getRoomType().equalsIgnoreCase("suite")) {
            checkSuiteRoom(HiringRecords.recordId, today, estRetDays);
        } else {
            checkStdRoom(HiringRecords.recordId, today, estRetDays);
        }
    }

    /**
     *  Get Day for the given date
     * @param day day
     * @param month month
     * @param year year
     * @return date
     */
    public static String getDay(String day, String month, String year) {
        int y = Integer.parseInt(year), m = Integer.parseInt(month), d = Integer
                .parseInt(day);
        return java.time.format.DateTimeFormatter.ofPattern("EEEE").format(
                LocalDate.of(y, m, d));
    }

    /**
     * Call to Standard Room
     * @param recordId recordId
     * @param today today
     * @param estRetDate estRetDate
     * @throws ParseException ParseException
     */
    public void checkStdRoom(String recordId, DateTime today, int estRetDate)
            throws ParseException {
        list.add(hmap);
        /* System.out.println(hmap); */
        stdRooms = new StandardRoom(roomId, data.hmap);
        stdRooms.rent(HiringRecords.recordId, today, estRetDate);
        stdRooms.returnRoom(HiringRecords.estRetDate);
    }

    /**
     *  Call to Suite Room
     * @param recordId recordId
     * @param today today
     * @param estRetDate estRetDate
     * @throws ParseException ParseException
     */
    public void checkSuiteRoom(String recordId, DateTime today, int estRetDate)
            throws ParseException {
        list.add(hmap);
        /* System.out.println(hmap); */
        stRooms = new SuiteRoom(roomId, data.hmap);
        stRooms.rent(HiringRecords.recordId, today, estRetDate);
        stRooms.returnRoom(HiringRecords.estRetDate);
        stRooms.performMaintenance();
    }

    /*
     * public static String createRecordId(String room) { recordId = room +
     * "_CUS" + Integer.toString(roomCount) + "_" + today.getEightDigitDate();
     * return recordId; }
     */

    /**
     * Get Date
     * @return today
     */
    public static String getDate(String date) {
        if (date.equalsIgnoreCase("00/0/0000")) {
            today = new DateTime();
        } else {

            today = new DateTime(Integer.parseInt(date.substring(0, 2)),
                    Integer.parseInt(date.substring(3, 4)),
                    Integer.parseInt(date.substring(5, 9)));
        }

        return today.toString();
    }

    /**
     * Call to Complete Maintaince method
     */
    public static void callCompleteMaintaince() {
        System.out.println("Day: " +Integer
                .parseInt(getLastMainDate().substring(0, 2)));
        System.out.println("Month:"+ Integer
                .parseInt(getLastMainDate().substring(3, 4)));
        System.out.println("Year: "+ Integer
                .parseInt(getLastMainDate().substring(6, 9)));
        stRooms.completeMaintenance(new DateTime(Integer
                .parseInt(getLastMainDate().substring(0, 2)), Integer
                .parseInt(getLastMainDate().substring(3, 4)), Integer
                .parseInt(getLastMainDate().substring(6, 9))));
    }

    /**
     * Get Date
     * @param estRetDate estRetDate
     * @return estRetDate
     */
    public static DateTime getDate(int estRetDate) {
        return new DateTime(today, estRetDate);
    }

    /*
     * public String createHotelRoomCount(String roomType) { String room = null;
     * if (roomType.equalsIgnoreCase("standard")) { room = "R_" +
     * Integer.toString(roomCount); HiringRecords.createRecordId(room);
     * roomCount += 1; } else { room = "S_" + Integer.toString(roomCount);
     * HiringRecords.createRecordId(room); roomCount += 1; }
     * 
     * return room; }
     */

    /**
     * Returns toString() 
     */
    public String toString() {
        // TODO Auto-generated method stub
        return getCrtRecRoom() + ":" + startDate + ":" + estRetDate + ":"
                + actRetDate + ":" + rentalFee + ":" + lateFee;
    }
}
