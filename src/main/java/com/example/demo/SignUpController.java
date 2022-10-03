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
import java.sql.*;
import static com.example.demo.HelloController.setUsername;

public class SignUpController extends NullPointerException {

    @FXML
    private TextField Fname;

    @FXML
    private TextField Lname;

    @FXML
    private TextField age;

    @FXML
    private TextField email;

    @FXML
    private Label errorAge;

    @FXML
    private Label errorEmail;

    @FXML
    private Label errorFavAnimal;

    @FXML
    private Label errorMobNo;

    @FXML
    private Label errorName;

    @FXML
    private Label errorPassword;

    @FXML
    private Label errorUsername;

    @FXML
    private TextField favanimal;

    @FXML
    private TextField mobno;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    public void onSignUpButtonClick(ActionEvent event) throws IOException {
        System.out.println("Button clicked!");
        if (!Fname.getText().isBlank() && !Lname.getText().isBlank() && !username.getText().isBlank() && !password.getText().isBlank()
                && !email.getText().isBlank() && !mobno.getText().isBlank() && !favanimal.getText().isBlank() && !age.getText().isBlank()){
            validateSignUp(event);
        }
        else {
            if(Fname.getText().isBlank() || Lname.getText().isBlank()){
                errorName.setText("⚠ Please enter full name!");
                Fname.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                Lname.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorName.setText(null);
                Fname.setStyle(null);
                Lname.setStyle(null);
            }
            if(username.getText().isBlank()){
                errorUsername.setText("⚠ Please enter username!");
                username.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorUsername.setText(null);
                username.setStyle(null);
            }
            if(password.getText().isBlank()){
                errorPassword.setText("⚠ Please enter password!");
                password.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorPassword.setText(null);
                password.setStyle(null);
            }
            if(email.getText().isBlank()){
                errorEmail.setText("⚠ Please enter email-id!");
                email.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorEmail.setText(null);
                email.setStyle(null);
            }
            if(mobno.getText().isBlank()){
                errorMobNo.setText("⚠ Please enter your mobile no!");
                mobno.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorMobNo.setText(null);
                mobno.setStyle(null);
            }
            if(favanimal.getText().isBlank()){
                errorFavAnimal.setText("⚠ Please enter your fav animal!");
                favanimal.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorFavAnimal.setText(null);
                favanimal.setStyle(null);
            }
            if(age.getText().isBlank()){
                errorAge.setText("⚠ Please enter your age!");
                age.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorAge.setText(null);
                age.setStyle(null);
            }

        }
    }

    private void validateSignUp(ActionEvent event) {
        System.out.println("Inside function signup");
        DBConnect connectnow = new DBConnect();
        Connection connectdb = connectnow.getConnection();
        String verifySignup = "select count(1) from demo.userdetails where Username = '" + username.getText() +"' ";
        Statement statement = null;
        /*int i = mobno.getText().length();
        if (i>10 || i<10){
            errorMobNo.setText("⚠ Please enter correct mobile no!");
            mobno.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
        }*/

            try {
                statement = connectdb.createStatement();
                ResultSet queryResult = statement.executeQuery(verifySignup);
                while (queryResult.next()) {
                    System.out.println("Inside while loop");
                    if (queryResult.getInt(1) == 1) {
                        System.out.println("inside if");
                        username.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                        errorUsername.setText("⚠ This username already exists");
                    } else {
                        System.out.println("inside else");
                        String insertDetails = "INSERT INTO demo.accountdetails (`Fname`, `Lname`, `Username`, `Password`, `Email`, `Mobile_no`, `Favourite_Animal`, `Age`) VALUES ('" + Fname.getText() + "','" + Lname.getText() + "','" + username.getText() + "','" + password.getText() + "','" + email.getText() + "','" + mobno.getText() + "','" + favanimal.getText() + "','" + age.getText() + "' \n)";
                        String insertUserDetails = "INSERT INTO demo.userdetails (`Username`, `Password`) VALUES ('" + username.getText() + "','" + password.getText() + "'\n)";
                        try {
                            statement = connectdb.createStatement();
                            int a = statement.executeUpdate(insertDetails);
                            int b = statement.executeUpdate(insertUserDetails);
                            if (a == 1 && b == 1) {
                                System.out.println("Inserted data!");
                            } else {
                                System.out.println("Failed to insert data");
                            }
                            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml")); //pass scene name here
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                            setUsername(String.valueOf(username));
                        } catch (Exception e) {
                            e.printStackTrace();
                            e.getCause();
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml")); //pass scene name here
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

