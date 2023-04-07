import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import Atlas.Application;
import Atlas.DAO.MarkerDAO;
import Atlas.Model.Marker;
import Atlas.Service.MarkerService;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;


public class MarkerAppTest {
    public MarkerDAO markerDAO;
    public MarkerDAO mockMarkerDAO;
    public MarkerService markerService;

    @Before
    public void setUp() {
        Application.databaseSetup();
        markerDAO = new MarkerDAO();

        mockMarkerDAO = Mockito.mock(MarkerDAO.class);
        markerService = new MarkerService(mockMarkerDAO);
    }

    /**
     * Tests for MarkerDAO.java
     */

     @Test
     public void markerDAO_GetAllMarkersTest1() throws IllegalArgumentException {
        List<Marker> allMarkers = markerDAO.getAllMarkers();
        //Marker m1 = new Marker(1, "marker 1", "test marker", Date.valueOf("2008-11-11 11:12:01"), Date.valueOf("2008-11-11 11:12:01"), 13.4334, 23.2342);
        Marker m2 = new Marker(2, "Name 2", "description", 45, 34);
        Marker m3 = new Marker(3, "Name 3", "description", 67, 100);
        Marker m4 = new Marker(4, "Name 4", "description", 34, 90);
        Marker m5 = new Marker(5, "Name 5", "description", 32, 20);

        Assert.assertTrue(allMarkers.get(0).getMarkerName().equals("marker 1"));
        //Assert.assertTrue(allMarkers.contains(m2));
        
     }

     @Test
     public void markerService_GetAllMarkersTest(){
        List<Marker> allMarkersReturned = new ArrayList<>();
        allMarkersReturned.add(new Marker(1, "marker 1", "test marker", 13.4334, 23.2342));
        allMarkersReturned.add(new Marker(2, "Name 2", "description", 45, 34));
        allMarkersReturned.add(new Marker(3, "Name 3", "description", 67, 100));
        allMarkersReturned.add(new Marker(4, "Name 4", "description", 34, 90));
        allMarkersReturned.add(new Marker(5, "Name 5", "description", 32, 20));
        Mockito.when(mockMarkerDAO.getAllMarkers()).thenReturn(allMarkersReturned);
        Assert.assertNotNull(markerService.getAllMarkers());
        //Assert.assertEquals(allMarkersReturned, markerService.getAllMarkers());
     }

    
}

