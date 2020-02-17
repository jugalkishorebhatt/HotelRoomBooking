package com.rmit.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.rmit.common.DataLogger;
import com.rmit.common.DateTime;

/**
 * 
 * @author bhattj
 *
 */
public class CityLodge extends DataLogger {

    static BufferedReader br = new BufferedReader(new InputStreamReader(
            System.in));
    static HiringRecords[] rec = new HiringRecords[50];

    // Counters
    static int cntRooms = 0;
    static int cntCust = 0;
    static int cnt = 0;

    static int cntAddRooms = 0;
    static int cntRentRooms = 0;
    static int cntReturnRooms = 0;
    static int cntLstMain = 0;
    static int cntCmpLstMain = 0;

    // Holds Hotel,Customer data
    static HashMap<String, Object> rooms = new HashMap<String, Object>();
    static HashMap<String, Object> custRoom = new HashMap<String, Object>();
    private static HashMap<String, Object> custLogs = new HashMap<String, Object>();

    // List of Fields to display when displaying Rooms Info
    static List<String> records = Arrays.asList("Room ID", "Number of beds",
            "Type", "Status", "Last maintenance date", "Feature summary");
    static List<String> rentalRec = Arrays.asList("Record ID", "Rent Date",
            "Estimated Return Date", "Actual Return Date", "Rental Fee",
            "Late Fee");

    static SimpleDateFormat myFormat = null;
    static HiringRecords rec1 = new HiringRecords();
    static DateTime date = null;

    static String roomId = null;
    static String roomType = null;
    static int noOfBeds = 0;
    static String lastMainDate = "00/00/0000";
    static String custId = null;
    static String rentDate = null;
    static String features = null;
    static int noOfDays = 0;
    static String retDate = null;
    static boolean lastMainFlag = true;

    /**
     * Returns HashMap of cust logs
     * @return custogs
     */
    public static HashMap<String, Object> getCustLogs() {
        return custLogs;
    }
    
    /**
     * Hotel Menu Log
     * 
     * @throws Exception
     *             exception
     */
    public void cityHotelMenu() throws Exception {
        int i = 0;
        do {
            System.out.println("**** CITYLODGE MAIN MENU ****");
            System.out.println("Add Room: 1");
            System.out.println("Rent Room: 2");
            System.out.println("Return Room: 3");
            System.out.println("Room Maintaince: 4");
            System.out.println("Complete Maintaince: 5");
            System.out.println("Display All Rooms: 6");
            System.out.println("Exit Program: 7");
            System.out.println("Enter your Choice: ");
            try {
                i = Integer.parseInt(br.readLine());
                enterChoice(i);

                /* System.out.println("Integer Entered:" + i); */
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format!");
            }
        } while (i != 7);
    }

    /**
     * Enter the choice of operation
     * 
     * @param i
     *            i
     * @throws Exception
     *             ex
     */
    public static void enterChoice(int i) throws Exception {
        switch (i) {
        case 1:
            addRooms();
            System.out.println("Room Added");
            break;
        case 2:
            rentRoom();
            System.out.println("Room Rented");
            break;
        case 3:
            returnRoom();
            System.out.println("Room Returned");
            break;
        case 4:
            maintainSuiteRoom();
            System.out.println("Room Maintain");
            break;
        case 5:
            completeMaintainSuiteRoom();
            System.out.println("Complete Maintaince");
            break;
        case 6:
            System.out.println("Enter RoomId for Rental Records:");
            String roomId = br.readLine();
            displayAllRooms(roomId);
            System.out.println("Display Rooms");
            break;
        case 7:
            System.out.println("Exit");
            if (i == 7)
                System.exit(0);
            break;
        default:
            System.out.println("Enter from the number listed");
        }
    }

    /**
     * Complete Suite Maintaince activity
     * 
     * @throws IOException
     *             io exceptions
     */
    private static void completeMaintainSuiteRoom() throws IOException {
        System.out.println("Room Id:");
        roomId = br.readLine();
        System.out.println("Complete Maintaince Date:");
        lastMainDate = br.readLine();
        setLastMainDate(lastMainDate);
        // callCompleteMaintaince();
        rooms.put("lastMainDate_" + roomId, getLastMainDate());
        rooms.put("lastMainFlag_" + roomId, false);
        getCustLogs().put(
                "Last maintenance date_" + roomId + "@" + cntCmpLstMain,
                getLastMainDate());
        cntCmpLstMain += 1;
    }

    /**
     * Add Suite for Maintaince
     * 
     * @throws IOException
     *             io exceptions
     */
    private static void maintainSuiteRoom() throws IOException {
        System.out.println("Room Id:");
        roomId = br.readLine();
        System.out.println("Last Maintaince Date:");
        lastMainDate = br.readLine();
        // TODO Auto-generated method stub
        setLastMainDate(lastMainDate);
        rooms.put("lastMainDate_" + roomId, getLastMainDate());
        rooms.put("lastMainFlag_" + roomId, true);
        getCustLogs().put("Last maintenance date_" + roomId + "@" + cntLstMain,
                getLastMainDate());
        cntLstMain += 1;
    }

    /**
     * Add Rooms to Hotel
     * 
     * @throws Exception
     *             ex
     */
    public static void addRooms() throws Exception {
        System.out.println("Room id:");
        roomId = br.readLine();

        System.out.println("Room Type:");
        roomType = br.readLine();
        if ((!roomType.equalsIgnoreCase("standard"))
                && (!roomType.equalsIgnoreCase("suite"))) {
            System.out.println("Room Type must be standard or suite");
            return;
        }

        System.out.println("Features:");
        features = br.readLine();
        if (features.length() > 20) {
            System.out.println("Length more than 20");
            return;
        } else {
            rooms.put("features_" + roomId, features);
        }

        if (roomType.equalsIgnoreCase("suite")) {
            rooms.put("noOfBeds_" + roomId, 6);
            getCustLogs()
                    .put("Number of beds_" + roomId + "@" + cntAddRooms, 6);
        } else {
            System.out.println("Number Of Beds:");
            noOfBeds = Integer.parseInt(br.readLine());
            rooms.put("noOfBeds_" + roomId, noOfBeds);
            getCustLogs().put("Number of beds_" + roomId + "@" + cntAddRooms,
                    noOfBeds);
        }

        rooms.put("roomId_" + roomId, roomId);
        getCustLogs().put("Room ID_" + roomId + "@" + cntAddRooms, roomId);
        rooms.put("roomType_" + roomId, roomType);
        getCustLogs().put("Type_" + roomId + "@" + cntAddRooms, roomType);
        rooms.put("roomStatus_" + roomId, "Available");
        rooms.put("lastMainFlag_" + roomId, false);
        getCustLogs().put("Status_" + roomId, "Available");
        cntAddRooms += 1;
    }

    /**
     * Display Hotel Info
     * 
     * @throws IOException
     *             io exceptions
     */
    public static void displayAllRooms(String roomId) throws IOException {

        for (Object o : custLogs.keySet()) {
            for (String b : records) {
                if (b.equalsIgnoreCase(o.toString().substring(0,
                        o.toString().indexOf("_")))
                        && o.toString().contains(roomId))
                    System.out.println(b + " :\t" + custLogs.get(o));
            }
        }

        System.out.println("RENTAL RECORDS:");
        for (int i = 0; i < 10; i++) {
            for (Object o1 : custLogs.keySet()) {
                for (String b : rentalRec) {
                    if (b.equalsIgnoreCase(o1.toString().substring(0,
                            o1.toString().indexOf("_")))
                            && (Integer.parseInt(o1.toString().substring(
                                    o1.toString().indexOf("@") + 1))) == i
                            && o1.toString().contains(roomId))
                        System.out.println(b + " :\t" + custLogs.get(o1));
                }
            }
            System.out.println("--------------------");
        }
    }

    /**
     * Hotel Room Renting
     * 
     * @throws Exception
     *             ex
     */
    public static void rentRoom() throws Exception {
        long days = 0;
        System.out.println("Room id:");
        String roomId = br.readLine();
        if (!rooms.containsValue(roomId)) {
            System.out.println(roomId + " does not exists");
        } else if (custRoom.containsValue(roomId)) {
            System.out.println(roomId + " is not available at the moment");
        } else if (!rooms.get("lastMainFlag_" + roomId).equals(false)) {
            System.out.println(roomId + " is under Maintaince");
        } else {
            System.out.println("Customer Id:");
            custId = br.readLine();
            if (!custId.startsWith("C")) {
                System.out.println("Customer Id should begin with C");
                return;
            }
            System.out.println("Rent date(dd/m/yyyy):");
            rentDate = br.readLine();
            checkDateFormat(rentDate);
            System.out.println("How many days?:");
            noOfDays = Integer.parseInt(br.readLine());
            if (rooms.get("lastMainFlag_" + roomId).equals(false)) {
                days = getDateDifference(rentDate, getLastMainDate());
            } else {
                days = getDateDifference();
            }
            if ((days > 0 && days < 10)) {
                System.out.println(roomId + "is under maintaince");
            } else {
                String day = date.getNameOfDay().toUpperCase();
                if (("SATURDAY".equalsIgnoreCase(day) || "SUNDAY".equalsIgnoreCase(day))
                        && ((noOfDays <= 3) || (noOfDays > 10))) {
                    System.out.println("Booking needs to be a Min Of 3 days over the weekends and not more than 10 days");
                } else if ((!"SATURDAY".equalsIgnoreCase(day) || !"SUNDAY".equalsIgnoreCase(day))
                        && ((noOfDays <= 2) || (noOfDays > 10))) {
                    System.out.println("Booking needs to be a Min Of 2 days over the weekdays and not more than 10 days");
                } else {
                    custRoom.put("roomId_" + roomId, roomId);
                    custRoom.put("custId_" + roomId, custId);
                    custRoom.put("rentDate_" + roomId, rentDate);
                    getCustLogs().put("Rent Date_" + roomId + "@" + cntRentRooms,rentDate);
                    custRoom.put("noOfDays_" + roomId, noOfDays);
                    custRoom.put("estRentDate_" + roomId, new DateTime(date,noOfDays));
                    getCustLogs().put("Estimated Return Date_" + roomId + "@"+ cntRentRooms,new DateTime(date, noOfDays));
                    getCustLogs().put("Feature summary_" + roomId, features);
                    getCustLogs().put("Status_" + roomId, "Rented");
                    cntRentRooms += 1;
                    for (Object o : rooms.keySet()) {
                        if (rooms.get(o).equals(roomId)) {
                            rooms.put("roomStatus_"+ o.toString().substring(o.toString().indexOf("_") + 1),"Unavailable");
                        }
                    }
                    System.out.println(roomId + " is now rented by customer "+ custId);
                }
            }
        }
    }

    /**
     * Check Date Format
     * 
     * @param date
     *            date
     */
    public static void checkDateFormat(String date) {
        if (rentDate.matches("\\d{2}/\\d{1}/\\d{4}")) {
            System.out.println("Check Date Format");
            return;
        }
    }

    /**
     * Returns date
     * 
     * @param day
     *            day
     * @param month
     *            month
     * @param year
     *            year
     * @return date
     */
    public static DateTime getGivenDate(int day, int month, int year) {
        return new DateTime(day, month, year);
    }

    /**
     * Returns the Given date difference
     * 
     * @return noOfDays
     */
    public static long getDateDifference(String rentDate, String lastMainDate) {
        date = getGivenDate(Integer.parseInt(rentDate.substring(0, 2)),
                Integer.parseInt(rentDate.substring(3, 4)),
                Integer.parseInt(rentDate.substring(5, 9)));
        DateTime start = getGivenDate(
                Integer.parseInt(lastMainDate.substring(0, 2)),
                Integer.parseInt(lastMainDate.substring(3, 4)),
                Integer.parseInt(lastMainDate.substring(6, 9)));
        DateTime End = new DateTime(start, 10);
        long min = date.getTime() - start.getTime();
        long max = date.getTime() - End.getTime();
        long minDays = TimeUnit.DAYS.convert(min, TimeUnit.MILLISECONDS);
        long maxDays = TimeUnit.DAYS.convert(max, TimeUnit.MILLISECONDS);
        return minDays - maxDays;
    }

    /**
     * Returns the default date difference
     * 
     * @return noOfDays
     */
    public static long getDateDifference() {

        DateTime start = date;
        DateTime End = new DateTime(start, 0);
        long min = date.getTime() - start.getTime();
        long max = End.getTime() - date.getTime();
        long minDays = TimeUnit.DAYS.convert(min, TimeUnit.MILLISECONDS);
        long maxDays = TimeUnit.DAYS.convert(max, TimeUnit.MILLISECONDS);
        return minDays - maxDays;
    }

    /**
     * Return booked room.
     * 
     * @throws IOException
     *             io exceptions
     * @throws ParseException
     *             parse exceptions
     */
    public static void returnRoom() throws IOException, ParseException {
        System.out.println("Room id:");
        roomId = br.readLine();
        if (!rooms.containsValue(roomId)) {
            System.out.println(roomId + " does not exists");
            return;
        }
        System.out.println("Return Date (dd/m/yyyy):");
        retDate = br.readLine();
        checkDateFormat(retDate);
        Set<Map.Entry<String, Object>> entrySet = rooms.entrySet();
        for (Object o : rooms.keySet()) {
            if (rooms.get(o).equals(roomId)) {
                rooms.put("roomStatus_"+ o.toString().substring(o.toString().indexOf("_") + 1),
                        "Available");
                custRoom.remove("roomId_"+ o.toString().substring(o.toString().indexOf("_") + 1));
                int day = DateTime.diffDays(new DateTime(Integer.parseInt(retDate.substring(0, 2)),
                                Integer.parseInt(retDate.substring(3, 4)),Integer.parseInt(retDate.substring(5, 9))),
                        (DateTime) custRoom.get("estRentDate_" + roomId));
                if (day == 0) {
                    rec[cnt] = new HiringRecords(roomId, custRoom.get("custId_" + roomId).toString(),
                            (int) rooms.get("noOfBeds_" + roomId), rooms.get("features_" + roomId).toString(), rooms
                                    .get("roomType_" + roomId).toString(),rooms.get("roomStatus_" + roomId).toString(),
                            custRoom.get("rentDate_" + roomId).toString().substring(0, 3)+ custRoom.get("rentDate_" + roomId)
                                            .toString().substring(3),(int) custRoom.get("noOfDays_" + roomId));
                    getCustLogs().put("Record ID_" + roomId + "@" + cntReturnRooms,rec[cnt].getCrtRecRoom());
                    if (!(rec[cnt].getActRetDate().toString().equalsIgnoreCase(rec[cnt].getEstRetDate().toString()))) {
                        getCustLogs().put("Actual Return Date_" + roomId + "@"+ cntReturnRooms,rec[cnt].getActRetDate());
                    }
                    rec[cnt].data.updateHmap("roomStatus", "Unavailable");
                    getCustLogs().put("Rental Fee_" + roomId + "@" + cntReturnRooms,rec[cnt].getRentalFee());
                    getCustLogs().put("Late Fee_" + roomId + "@" + cntReturnRooms,rec[cnt].getLateFee());
                    getCustLogs().put("Status_" + roomId, "Available");
                    rec[cnt].data.showDetails();
                    cnt += 1;
                    cntReturnRooms += 1;
                } else {
                    rec[cnt] = new HiringRecords(roomId, custRoom.get("custId_" + roomId).toString(),
                            (int) rooms.get("noOfBeds_" + roomId), rooms.get("features_" + roomId).toString(), rooms
                                    .get("roomType_" + roomId).toString(),rooms.get("roomStatus_" + roomId).toString(),
                            custRoom.get("rentDate_" + roomId).toString().substring(0, 3)+ custRoom.get("rentDate_" + roomId)
                                            .toString().substring(3),(int) custRoom.get("noOfDays_" + roomId), day);
                    getCustLogs().put("Record ID_" + roomId + "@" + cntReturnRooms,rec[cnt].getCrtRecRoom());
                    System.out.println(rec[cnt]);
                    rec[cnt].data.updateHmap("roomStatus", "Unavailable");
                    rec[cnt].data.updateHmap(
                            "ActRetDate",retDate.substring(0, 3) + "0"+ retDate.substring(3));
                    if (!(rec[cnt].getActRetDate().toString().equalsIgnoreCase(rec[cnt].getEstRetDate().toString()))) {
                        getCustLogs().put("Actual Return Date_" + roomId + "@"+ cntReturnRooms,rec[cnt].getActRetDate());
                    }
                    getCustLogs().put("Rental Fee_" + roomId + "@" + cntReturnRooms,rec[cnt].getRentalFee());
                    getCustLogs().put("Late Fee_" + roomId + "@" + cntReturnRooms,rec[cnt].getLateFee());
                    getCustLogs().put("Status_" + roomId, "Available");
                    rec[cnt].data.showDetails();
                    cnt += 1;
                    cntReturnRooms += 1;
                }
                if (rooms.get(o).equals(rooms.get("roomType_" + roomId).toString())) {
                    StandardRoom std = new StandardRoom();
                    System.out.println(std);
                } else {
                    SuiteRoom suit = new SuiteRoom();
                    System.out.println(suit);
                }
            }
        }
    }

}
