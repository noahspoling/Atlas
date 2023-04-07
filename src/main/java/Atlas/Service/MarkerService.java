package Atlas.Service;

import java.util.List;

import Atlas.DAO.MarkerDAO;
import Atlas.Model.Marker;

public class MarkerService {
    MarkerDAO markerDAO;
    public int delete_service_response;

    /**
     * Intializes a service from no parameters
     */

    public MarkerService(){
        this.markerDAO = new MarkerDAO();
    }

    /**
     * Constructor for testing independently of Database Access Object
     * 
     * @param markerDAO
     */

    public MarkerService(MarkerDAO markerDAO) {
        this.markerDAO = markerDAO;
    }

    /**
     * TODO: Use the Database Access Object to add a new marker to database
     * 
     * @param marker Marker class instance
     * @return marker object that was added to the marker table
     */

    public Marker postMarker(Marker marker) {
        return this.markerDAO.insertMarker(marker);
    }

    /**
     * 
     * @param marker_id
     * @param marker
     * 
     * @return Marker class instance
     */

    public Marker updateMarker(int marker_id, Marker marker) {
        if(this.markerDAO.getMarkerById(marker_id) == null){
            return null;
        }
        else {
            this.markerDAO.updateMarker(marker_id, marker);
            return this.markerDAO.getMarkerById(marker_id);
        }
    }

    /**
     * TODO Deletes marker from the database
     * @param marker_id
     * 
     */

    public int deleteMarker(int marker_id) {
        return this.markerDAO.deleteMarker(marker_id);
    }

    /**
     * TODO: Gets Marker by id value
     * 
     * @param marker_id
     * @return Marker class instance
     */

    public Marker getMarker(int marker_id) {
        return this.markerDAO.getMarkerById(marker_id);
    }

    /**
     * Retrieves a list of marker objects in the table marker
     * 
     * @return List of marker instances in database
     */

    public List<Marker> getAllMarkers()  {
        return this.markerDAO.getAllMarkers();
    }

    /**
     * TODO: Gets List of Markers by group id
     * 
     * @param group_id
     * 
     * @return list of markers in a group
     */

    public List<Marker> getMarkersFromGroupId(int group_id){
        return this.markerDAO.getMarkersByGroup(group_id);
    }
}
