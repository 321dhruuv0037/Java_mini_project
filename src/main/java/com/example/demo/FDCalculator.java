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
import java.util.Scanner;

public class FDCalculator extends  NullPointerException{

    @FXML
    private TextField invested;

    @FXML
    private TextField rate;

    @FXML
    private TextField returns;

    @FXML
    private TextField time;

    @FXML
    private Label errorInvested;

    @FXML
    private Label errorRate;

    @FXML
    private Label errorTime;

    public void onCalculateButtonClick(ActionEvent event) throws IOException{
        if (!invested.getText().isBlank() && !time.getText().isBlank() && !rate.getText().isBlank()){
            calculateFD(event);
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
    public void calculateFD(ActionEvent event){
        double interest;
        float final_val;
        double p = Double.parseDouble(invested.getText());

        double t = Double.parseDouble(time.getText());

        double r = Double.parseDouble(rate.getText());
        interest= (p*t*r)/100;
        final_val= (float) (interest+p);

        returns.setText(String.valueOf(final_val));
        invested.setStyle(null);
        rate.setStyle(null);
        time.setStyle(null);
        errorInvested.setText("");
        errorRate.setText("");
        errorTime.setText("");

    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToInvestment(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("investment.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}