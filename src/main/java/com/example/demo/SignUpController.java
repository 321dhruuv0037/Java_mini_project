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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.regex.*;
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
        String s = mobno.getText();
        String m = age.getText();
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email.getText());
        System.out.println(matcher.matches());
        if(matcher.matches()) {
            errorEmail.setText(null);
            email.setStyle(null);
            int i = mobno.getText().length();
            try {
                double j = Double.parseDouble(s);
                System.out.println(j);
                try {
                    int k = Integer.parseInt(m);
                    if (k>12 && k<100) {

                        if (i > 10 || i < 10) {
                            errorMobNo.setText("⚠ Please enter correct mobile no!");
                            mobno.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                        }
                        else {

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
                                        //String password1= String.valueOf(password.getText().hashCode());
                                        //System.out.println(password1);
                                        String encryptedPassword = encryption(password.getText()); //to encrypt password using md5 hashing
                                        System.out.println("inside else");
                                        String insertDetails = "INSERT INTO demo.accountdetails (`Fname`, `Lname`, `Username`, `Password`, `Email`, `Mobile_no`, `Favourite_Animal`, `Age`) VALUES ('" + Fname.getText() + "','" + Lname.getText() + "','" + username.getText() + "','" + encryptedPassword + "','" + email.getText() + "','" + mobno.getText() + "','" + favanimal.getText() + "','" + age.getText() + "' \n)";
                                        String insertUserDetails = "INSERT INTO demo.userdetails (`Username`, `Password`) VALUES ('" + username.getText() + "','" + encryptedPassword + "'\n)";
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
                                            setUsername(String.valueOf(username.getText()));
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
                    }
                    else {
                        errorAge.setText("⚠ Age limit 13-99");
                        age.setText(null);
                        age.setPromptText("13-99");
                        age.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                    }
                }
                catch (NumberFormatException e){
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
                errorMobNo.setText("⚠ Please enter correct mobile no!");
                mobno.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");
                mobno.setText(null);
            }
        }
        else
        {
            email.setText(null);
            errorEmail.setText("⚠ Please enter correct email-id!");
            email.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 15px");

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

    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml")); //pass scene name here
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}