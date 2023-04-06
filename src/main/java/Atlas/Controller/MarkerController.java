package Atlas.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Atlas.Model.Marker;
import Atlas.Service.MarkerService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class MarkerController {
    MarkerService markerService;

    public MarkerController(){
        markerService = new MarkerService();
    }

    public Javalin startAPI(){
        Javalin app = Javalin.create();
        app.post("/markers", this::postMarkerHandler);
        app.put("/markers/{flight_id}", this::updateMarkerHandler);
        app.get("/markers", this::getAllMarkersHandler);
        app.get("/markers/{marker_id}", this::getMarkerHandler);
        app.delete("/markers/{markers_id}", this::deleteMarkerHandler);
        
        return null;
    }
    /**
     * 
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin. It will
     *            be available to this method automatically thanks to the app.put method.
     */
    private void getAllMarkersHandler(Context ctx){
        ctx.json(markerService.getAllMarkers());
    };
    /**
     * REQUIRES WORK
     * @param ctx
     */
    private void getMarkerHandler(Context ctx){
        System.out.println(ctx.body());
        ctx.json(markerService.getMarker(0));
    }

    /**
     * Handler for posting a new Marker
     * Jackson Object Mapper turns json from post request into a Java Object.
     * If null then return client error.
     * 
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin. It will
     *            be available to this method automatically thanks to the app.put method.
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    private void postMarkerHandler(Context ctx) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Marker marker;
        marker = mapper.readValue(ctx.body(), Marker.class);
        Marker addedMarker = markerService.postMarker(marker);
        if (addedMarker == null) {
            ctx.status(400);
        } else {
            ctx.json(mapper.writeValueAsString(addedMarker));
        }


    }
    /**
     * 
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin. It will
     *            be available to this method automatically thanks to the app.put method.
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    private void updateMarkerHandler(Context ctx) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Marker marker = mapper.readValue(ctx.body(), Marker.class);
        int marker_id = Integer.parseInt(ctx.pathParam("marker_id"));
        Marker updatedMarker = markerService.updateMarker(marker_id, marker);
        System.out.println(updatedMarker);
        if(updatedMarker == null) {
            ctx.status(400);
        }
        else {
            ctx.json(mapper.writeValueAsString(updatedMarker));
        }

    }
    /**
     * Handler for deleting a marker from the database
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin. It will
     *            be available to this method automatically thanks to the app.put method.
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    private void deleteMarkerHandler(Context ctx) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Marker marker = mapper.readValue(ctx.body(), Marker.class);
        int marker_id = Integer.parseInt(ctx.pathParam("marker_id"));
        Marker deleted_marker = markerService.getMarker(marker_id);
        int delete_service_response = markerService.deleteMarker(marker_id);
        System.out.println(delete_service_response);
        if(delete_service_response == -1 || delete_service_response == 0) {
            ctx.status(400);
        }
        else {
            ctx.json(mapper.writeValueAsString(deleted_marker));
        }
    }


}
