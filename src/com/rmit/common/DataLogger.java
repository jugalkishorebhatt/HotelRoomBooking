package com.rmit.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.rmit.actions.HiringRecords;

/**
 * 
 * @author bhattj
 *
 */
public class DataLogger extends HiringRecords {

    public static HashMap<String, Object> hmap = null;
    List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

    /**
     * Default Constructor
     */
    public DataLogger() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Put Data
     */
    public void putData() {
        hmap = new HashMap<String, Object>();
        hmap.put("RoomId", super.getRoomId());
        hmap.put("noOfBed", super.getNooFBeds());
        hmap.put("features", super.getFeatures());
        hmap.put("roomStatus", super.getRoomStatus());
        hmap.put("roomType", super.getRoomType());
        hmap.put("RecordId", super.getRecordId());
        hmap.put("RentDate", super.getStartDate());

        if (!(super.getActRetDate().toString()
                .equalsIgnoreCase(super.getEstRetDate().toString()))) {
            hmap.put("ActRetDate", super.getActRetDate());
        }
        hmap.put("EstRetDate", super.getEstRetDate());
        hmap.put("RentalFee", super.getRentalFee());
        hmap.put("LateFee", super.getLateFee());
        hmap.put("crtRecRoom", super.getCrtRecRoom());
        /* hmap.put("maintanceFlag", super.isFlag()); */
        hmap.put("lastMainDate", super.getLastMainDate());
    }


    /**
     * Show Details for the customer
     */
    public void showDetails() {
        Set<String> keySet = hmap.keySet();
        Iterator<String> keySetIterator = keySet.iterator();
        while (keySetIterator.hasNext()) {
            String key = keySetIterator.next();
            System.out.println(key + ":\t" + getDetails(key));
        }
    }

    /**
     * Get Details for fields
     * @param field hmap
     * @return
     */
    public Object getDetails(Object field) {
        return hmap.get(field);
    }

    /**
     * Update hmap 
     * @param key key
     * @param value value
     */
    public void updateHmap(String key, String value){
        hmap.put(key, value);
    }
    
    /**
     * Get Customer,Record id,Room combination 
     * @return crtRecRoom
     */
    public static String getCrtRecRoom() {
        return hmap.get("crtRecRoom").toString();
    }
}
