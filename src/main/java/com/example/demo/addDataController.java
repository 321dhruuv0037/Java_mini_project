package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import static com.example.demo.HelloController.getUsername;

public class addDataController {
    @FXML
    private Label errorInvested;

    @FXML
    private Label errorRate;

    @FXML
    private Label errorReturns;

    @FXML
    private Label errorTime;

    @FXML
    private Label errorType;

    @FXML
    private MenuItem fd;

    @FXML
    private MenuItem gold;

    @FXML
    private TextField invested;

    @FXML
    private MenuItem lumpsum;

    @FXML
    private TextField rate;

    @FXML
    private MenuButton regarding;

    @FXML
    private TextField returns;

    @FXML
    private MenuItem sip;

    @FXML
    private TextField time;

    public String username = getUsername();
    public String choice = null;

    public void onSubmitButtonClick(ActionEvent event){
        choice=choice;
        if (choice!=null && !invested.getText().isBlank() && !rate.getText().isBlank() && !time.getText().isBlank() && !returns.getText().isBlank()){
            System.out.println("validating");
            addValues(event);
        }
        else {
            if (choice==null){
                //System.out.println("Choice error");
                errorType.setText("⚠ Invalid input!");
                //regarding.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorType.setText(null);
                //regarding.setStyle(null);
            }
            if (invested.getText().isBlank()){
                errorInvested.setText("⚠ Please enter amount");
                invested.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorInvested.setText("");
                invested.setStyle(null);
            }
            if (rate.getText().isBlank()){
                errorRate.setText("⚠ Please enter rate");
                rate.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorRate.setText("");
                rate.setStyle(null);
            }
            if (time.getText().isBlank()){
                errorTime.setText("⚠ Please enter time(yrs)");
                time.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorTime.setText("");
                time.setStyle(null);
            }
            if (returns.getText().isBlank()){
                errorReturns.setText("⚠ Please enter returns");
                returns.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorReturns.setText("");
                returns.setStyle(null);
            }

        }
    }
    public void onCalculateButtonClick(ActionEvent event){
        choice=choice;
        if (choice!=null && !invested.getText().isBlank() && !rate.getText().isBlank() && !time.getText().isBlank()){
            System.out.println("validating");
            calculateValues(event);
        }
        else {
            if (choice==null){
                //System.out.println("Choice error");
                errorType.setText("⚠ Invalid input!");
                //regarding.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorType.setText(null);
                //regarding.setStyle(null);
            }
            if (invested.getText().isBlank()){
                errorInvested.setText("⚠ Please enter amount");
                invested.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorInvested.setText("");
                invested.setStyle(null);
            }
            if (rate.getText().isBlank()){
                errorRate.setText("⚠ Please enter rate");
                rate.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorRate.setText("");
                rate.setStyle(null);
            }
            if (time.getText().isBlank()){
                errorTime.setText("⚠ Please enter time(yrs)");
                time.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorTime.setText("");
                time.setStyle(null);
            }
        }
    }

    private void calculateValues(ActionEvent event) {
        try {
            double p = Double.parseDouble(invested.getText());

            double t = Double.parseDouble(time.getText());

            double r = Double.parseDouble(rate.getText());

            if (p < 0.1 || r < 0.1 || t < 0.1) {
                if (p<0.1){
                    errorInvested.setText("⚠ Invalid input");
                    invested.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");

                }
                else {
                    errorInvested.setText("");
                    invested.setStyle(null);
                }
                if (r<0.1) {
                    errorRate.setText("⚠ Invalid input");
                    rate.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
                }
                else {
                    errorRate.setText("");
                    rate.setStyle(null);
                }
                if (t<0.1) {
                    errorTime.setText("⚠ Invalid input");
                    time.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
                }
                else {
                    errorTime.setText("");
                    time.setStyle(null);
                }
                returns.setText("");
            } else {
                if (choice=="FD"){
                    double interest;
                    float final_val;
                    interest = (p * t * r) / 100;
                    final_val = (float) (interest + p);

                    returns.setText(String.valueOf(final_val));
                    invested.setStyle(null);
                    rate.setStyle(null);
                    time.setStyle(null);
                    returns.setStyle(null);
                    errorType.setText("");
                    errorReturns.setText("");
                    errorInvested.setText("");
                    errorRate.setText("");
                    errorTime.setText("");
                } else if (choice=="LUMP SUM") {
                    float final_val;
                    final_val = (float) (p * (Math.pow((1 + r / 100), t)));

                    returns.setText(String.valueOf(final_val));
                    invested.setStyle(null);
                    rate.setStyle(null);
                    time.setStyle(null);
                    returns.setStyle(null);
                    errorType.setText("");
                    errorReturns.setText("");
                    errorInvested.setText("");
                    errorRate.setText("");
                    errorTime.setText("");

                } else if (choice=="GOLD") {
                    float final_val;
                    final_val = (float) (p * (Math.pow((1 + r / 100), t)));

                    returns.setText(String.valueOf(final_val));
                    invested.setStyle(null);
                    rate.setStyle(null);
                    time.setStyle(null);
                    returns.setStyle(null);
                    errorType.setText("");
                    errorReturns.setText("");
                    errorInvested.setText("");
                    errorRate.setText("");
                    errorTime.setText("");
                }
            }

        }catch (NumberFormatException e){
            errorInvested.setText("⚠ Invalid input");
            errorRate.setText("⚠ Invalid input");
            errorTime.setText("⚠ Invalid input");
            returns.setText("");
            invested.setText("");
            rate.setText("");
            time.setText("");
            invested.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            rate.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            time.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
        }


    }

    private void addValues(ActionEvent event) {
        System.out.println("Inside function ");
        DBConnect connectnow = new DBConnect();
        Connection connectdb = connectnow.getConnection();
        Statement statement = null;
        System.out.println(username);
        try {
            double p = Double.parseDouble(invested.getText());

            double t = Double.parseDouble(time.getText());

            double r = Double.parseDouble(rate.getText());

            double ret = Double.parseDouble(returns.getText());
            if (p < 0.1 || r < 0.1 || t < 0.1) {
                if (p<0.1){
                    errorInvested.setText("⚠ Invalid input");
                    invested.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");

                }
                else {
                    errorInvested.setText("");
                    invested.setStyle(null);
                }
                if (r<0.1) {
                    errorRate.setText("⚠ Invalid input");
                    rate.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
                }
                else {
                    errorRate.setText("");
                    rate.setStyle(null);
                }
                if (t<0.1) {
                    errorTime.setText("⚠ Invalid input");
                    time.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
                }
                else {
                    errorTime.setText("");
                    time.setStyle(null);
                }
                returns.setText("");
            } else {
                if (choice=="FD"){
                    double interest;
                    float final_val;
                    interest = (p * t * r) / 100;
                    final_val = (float) (interest + p);

                    returns.setText(String.valueOf(final_val));
                    invested.setStyle(null);
                    rate.setStyle(null);
                    time.setStyle(null);
                    returns.setStyle(null);
                    errorType.setText("");
                    errorReturns.setText("");
                    errorInvested.setText("");
                    errorRate.setText("");
                    errorTime.setText("");
                } else if (choice=="LUMP SUM") {
                    float final_val;
                    final_val = (float) (p * (Math.pow((1 + r / 100), t)));

                    returns.setText(String.valueOf(final_val));
                    invested.setStyle(null);
                    rate.setStyle(null);
                    time.setStyle(null);
                    returns.setStyle(null);
                    errorType.setText("");
                    errorReturns.setText("");
                    errorInvested.setText("");
                    errorRate.setText("");
                    errorTime.setText("");

                } else if (choice=="GOLD") {
                    float final_val;
                    final_val = (float) (p * (Math.pow((1 + r / 100), t)));

                    returns.setText(String.valueOf(final_val));
                    invested.setStyle(null);
                    rate.setStyle(null);
                    time.setStyle(null);
                    returns.setStyle(null);
                    errorType.setText("");
                    errorReturns.setText("");
                    errorInvested.setText("");
                    errorRate.setText("");
                    errorTime.setText("");
                }
                String insertDetails = "INSERT INTO demo.portfolio (`Username`, `Type`, `Invested`, `Rate`, `Time`, `Returns`) VALUES ('" + username + "', '" + choice + "', '" + invested.getText() + "', '" + rate.getText() + "', '" + time.getText() + "', '" + returns.getText() + "'\n)";
                try {
                    System.out.println("inside try");
                    statement = connectdb.createStatement();
                    int a = statement.executeUpdate(insertDetails);
                    if (a == 1) {
                        System.out.println("Inserted data!");
                    } else {
                        System.out.println("Failed to insert data");
                    }
                    Parent root = FXMLLoader.load(getClass().getResource("table.fxml")); //pass scene name here
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();
                }
            }
        }
        catch (NumberFormatException e){
            errorReturns.setText("⚠ Invalid input");
            returns.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
        }

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
    public void gold(ActionEvent event){
        regarding.setText("GOLD");
        choice="GOLD";
    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToPortfolio(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("table.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
