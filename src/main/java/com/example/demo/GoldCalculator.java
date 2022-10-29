package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import static com.example.demo.HelloController.getUsername;

public class GoldCalculator extends NullPointerException {

    @FXML
    private Label errorInvested;

    @FXML
    private Label errorRate;

    @FXML
    private Label errorTime;

    @FXML
    private TextField invested;

    @FXML
    private TextField rate;

    @FXML
    private TextField returns;

    @FXML
    private TextField time;
    @FXML
    private Label errorText;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public String username=getUsername();
    public String choice="GOLD";

    public void onCalculateButtonClick(ActionEvent event) throws IOException{
        if (!invested.getText().isBlank() && !time.getText().isBlank() && !rate.getText().isBlank()){
            calculateLumpSum(event);
        }
        else if (invested.getText().isBlank() && time.getText().isBlank() && rate.getText().isBlank()) {
            errorInvested.setText("⚠ Please enter principal");
            errorRate.setText("⚠ Please enter rate of interest");
            errorTime.setText("⚠ Please enter time period");
            returns.setText("");
            invested.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            rate.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            time.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
        }
        else if(invested.getText().isBlank() && time.getText().isBlank() && !rate.getText().isBlank()){
            errorInvested.setText("⚠ Please enter principal");
            errorRate.setText("");
            errorTime.setText("⚠ Please enter time period");
            returns.setText("");
            invested.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            rate.setStyle(null);
            time.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
        }
        else if(invested.getText().isBlank() && !time.getText().isBlank() && rate.getText().isBlank()){
            errorInvested.setText("⚠ Please enter principal");
            errorRate.setText("⚠ Please enter rate of interest");
            errorTime.setText("");
            returns.setText("");
            invested.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            rate.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            time.setStyle(null);
        }
        else if(!invested.getText().isBlank() && time.getText().isBlank() && rate.getText().isBlank()){
            errorInvested.setText("");
            errorRate.setText("⚠ Please enter rate of interest");
            errorTime.setText("⚠ Please enter time period");
            returns.setText("");
            invested.setStyle(null);
            rate.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            time.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
        }
        else if(invested.getText().isBlank() && !time.getText().isBlank() && !rate.getText().isBlank()){
            errorInvested.setText("⚠ Please enter principal");
            errorRate.setText("");
            errorTime.setText("");
            returns.setText("");
            invested.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            rate.setStyle(null);
            time.setStyle(null);
        }
        else if(!invested.getText().isBlank() && !time.getText().isBlank() && rate.getText().isBlank()){
            errorInvested.setText("");
            errorRate.setText("⚠ Please enter rate of interest");
            errorTime.setText("");
            returns.setText("");
            invested.setStyle(null);
            rate.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            time.setStyle(null);
        }
        else if(!invested.getText().isBlank() && time.getText().isBlank() && !rate.getText().isBlank()){
            errorInvested.setText("");
            errorRate.setText("");
            errorTime.setText("⚠ Please enter time period");
            returns.setText("");
            invested.setStyle(null);
            rate.setStyle(null);
            time.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
        }

    }
    public void calculateLumpSum(ActionEvent event){
        try {
            float final_val;

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
                final_val = (float) (p * (Math.pow((1 + r / 100), t)));

                returns.setText(String.valueOf(final_val));
                invested.setStyle(null);
                rate.setStyle(null);
                time.setStyle(null);
                errorInvested.setText("");
                errorRate.setText("");
                errorTime.setText("");
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

    public void addValuesIntoPortfolio(ActionEvent event) {
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

            String insertDetails = "INSERT INTO demo.portfolio (`Username`, `Type`, `Invested`, `Rate`, `Time`, `Returns`) VALUES ('"+username+"', '"+choice+"', '"+invested.getText()+"', '"+rate.getText()+"', '"+time.getText()+"', '"+returns.getText()+"'\n)";
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
                Parent root = FXMLLoader.load(getClass().getResource("table.fxml")); //pass scene name here
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
        catch (NumberFormatException e){
            errorText.setText("⚠ Calculate value before inserting data");

        }

    }


    public void switchToInvestment(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("investment.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}