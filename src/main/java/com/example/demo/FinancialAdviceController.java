package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import static com.example.demo.HelloController.getUsername;

public class FinancialAdviceController extends NullPointerException {

    @FXML
    private TextField dd;

    @FXML
    private MenuButton menu;

    @FXML
    private TextField mm;

    @FXML
    private TextField note;

    @FXML
    private TextField regarding;

    @FXML
    private MenuItem t13_t16;

    @FXML
    private MenuItem t17_t20;

    @FXML
    private MenuItem t9_t12;

    @FXML
    private TextField yyyy;
    @FXML
    private Label errorDate;

    @FXML
    private Label errorRegarding;

    @FXML
    private Label errorTime;

    public String time_slot = null;
    public String date = null;
    public String username = getUsername();
    public void onSubmitButtonClick(ActionEvent event){
        System.out.println("Button clicked");
        if (!regarding.getText().isBlank() && !dd.getText().isBlank() && !mm.getText().isBlank() && !yyyy.getText().isBlank() && !time_slot.equals(null)){
            System.out.println("validating");
            insertRequest(event);
        }
        else {
            if (regarding.getText().isBlank()){
                errorRegarding.setText("⚠ Invalid input!");
                regarding.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorRegarding.setText(null);
                regarding.setStyle(null);
            }
            if (dd.getText().isBlank() || mm.getText().isBlank() || yyyy.getText().isBlank()){
                errorDate.setText("⚠ Please enter date!");
                dd.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                mm.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                yyyy.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorDate.setText(null);
                dd.setStyle(null);
                mm.setStyle(null);
                yyyy.setStyle(null);
            }
            if (time_slot.equals(null)){
                errorTime.setText("⚠ Please select time slot!");
            }
            else {
                errorTime.setText(null);
            }
        }
    }

    private void wrapText(ActionEvent event){
        note.setMaxWidth(381);
    }

    private void insertRequest(ActionEvent event) {
        System.out.println("Inside function signup");
        DBConnect connectnow = new DBConnect();
        Connection connectdb = connectnow.getConnection();
        Statement statement = null;
        System.out.println(username);
        if (note.getText().isBlank()) {
            note.setText("");
        }
        date = dd.getText()+"/"+mm.getText()+"/"+yyyy.getText();
        System.out.println(date);
        String insertDetails = "INSERT INTO demo.financial_advice (`Username`, `Regarding`, `Date`, `Time_slot`, `Note`) VALUES ('"+username+"', '"+regarding.getText()+"', '"+date+"', '"+time_slot+"', '"+note.getText()+"'\n)";
        try {
            System.out.println("inside try");
            statement = connectdb.createStatement();
            int a = statement.executeUpdate(insertDetails);
            if (a == 1 ) {
                System.out.println("Inserted data!");
            }
            else{
                System.out.println("Failed to insert data");
            }
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml")); //pass scene name here
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void t9_t12(ActionEvent event){
        menu.setText("9:00-12:00");
        time_slot="9:00-12:00";
    }

    public void t13_t16(ActionEvent event){
        menu.setText("13:00-16:00");
        time_slot="13:00-16:00";
    }

    public void t17_t20(ActionEvent event){
        menu.setText("17:00-20:00");
        time_slot="17:00-20:00";
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
