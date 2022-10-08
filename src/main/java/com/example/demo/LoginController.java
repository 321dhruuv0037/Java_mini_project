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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.demo.HelloController.setUsername;



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
        String encryptedPassword = encryption(password.getText());
        DBConnect connectnow = new DBConnect();
        Connection connectdb = connectnow.getConnection();
        String verifylogin = "select count(1) from demo.userdetails where Username = '" + username.getText() + "' and Password  = '" + encryptedPassword + "'";

        Statement statement = null;
        try {
            statement = connectdb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            while(queryResult.next()){
                if (queryResult.getInt(1)==1){
                    try {
                        String u = (username.getText());
                        root = FXMLLoader.load(getClass().getResource("menu.fxml")); //pass scene name here
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        System.out.println(u);
                        setUsername(String.valueOf(u));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                else {
                    errorUsername.setText("");
                    username.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                    username.setText("");
                    errorPassword.setText("⚠ Invalid User!");
                    password.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                    password.setText("");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String encryption(String text) {
        String password = text;
        String encryptedpassword1 = null;
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes()); //using md5 update function

            //Converting hash values to bytes
            byte[] bytes = m.digest();

            //Converting from bytes to hexadecimal form
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            encryptedpassword1 = s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        System.out.println(password);
        System.out.println("MD5: " + encryptedpassword1);
        return encryptedpassword1;
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
