package Atlas.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Atlas.Model.Marker;
import Atlas.Controller.*;
import Atlas.Service.MarkerService;
import Atlas.Controller.MarkerController;
import io.javalin.Javalin;
import io.javalin.community.ssl.SSLPlugin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import io.javalin.http.Handler;



/**
 * Base controller class to handle the configuration of the jettyserver, access manager, cors, etc.
 * 
 */

public class BaseController {
    private MarkerController mc;
    public BaseController(){
        this.mc = new MarkerController();
    }
        public Javalin startAPI(){
            Javalin app = Javalin.create(config -> {
                config.plugins.enableCors(cors -> {
                    cors.add(it -> {
                        it.anyHost();
                    });
                });
            });
            mc.addRoutes(app);

            return app;
        }

}