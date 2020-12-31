package traas.entity;

/**
 * @author CocoHecmatial
 * @since 2020/5/11 9:40
 **/
public class CollidedVehicle {

    public CollidedVehicle(String collideVehicleID, double longitude, double latitude, Long collideTimestamp) {
        this.collideVehicleID = collideVehicleID;
        this.longitude = longitude;
        this.latitude = latitude;
        this.collideTimestamp = collideTimestamp;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCollideVehicleID() {
        return collideVehicleID;
    }

    public void setCollideVehicleID(String collideVehicleID) {
        this.collideVehicleID = collideVehicleID;
    }

    public Long getCollideTimestamp() {
        return collideTimestamp;
    }

    public void setCollideTimestamp(Long collideTimestamp) {
        this.collideTimestamp = collideTimestamp;
    }

    public Long getRecoverTimestamp() {
        return recoverTimestamp;
    }

    public void setRecoverTimestamp(Long recoverTimestamp) {
        this.recoverTimestamp = recoverTimestamp;
    }

    private String collideVehicleID;
    private double longitude;
    private double latitude;
    private Long collideTimestamp;
    private Long recoverTimestamp;
}
