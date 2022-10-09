package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class EmiCalculator extends NullPointerException {
    @FXML
    private MenuButton menu;

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

    public void onCalculateButtonClick(ActionEvent event) {
        if (!invested.getText().isBlank() && !time.getText().isBlank() && !rate.getText().isBlank()){
            calculateEMI(event);
        }
        else if (invested.getText().isBlank() && time.getText().isBlank() && rate.getText().isBlank()) {
            errorInvested.setText("⚠ Please enter principal");
            errorRate.setText("⚠ Please enter interest rate");
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
            errorRate.setText("⚠ Please enter interest rate");
            errorTime.setText("");
            returns.setText("");
            invested.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            rate.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            time.setStyle(null);
        }
        else if(!invested.getText().isBlank() && time.getText().isBlank() && rate.getText().isBlank()){
            errorInvested.setText("");
            errorRate.setText("⚠ Please enter interest rate");
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
            errorRate.setText("⚠ Please enter interest rate");
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

    public void calculateEMI(ActionEvent event){
        try {
            float emi;


            double p = Double.parseDouble(invested.getText());

            double t = Double.parseDouble(time.getText());

            double r = Double.parseDouble(rate.getText());
            if (p < 0.1 || r < 0.1 || t < 0.1) {
                errorInvested.setText("⚠ Invalid input");
                errorRate.setText("⚠ Invalid input");
                errorTime.setText("⚠ Invalid input");
                returns.setText("");
                invested.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
                rate.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
                time.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            } else {
                t = t * 12;
                r = r / (12 * 100);
                emi = (float) ((p * r * Math.pow(1 + r, t)) / (Math.pow(1 + r, t) - 1));

                returns.setText(String.valueOf(emi));
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

    public void CarLoan(ActionEvent event){
        menu.setText("Car Loan");
        rate.setPromptText("6% to 9.45%");
        rate.setText(null);
    }
    public void EducationLoan(ActionEvent event){
        menu.setText("Education Loan");
        rate.setPromptText("10.40% to 10.65%");
        rate.setText(null);
    }
    public void PersonalLoan(ActionEvent event){
        menu.setText("Personal Loan");
        rate.setPromptText("10.5% to 21.00%");
        rate.setText(null);
    }
    public void HomeLoan(ActionEvent event){
        menu.setText("Home Loan");
        rate.setPromptText("7.8% to 9.6%");
        rate.setText(null);
    }

}
