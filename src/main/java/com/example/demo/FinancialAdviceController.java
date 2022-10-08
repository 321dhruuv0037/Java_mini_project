package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.Timestamp;

import static com.example.demo.HelloController.getUsername;

public class FinancialAdviceController extends NullPointerException {

    @FXML
    private DatePicker calender;

    @FXML
    private Label errorDate;

    @FXML
    private Label errorRegarding;

    @FXML
    private Label errorTime;

    @FXML
    private MenuItem fd;

    @FXML
    private MenuItem gold;

    @FXML
    private MenuItem loan;

    @FXML
    private MenuItem lumpsum;

    @FXML
    private MenuButton menu;

        @FXML
    private TextField note;

    @FXML
    private MenuItem other;

    @FXML
    private MenuButton regarding;

    @FXML
    private MenuItem sip;

    @FXML
    private MenuItem t13_t16;

    @FXML
    private MenuItem t17_t20;

    @FXML
    private MenuItem t9_t12;

    public String time_slot = null;
    public String date = null;
    public String choice = null;

    public String username = getUsername();


    public void onSubmitButtonClick(ActionEvent event){
        date=String.valueOf(calender.getValue());
        System.out.println(date);
        choice=choice;
        time_slot=time_slot;
        System.out.println("Button clicked");
        if (choice!=null && date!=null && time_slot!=null){
            System.out.println("validating");
            insertRequest(event);
        }
        else {
            if (choice==null){
                //System.out.println("Choice error");
                errorRegarding.setText("⚠ Invalid input!");
                //regarding.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorRegarding.setText(null);
                //regarding.setStyle(null);
            }
            if (date==null){
                errorDate.setText("⚠ Please select date!");
            }
            else {
                errorDate.setText(null);
            }
            if (time_slot==null){
                //System.out.println("Choice error");
                errorTime.setText("⚠ Please select time slot!");
            }
            else {
                errorTime.setText(null);
            }
        }
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
        //date = dd.getText()+"/"+mm.getText()+"/"+yyyy.getText();
        System.out.println(date);
        String insertDetails = "INSERT INTO demo.financial_advice (`Username`, `Regarding`, `Date`, `Time_slot`, `Note`) VALUES ('"+username+"', '"+choice+"', '"+date+"', '"+time_slot+"', '"+note.getText()+"'\n)";
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

    public void fd(ActionEvent event){
        regarding.setText("FD");
        choice="FD";
    }
    public void sip(ActionEvent event){
        regarding.setText("SIP");
        choice="SIP";
    }
    public void lumpsum(ActionEvent event){
        regarding.setText("LUMP SUM");
        choice="LUMP SUM";
    }
    public void loan(ActionEvent event){
        regarding.setText("LOAN");
        choice="LOAN";
    }
    public void gold(ActionEvent event){
        regarding.setText("GOLD");
        choice="GOLD";
    }
    public void other(ActionEvent event){
        regarding.setText("OTHER");
        choice="OTHER";
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
