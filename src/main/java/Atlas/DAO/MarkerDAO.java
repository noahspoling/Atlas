package Atlas.DAO;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Atlas.Model.Marker;
import Atlas.Util.ConnectionUtil;

public class MarkerDAO {
    /**
     * 
     * @return List of Marker instances
     */
    public List<Marker> getAllMarkers(){
        Connection connection = ConnectionUtil.getConnection();
        List<Marker> markers = new ArrayList<>();
        try {
            //Get all markers from table marker
            String sql = "SELECT * FROM marker;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Marker marker = new Marker( rs.getInt("marker_id"),
                    rs.getString("label"), rs.getString("description"),
                    rs.getDate("createdAt"), rs.getDate("updatedAt"),
                    rs.getDouble("latitude"), rs.getDouble("longitude"));
                markers.add(marker);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return markers;
    }
    /**
     * TODO gets a marker object by id value
     * 
     * @param id identifing value of marker record
     * @return marker Marker class instance
     */
    public Marker getMarkerById(int id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM marker WHERE marker_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Marker marker = new Marker( rs.getInt("marker_id"),
                rs.getString("label"), rs.getString("description"),
                rs.getDate("createdAt"), rs.getDate("updatedAt"),
                rs.getDouble("latitude"), rs.getDouble("longitude"));
                return marker;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     * TODO inserts marker into database
     * Dates are handled as part of the table structure
     * 
     * @param marker Marker class
     * @return Marker class instance
     */
    public Marker insertMarker(Marker marker) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO marker (label, description, createdAt, updatedAt, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            long time = System.currentTimeMillis();
            ps.setString(1, marker.getMarkerName());
            ps.setString(2, marker.getMarkerDescription());
            ps.setDate(3, new Date(time));
            ps.setDate(4, new Date(time));
            ps.setDouble(5, marker.getLatitude());
            ps.setDouble(6, marker.getLongitude());

            ps.executeUpdate();
            ResultSet resultPrimaryKey = ps.getGeneratedKeys();
            if(resultPrimaryKey.next()){
                int generated_marker_id = (int) resultPrimaryKey.getLong(1);
                //Select by generated id and return instance
                Marker insertedMarker = this.getMarkerById(generated_marker_id);
                return insertedMarker;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     * 
     * @param id identifing value of marker record
     * @param marker Marker class
     */
    public void updateMarker(int id, Marker marker) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "UPDATE marker SET label = ?, description = ?, updatedAt = ?, latitude = ?, longitude = ? WHERE marker_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            long time = System.currentTimeMillis();
            ps.setString(1, marker.getMarkerName());
            ps.setString(2, marker.getMarkerDescription());
            ps.setDate(3, new Date(time));
            ps.setDouble(4, marker.getLatitude());
            ps.setDouble(5, marker.getLongitude());
            ps.setInt(6, id);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * TODO deletes reord from marker table from id value
     * Row is moved to deleted markers table via a trigger on the server
     * @param id identifing value of marker record
     * 
     * @return
     *<pre>-1 if an error occurred
     *      0 if it doesn't exist
     *      1 if record was deleted </pre>
     */
    public int deleteMarker(int id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            //TODO figure out if I want to handle the row on delete in here
            // or as a trigger function in the audit tables
            String sql = "DELETE FROM marker WHERE marker_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int deleted = ps.executeUpdate();
            if (deleted == 0) {
                JOptionPane.showMessageDialog(null, "Marker not found");
                return 0;
            }
            return 1;

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    /**
     * TODO returns a list of markers by group id value
     * @param id identifing value of group record
     * 
     * @return List of Marker instances
     */
    public List<Marker> getMarkersByGroup(int id){
        return null;
    }

}
