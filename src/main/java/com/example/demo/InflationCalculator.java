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
import javafx.scene.text.Text;

import java.io.IOException;

public class InflationCalculator extends NullPointerException {

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
    private Text label1;
    public void onCalculateButtonClick(ActionEvent event) throws IOException{
        if (!invested.getText().isBlank() && !time.getText().isBlank() && !rate.getText().isBlank()){
            calculateInflation(event);
        }
        else if (invested.getText().isBlank() && time.getText().isBlank() && rate.getText().isBlank()) {
            errorInvested.setText("⚠ Please enter principal");
            errorRate.setText("⚠ Please enter inflation rate");
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
            errorRate.setText("⚠ Please enter inflation rate");
            errorTime.setText("");
            returns.setText("");
            invested.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            rate.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            time.setStyle(null);
        }
        else if(!invested.getText().isBlank() && time.getText().isBlank() && rate.getText().isBlank()){
            errorInvested.setText("");
            errorRate.setText("⚠ Please enter inflation rate");
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
            errorRate.setText("⚠ Please enter inflation rate");
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

    private void calculateInflation(ActionEvent event) throws IOException {
        try{
            float val;

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
            }else {
                r = r / 100;

                for (int i = 1; i <= t; i++) {
                    p += p * r;
                }

                val = (float) p;

                label1.setText("Value after " + t + " years : ");
                returns.setText(String.valueOf(val));
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

    public void switchToInvestment(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("investment.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}