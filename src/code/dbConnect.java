package code;

import javafx.scene.control.Alert;

import java.net.URL;
import java.net.URLConnection;

public class dbConnect {
    Alert alert = new Alert(Alert.AlertType.WARNING);

    public boolean test_connection(){
        try {
            URL url = new URL("https://www.google.com/");
            URLConnection connection = url.openConnection();
            connection.connect();
            System.out.println("Connection Successful");
            return true;
        }
        catch (Exception e) {
            alert.setHeaderText("No Internet");
            alert.setContentText("Harap terhubung dengan internet");
            alert.show();
            System.out.println("Internet Not Connected");
        }
        return false;
    }
}
