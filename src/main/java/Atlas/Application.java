package Atlas;

import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Atlas.Controller.BaseController;
import Atlas.Util.ConnectionUtil;

/**
 * There is no need to modify anything in this class.
 * The main method will start a new Javalin API on the console at localhost:8080.
 * Take a look at the MarkerController class for API documentation as well as instructions for how to
 * access the API endpoints.
 */
public class Application {
    /**
     * You can run this main method to run the API.
     * @param args
     */
    public static void main(String[] args) {
        databaseSetup();
        BaseController baseController = new BaseController();
        Javalin app = baseController.startAPI();
        app.start(8080);
    }
    /**
     */
    public static void databaseSetup(){
        try {
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps2 = conn.prepareStatement("drop table if exists marker");
            ps2.executeUpdate();
            PreparedStatement ps3 = conn.prepareStatement("create table marker(" +
                    "marker_id int primary key auto_increment, " +
                    "label varchar(50) NOT NULL, " +
                    "description varchar(255), " +
                    "createdAt datetime NOT NULL, " +
                    "updatedAt datetime NOT NULL, " +
                    "latitude double NOT NULL, "+
                    "longitude double NOT NULL);");
            ps3.executeUpdate();
            PreparedStatement ps4 = conn.prepareStatement("insert into marker " +
                    "(label, description, createdAt, updatedAt, latitude, longitude) values " +
                    "('marker 1', 'test marker', '2008-11-11 11:12:01', '2008-11-11 11:12:01', '13.4334', '23.2342')," +
                    "('marker 2', 'test marker', '2008-11-11 11:12:01', '2008-11-11 11:12:01', '13.4334', '23.2342')," +
                    "('marker 3', 'test marker', '2008-11-11 11:12:01', '2008-11-11 11:12:01', '43.4534', '23.2342')," +
                    "('marker 4', 'test marker', '2008-11-11 11:12:01', '2008-11-11 11:12:01', '24.4335', '23.2342')," +
                    "('marker 5', 'test marker', '2008-11-11 11:12:01', '2008-11-11 11:12:01', '13.4334', '23.2342');");
            ps4.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
