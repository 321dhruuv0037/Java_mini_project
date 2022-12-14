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

public class CompareController {

    @FXML
    private Label errorInvested1;

    @FXML
    private Label errorInvested2;

    @FXML
    private Label errorRate1;

    @FXML
    private Label errorRate2;

    @FXML
    private Label errorReturns1;

    @FXML
    private Label errorReturns2;

    @FXML
    private Label errorTime1;

    @FXML
    private Label errorTime2;

    @FXML
    private Label errorType1;

    @FXML
    private Label errorType2;

    @FXML
    private MenuItem fd;

    @FXML
    private MenuItem fd1;

    @FXML
    private MenuItem gold;

    @FXML
    private MenuItem gold1;

    @FXML
    private TextField invested1;
    @FXML
    private MenuItem lumpsum;

    @FXML
    private MenuItem lumpsum1;

    @FXML
    private TextField rate1;

    @FXML
    private TextField returns1;

    @FXML
    private TextField returns2;

    @FXML
    private TextField time1;

    @FXML
    private MenuButton type1;

    @FXML
    private MenuButton type2;

    Parent root;
    Stage stage;
    Scene scene;
    public String choice1 =null;
    public String choice2 =null;

    public void onCompareButtonClick(ActionEvent event){
        choice1 = choice1;
        choice2 = choice2;
        if (choice1.equals(choice2)){
            errorType1.setText("⚠ Invalid input!");
            errorType2.setText("⚠ Invalid input!");
        }
        else {
            if (!invested1.getText().isBlank() && !rate1.getText().isBlank() && !time1.getText().isBlank() && choice1 != null && choice2 != null) {
                System.out.println("Validating");
                calculateField1(event);
                calculateField2(event);
                compareReturns(event);
            } else {
                if (choice1 == null) {
                    //System.out.println("Choice error");
                    errorType1.setText("⚠ Invalid input!");
                    //regarding.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                } else {
                    errorType1.setText(null);
                    //regarding.setStyle(null);
                }
                if (invested1.getText().isBlank()) {
                    errorInvested1.setText("⚠ Invalid input!");
                    invested1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
                } else {
                    errorInvested1.setText("");
                    invested1.setStyle(null);
                }
                if (rate1.getText().isBlank()) {
                    errorRate1.setText("⚠ Invalid input!");
                    rate1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
                } else {
                    errorRate1.setText("");
                    rate1.setStyle(null);
                }
                if (time1.getText().isBlank()) {
                    errorTime1.setText("⚠ Invalid input!");
                    time1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
                } else {
                    errorTime1.setText("");
                    time1.setStyle(null);
                }
                if (choice2 == null) {
                    //System.out.println("Choice error");
                    errorType2.setText("⚠ Invalid input!");
                    //regarding.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                } else {
                    errorType2.setText(null);
                    //regarding.setStyle(null);
                }

            }

        }

    }

    private void compareReturns(ActionEvent event) {
        float a = Float.parseFloat(returns1.getText());
        float b = Float.parseFloat(returns2.getText());
        if (a>b){
            returns1.setStyle("-fx-border-color: green; -fx-border-width: 5px; -fx-border-radius: 90px");
        } else if (b>a) {
            returns2.setStyle("-fx-border-color: green; -fx-border-width: 5px; -fx-border-radius: 90px");
        }
    }

    private void calculateField1(ActionEvent event) {
        try{
            double p = Double.parseDouble(invested1.getText());

            double t = Double.parseDouble(time1.getText());

            double r = Double.parseDouble(rate1.getText());

            if (p < 0.1 || r < 0.1 || t < 0.1) {
                if (p<0.1){
                    errorInvested1.setText("⚠ Invalid input");
                    invested1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");

                }
                else {
                    errorInvested1.setText("");
                    invested1.setStyle(null);
                }
                if (r<0.1) {
                    errorRate1.setText("⚠ Invalid input");
                    rate1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
                }
                else {
                    errorRate1.setText("");
                    rate1.setStyle(null);
                }
                if (t<0.1) {
                    errorTime1.setText("⚠ Invalid input");
                    time1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
                }
                else {
                    errorTime1.setText("");
                    time1.setStyle(null);
                }
                returns1.setText("");
            }
            else {
                if (choice1=="FD"){
                    double interest;
                    float final_val;
                    interest = (p * t * r) / 100;
                    final_val = (float) (interest + p);

                    returns1.setText(String.valueOf(final_val));
                    invested1.setStyle(null);
                    rate1.setStyle(null);
                    time1.setStyle(null);
                    returns1.setStyle(null);
                    errorType1.setText("");
                    errorReturns1.setText("");
                    errorInvested1.setText("");
                    errorRate1.setText("");
                    errorTime1.setText("");
                } else if (choice1=="LUMP SUM") {
                    float final_val;
                    final_val = (float) (p * (Math.pow((1 + r / 100), t)));

                    returns1.setText(String.valueOf(final_val));
                    invested1.setStyle(null);
                    rate1.setStyle(null);
                    time1.setStyle(null);
                    returns1.setStyle(null);
                    errorType1.setText("");
                    errorReturns1.setText("");
                    errorInvested1.setText("");
                    errorRate1.setText("");
                    errorTime1.setText("");

                } else if (choice1=="GOLD") {
                    float final_val;
                    final_val = (float) (p * (Math.pow((1 + r / 100), t)));

                    returns1.setText(String.valueOf(final_val));
                    invested1.setStyle(null);
                    rate1.setStyle(null);
                    time1.setStyle(null);
                    returns1.setStyle(null);
                    errorType1.setText("");
                    errorReturns1.setText("");
                    errorInvested1.setText("");
                    errorRate1.setText("");
                    errorTime1.setText("");
                }
            }

        }catch (NumberFormatException e){
            errorInvested1.setText("⚠ Invalid input");
            errorRate1.setText("⚠ Invalid input");
            errorTime1.setText("⚠ Invalid input");
            returns1.setText("");
            invested1.setText("");
            rate1.setText("");
            time1.setText("");
            invested1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            rate1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            time1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
        }

    }
    private void calculateField2(ActionEvent event) {
        try{
            double p = Double.parseDouble(invested1.getText());

            double t = Double.parseDouble(time1.getText());

            double r = Double.parseDouble(rate1.getText());

            if (p < 0.1 || r < 0.1 || t < 0.1) {
                if (p<0.1){
                    errorInvested1.setText("⚠ Invalid input");
                    invested1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");

                }
                else {
                    errorInvested1.setText("");
                    invested1.setStyle(null);
                }
                if (r<0.1) {
                    errorRate1.setText("⚠ Invalid input");
                    rate1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
                }
                else {
                    errorRate1.setText("");
                    rate1.setStyle(null);
                }
                if (t<0.1) {
                    errorTime1.setText("⚠ Invalid input");
                    time1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
                }
                else {
                    errorTime1.setText("");
                    time1.setStyle(null);
                }
                returns2.setText("");
            }
            else {
                if (choice2=="FD"){
                    double interest;
                    float final_val;
                    interest = (p * t * r) / 100;
                    final_val = (float) (interest + p);

                    returns2.setText(String.valueOf(final_val));
                    invested1.setStyle(null);
                    rate1.setStyle(null);
                    time1.setStyle(null);
                    returns2.setStyle(null);
                    errorType1.setText("");
                    errorReturns2.setText("");
                    errorInvested1.setText("");
                    errorRate1.setText("");
                    errorTime1.setText("");

                } else if (choice2=="LUMP SUM") {
                    float final_val;
                    final_val = (float) (p * (Math.pow((1 + r / 100), t)));

                    returns2.setText(String.valueOf(final_val));
                    invested1.setStyle(null);
                    rate1.setStyle(null);
                    time1.setStyle(null);
                    returns2.setStyle(null);
                    errorType1.setText("");
                    errorReturns2.setText("");
                    errorInvested1.setText("");
                    errorRate1.setText("");
                    errorTime1.setText("");

                } else if (choice2=="GOLD") {
                    float final_val;
                    final_val = (float) (p * (Math.pow((1 + r / 100), t)));

                    returns2.setText(String.valueOf(final_val));
                    invested1.setStyle(null);
                    rate1.setStyle(null);
                    time1.setStyle(null);
                    returns2.setStyle(null);
                    errorType1.setText("");
                    errorReturns2.setText("");
                    errorInvested1.setText("");
                    errorRate1.setText("");
                    errorTime1.setText("");
                }
            }

        }catch (NumberFormatException e){
            errorInvested1.setText("⚠ Invalid input");
            errorRate1.setText("⚠ Invalid input");
            errorTime1.setText("⚠ Invalid input");
            returns2.setText("");
            invested1.setText("");
            rate1.setText("");
            time1.setText("");
            invested1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            rate1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            time1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
        }

    }

    public void fd(ActionEvent event){
        type1.setText("FD");
        choice1="FD";
    }
    public void lumpsum(ActionEvent event){
        type1.setText("LUMP SUM");
        choice1="LUMP SUM";
    }
    public void gold(ActionEvent event){
        type1.setText("GOLD");
        choice1="GOLD";
    }
    public void fd1(ActionEvent event){
        type2.setText("FD");
        choice2="FD";
    }
    public void lumpsum1(ActionEvent event){
        type2.setText("LUMP SUM");
        choice2="LUMP SUM";
    }
    public void gold1(ActionEvent event){
        type2.setText("GOLD");
        choice2="GOLD";
    }
    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}