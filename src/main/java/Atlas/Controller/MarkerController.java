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
        app.put("/markers/{marker_id}", this::updateMarkerHandler);
        app.get("/markers", this::getAllMarkersHandler);
        app.get("/markers/{marker_id}", this::getMarkerHandler);
        app.delete("/markers/{marker_id}", this::deleteMarkerHandler);
        
        return app;
    }
    /**
     * 
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin. It will
     *            be available to this method automatically thanks to the app.put method.
     */
    private void getAllMarkersHandler(Context ctx){
        System.out.println("This is a test");
        System.out.println(ctx);
        ctx.json(markerService.getAllMarkers());
    };
    /**
     * Gets the id from the path param and returns the object from the database.
     * 
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin. It will
     *            be available to this method automatically thanks to the app.put method.
     */
    private void getMarkerHandler(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("marker_id"));
        Marker marker = markerService.getMarker(id);
        if (marker != null) {
            ctx.json(marker);
        } else {
            ctx.status(400).result("Marker not found");
        }
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
     * Handler for deleting a marker from the database gets marker from REST argument marker_id to get marker object
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin. It will
     *            be available to this method automatically thanks to the app.put method.
     * @return void
     */
    private void deleteMarkerHandler(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("marker_id"));
        Marker marker = markerService.getMarker(id);

        // delete marker service does return and int between -1 and 1 however this might be better handled here.
        if (marker != null) {
            markerService.deleteMarker(id);
        } else {
            ctx.status(400).result("Marker not found");
        }
        
    }


}
