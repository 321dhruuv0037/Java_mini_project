package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController extends NullPointerException{

    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    @FXML
    private Label errorPassword;

    @FXML
    private Label errorUsername;


    private Parent root;
    private Stage stage;
    private Scene scene;

    public void onLoginButtonClick(ActionEvent event) throws IOException {
        if (!username.getText().isBlank() && !password.getText().isBlank()){
            validateLogin(event);
        }
        else if (username.getText().length()==0 && password.getText().length()==0) {
            errorUsername.setText("⚠ Please enter username!");
            username.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            errorPassword.setText("⚠ Please enter password!");
            password.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
        }
        else if (username.getText().length()==0) {
            errorUsername.setText("⚠ Please enter username!");
            username.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            errorPassword.setText("");
            password.setStyle("");
        }
        else if (password.getText().length()==0) {
            errorUsername.setText("");
            username.setStyle("");
            errorPassword.setText("⚠ Please enter password!");
            password.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
        }
    }

    private void validateLogin(ActionEvent event) {
        DBConnect connectnow = new DBConnect();
        Connection connectdb = connectnow.getConnection();
        String verifylogin = "select count(1) from demo.userdetails where Username = '" + username.getText() + "' and Password  = '" + password.getText() + "'";

        Statement statement = null;
        try {
            statement = connectdb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            while(queryResult.next()){
                if (queryResult.getInt(1)==1){
                    try {
                        root = FXMLLoader.load(getClass().getResource("menu.fxml")); //pass scene name here
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                else {
                    errorUsername.setText("");
                    username.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                    errorPassword.setText("⚠ Invalid User!");
                    password.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");

                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void switchToForgotPassword(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("forgot_password.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.show();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sign-up.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
