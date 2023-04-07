package Atlas.Model;
import java.sql.Date;
import java.util.Objects;

public class Marker {
    private int markerId;
    private String markerName;
    private String markerDescription;
    private Date createdAt;
    private Date updatedAt;
    private double latitude;
    private double longitude;

    /**
     * TODO Returns an empty marker instance
     * 
     */
    public Marker() {

    }
    /**
     * TODO Returns an marker instance without an id for adding new markers
     * 
     * @param markerName
     * @param markerDescription
     * @param latitude
     * @param longitude
     * 
     * @return 
     */
    public Marker(String markerName, String markerDescription, double latitude, double longitude) {
        this.markerName = markerName;
        this.markerDescription = markerDescription;
        //If latitude is in range
        if(latitude >= -90 && latitude <= 90) {
            this.latitude = latitude;
        }
        else {
            throw new Error("Error: Input latitude not in range of -90 to 90");
        }
        //If longitude is in range
        if (longitude >= -180 && longitude <= 180) {
            this.longitude = longitude;
        }
        else {
            throw new Error("Error: Input longitude not in range of -180 to 180");
        }

    }
    /**
     * TODO Returns an marker instance without an id for adding new markers
     * 
     * @param markerId
     * @param markerName
     * @param markerDescription
     * @param createdAt
     * @param updatedAt
     * @param latitude
     * @param longitude
     */
    public Marker(int markerId, String markerName, String markerDescription,
        double latitude, double longitude) {

        this.markerId = markerId;
        this.markerName = markerName;
        this.markerDescription = markerDescription;
        if(latitude >= -90 && latitude <= 90) {
            this.latitude = latitude;
        }
        else {
            throw new Error("Error: Input latitude not in range of -90 to 90");
        }
        if (longitude >= -180 && longitude <= 180) {
            this.longitude = longitude;
        }
        else {
            throw new Error("Error: Input longitude not in range of -180 to 180");
        }
    }

    /**
     * TODO Returns an marker instance without an id for adding new markers
     * 
     * @param markerId
     * @param markerName
     * @param markerDescription
     * @param createdAt
     * @param updatedAt
     * @param latitude
     * @param longitude
     */
    public Marker(int markerId, String markerName, String markerDescription,
        Date createdAt, Date updatedAt, double latitude, double longitude) {

        this.markerId = markerId;
        this.markerName = markerName;
        this.markerDescription = markerDescription;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        if(latitude >= -90 && latitude <= 90) {
            this.latitude = latitude;
        }
        else {
            throw new Error("Error: Input latitude not in range of -90 to 90");
        }
        if (longitude >= -180 && longitude <= 180) {
            this.longitude = longitude;
        }
        else {
            throw new Error("Error: Input longitude not in range of -180 to 180");
        }
    }
    /**
     * TODO Gets the ID of a marker instance
     * 
     * @return int markerId
     */
    public int getMarkerId(){
        return this.markerId;
    }
    /**
     * TODO Sets the ID of a marker instance
     * 
     * @param markerId
     * 
     * @return void
     */
    public void setMarkerId(int markerId){
        this.markerId = markerId;
    }
    public String getMarkerName(){
        return this.markerName;
    }
    public void setMarkerName(String markerName){
        this.markerName = markerName;
    }
    public String getMarkerDescription(){
        return this.markerDescription;
    }
    public void setMarkerDescription(String markerDescription){
        this.markerDescription = markerDescription;
    }
    public Date getDateCreated(){
        return this.createdAt;
    }
    public void setDateCreated(Date createdAt){
        this.createdAt = createdAt;
    }
    public Date getDateUpdated(){
        return this.updatedAt;
    }
    public void setDateUpdated(String markerDescription){
        this.markerDescription = markerDescription;
    }
    public double getLatitude(){
        return this.latitude;
    }
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    public double getLongitude(){
        return this.longitude;
    }
    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Marker{" +
                "marker_id='" + this.markerId + '\'' +
                ", label='" + this.markerName + '\'' +
                ", description='" + this.markerDescription + '\'' +
                ", createdAt='" + this.createdAt + '\'' +
                ", updatedAt='" + this.updatedAt + '\'' +
                ", latitude='" + this.latitude + '\'' +
                ", longitude='" + this.longitude + '\'' +
                '}';
    }
}
