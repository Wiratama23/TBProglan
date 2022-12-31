package code;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class jadwalController implements Initializable {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    @FXML
    private WebView webView;

    @FXML
    private Pane backbut;

    @FXML
    private Pane forwardbut;

    @FXML
    private Pane homebt;

    @FXML
    private Pane refreshbut;

    @FXML
    private TextField searchtf;

    @FXML
    private HBox hboxt;

    private WebEngine engine;
    private WebHistory history;

    private String homepage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        engine = webView.getEngine();
        homepage = "google";
        searchtf.setText(homepage);
        hboxt.getChildren().remove(forwardbut);
        //engine.load("https://"+searchtf.getText());
        loadPage();
    }

    public void checking(){
        dbConnect test = new dbConnect();
        if(test.test_connection()){
            loadPage();
        }
    }
    public void loadPage() {
        if ((searchtf.getText().contains("www.") && searchtf.getText().contains("https://")) && searchtf.getText().contains(".com")) {
            engine.load(searchtf.getText());
            searchtf.setText(searchtf.getText());
        } else if (searchtf.getText().contains("www.") && searchtf.getText().contains(".com")) {
            engine.load("https://" + searchtf.getText());
            searchtf.setText("https://" + searchtf.getText());
        } else if (searchtf.getText().contains(".com")) {
            engine.load("https://www." + searchtf.getText());
            searchtf.setText("https://www." + searchtf.getText());
        }else if(searchtf.getText().isEmpty()){
            history = engine.getHistory();
            ObservableList<WebHistory.Entry> entries = history.getEntries();
            searchtf.setText(entries.get(history.getCurrentIndex()).getUrl());
            alert.setHeaderText("Illegal Action");
            alert.setContentText("Textfield kosong");
            alert.show();
        }
        else {
            engine.load("https://www." + searchtf.getText() + ".com");
            searchtf.setText("https://www." + searchtf.getText() + ".com");
        }
    }

    public void refreshPage() {
        engine.reload();
    }

    public void homePage() {
        searchtf.setText(homepage);
        loadPage();
    }

    public void back() {
        hboxt.getChildren().add(1, forwardbut);
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(-1);
        searchtf.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    public void forward() {
        hboxt.getChildren().remove(forwardbut);
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(1);
        searchtf.setText(entries.get(history.getCurrentIndex()).getUrl());
    }
}
