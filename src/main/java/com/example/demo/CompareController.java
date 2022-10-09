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
    private TextField invested2;

    @FXML
    private MenuItem lumpsum;

    @FXML
    private MenuItem lumpsum1;

    @FXML
    private TextField rate1;

    @FXML
    private TextField rate2;

    @FXML
    private TextField returns1;

    @FXML
    private TextField returns2;

    @FXML
    private MenuItem sip;

    @FXML
    private MenuItem sip1;

    @FXML
    private TextField time1;

    @FXML
    private TextField time2;

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
        if (!invested1.getText().isBlank() && !rate1.getText().isBlank() && !time1.getText().isBlank() && choice1!=null){
            System.out.println("Validating choice 1");
            calculateField1(event);
        }
        else {
            if (choice1==null){
                //System.out.println("Choice error");
                errorType1.setText("⚠ Invalid input!");
                //regarding.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorType1.setText(null);
                //regarding.setStyle(null);
            }
            if (invested1.getText().isBlank()){
                errorInvested1.setText("⚠ Invalid input!");
                invested1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorInvested1.setText("");
                invested1.setStyle(null);
            }
            if (rate1.getText().isBlank()){
                errorRate1.setText("⚠ Invalid input!");
                rate1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorRate1.setText("");
                rate1.setStyle(null);
            }
            if (time1.getText().isBlank()){
                errorTime1.setText("⚠ Invalid input!");
                time1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorTime1.setText("");
                time1.setStyle(null);
            }
            if (returns1.getText().isBlank()){
                errorReturns1.setText("⚠ Invalid input!");
                returns1.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorReturns1.setText("");
                returns1.setStyle(null);
            }

        }
        if (!invested2.getText().isBlank() && !rate2.getText().isBlank() && !time2.getText().isBlank() && choice2!=null){
            System.out.println("Validating choice 2");
            calculateField2(event);
        }
        else {
            if (choice2==null){
                //System.out.println("Choice error");
                errorType2.setText("⚠ Invalid input!");
                //regarding.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorType2.setText(null);
                //regarding.setStyle(null);
            }
            if (invested2.getText().isBlank()){
                errorInvested2.setText("⚠ Invalid input!");
                invested2.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorInvested2.setText("");
                invested2.setStyle(null);
            }
            if (rate2.getText().isBlank()){
                errorRate2.setText("⚠ Invalid input!");
                rate2.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorRate2.setText("");
                rate2.setStyle(null);
            }
            if (time2.getText().isBlank()){
                errorTime2.setText("⚠ Invalid input!");
                time2.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorTime2.setText("");
                time2.setStyle(null);
            }
            if (returns2.getText().isBlank()){
                errorReturns2.setText("⚠ Invalid input!");
                returns2.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 90px");
            }
            else {
                errorReturns2.setText("");
                returns2.setStyle(null);
            }
        }

    }

    private void calculateField2(ActionEvent event) {
    }

    private void calculateField1(ActionEvent event) {

    }

    public void fd(ActionEvent event){
        type1.setText("FD");
        choice1="FD";
    }
    public void sip(ActionEvent event){
        type1.setText("SIP");
        choice1="SIP";
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
    public void sip1(ActionEvent event){
        type2.setText("SIP");
        choice2="SIP";
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
/*catch (NumberFormatException e){
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

 */
