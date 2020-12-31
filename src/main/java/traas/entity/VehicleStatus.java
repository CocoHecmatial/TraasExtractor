package traas.entity;

/**
 * @author CocoHecmatial
 * @since 2020/5/9 11:01
 **/
public class VehicleStatus {
    public VehicleStatus(String vehicleID, double speed, double angle, String laneInfo, double longitude, double latitude, Long timestamp) {
        this.vehicleID = vehicleID;
        this.speed = speed;
        this.angle = angle;
        this.laneInfo = laneInfo;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getLaneInfo() {
        return laneInfo;
    }

    public void setLaneInfo(String laneInfo) {
        this.laneInfo = laneInfo;
    }

    private String vehicleID;
    private double speed;
    private double angle;
    private String laneInfo;
    private double longitude;
    private double latitude;
    private Long timestamp;
}
