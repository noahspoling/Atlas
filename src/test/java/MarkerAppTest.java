import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import Atlas.Application;
import Atlas.DAO.MarkerDAO;
import Atlas.Model.Marker;
import Atlas.Service.MarkerService;

import java.util.ArrayList;
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

    
}

