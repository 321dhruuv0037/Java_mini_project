package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.demo.HelloController.getUsername;
import static com.example.demo.HelloController.setUsername;

public class deleteDataController {
    @FXML
    private Label errorFavAnimal;

    @FXML
    private Label errorPassword;

    @FXML
    private Label errorType;

    @FXML
    private Label errorid;

    @FXML
    private TextField favAnimal;

    @FXML
    private MenuItem fd;

    @FXML
    private MenuItem gold;

    @FXML
    private TextField id;

    @FXML
    private MenuItem lumpsum;

    @FXML
    private PasswordField password;

    @FXML
    private MenuItem sip;

    @FXML
    private MenuButton type;

    public String username = getUsername();
    public String choice = null;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void onSumbitButtonClick(ActionEvent event){
        choice=choice;
        if (choice!=null && !id.getText().isBlank() && !password.getText().isBlank() && !favAnimal.getText().isBlank()){
            System.out.println("validating");
            deleteValues(event);
        }
        else{
            if (choice==null){
                //System.out.println("Choice error");
                errorType.setText("⚠ Invalid input!");
                //regarding.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorType.setText(null);
                //regarding.setStyle(null);
            }
            if(id.getText().isBlank()){
                errorid.setText("⚠ Please enter id!");
                id.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorid.setText(null);
                id.setStyle(null);
            }
            if(password.getText().isBlank()){
                errorPassword.setText("⚠ Please enter password!");
                password.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorPassword.setText(null);
                password.setStyle(null);
            }
            if(favAnimal.getText().isBlank()){
                errorFavAnimal.setText("⚠ Please enter your fav animal!");
                favAnimal.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
            }
            else {
                errorFavAnimal.setText(null);
                favAnimal.setStyle(null);
            }
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
    private void deleteValues(ActionEvent event) {
        System.out.println("Inside function ");
        DBConnect connectnow = new DBConnect();
        Connection connectdb = connectnow.getConnection();
        Statement statement = null;
        System.out.println(username);
        String encryptedPassword = encryption(password.getText()); //to encrypt password using md5 hashing
        String verifyUser = "select count(1) from demo.accountdetails where Username = '" + username + "' and Password  = '" + encryptedPassword + "' and Favourite_Animal = '"+favAnimal.getText()+"' ";
        try {
            statement = connectdb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyUser);
            while (queryResult.next()) {
                System.out.println("Inside while loop");
                if (queryResult.getInt(1) == 1) {
                    System.out.println("inside if");
                    String findEntry = "DELETE FROM `demo`.`portfolio` WHERE (`Id` = '"+id.getText()+"') and (`Type` = '"+choice+"') and (`Username` = '"+username+"');";
                    try {
                        statement = connectdb.createStatement();
                        int a = statement.executeUpdate(findEntry);
                        if (a == 1 ) {
                            System.out.println("Deleted data!");
                        } else {
                            System.out.println("Failed to delete data");
                        }
                        Parent root = FXMLLoader.load(getClass().getResource("table.fxml")); //pass scene name here
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                        errorType.setText("⚠ Invalid input!");
                        errorid.setText("⚠ Invalid input!");
                        id.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                    }
                }
                else {
                    password.setText("");
                    errorPassword.setText("⚠ Invalid input!");
                    password.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                    favAnimal.setText("");
                    errorFavAnimal.setText("⚠ Invalid input!");
                    favAnimal.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void fd(ActionEvent event){
        type.setText("FD");
        choice="FD";
    }
    public void sip(ActionEvent event){
        type.setText("SIP");
        choice="SIP";
    }
    public void lumpsum(ActionEvent event){
        type.setText("LUMP SUM");
        choice="LUMP SUM";
    }
    public void gold(ActionEvent event){
        type.setText("GOLD");
        choice="GOLD";
    }

    public void switchToPortfolio(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("table.fxml")); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
