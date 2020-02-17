package com.rmit.actions;

/**
 * 
 * @author bhattj
 *
 */
public abstract class Hotel {

    private static String roomId = null;
    private static int noOfBeds = 0;
    private static String features = null;
    private static String roomType = null;
    private static String roomStatus = null;

    /**
     * Parameter Constructor
     * 
     * @param noOfBeds
     *            noOfBeds
     * @param features
     *            features
     * @param roomType
     *            roomType
     * @param roomStatus
     *            roomStatus
     */
    public Hotel(int noOfBeds, String features, String roomType,
            String roomStatus) {
        // this.count = count;
        Hotel.noOfBeds = noOfBeds;
        setNooFBeds(Hotel.noOfBeds);
        Hotel.features = features;
        setFeatures(Hotel.features);
        Hotel.roomType = roomType;
        setRoomType(Hotel.roomType);
        Hotel.roomStatus = roomStatus;
        setRoomStatus(Hotel.roomStatus);
    }

    /**
     * Default Constructor
     */
    public Hotel() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Get Room Id
     * 
     * @return the roomId
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Set Room Id
     * 
     * @param roomId
     *            the roomId to set
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * Get the Number of Beds
     * 
     * @return the nooFBeds
     */
    public int getNooFBeds() {
        return noOfBeds;
    }

    /**
     * @param nooFBeds
     *            the nooFBeds to set
     */
    public void setNooFBeds(int nooFBeds) {
        this.noOfBeds = nooFBeds;
    }

    /**
     * @return the features
     */
    public String getFeatures() {
        return features;
    }

    /**
     * @param features
     *            the features to set
     */
    public void setFeatures(String features) {
        this.features = features;
    }

    /**
     * @return the roomType
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * @param roomType
     *            the roomType to set
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * @return the roomStatus
     */
    public String getRoomStatus() {
        return roomStatus;
    }

    /**
     * @param roomStatus
     *            the roomStatus to set
     */
    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }
}
